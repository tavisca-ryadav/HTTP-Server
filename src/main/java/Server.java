import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

public class Server {
    public  ServerSocket server;
    public ClientHandler handleClient;
    public boolean running=false;
    public boolean serverSuccesfullyStarted=false;
    public void startServer(int port){
        try {
            LogHandler.logger.setLevel(Level.INFO);
            server = new ServerSocket(port);
            serverSuccesfullyStarted=true;
            running = true;
            LogHandler.logger.log(Level.INFO,"Server Started\nwaiting for clients");
            stopServer();//Stop the server for running test cases

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
        LogHandler.logger.log(Level.INFO,"Server Stopped");
    }

    public static void main(String[] args){
        Server server = new Server();
        server.startServer(5000);
    }
}