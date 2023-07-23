package KHOneTop.Thx2GettinaJob.exam.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PublicExamCrawlerTest {

    @Test
    void toeicCrawler() throws Exception {
        //given
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy.MM.dd (E) a h시m분", Locale.KOREA);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy.MM.dd (E) a h시", Locale.KOREA);


        //when
        Document doc = Jsoup.connect("https://www.toeicswt.co.kr/receipt/examSchList.php").get();
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

    @Test
    void afpkCrawler() throws Exception {
        //given
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 (E) a h:mm", Locale.KOREA);
        DateTimeFormatter formatter2 = new DateTimeFormatterBuilder()
                .appendPattern("M월 d일 (E) a h:mm")
                .parseDefaulting(ChronoField.YEAR_OF_ERA, Year.now().getValue())
                .toFormatter(Locale.KOREA);


        //when
        Document doc = Jsoup.connect("https://www.fpsbkorea.org/?mnu_usn=27").get();
        Elements titles = doc.select("dl.eduList dt");
        Elements dates = doc.select("dl.eduList dd");

        Pattern pattern = Pattern.compile("\\d+회");

        int count = 0;
        String result = null;
        String examDate = null;
        LocalDateTime examDateTime = null;
        Element date = null;
        LocalDateTime startDateTime = null;
        LocalDateTime endDateTime = null;
        String[] parts = new String[0];
        String text1 = null;
        String text2 = null;
        LocalDateTime resultDate = null;
        String result2 = null;
        for (Element title : titles) {
            date = dates.get(count);
            String examName = title.select("dt").get(0).text();
            examDate = date.select("strong").get(0).text();
            text1 = date.select("li").get(0).text();
            text2 = date.select("li").get(4).text();


            Matcher matcher = pattern.matcher(examName);
            if (matcher.find()) {
                result = "제" + matcher.group();
            }
            int startIndex = examDate.indexOf(":") + 1;
            int endIndex = examDate.indexOf("~");
            examDate = examDate.substring(startIndex, endIndex).trim();
            examDateTime = LocalDateTime.parse(examDate, formatter);

            parts = text1.split("부터|까지");
            startDateTime = LocalDateTime.parse(parts[0].substring(5).trim(), formatter2);
            endDateTime = LocalDateTime.parse(parts[1].trim(), formatter2);
            startIndex = text2.indexOf("발표");
            result2 = text2.substring(startIndex + 2).trim();
            resultDate = LocalDateTime.parse(result2, formatter2);

            count++;
        }


        //then
        System.out.println(result);
        System.out.println(examDateTime);
        System.out.println(startDateTime);
        System.out.println(endDateTime);
        System.out.println(resultDate);
    }

    @Test
    void koreanCrawler() throws Exception {
        //given
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일(E) H:mm", Locale.ENGLISH);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy년 M월 d일(E)", Locale.ENGLISH);



        //when
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
        LocalDateTime dateTime1 = LocalDateTime.parse(splitInput[0], formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(splitInput[1], formatter);
//
        String[] splitInput2 = addregDate.split(" ~ ");
        LocalDateTime dateTime3 = LocalDateTime.parse(splitInput2[0], formatter);
        LocalDateTime dateTime4 = LocalDateTime.parse(splitInput2[1], formatter);
//
        LocalDateTime examDateTime = LocalDateTime.parse(examDate, formatter);
        LocalDateTime resultDateTime = LocalDateTime.parse(resultDate, formatter);


        System.out.println(examName);
        System.out.println(dateTime1);
        System.out.println(dateTime2);
        System.out.println(dateTime3);
        System.out.println(dateTime4);
        System.out.println(examDateTime);
        System.out.println(resultDateTime);
    }

    @Test
    void toeicSpeackingCrawler() throws Exception {

        //given
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy.MM.dd (E) a h시", Locale.KOREA);


        //when
        Document doc = Jsoup.connect("https://www.toeicswt.co.kr/receipt/examSchList.php").get();
        Elements rows = doc.select("table tbody tr");

        Element row = rows.get(0);
        String examDate = row.select("td").get(0).text();
        String resultDate = row.select("td").get(1).text();
        String regDate = row.select("td").get(2).text();

        examDate += " 오전 0시";
        resultDate = resultDate.replace("낮", "오후");

        String[] splitInput = regDate.split("~");
        LocalDateTime examDateTime = LocalDateTime.parse(examDate, formatter1);
        LocalDateTime resultDateTime = LocalDateTime.parse(resultDate, formatter1);
        LocalDateTime dateTime1 = LocalDateTime.parse(splitInput[0], formatter1);
        LocalDateTime dateTime2 = LocalDateTime.parse(splitInput[1], formatter1);




        //then
        System.out.println(examDateTime);
        System.out.println(resultDateTime);
        System.out.println(dateTime1);
        System.out.println(dateTime2);
    }

    @Test
    void hskCrawler() throws Exception {

        //given
        DateTimeFormatter formatter1 = new DateTimeFormatterBuilder()
                .appendPattern("M월 d일 (E) a h시")
                .parseDefaulting(ChronoField.YEAR_OF_ERA, Year.now().getValue())
                .toFormatter(Locale.KOREA);


        //when
        Document doc = Jsoup.connect("https://www.hsk-korea.co.kr/about/schedule_hsk_ibt.aspx").get();
        Elements rows = doc.select("div.board-wrap.type_accordion ul li");

        Element row = rows.get(1);
        String examDate = row.select("span").get(2).text();
        Elements dl = row.select("dl");
        String regDate = dl.get(1).select("dd").text();
        String resultDate = dl.get(2).select("dd").text();

        examDate += " 오전 0시";
        resultDate += " 오전 0시";

        String[] splitInput = regDate.split(" ~ ");
        splitInput[0] += " 오전 0시";
        splitInput[1] += " 오전 0시";
        LocalDateTime examDateTime = LocalDateTime.parse(examDate, formatter1);
        LocalDateTime resultDateTime = LocalDateTime.parse(resultDate, formatter1);
        LocalDateTime dateTime1 = LocalDateTime.parse(splitInput[0], formatter1);
        LocalDateTime dateTime2 = LocalDateTime.parse(splitInput[1], formatter1);




        //then
        System.out.println(examDateTime);
        System.out.println(resultDateTime);
        System.out.println(dateTime1);
        System.out.println(dateTime2);
    }

}