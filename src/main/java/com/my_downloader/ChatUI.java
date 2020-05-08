package com.my_downloader;

import com.my_downloader.ComboList.ComboList;
import com.my_downloader.model.DownloadDataList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;

public class ChatUI {
    @FXML public ListView<String> messageList;
    @FXML public TextField message;
    @FXML public Button sendBtn;
    public List<String> stringList = new ArrayList();

    @FXML
    public void initialize() throws IOException,Exception {
        stringList.add("<<Type q or quit to exit!>>");
        messageList.setItems(FXCollections.observableList(stringList));
    }

    @FXML
    public void onEnter(ActionEvent actionEvent) throws Exception {
        chat();
    }

    @FXML
    public void sendMessage(ActionEvent actionEvent) throws Exception {
        //stringList = new ArrayList<>();
       // System.out.println("Hello");
        chat();
    }

    public void chat() throws Exception {
        String messageText = message.getText();
        if(messageText != null) {
            ProcessBuilder pb = new ProcessBuilder("python3","Help/chatBot/Chat.py");
            messageText = "YOU: "+messageText;
            stringList.add(messageText);
            pb.inheritIO();
            Process proc=pb.start();
           // System.out.println(proc.waitFor());
            OutputStream out = proc.getOutputStream();
            File myObj = new File("Help/chatBot/response.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                stringList.add(data);
                System.out.println(data);
            }
            message.setText("");
            myReader.close();

//            BufferedReader bf = new BufferedReader(reader);
//            String s;
//            while ((s = bf.readLine()) != null) {
//                System.out.println(s+"REP");
//                String reply = "Bot: "+s;
//                stringList.add(reply);
//            }
            ObservableList<String> observableList = FXCollections.observableArrayList(stringList);
            messageList.setItems(observableList);
        }
    }
}
