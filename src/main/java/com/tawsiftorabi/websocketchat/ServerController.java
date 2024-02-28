package com.tawsiftorabi.websocketchat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;


public class ServerController implements Initializable {


    @FXML
    private Button ClientSendButton;
    @FXML private TextField InputChat;
    @FXML private TextArea OutputText;
    @FXML private Text serverStatus;

    int SendNow = 0;
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

        final ServerSocket serverSocket ;
        final Socket clientSocket ;
        final BufferedReader in;
        final PrintWriter out;

        try {
            serverSocket = new ServerSocket(5000);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));

            Thread sender= new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                    while(true){
                        msg = InputChat.getText();
                        if(SendNow == 1){
                            OutputText.setText(OutputText.getText() + "\n" + "Server : " + msg);
                            out.println(msg);
                            InputChat.setText("");
                            SendNow = 0;
                            out.flush();
                        }
                    }
                }
            });
            sender.start();

            Thread receive= new Thread(new Runnable() {
                String msg ;
                @Override
                public void run() {
                    try {
                        msg = in.readLine();

                        while(msg!=null){
                            System.out.println("Client : "+msg);
                            OutputText.setText(OutputText.getText() + "\n" + "Client : " + msg);
                            msg = in.readLine();
                        }

                        serverStatus.setText("Client Connected!");

                        out.close();
                        clientSocket.close();
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            receive.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}