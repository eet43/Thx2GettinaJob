package KHOneTop.Thx2GettinaJob.exam.service;

import KHOneTop.Thx2GettinaJob.common.response.Codeset;
import KHOneTop.Thx2GettinaJob.common.response.CustomException;
import KHOneTop.Thx2GettinaJob.exam.dto.AddAlwaysPublicExamRequest;
import KHOneTop.Thx2GettinaJob.exam.entity.PublicExam;
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
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewExamAddService {

    private final ExamRepository examRepository;

    @Transactional
    public void addAllTimesExam(AddAlwaysPublicExamRequest request) {
        isExistByName(request.name());

        ExamTimeStamp timeStamp = ExamTimeStamp.builder()
                .turn("상시접수")
                .build();

        PublicExam publicExam = PublicExam.builder()
                .name(request.name() + "(상시접수)")
                .issuer(request.issuer())
                .url(request.url())
                .isPublic(true)
                .build();

        publicExam.addExamTime(timeStamp);
        examRepository.save(publicExam);
    }

    @Transactional
    public void addToeicExam() {

        isExistByName("토익");

        DateTimeFormatter timeMinformatter = DateTimeFormatter.ofPattern("yyyy.MM.dd (E) a h시m분", Locale.KOREA);
        DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("yyyy.MM.dd (E) a h시", Locale.KOREA);
        try {

            PublicExam toeicPublicExam = PublicExam.builder()
                    .name("토익")
                    .issuer("한국 TOEIC 위원회")
                    .url("https://exam.toeic.co.kr/")
                    .isPublic(true)
                    .examTimeStamp(new ArrayList<>())
                    .build();

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
                        .turn(examName)
                        .examDate(examDateTime)
                        .regStartDate(regStartDateTime)
                        .regEndDate(regEndDateTime)
                        .addRegStartDate(addRegStartDateTime)
                        .addRegEndDate(addRegEndDateTime)
                        .resultDate(resultDateTime)
                        .build();

                toeicPublicExam.addExamTime(examTimeStamp);
            }

            examRepository.save(toeicPublicExam);

        } catch (IOException e) {
            log.debug(e.getMessage());
        }
    }

    @Transactional
    public void addAfpkExam() {

        isExistByName("AFPK");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 (E) a h:mm", Locale.KOREA);
        DateTimeFormatter formatter2 = new DateTimeFormatterBuilder()
                .appendPattern("M월 d일 (E) a h:mm")
                .parseDefaulting(ChronoField.YEAR_OF_ERA, Year.now().getValue())
                .toFormatter(Locale.KOREA);

        try {

            PublicExam afpkPublicExam = PublicExam.builder()
                    .name("AFPK")
                    .issuer("사단법인한국에프피에스비")
                    .url("https://www.fpsbkorea.org/?mnu_usn=29")
                    .isPublic(true)
                    .examTimeStamp(new ArrayList<>())
                    .build();

            Document doc = Jsoup.connect("https://www.fpsbkorea.org/?mnu_usn=27").get();
            Elements titles = doc.select("dl.eduList dt");
            Elements dates = doc.select("dl.eduList dd");
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
                LocalDateTime regStartDateTime = LocalDateTime.parse(parts[0].substring(5).trim(), formatter2);
                LocalDateTime regEndDateTime = LocalDateTime.parse(parts[1].trim(), formatter2);
                LocalDateTime resultDateTime = LocalDateTime.parse(resultDate, formatter2);

                ExamTimeStamp examTimeStamp = ExamTimeStamp.builder()
                        .turn(examName)
                        .examDate(examDateTime)
                        .regStartDate(regStartDateTime)
                        .regEndDate(regEndDateTime)
                        .resultDate(resultDateTime)
                        .build();

                afpkPublicExam.addExamTime(examTimeStamp);
                count++;
            }

            examRepository.save(afpkPublicExam);

        } catch (IOException e) {
            log.debug(e.getMessage());
        }
    }

    @Transactional
    public void addKoreanExam() {

        isExistByName("한국사능력검정시험");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일(E) H:mm", Locale.ENGLISH);

        try {

            PublicExam koreanPublicExam = PublicExam.builder()
                    .name("한국사능력검정시험")
                    .issuer("국사편찬위원회")
                    .url("https://www.historyexam.go.kr/")
                    .isPublic(true)
                    .examTimeStamp(new ArrayList<>())
                    .build();

            Document doc = Jsoup.connect("https://www.historyexam.go.kr/pageLink.do?link=examSchedule&netfunnel_key=8D5649CEF7116C29195BCF7E3334993C25C5E6FEB802CBA5F3021DFCC1A813528892C27C94799D0FE17CB8B45161D4464BFF3E2B9BA24F07978D55454B547C4F538248990C1EF8EF0EBCD0B243EBE61B4B16061CE3A7838A42C123D251D01EACA6473F2826BD6E38AB7A4693F370CC97302C382C312C302C30").get();
            Elements rows = doc.select("table tbody tr");

            for (int i = 1; i < 7; i++) {
                Element row = rows.get(i);
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
                        .turn(examName)
                        .examDate(examDateTime)
                        .regStartDate(regStartDateTime)
                        .regEndDate(regEndDateTime)
                        .addRegStartDate(addRegStartDateTime)
                        .addRegEndDate(addRegEndDateTime)
                        .resultDate(resultDateTime)
                        .build();

                koreanPublicExam.addExamTime(examTimeStamp);
            }

            examRepository.save(koreanPublicExam);

        } catch (IOException e) {
            log.debug(e.getMessage());
        }
    }

    @Transactional
    public void addToeicSpeackingExam() {

        isExistByName("토익 스피킹");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd (E) a h시", Locale.KOREA);

        try {
            PublicExam toeicSpeakingPublicExam = PublicExam.builder()
                    .name("토익 스피킹")
                    .issuer("ETS")
                    .url("https://www.toeicswt.co.kr/")
                    .isPublic(true)
                    .examTimeStamp(new ArrayList<>())
                    .build();

            Document doc = Jsoup.connect("https://www.toeicswt.co.kr/receipt/examSchList.php").get();
            Elements rows = doc.select("table tbody tr");

            int count = 1;
            for (Element row : rows) {
                String examName = "제" + count + "회";
                String examDate = row.select("td").get(0).text();
                String resultDate = row.select("td").get(1).text();
                String regDate = row.select("td").get(2).text();

                examDate += " 오전 0시";
                regDate = regDate.replace("밤", "오전");
                resultDate = resultDate.replace("낮", "오후");

                String[] splitInput = regDate.split("~");
                LocalDateTime examDateTime = LocalDateTime.parse(examDate, formatter);
                LocalDateTime resultDateTime = LocalDateTime.parse(resultDate, formatter);
                LocalDateTime regStartDateTime = LocalDateTime.parse(splitInput[0], formatter);
                LocalDateTime regEndDateTime = LocalDateTime.parse(splitInput[1], formatter);

                ExamTimeStamp examTimeStamp = ExamTimeStamp.builder()
                        .turn(examName)
                        .examDate(examDateTime)
                        .regStartDate(regStartDateTime)
                        .regEndDate(regEndDateTime)
                        .resultDate(resultDateTime)
                        .build();

                toeicSpeakingPublicExam.addExamTime(examTimeStamp);
                count++;
            }

            examRepository.save(toeicSpeakingPublicExam);

        } catch (IOException e) {
            log.debug(e.getMessage());
        }
    }

    @Transactional
    public void addHskExam() {
        isExistByName("HSK");

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("M월 d일 (E) a h시")
                .parseDefaulting(ChronoField.YEAR_OF_ERA, Year.now().getValue())
                .toFormatter(Locale.KOREA);

        try {
            PublicExam hskPublicExam = PublicExam.builder()
                    .name("HSK")
                    .issuer("HSK한국사무국")
                    .url("https://www.hsk-korea.co.kr/main/main.aspx")
                    .isPublic(true)
                    .examTimeStamp(new ArrayList<>())
                    .build();

            Document doc = Jsoup.connect("https://www.hsk-korea.co.kr/about/schedule_hsk_ibt.aspx").get();
            Elements rows = doc.select("div.board-wrap.type_accordion ul li");

            for (int i = 1; i < rows.size(); i++) {
                String examName = "제" + i + "회";
                Element row = rows.get(i);
                String examDate = row.select("span").get(2).text();
                Elements dl = row.select("dl");
                String regDate = dl.get(1).select("dd").text();
                String resultDate = dl.get(2).select("dd").text();

                examDate += " 오전 0시";
                resultDate += " 오전 0시";

                String[] splitInput = regDate.split(" ~ ");
                splitInput[0] += " 오전 0시";
                splitInput[1] += " 오전 0시";

                LocalDateTime examDateTime = LocalDateTime.parse(examDate, formatter);
                LocalDateTime resultDateTime = LocalDateTime.parse(resultDate, formatter);
                LocalDateTime regStartDateTime = LocalDateTime.parse(splitInput[0], formatter);
                LocalDateTime regEndDateTime = LocalDateTime.parse(splitInput[1], formatter);

                ExamTimeStamp examTimeStamp = ExamTimeStamp.builder()
                        .turn(examName)
                        .examDate(examDateTime)
                        .regStartDate(regStartDateTime)
                        .regEndDate(regEndDateTime)
                        .resultDate(resultDateTime)
                        .build();

                hskPublicExam.addExamTime(examTimeStamp);
            }
            examRepository.save(hskPublicExam);

        } catch (IOException e) {
            log.debug(e.getMessage());
        }
    }

    private String changeDateFormat(String text, String target, String value) {
        return text.contains(target) ? text.replace(target, value) : text;
    }

    private void isExistByName(String name) {
        if (examRepository.existsByName(name)) {
            throw new CustomException(Codeset.ALREADY_EXAM_NAME, "이미 존재하는 시험 데이터입니다.");
        }
    }
}
