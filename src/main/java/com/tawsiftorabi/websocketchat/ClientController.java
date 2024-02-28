package com.tawsiftorabi.websocketchat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;



public class ClientController implements Initializable {


    @FXML private Button ClientSendButton;
    @FXML private TextField InputChat;
    @FXML private TextArea OutputText;
    @FXML private Text serverStatus;

    public int SendNow = 0;
    @FXML
    void ClientSend() {
        SendNow = 1;
        System.out.println(SendNow);
    }

    @FXML
    void SendChatEnter(KeyEvent k){
        if (k.getCode().equals(KeyCode.ENTER)) {
            ClientSend();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        final Socket clientSocket;
        final BufferedReader in;
        final PrintWriter out;

        try {
            clientSocket = new Socket("127.0.0.1",5000);

            if(clientSocket.isConnected()){
                serverStatus.setText("Connected!");
            }else{
                serverStatus.setText("Disconnected!");
            }

            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


            Thread sender = new Thread(new Runnable() {
                String msg;

                @Override
                public void run() {
                    while(true){
                            msg = InputChat.getText();
                            if(SendNow == 1){
                                OutputText.setText(OutputText.getText() + "\n" + "Client : " + msg);
                                out.println(msg);
                                InputChat.setText("");
                                SendNow = 0;
                                out.flush();
                            }
                        }
                    }
            });
            sender.start();

            Thread receiver = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                    try {
                        msg = in.readLine();
                        while(msg!=null){
                            System.out.println("Server : "+msg);
                            OutputText.setText(OutputText.getText() + "\n" + "Server : " + msg);
                            msg = in.readLine();
                        }
                        System.out.println("Server out of service");
                        out.close();
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            receiver.start();

        }catch (IOException e){
            e.printStackTrace();
        }



    }
}


