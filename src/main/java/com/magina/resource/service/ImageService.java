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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.magina.resource.domain.Image;
import com.magina.resource.repository.ImageRepository;

@Service
public class ImageService {
    
    // 获取img标签正则  
    private static final String IMG_NODE_REG = "<(img|IMG)(.*?)(/>|></img>|>)";
    // 获取src路径的正则  
    private static final String IMG_SRC_REG = "(src|SRC)=(\"|\')(.*?)(\"|\')";
    
    @Autowired
    private ImageRepository imageRepository;
    
    public Page<Image> listImages(Pageable pageable, String query) {
        return StringUtils.hasText(query) ? imageRepository.findByNameLike(query, pageable) : imageRepository.findAll(pageable);
    }
    
    public void save(Image image) {
        imageRepository.save(image);
    }
    
    public void download() throws IOException {
        List<Image> images = imageRepository.findByUsed(false);
        Map<String, List<Image>> map = images.stream().collect(Collectors.groupingBy(Image::getName));
        
        for (Entry<String, List<Image>> entry : map.entrySet()) {
            String folder = "D:" + File.separator + "图片" + File.separator + entry.getKey();
            int index = 0;
            for (Image image : images) {
                String html = getHtml(image.getLink());
                //获取图片标签  
                List<String> imgNodes = listImageNodes(html);
                //获取图片src地址  
                List<String> imgSrcs = listImageSrcs(imgNodes);
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
     * 获取ImageUrl地址
     */
    private List<String> listImageNodes(String html) {
        Matcher matcher = Pattern.compile(IMG_NODE_REG).matcher(html);
        List<String> imgUrls = Lists.newArrayList();
        while (matcher.find()) {
            imgUrls.add(matcher.group(2));
        }
        return imgUrls;
    }

    /***
     * 获取ImageSrc地址
     */
    private List<String> listImageSrcs(List<String> imageUrls) {
        List<String> imgSrcs = Lists.newArrayList();
        imageUrls.forEach(image -> {
            Matcher matcher = Pattern.compile(IMG_SRC_REG).matcher(image);
            while (matcher.find()) {
                imgSrcs.add(matcher.group(3));
            }
        });
        return imgSrcs;
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