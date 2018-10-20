package com.magina.resource.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

public class Main {

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        String html = main.getHtml("http://www.diaosisou.info/list/774/1");
        List<String> magnets = main.getMagnets(html);
        magnets.forEach(System.out::println);
    }
    
    private List<String> getMagnets(String html) {
        Document doc = Jsoup.parse(html);
        
        Elements liTags = doc.getElementsByTag("li");
        return liTags.stream().map(li -> {
            Elements aTags = li.getElementsByTag("a");
            return aTags.stream().map(a -> {
                if (a.attr("href").startsWith("/torrent")) {
                    return a.text().replaceAll("\\[磁力链接\\] \\[迅雷链接\\] \\[高清在线播放\\]", "").trim();
                } else if (a.attr("href").startsWith("magnet")) {
                    return a.attr("href").trim();
                }
                return null;
            }).filter(a -> a != null).collect(Collectors.joining(": "));
        }).filter(str -> StringUtils.hasText(str)).collect(Collectors.toList());
    }
    
    private String getHtml(String address) throws IOException {
        URLConnection connection = setAgent(address);
        try (InputStream in = connection.getInputStream()) {
            return StreamUtils.copyToString(in, Charset.forName("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private URLConnection setAgent(String address) throws IOException {
        URL url;
        try {
            url = new URL(address);
        } catch (MalformedURLException e) {
            url = new URL("http:" + address);
        }
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:62.0) Gecko/20100101 Firefox/62.0");
        return connection;
    }
    
}
