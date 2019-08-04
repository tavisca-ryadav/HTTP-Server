import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Server {
    public  ServerSocket server;
    public ClientHandler handleClient;
    public static Logger logger = LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME);
    public boolean running=false;
    public boolean serverSuccesfullyStarted=false;
    public void startServer(int port){
        try {
            logger.setLevel(Level.INFO);
            server = new ServerSocket(port);
            serverSuccesfullyStarted=true;
            running = true;
            logger.log(Level.INFO,"Server Started\nwaiting for clients");
            stopServer();

           while(running){
                Socket socket = server.accept();
                handleClient = new ClientHandler(socket);
                Thread thread = new Thread((handleClient));
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopServer(){
        running = false;
    }
}