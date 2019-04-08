package tk.itiger.listeners.adapters;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import tk.itiger.htmlparser.HtmlParser;

import java.io.IOException;

public class PersonalRegistratorListener extends ListenerAdapter {
    private MessageReceivedEvent event;

    private boolean checkingUserLevelActivated;
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        this.event = event;
        String msg = getMessage();
        if (msg.equalsIgnoreCase("check the level!")){
            checkingUserLevelActivated = true;
            sendMessage("With pleasure. Give me an URL");
        }else if (checkingUserLevelActivated == true && msg.startsWith("https://javarush.ru/users/")){
            try {
                sendMessage(HtmlParser.getUserInfo(msg));
                checkingUserLevelActivated = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private String getMessage() {
        return event.getMessage().getContentRaw();
    }

    private void sendMessage(String msg) {
        event.getChannel().sendMessage(msg).queue();
    }


}
