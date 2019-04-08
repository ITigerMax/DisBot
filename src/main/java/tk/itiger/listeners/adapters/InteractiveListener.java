package tk.itiger.listeners.adapters;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import tk.itiger.htmlparser.HtmlParser;

import java.io.IOException;

public class InteractiveListener extends ListenerAdapter{
    private MessageReceivedEvent event;



        @Override
        public void onMessageReceived(MessageReceivedEvent event){
        this.event = event;
        System.out.println("Message was received from " + event.getAuthor().getName() + " : " + event.getMessage().getContentDisplay());
        String msg = getMessage();
            if (msg.equalsIgnoreCase("smith are u real?")){
            sendMessage("No! We are all are in the matrix! :yum: ");
        }else if (msg.equalsIgnoreCase("Smith") || msg.equalsIgnoreCase(" agent Smith")){
            sendMessage("Yes...");
        }else if (msg.equalsIgnoreCase("Smith what do u can?")){
            sendMessage(":thinking:");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sendMessage("I'm only studding.");
        }else if (msg.equalsIgnoreCase("Hi!")){
            sendMessage("Hello," + event.getAuthor().getName() +" my friend!!!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sendMessage("Glad, that u are not a Neo))  :sunglasses: ");
        }
    }

    private String getMessage() {
       return event.getMessage().getContentRaw();
    }

    private void sendMessage(String msg) {
        event.getChannel().sendMessage(msg).queue();
    }

}


