package GameMain;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Test {
    public static void main(String[] args) {
        try {
            System.out.println("STARTING");
            Socket sock = new Socket("127.0.0.1", 30000);
            OutputStream out = sock.getOutputStream();
            PrintWriter pw = new PrintWriter(out, true);
            pw.println("Java apps for everyone woot");
            sock.close();
            System.out.println("ENDING");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}