package tk.itiger;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tk.itiger.bot.BotAgentSmith;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        logger.info("Spring context is staring up...");

        ApplicationContext javaConfigContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        BotAgentSmith botAgentSmith = (BotAgentSmith) javaConfigContext.getBean("botAgentSmith");
        botAgentSmith.run();
    }
}
