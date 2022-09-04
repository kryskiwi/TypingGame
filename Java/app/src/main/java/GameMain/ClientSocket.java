package GameMain;

import java.io.*;
import java.net.Socket;

public class ClientSocket {
    Socket c_socket;

    public ClientSocket() throws IOException {
        this.c_socket = new Socket("127.0.0.1", 30000);
    }

    public void write( String message ) throws IOException {
        OutputStream out = c_socket.getOutputStream();
        PrintWriter pw = new PrintWriter(out, true);
        // pw.println("Java app");
        pw.println(message);
        // DataOutputStream data_out = this.c_socket.getOutputStream());
        // data_out.writeUTF(message);
        // data_out.flush();
        // data_out.close();
    }

    public String read() throws IOException{
        DataInputStream data_in = new DataInputStream(this.c_socket.getInputStream());
        String read_data = (String) data_in.readUTF();
        return read_data;
    }

    public void teardown() throws IOException {
        this.c_socket.close();
    }

}