package com.tt.serverclient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {
    public static void main(String[] args) throws IOException {
            ServerSocket ss = new ServerSocket(3500);
            System.out.println("Server Ready. Waiting for Client.");
            while (true) {
                    Socket soc = ss.accept();

                    ObjectInputStream input = new ObjectInputStream(soc.getInputStream());
                    ObjectOutputStream output = new ObjectOutputStream(soc.getOutputStream());
                    Scanner ob = new Scanner(System.in);
                    String msg = ob.nextLine();
                    output.writeObject(msg);
                    try {
                            Object cMsg = input.readObject();
                            System.out.println("Msg from Client: " + (String) cMsg);
                    } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                    }
            }
            //System.out.println("Connection Established");
    }
}
