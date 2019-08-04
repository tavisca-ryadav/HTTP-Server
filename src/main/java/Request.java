import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.function.Supplier;
import java.util.logging.Level;

public class Request {
    public String recieveRequest(Socket socket) {
        String content="";
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
            byte[] byteArray = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(byteArray);
            content = new String(byteArray);
        } catch (IOException e) {
            LogHandler.logger.log(Level.INFO, (Supplier<String>) e);
        }
        return content;
    }
}
