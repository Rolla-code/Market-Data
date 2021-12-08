package com.example.marketdataservice.MarketDataPubSub.configuration;

import com.example.marketdataservice.marketData.dto.MarketData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.Arrays;
import java.util.Map;

@Configuration
public class RedisConfiguration {

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setHostName("localhost");
        redisStandaloneConfiguration.setHostName("139.59.180.219");
//        redisStandaloneConfiguration.setPort(1957);
        redisStandaloneConfiguration.setPort(6379);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public ChannelTopic channelTopic1() {
        return new ChannelTopic("MarketData1Channel");
    }

    @Bean
    public ChannelTopic channelTopic2() {
        return new ChannelTopic("MarketData2Channel");
    }

    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<MarketData>(MarketData.class));
        return template;
    }


    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        return new MessageListenerAdapter(new RedisMessageSubscriber());
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());;
        container.setMessageListeners(Map.of(messageListenerAdapter(), Arrays.asList(channelTopic1(),channelTopic2())));
        return container;
    }



}
