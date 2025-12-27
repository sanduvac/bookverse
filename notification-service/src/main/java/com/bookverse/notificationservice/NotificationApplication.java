package com.bookverse.notificationservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

@SpringBootApplication
public class NotificationApplication implements CommandLineRunner {

    private final static String EXCHANGE_NAME = "order_events";

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Cloud bağlantısı için 10 saniye beklemeye gerek yok ama
        // sistemin tam oturması için 2-3 saniye yine de bekleyebiliriz.
        Thread.sleep(3000); 

        ConnectionFactory factory = new ConnectionFactory();
        
   
        String cloudUrl = "amqps://nxedgmcs:6kLeRy_tKFNOxtaxC2n_IW_9XrPdZt2K@shrimp.rmq.cloudamqp.com/nxedgmcs"; 
        
        try {
            factory.setUri(cloudUrl); // Bulut bağlantısını ayarla
        } catch (Exception e) {
            System.err.println("CloudAMQP URL hatası! Lütfen linki kontrol edin.");
            e.printStackTrace();
            return; // Hata varsa devam etme
        }
        // --- DEĞİŞİKLİK BİTİŞİ ---

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "order.created");

        System.out.println(" [*] Bildirim Servisi Hazır (CloudAMQP)! Mesajlar bekleniyor...");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Olay Alındı: '" + message + "'");
            System.out.println(" ==> Kullanıcıya E-Posta gönderiliyor... 📧");
        };

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}