package KHOneTop.Thx2GettinaJob.exam.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ExamCrawlerTest {

    @Test
    void toeicCrawler() throws Exception {
        //given
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy.MM.dd (E) a h시m분", Locale.KOREA);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy.MM.dd (E) a h시", Locale.KOREA);


        //when
        Document doc = Jsoup.connect("https://exam.toeic.co.kr/receipt/examSchList.php").get();
        Elements rows = doc.select("table tbody tr");

        Element row = rows.get(0);
//        String examName = row.select("td").get(0).text();
        String examName = "★ 제486회";
        examName = examName.contains("★") ? examName.replace("★ ", "") : examName;
        String examDate = row.select("td").get(1).text();
        String resultDate = row.select("td").get(2).text();

        Element pTag = row.select("td").get(3).select("p").first();
        String[] splitText = pTag.html().split("<br>");
        String text1 = Jsoup.parse(splitText[0]).text();
        String text2 = Jsoup.parse(splitText[1]).text();
        resultDate = resultDate.replace("낮", "오후");
        text2 = text2.replace("낮", "오후");

        String[] splitInput = text1.split("~");
        LocalDateTime dateTime1 = LocalDateTime.parse(splitInput[0].substring(splitInput[0].indexOf(":") + 2), formatter2);
        LocalDateTime dateTime2 = LocalDateTime.parse(splitInput[1], formatter2);

        String[] splitInput2 = text2.split("~");
        LocalDateTime dateTime3 = LocalDateTime.parse(splitInput2[0].substring(splitInput[0].indexOf(":") + 2), formatter2);
        LocalDateTime dateTime4 = LocalDateTime.parse(splitInput2[1], formatter2);

        LocalDateTime examDateTime = LocalDateTime.parse(examDate, formatter1);
        LocalDateTime resultDateTime = LocalDateTime.parse(resultDate, formatter2);


        //then
        assertThat(examName).isEqualTo("제486회");
        System.out.println(examDateTime);
        System.out.println(resultDateTime);
        System.out.println(dateTime1);
        System.out.println(dateTime2);
        System.out.println(dateTime3);
        System.out.println(dateTime4);
    }

}