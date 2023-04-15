package KHOneTop.Thx2GettinaJob.exam.service;

import KHOneTop.Thx2GettinaJob.exam.entity.Category;
import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import KHOneTop.Thx2GettinaJob.exam.entity.ExamTimeStamp;
import KHOneTop.Thx2GettinaJob.exam.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewExamAddService {

    private final ExamRepository examRepository;

    @Transactional
    public void addToeicExam() {

        DateTimeFormatter timeMinformatter = DateTimeFormatter.ofPattern("yyyy.MM.dd (E) a h시m분", Locale.KOREA);
        DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("yyyy.MM.dd (E) a h시", Locale.KOREA);
        try {

            Document doc = Jsoup.connect("https://exam.toeic.co.kr/receipt/examSchList.php").get();
            Elements rows = doc.select("table tbody tr");

            for (Element row : rows) {
                String examName = row.select("td").get(0).text();
                String examDate = row.select("td").get(1).text();
                String resultDate = row.select("td").get(2).text();
                Element pTag = row.select("td").get(3).select("p").first();
                String[] splitText = pTag.html().split("<br>");
                String regDate = Jsoup.parse(splitText[0]).text();
                String addRegDate = Jsoup.parse(splitText[1]).text(); // 태그 가공해서 문자열 가져오기

                examName = changeDateFormat(examName, "★ ", "");
                resultDate = changeDateFormat(resultDate, "낮", "오후");
                regDate = changeDateFormat(regDate, "낮", "오후");
                addRegDate = changeDateFormat(addRegDate, "낮", "오후"); // format 에 필요없는 내용들 제거

                String[] regDateInput = regDate.split("~");
                String[] addRegDateInput = addRegDate.split("~");



                LocalDateTime examDateTime = LocalDateTime.parse(examDate, timeMinformatter);
                LocalDateTime resultDateTime = LocalDateTime.parse(resultDate, timeformatter);
                LocalDateTime regStartDateTime = LocalDateTime.parse(regDateInput[0].substring(regDateInput[0].indexOf(":") + 2), timeformatter);
                LocalDateTime regEndDateTime = LocalDateTime.parse(regDateInput[1], timeformatter);
                LocalDateTime addRegStartDateTime = LocalDateTime.parse(addRegDateInput[0].substring(addRegDateInput[0].indexOf(":") + 2), timeformatter);
                LocalDateTime addRegEndDateTime = LocalDateTime.parse(addRegDateInput[1], timeformatter);

                ExamTimeStamp examTimeStamp = ExamTimeStamp.builder()
                        .examDate(examDateTime)
                        .regStartDate(regStartDateTime)
                        .regEndDate(regEndDateTime)
                        .addRegStartDate(addRegStartDateTime)
                        .addRegEndDate(addRegEndDateTime)
                        .resultDate(resultDateTime)
                        .build();

                Exam toeicExam = Exam.builder()
                        .name(examName)
                        .category(Category.TOEIC)
                        .examTimeStamp(examTimeStamp)
                        .build();

                examRepository.save(toeicExam);
            }

        } catch (IOException e) {
            log.debug(e.getMessage());
        }
    }

    @Transactional
    public void addAfpkExam() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 (E) a h:mm", Locale.KOREA);
        DateTimeFormatter formatter2 = new DateTimeFormatterBuilder()
                .appendPattern("M월 d일 (E) a h:mm")
                .parseDefaulting(ChronoField.YEAR_OF_ERA, Year.now().getValue())
                .toFormatter(Locale.KOREA);

        try {
            Document doc = Jsoup.connect("https://www.fpsbkorea.org/?mnu_usn=27").get();
            Elements titles = doc.select("dl dt");
            Elements dates = doc.select("dl dd");
            Pattern pattern = Pattern.compile("\\d+회");

            int count = 0;
            for (Element title : titles) {
                Element date = dates.get(count);
                String examName = title.select("dt").get(0).text();
                String examDate = date.select("strong").get(0).text();
                String regDate = date.select("li").get(0).text();
                String resultDate = date.select("li").get(4).text();


                Matcher matcher = pattern.matcher(examName);
                if (matcher.find()) {
                    examName = "제" + matcher.group();
                }

                int regStartIndex = examDate.indexOf(":") + 1;
                int regEndIndex = examDate.indexOf("~");
                examDate = examDate.substring(regStartIndex, regEndIndex).trim();
                String[] parts = regDate.split("부터|까지");
                int startResultIndex = resultDate.indexOf("발표");
                resultDate = resultDate.substring(startResultIndex + 2).trim();

                LocalDateTime examDateTime = LocalDateTime.parse(examDate, formatter);
                LocalDateTime regStartDateTime = LocalDateTime.parse(parts[0].substring(parts[0].indexOf("2")).trim(), formatter2);
                LocalDateTime regEndDateTime = LocalDateTime.parse(parts[1].trim(), formatter2);
                LocalDateTime resultDateTime = LocalDateTime.parse(resultDate, formatter2);

                ExamTimeStamp examTimeStamp = ExamTimeStamp.builder()
                        .examDate(examDateTime)
                        .regStartDate(regStartDateTime)
                        .regEndDate(regEndDateTime)
                        .resultDate(resultDateTime)
                        .build();

                Exam afpkExam = Exam.builder()
                        .name(examName)
                        .category(Category.AFPK)
                        .examTimeStamp(examTimeStamp)
                        .build();

                examRepository.save(afpkExam);

            }
        } catch (IOException e) {
            log.debug(e.getMessage());
        }
    }

    @Transactional
    public void addKoreanExam() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일(E) H:mm", Locale.ENGLISH);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy년 M월 d일(E)", Locale.ENGLISH);

        try {
            Document doc = Jsoup.connect("https://www.historyexam.go.kr/pageLink.do?link=examSchedule&netfunnel_key=8D5649CEF7116C29195BCF7E3334993C25C5E6FEB802CBA5F3021DFCC1A813528892C27C94799D0FE17CB8B45161D4464BFF3E2B9BA24F07978D55454B547C4F538248990C1EF8EF0EBCD0B243EBE61B4B16061CE3A7838A42C123D251D01EACA6473F2826BD6E38AB7A4693F370CC97302C382C312C302C30").get();
            Elements rows = doc.select("table tbody tr");

            Element row = rows.get(1);
            String examName = row.select("td").get(0).text();
            String regDate = row.select("td").get(1).text();
            String addregDate = row.select("td").get(2).text();
            String examDate = row.select("td").get(3).text();
            String resultDate = row.select("td").get(4).text();
            examDate += " 00:00";
            resultDate += " 00:00"; //default 값 처리


            String[] splitInput = regDate.split(" ~ ");
            LocalDateTime regStartDateTime = LocalDateTime.parse(splitInput[0], formatter);
            LocalDateTime regEndDateTime = LocalDateTime.parse(splitInput[1], formatter);
//
            String[] splitInput2 = addregDate.split(" ~ ");
            LocalDateTime addRegStartDateTime = LocalDateTime.parse(splitInput2[0], formatter);
            LocalDateTime addRegEndDateTime = LocalDateTime.parse(splitInput2[1], formatter);
//
            LocalDateTime examDateTime = LocalDateTime.parse(examDate, formatter);
            LocalDateTime resultDateTime = LocalDateTime.parse(resultDate, formatter);

            ExamTimeStamp examTimeStamp = ExamTimeStamp.builder()
                    .examDate(examDateTime)
                    .regStartDate(regStartDateTime)
                    .regEndDate(regEndDateTime)
                    .addRegStartDate(addRegStartDateTime)
                    .addRegEndDate(addRegEndDateTime)
                    .resultDate(resultDateTime)
                    .build();

            Exam koreanExam = Exam.builder()
                    .name(examName)
                    .category(Category.KOREAN)
                    .examTimeStamp(examTimeStamp)
                    .build();

            examRepository.save(koreanExam);
        } catch (IOException e) {
            log.debug(e.getMessage());
        }
    }

    private String changeDateFormat(String text, String target, String value) {
        return text.contains(target) ? text.replace(target, value) : text;
    }
}
