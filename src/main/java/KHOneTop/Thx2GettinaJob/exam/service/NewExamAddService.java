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
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewExamAddService {

    private final ExamRepository examRepository;
    private static final DateTimeFormatter timeMinformatter = DateTimeFormatter.ofPattern("yyyy.MM.dd (E) a h시m분", Locale.KOREA);
    private static final DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("yyyy.MM.dd (E) a h시", Locale.KOREA);

    @Transactional
    public void addToeicExam() {
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

                examName = changeDateFormat(examName, "* ", "");
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

                ExamTimeStamp examTimeStamp = new ExamTimeStamp(examDateTime, regStartDateTime, regEndDateTime,
                        addRegStartDateTime, addRegEndDateTime, resultDateTime);
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

    private String changeDateFormat(String text, String target, String value) {
        return text.contains(target) ? text.replace(target, value) : text;
    }
}
