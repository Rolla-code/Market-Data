package group14.orderservice.mdPubSub;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Getter
public class RedisMessageSubscriber implements MessageListener {

    Logger logger =  LoggerFactory.getLogger(RedisMessageSubscriber.class);

    private static List<MarketData> mdOne;
    private static List<MarketData> mdTwo;

    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] pattern) {
        if (new String(message.getChannel(), StandardCharsets.UTF_8).equals("MarketData1Channel")){
            ObjectMapper objectMapper = new ObjectMapper();
            String body = new String(message.getBody());

            MarketData[] msg1 = objectMapper.readValue(body, MarketData[].class);
            mdOne = Arrays.asList(msg1);
//            logger.info("Consumed Message from Exchange 1 {}", Arrays.asList(msg1));
            logger.info("Consumed Message from Exchange 1 {}", mdOne);

        } else if (new String(message.getChannel(), StandardCharsets.UTF_8).equals("MarketData2Channel")) {
            ObjectMapper objectMapper = new ObjectMapper();
            String body = new String(message.getBody());

            MarketData[] msg2 = objectMapper.readValue(body, MarketData[].class);
            mdTwo = Arrays.asList(msg2);
            logger.info("Consumed Message from Exchange 2 {}", Arrays.asList(msg2));
        }
    }

}
