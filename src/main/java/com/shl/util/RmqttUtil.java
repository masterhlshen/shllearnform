package com.shl.util;

public final class RmqttUtil {
    /*private static final String EXCHANGE_NAME = "xc_topic_exchange";

    public void sentMsg(Map data, String... routingKeys) throws IOException, TimeoutException {
        sentMsg(EXCHANGE_NAME, data, routingKeys);
    }

    public void sentMsg(String exchangeName, Map data, String... routingKeys) throws IOException, TimeoutException {
        String message = JsonUtils.writeValueAsString(data);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(exchangeName, "topic", true);
            for (String routingKey : routingKeys) {
                channel.basicPublish(exchangeName, routingKey, null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
            }
        }
    }

    public void dealTask(Work work, String[] routeKeys) throws IOException, TimeoutException {
        dealTask(work, EXCHANGE_NAME, routeKeys);
    }

    public void dealTask(Work work, String exhangeName, String[] routeKeys) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic", true);
        String queueName = channel.queueDeclare().getQueue();

        for (String routeKey : routeKeys) {
            channel.queueBind(queueName, EXCHANGE_NAME, routeKey);
        }
        channel.basicQos(1);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
            int res = work.deal(JsonUtils.readValueByClass(message, Map.class));
            if (res == -1) {
                channel.basicReject(delivery.getEnvelope().getDeliveryTag(), true);
            } else {
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        channel.basicConsume(queueName, false, deliverCallback, consumerTag -> {
        });
    }*/
}
