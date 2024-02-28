package com.tt.serverclient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        try {
            System.out.println("Client Created!");
            Socket soc = new Socket("127.0.0.1", 3500);
            Scanner sc = new Scanner(System.in);
            ObjectInputStream input = new ObjectInputStream(soc.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(soc.getOutputStream());
            try{
                Object getServer = input.readObject();
                System.out.println("From Server " + (String)getServer);
                String msg = sc.nextLine();
                output.writeObject(msg);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
    } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
