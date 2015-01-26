package java.TestWebSocket.WithContainer;

import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

/**
 * Created by lab on 2014/11/30.
 */
@ClientEndpoint
public class WordGrameClient {
    private static Logger logger = Logger.getLogger(WordGrameClient.class.getName());
    private static CountDownLatch latch;

    @OnOpen
    public void onOpen(Session session) throws IOException {
        logger.info("Connected : "+session.getId());

        session.getBasicRemote().sendText("start");
    }

    @OnClose
    public void onClose(Session session,CloseReason closeReason){
        logger.info("Close : "+closeReason.getReasonPhrase());
        latch.countDown();
    }

    @OnMessage
    public String onMessage(Session session, String message) throws IOException {
        logger.info("Client : "+message);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    @OnError
    public void onError(Throwable throwable){
        logger.info("Error : "+throwable.getMessage());
        throwable.printStackTrace();
    }

    public static void main(String[] args) throws URISyntaxException, DeploymentException, InterruptedException {
        latch = new CountDownLatch(1);
        ClientManager clientManager = ClientManager.createClient();

        clientManager.connectToServer(WordGrameClient.class,new URI("ws://localhost:8080/ssc/wordgames"));
        latch.await();
    }
}
