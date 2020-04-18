package com.my_downloader;

import javafx.scene.control.TextField;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Download implements  Runnable{
    private String link;
    private String filePath;
    //private TextField textField;

    public Download(String url , String filePath) {
       this.link = url;
       this.filePath = filePath;
       //this.textField = textField;
    }

    @Override
    public void run() {
        try {
            System.out.println(link);
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            double fileSize = (double)httpURLConnection.getContentLengthLong();
            int lastIndex = link.lastIndexOf("/");
            String fileName = link.substring(lastIndex+1);
            filePath += "/" + fileName;
            System.out.println(httpURLConnection.getContentType());

            if(httpURLConnection.getContentType().equals("application/pdf")) {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                FileOutputStream fileOutputStream = new FileOutputStream(this.filePath);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream, 1024);
                byte[] buffer = new byte[1024];
                double downloaded = 0.00;
                int read = 0;
                double downloadPercentage = 0.00;
                while ((read = bufferedInputStream.read(buffer, 0, 1024)) >= 0) {
                    bufferedOutputStream.write(buffer, 0, read);
                    downloaded += read;
                    downloadPercentage = (downloaded * 100) / fileSize;
                    String percent = String.format("%.4f", downloadPercentage);
                    System.out.println("Downloaded" + " " + percent);
                }
            }
            else{
                ProcessBuilder pb = new ProcessBuilder("/usr/bin/python3.7","pyth.py",link,filePath);
                pb.inheritIO();
                Process proc=pb.start();

                Reader reader = new InputStreamReader(proc.getInputStream());
                BufferedReader bf = new BufferedReader(reader);
                String s;
                while ((s = bf.readLine()) != null) {
                    System.out.println(s);
                }
            }

        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
           // textField.clear();
        }
    }


}
