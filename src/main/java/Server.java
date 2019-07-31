
import javax.annotation.processing.FilerException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {
    private Socket socket = null;
    private ServerSocket server = null;
    private BufferedInputStream bufferedInputStream = null;

    public Server(int port){
        try {
            server = new ServerSocket(port);
            System.out.println("Server Started");
            System.out.println("waiting for client");
            socket = server.accept();
            System.out.println("Client accepted");

            File file = parseRequest(socket);
            handleResponse(socket,file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private File parseRequest(Socket socket) {
        String fileName = "";
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
            byte[] byteArray = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(byteArray);
            String content = new String(byteArray);
            String[] split = content.split(" ");
            fileName = split[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File("Files/"+fileName);

    }



    private void handleResponse(Socket socket,File file) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(socket.getOutputStream());
            out.print("HTTP/1.1 200 \r\n"); // Version & status code
            out.print("Content-Type:html\r\n"); // The type of data
            out.print("Connection: close\r\n"); // Will close stream
            out.print("\r\n"); // End of headers
            if(file.exists()){
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                while(bufferedReader.readLine()!=null)
                out.print(bufferedReader.readLine());
            }
            else
                out.print("<html><body><h>Error 404 File not Found</h></body></html>");

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        Server server =new Server(5000);
    }
}