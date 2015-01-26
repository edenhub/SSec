package java.TestWebSocket.WithContainer;

import org.glassfish.tyrus.server.Server;

import javax.websocket.DeploymentException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by lab on 2014/11/30.
 */
public class ServerManager {
    public static void main(String[] args) throws DeploymentException, IOException {
        Server server = new Server("localhost",8081,"/wst",WordGrameServer.class);
        server.start();

        String line = new BufferedReader(new InputStreamReader(System.in)).readLine();
        server.stop();
    }
}
