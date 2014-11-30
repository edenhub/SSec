package WebSocket.WordGame;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lab on 2014/11/30.
 */
public class WordGameConfig implements ServerApplicationConfig {
    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> classes) {
        return null;
//        Set<ServerEndpointConfig> result = new HashSet<ServerEndpointConfig>();
//
//        if (classes.contains(WordGrameServerAnno.class)){
//            result.add(ServerEndpointConfig.Builder.create(WordGrameServerAnno.class,"").build());
//        }
//
//        return result;
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> classes) {
        Set<Class<?>> result = new HashSet<Class<?>>();

        System.out.println("add ... ");
        for(Class clazz : classes){
            if(clazz.getName().equals(WordGrameServerAnno.class.getName()))
                result.add(clazz);
        }

        return result;
    }
}
