package com.example.api_b811204.student;

import com.example.api_b811204.model.PutStudentReq;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentCrawler {
    private String url;
    private String selector;

    private String[] degree = {"phd", "master","undergrad"};

    public StudentCrawler(String url, String selector) {
        this.url = url;
        this.selector = selector;
    }

    public List<PutStudentReq> getList() {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select(selector);
            List<PutStudentReq> params = new ArrayList<>();
            for (int i = 0; i < elements.size(); i++) {
                Elements element = elements.get(i).children();
                for (int j = 0; j < element.size(); j++) {
                    String crawled = element.get(j).text();
                    String[] result = crawled.split(", ");

                    List<String> list = new ArrayList<>(Arrays.asList(result));
                    list.add(degree[i]);
                    PutStudentReq param = new PutStudentReq(list.get(0), list.get(1), Integer.valueOf(list.get(2)), list.get(3));
                    params.add(param);
                }
            }
            return params;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
