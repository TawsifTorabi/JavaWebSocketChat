import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) throws IOException {
            ServerSocket ss = new ServerSocket(3500);
            System.out.println("Server Ready. Waiting for Client.");
            Socket soc = ss.accept();
            System.out.println("Connection Established");
    }
}
