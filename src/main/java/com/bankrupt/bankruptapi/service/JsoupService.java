package com.bankrupt.bankruptapi.service;

import com.bankrupt.bankruptapi.model.Board;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JsoupService {

    public ArrayList<Board> getBoardList(String html) {
        ArrayList<Board> result = new ArrayList<>();

        try {
            Document doc = Jsoup.parse(html);
            Element tableHor = doc.getElementsByClass("tableHor").first();
            Element tbody = tableHor.getElementsByTag("tbody").first();

            for (Element tr : tbody.getElementsByTag("tr")) {
                Elements td = tr.getElementsByTag("td");

                Board board = Board.builder()
                        .id(Integer.parseInt(td.get(0).text()))
                        .court(td.get(1).text())
                        .seller(td.get(2).text())
                        .title(td.get(3).child(0).text())
                        .referer(td.get(3).child(0).attribute("href").getValue())
                        .build();

                result.add(board);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return result;
    }

    public Integer getLastPageIndex(String html) {

        try {
            Document doc = Jsoup.parse(html);
            Element pagelist = doc.getElementsByClass("pagelist").first();
            Element prev = pagelist.getElementsByClass("prev").first();
            String href = prev.attribute("href").getValue();

            String numPage = href.split("=")[1];
            return Integer.parseInt(numPage);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
