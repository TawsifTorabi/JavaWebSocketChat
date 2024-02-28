import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        try {
            System.out.println("Client Created!");
            Socket soc = new Socket("127.0.0.1", 3500);
            Scanner sc = new Scanner(System.in);
            ObjectInputStream input = new ObjectInputStream(soc.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(soc.getOutputStream());
            String str = sc.nextLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
