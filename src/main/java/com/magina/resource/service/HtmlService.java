package com.magina.resource.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.google.common.collect.Lists;
import com.magina.resource.domain.Image;
import com.magina.resource.repository.ImageRepository;

@Service
public class HtmlService {

    @Autowired
    private ImageRepository imageRepository;

    public void download() throws IOException {
        List<Image> images = imageRepository.findByUsed(false);
        Map<String, List<Image>> map = images.stream().collect(Collectors.groupingBy(Image::getName));
        
        for (Entry<String, List<Image>> entry : map.entrySet()) {
            String folder = "D:" + File.separator + "图片" + File.separator + entry.getKey();
            int index = 0;
            for (Image image : images) {
                String html = getHtml(image.getLink());
                Document doc = Jsoup.parse(html);
                Elements es = doc.getElementsByTag("img");
                List<String> imgSrcs = Lists.newArrayList();
                for(Element e : es) {   
                    imgSrcs.add(e.attr("src"));
                }
                //下载图片  
                index = download(folder, index + 1, imgSrcs);
                if (index != 0) { //下载成功
                    image.setUsed(true);
                    imageRepository.save(image);
                }
            }
        }
    }

    /***
     * 获取HTML内容
     */
    private String getHtml(String address) throws IOException {
        URLConnection connection = setAgent(address);
        try (InputStream in = connection.getInputStream()) {
            return StreamUtils.copyToString(in, Charset.forName("utf-8"));
        } catch (Exception e) {
            return null;
        }
    }

    /***
     * 下载图片
     */
    private int download(String folder, int start, List<String> imgSrcs){
        File dir = new File(folder);
        if(!dir.exists()){
            dir.mkdir();
        }
        System.out.println("开始下载");
        int count = 0;
        for (int i=0; i<imgSrcs.size(); i++) {
            File file = new File(dir.getAbsolutePath() + "/" + (start + i) + ".jpg");
            try {
                URLConnection connection = setAgent(imgSrcs.get(i));
                StreamUtils.copy(connection.getInputStream(), new FileOutputStream(file));
                count = start + i;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
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