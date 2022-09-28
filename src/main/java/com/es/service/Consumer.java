package com.es.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

@MessageDriven(mappedName = "jms/dest", activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class Consumer implements MessageListener {
    private static final Path PATH = Paths.get("E:\\Programs\\glassfish5\\file.txt");

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            String text = textMessage.getText();

            /*if (text.endsWith("!")) {
                Files.write(PATH, (text + '\n').getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
            }*/

            System.out.println(text);

            TimeUnit.MILLISECONDS.sleep(1);
        } catch (JMSException | InterruptedException /*| IOException*/ e) {
            e.printStackTrace();
        }
    }
}
