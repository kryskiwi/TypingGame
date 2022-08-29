package GameMain;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Test {
    public static void main(String[] args) {
        try {
            Socket sock = new Socket("127.0.0.1", 20000);
            OutputStream out = sock.getOutputStream();
            PrintWriter pw = new PrintWriter(out, true);
            pw.println("Java app");
            sock.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}