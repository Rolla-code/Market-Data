package com.example.marketdataservice.MarketDataPubSub.configuration;

import com.example.marketdataservice.marketData.dto.MarketData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RedisMessagePublisher {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ChannelTopic channelTopic1;

    @Autowired
    private ChannelTopic channelTopic2;

    // TODO Configure URL with Subscription Webhook URL
    //@ApiOperation("Subscription to Exchange Data Webhook")
    @PostMapping("/publish")
    public String publish(@RequestBody List<MarketData> message) {
        redisTemplate.convertAndSend(channelTopic1.getTopic(), message);
        return "Message Published";
    }

    @PostMapping("/publish2")
    public String publish2(@RequestBody List<MarketData> message) {
        redisTemplate.convertAndSend((channelTopic2.getTopic()), message);
        return "Message Published";
    }

}
