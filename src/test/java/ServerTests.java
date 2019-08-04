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
    public void ServerStarted(){
       server.stopServer();
        Assert.assertEquals(true,server.serverSuccesfullyStarted);
    }

   @After
    public void after(){
        try {
            server.server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
