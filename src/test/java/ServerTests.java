import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ServerTests {
    Server server;
   public ServerTests(){
        server = new Server();
        server.startServer(5000);
    }

    @Test
    public void TestServer(){
        Assert.assertEquals(true,server.serverSuccesfullyStarted);
    }

    @Test
    public void TestExtractFilePathFromRecievedHeaderRequest(){
       RequestParser requestParser = new RequestParser();
       String requestHeader = "GET /web/index.html HTTP/1.1\n" +
               "Host: localhost:5000\n" +
               "Connection: keep-alive\n" +
               "Cache-Control: max-age=0\n";
       Assert.assertEquals("/web/index.html",requestParser.extractFilePath(requestHeader));
    }

    @Test
    public void TestFileExtension(){
       RequestParser requestParser = new RequestParser();
       Assert.assertEquals("html",requestParser.extractTypeOfFile("File/web/index.html"));
       Assert.assertEquals("css",requestParser.extractTypeOfFile("File/web/bootstrap.css"));
       Assert.assertEquals("png",requestParser.extractTypeOfFile("Testimonial-1.png"));
       Assert.assertEquals("jpg",requestParser.extractTypeOfFile("File/web/images/screenshot.jpg"));
    }

    @Test


   @After
    public void after(){
        try {
            server.server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
