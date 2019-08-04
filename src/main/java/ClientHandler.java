import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class HandleClient implements Runnable {
    private Socket socket=null;
    private Request request;
    private Responder responder;
    private FileReader fileReader;
    private RequestParser requestParser;
    public HandleClient(Socket socket){
       this.socket = socket;
       request = new Request();
       fileReader = new FileReader();
       requestParser = new RequestParser();
    }

    @Override
    public void run() {
        String recievedContent = request.recieveRequest(socket);
        String filePath = requestParser.extractFilePath(recievedContent);

        File file = new File("Files/" + filePath);
        if (!file.exists()) {
            System.out.println(filePath + " not found");
            file = new File("Files/error.html");
        }

        responder = new Responder(file,socket);
        responder.handle();

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}