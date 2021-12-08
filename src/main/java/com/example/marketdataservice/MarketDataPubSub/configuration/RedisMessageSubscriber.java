package com.example.marketdataservice.MarketDataPubSub.configuration;

import com.example.marketdataservice.marketData.dto.MarketData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class RedisMessageSubscriber implements MessageListener {

    Logger logger =  LoggerFactory.getLogger(RedisMessageSubscriber.class);

    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] pattern) {
        if (new String(message.getChannel(), StandardCharsets.UTF_8).equals("MarketData1Channel")){
            ObjectMapper objectMapper = new ObjectMapper();
            String body = new String(message.getBody());

            MarketData[] msg = objectMapper.readValue(body, MarketData[].class);
            logger.info("Consumed Message from Exchange 1 {}", Arrays.asList(msg));

        } else if(new String(message.getChannel(), StandardCharsets.UTF_8).equals("MarketData2Channel")) {
            ObjectMapper objectMapper = new ObjectMapper();
            String body = new String(message.getBody());

            MarketData[] msg = objectMapper.readValue(body, MarketData[].class);
            logger.info("Consumed Message from Exchange 2 {}", Arrays.asList(msg));
        }
    }

}
