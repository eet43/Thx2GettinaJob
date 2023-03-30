package KHOneTop.Thx2GettinaJob.exam.crawler;

import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import KHOneTop.Thx2GettinaJob.exam.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ExamCrawler {
    private final ExamRepository examRepository;

    @Scheduled(cron = "0 0 12 * * ?")
    @Transactional
    public void crawlToeicSchedule() {
        try {
            Document doc = Jsoup.connect("https://exam.toeic.co.kr/receipt/examSchList.php").get();
            Elements rows = doc.select("table tbody tr");
            for (Element row : rows) {
                String examName = row.select("td").get(0).text();
                String examDate = row.select("td").get(1).text();
//                Exam exam = new Exam(examName, examDate);
//                examRepository.save(exam);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}