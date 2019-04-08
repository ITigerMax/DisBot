package tk.itiger.bot;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tk.itiger.utils.ExternalContextReader;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class BotAgentSmith implements Runnable{

    private static final Logger LOGGER = LogManager.getLogger(BotAgentSmith.class);
    @Value("NTYzODExMDI1NjMyMTAwMzg0.XKexYA.tYGa3fVOTUXeDEazBty_jzLGXig")
    private String token;
    @Value("Listeners.properties")
    private String listeners;
    private  JDA jda;
    private static List<String> listOfEventListeners = new ArrayList();

    @Override
    public void run() {
        LOGGER.info("in run method");
        while (true){
            LOGGER.info("looking for new listeners");
        Map<String, String> listenersClassProperties = ExternalContextReader.getDataContext(listeners);
            for (Map.Entry<String, String> pair : listenersClassProperties.entrySet()) {
                String listenerClassKey = pair.getKey();
                String listenerClassProperty = pair.getValue();
                if (!listOfEventListeners.contains(listenerClassKey)){
                    try {
                        ListenerAdapter adapter = (ListenerAdapter) Class.forName(listenerClassProperty).newInstance();
                        if (listOfEventListeners.add(listenerClassKey)) {
                            LOGGER.info("```````````````````Adding " + listenerClassProperty);
                        }
                        jda.addEventListener(adapter);
                    } catch (InstantiationException | IllegalAccessException |ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    for (Object adapter : jda.getRegisteredListeners()){
                        System.out.println(adapter.getClass().getSimpleName());
                    }
                }
            }
            LOGGER.info("next search for the listeners through 5sec... sleep...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
    @PostConstruct
    public  void init(){
        LOGGER.info("started to initialize JDABuilder");
        try {
             jda = new JDABuilder(token).build();
             jda.awaitReady();
            System.out.println(jda.getRegisteredListeners());
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("finished to initialize JDABuilder");

    }

}
