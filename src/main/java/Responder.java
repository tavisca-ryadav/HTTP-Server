import java.io.*;
import java.net.Socket;

public class Respond {
    public void handle(Socket socket, String fileContent,String fileType) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(socket.getOutputStream());
            out.print("HTTP/1.1 200 \r\nContent-Type:"+fileType+"\r\nConnection: close\r\n\r\n");
            out.print(fileContent);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
