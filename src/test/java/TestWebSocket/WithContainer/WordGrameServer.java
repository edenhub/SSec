package java.TestWebSocket.WithContainer;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by lab on 2014/11/30.
 */

@ServerEndpoint(value="/wordgames")
public class WordGrameServer {
    private static Logger logger = Logger.getLogger(WordGrameServer.class.getName());

    @OnOpen
    public void onOpen(Session session){
        logger.info("WordGrames start ip");
    }

    @OnClose
    public void onClose(Session session,CloseReason closeReason){
        logger.info("Session close with reason : "+closeReason.getReasonPhrase());
    }

    @OnMessage
    public String onMessage(Session session,String message) throws IOException {
        logger.info("Server : "+message);
        if (message.equals("QUIT")) {
            session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE,"Quit Normal"));
        }

        return message;
    }
}
