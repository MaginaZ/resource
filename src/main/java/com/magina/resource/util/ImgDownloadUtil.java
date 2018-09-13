package com.magina.resource.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StreamUtils;

import com.google.common.collect.Lists;

class ImgDownloadUtil {

    // 获取img标签正则  
    private static final String IMG_NODE_REG = "<(img|IMG)(.*?)(/>|></img>|>)";
    // 获取src路径的正则  
    private static final String IMG_SRC_REG = "(src|SRC)=(\"|\')(.*?)(\"|\')";
    
    public static void main(String[] args) throws IOException {
        ImgDownloadUtil util = new ImgDownloadUtil();
        
        //获得html文本内容  
        String html = util.getHtml("http://www.baidu.com");
        //获取图片标签  
        List<String> imgNodes = util.listImageNodes(html);
        //获取图片src地址  
        List<String> imgSrcs = util.listImageSrcs(imgNodes);
        //下载图片  
        util.download("测试", 1, imgSrcs);
    }

    /***
     * 获取HTML内容
     */
    private String getHtml(String address) throws IOException {
        URL url = new URL(address);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0");
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
    private void download(String name, int start, List<String> imgSrcs){
        File dir = new File("E:/" + name);
        if(!dir.exists()){
            dir.mkdir();
        }
        System.out.println("开始下载");
        int count = 0;
        for (int i=0; i<imgSrcs.size(); i++) {
            File file = new File("E:/" + "/" + (start + i) + ".jpg");
            try {
                URLConnection connection = setAgent(imgSrcs.get(i));
                StreamUtils.copy(connection.getInputStream(), new FileOutputStream(file));
                count = start + i;
            } catch (Exception e) {
                e.printStackTrace();
            }
        
        }
        System.out.println("下载结束, 文件编号: " + count);
    }

    private URLConnection setAgent(String address) throws IOException {
        URL url = new URL(address);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0");
        return connection;
    }
    
}