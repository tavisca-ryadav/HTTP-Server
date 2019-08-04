import java.io.*;
import java.net.Socket;
import java.util.logging.Level;

public class Responder {
    private File file;
    private Socket socket;
    private String fileType;

    public Responder(File file, Socket socket) {
        this.file = file;
        this.socket = socket;
    }

    public void handle() {
        LogHandler.logger.log(Level.INFO,file.getName()+" has been loaded");
        RequestParser requestParser = new RequestParser();
        fileType = requestParser.extractTypeOfFile(file.getName());

        try {
            java.io.File file1 = new java.io.File(file.getPath());
            FileInputStream fileInputStream = new FileInputStream(file1);
            int fileLength = (int) file1.length();
            byte[] fileInBytes = new byte[fileLength];
            fileInputStream.read(fileInBytes);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
            bufferedOutputStream.write(("HTTP/1.1 " + 200 + " " + " OK " + "\r\n").getBytes());
            bufferedOutputStream.write(("Content-Type:" + fileType + "\r\n").getBytes());
            bufferedOutputStream.write(("Content-Length: " + fileLength + "\r\n").getBytes());
            bufferedOutputStream.write("\r\n".getBytes());
            bufferedOutputStream.write(fileInBytes, 0, fileLength);
            bufferedOutputStream.flush();

        } catch (Exception e) {
            LogHandler.logger.log(Level.INFO,"Exception in file : " + file.getPath());
            e.printStackTrace();
        }
    }
}
