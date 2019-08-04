import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;

public class ClientHandler implements Runnable {
    private Socket socket=null;
    private Request request;
    private Responder responder;
    private RequestParser requestParser;
    public ClientHandler(Socket socket){
       this.socket = socket;
       request = new Request();
       requestParser = new RequestParser();
    }

    @Override
    public void run() {
        String recievedContent = request.recieveRequest(socket);
        LogHandler.logger.log(Level.INFO,recievedContent);
        String filePath = requestParser.extractFilePath(recievedContent);
        File file = new File("Files/" + filePath);
        if (!file.exists()) {
            LogHandler.logger.log(Level.INFO,filePath + " not found");
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