package com.my_downloader;

import com.my_downloader.dao.MainUIDao;
import java.io.*;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Download implements  Runnable{
    private String link;
    private String filePath;
    private int id;
    private String progress;
    private String error;
    private boolean isNotify;
    //private TextField textField;

    public Download(String url , String filePath, int id, boolean isNotify) {
       this.link = url;
       this.filePath = filePath;
       this.id = id;
       System.out.println(isNotify);
       this.isNotify = isNotify;
    }

    /**
     * Run Thread.
     */
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

            System.out.println(isNotify);
            if(isNotify) sendEmail();

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

                error = "None";
                progress = "Completed";
            }
            else{
                ProcessBuilder pb = new ProcessBuilder("/usr/bin/python3.7","DownloadYouTube.py",link,filePath);
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
            error = "Invalid URL";
            progress = "Failed";
            System.out.println(id);
//            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid URL", ButtonType.OK);
//            alert.showAndWait();
//            if (alert.getResult() == ButtonType.OK) {
//                alert.close();
//            }
//            System.exit(0);

        }
        finally {
           // textField.clear();
            try{
                boolean isUpdated = new MainUIDao().updateProgress(id, error, progress);
                if(!isUpdated) System.exit(0);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Send Email using SendGrid.
     * @throws Exception
     */
    public void sendEmail() throws Exception {
        final String username = "apikey";
        final String password = getApiSendGrid();
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.sendgrid.net");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("benuraab@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("benuraab@gmail.com")
            );
            message.setSubject("Testing Gmail TLS");
            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public String getApiSendGrid() throws IOException {
        BufferedReader bufferedReader;
        String line,password=null;
        bufferedReader = new BufferedReader(new FileReader("SendGridAuth.txt"));
        while ((line = bufferedReader.readLine()) != null) {
            if(line.contains("PASSWORD")) {
                password = line.substring(12);
                System.out.println(password);
            }
            else password = null;
        }

        return  password;
    }


}
