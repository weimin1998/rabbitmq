package cn.itcast.mq.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest

public class AMQPTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMessageToSimpleQueue() {
        String queue = "simple.queue";
        String message = "hello, spring amqp";

        rabbitTemplate.convertAndSend(queue, message);
    }


    @Test
    public void testSendMessageToWorkQueue() {
        String queue = "work.queue";
        String message = "hello, message__";
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(20);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            rabbitTemplate.convertAndSend(queue, message + i);
        }
    }

    @Test
    public void testSendMessageToFanoutExchange() {
        String exchange = "itcast.fanout";
        String message = "hello, everyone";
        rabbitTemplate.convertAndSend(exchange,"",message);
    }

    @Test
    public void testSendMessageToDirectExchange() throws InterruptedException {
        String exchange = "itcast.direct";
        String message1 = "hello, blue";
        String message2 = "hello, yellow";
        String message3 = "hello, red";
        rabbitTemplate.convertAndSend(exchange,"blue",message1);
        Thread.sleep(10);
        rabbitTemplate.convertAndSend(exchange,"yellow",message2);
        Thread.sleep(10);
        rabbitTemplate.convertAndSend(exchange,"red",message3);
    }

    @Test
    public void testSendMessageToTopicExchange() throws InterruptedException {
        String exchange = "itcast.topic";
        String message1 = "中国新闻";
        String message2 = "中国天气";
        String message3 = "日本新闻";
        rabbitTemplate.convertAndSend(exchange,"china.news",message1);
        Thread.sleep(10);
        rabbitTemplate.convertAndSend(exchange,"china.weather",message2);
        Thread.sleep(10);
        rabbitTemplate.convertAndSend(exchange,"japan.news",message3);
    }

    @Test
    public void testSendObjectMessageToObjectQueue() {

        String queue = "object.queue";
        Map<String,String> map = new HashMap<>();
        map.put("name","weimin");
        rabbitTemplate.convertAndSend(queue,map);
    }

}
