package cn.itcast.mq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class SpringRabbitListener {

//    @RabbitListener(queues = "simple.queue")
//    public void listenSimpleQueue(String message) {
//        System.out.println("收到消息: " + message);
//    }


//    @RabbitListener(queues = "work.queue")
//    public void listenWorkQueue(String message) {
//        System.out.println("消费者1收到消息: " + message + " " + LocalTime.now());
//        try {
//            Thread.sleep(20);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @RabbitListener(queues = "work.queue")
//    public void listenWorkQueue1(String message) {
//        System.err.println("消费者2收到消息: " + message + " " + LocalTime.now());
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @RabbitListener(queues = "fanout.queue1")
//    public void listenFanoutQueue1(String message) {
//        System.out.println("消费者收到来自fanout.queue1消息: " + message + " " + LocalTime.now());
//    }
//
//    @RabbitListener(queues = "fanout.queue2")
//    public void listenFanoutQueue2(String message) {
//        System.out.println("消费者收到来自fanout.queue2消息: " + message + " " + LocalTime.now());
//    }
//
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(name = "direct.queue1"),// 监听的队列
//            exchange = @Exchange(name = "itcast.direct", type = ExchangeTypes.DIRECT),// 队列绑定的交换机，默认就是Direct类型
//            key = {"red", "blue"}// binding key
//    ))
//    public void listenDirectQueue1(String message) {
//        System.out.println("消费者1收到来自direct.queue1消息: " + message + " " + LocalTime.now());
//    }
//
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(name = "direct.queue2"),
//            exchange = @Exchange(name = "itcast.direct", type = ExchangeTypes.DIRECT),
//            key = {"red", "yellow"}
//    ))
//    public void listenDirectQueue2(String message) {
//        System.out.println("消费者2收到来自direct.queue1消息: " + message + " " + LocalTime.now());
//    }
//
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "itcast.topic", type = ExchangeTypes.TOPIC),
            key = "china.#"
    ))
    public void listenTopicQueue1(String message) {
        System.out.println("消费者1收到来自topic.queue1消息: " + message + " " + LocalTime.now());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(name = "itcast.topic", type = ExchangeTypes.TOPIC),
            key = "#.news"
    ))
    public void listenTopicQueue2(String message) {
        System.out.println("消费者2收到来自topic.queue2消息: " + message + " " + LocalTime.now());
    }

}
