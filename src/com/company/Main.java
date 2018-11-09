package com.company;

import com.company.UserDetail.userInfo;
import com.company.noticeManag.Notice;
import com.company.noticeManag.NoticeList;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static String dizhi = "http://www.lib.wust.edu.cn:8780/reader/redr_verify.php";

    public static void main(String[] args) throws IOException {
	// write your code here
        String token = "";
        String usrName = "";
        String passWord = "";
        String yanzhengma = "";
        Map<String,String> cookies = new HashMap<>();
        Connection connection = Jsoup.connect(dizhi);
        connection.timeout(6*1000);
        Connection.Response res = connection.execute();
        cookies = res.cookies();
        Document doc =  res.parse();
        Element tokeninfo = doc.getElementById("csrf_token");
        token = tokeninfo.attr("value");
        System.out.println(token);
        System.out.println(cookies);
        getPatcha patcha = new getPatcha(cookies);
        patcha.updataCha();
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入学号:");
        usrName = scanner.nextLine();
        System.out.print("请输入密码:");
        passWord = scanner.nextLine();
        System.out.print("请输入验证码:");
        yanzhengma = scanner.nextLine();
        Connection connection1 = Jsoup.connect(dizhi);
        connection1.timeout(6*1000)
        .method(Connection.Method.POST)
 //       .headers(cookies)
        .data("number",usrName)
        .data("passwd",passWord)
        .data("captcha",yanzhengma)
        .data("select","bar_no")
        .data("returnUrl","")
        .data("csrf_token",token);
        Connection.Response response = connection1.cookies(cookies).execute();
        Document document = response.parse();
        getNoticeList();
        getUserDetail(cookies);
        search_book("java");
    }

    private static void getNoticeList() throws IOException {
        String noticeUrl = "http://www.lib.wust.edu.cn/";
        NoticeList noticeList = new NoticeList();
        Connection connection = Jsoup.connect(noticeUrl);
        connection.timeout(5*1000);
        Document document = connection.get();
        Elements elements = document.select("#gonggao").select("tr > td");
        for (Element e: elements) {
            Notice notice = new Notice(noticeUrl+e.select("a").attr("href"),e.select("a").text(),e.select("font").text());
            noticeList.addNotice(notice);
        }
        List<Notice> n = noticeList.getNoticeList();
        for (Notice i:n) {
            System.out.println(i.getIntro()+"   "+i.getDate());
        }
    }

    private static void getUserDetail(Map<String,String> cookies) throws IOException {
        Connection connection = Jsoup.connect("http://www.lib.wust.edu.cn:8780/reader/redr_info_rule.php");
        connection.cookies(cookies);
        Connection.Response res = connection.execute();
        Document doc = res.parse();
        Elements table_trs = doc.select("#mylib_info").select("td");
        String name =  table_trs.get(1).text();
        System.out.println(name);
        for (Element e:table_trs) {
            System.out.println(e.text());
        }
    }

    private static void search_book(String bookName) throws IOException {
        String url = "http://www.lib.wust.edu.cn:8780/opac/openlink.php";
        Connection connection = Jsoup.connect(url);
        connection.data("strSearchType","title")
        .data("strText",bookName)
        .data("loginsubmit.x","0")
        .data("loginsubmit.y","0")
        .method(Connection.Method.GET);
        Connection.Response response = connection.execute();
        Document doc = response.parse();
        Elements elements = doc.select(".book_list_info");
        for (Element e:elements) {
            System.out.println(e);
        }
    }

}
