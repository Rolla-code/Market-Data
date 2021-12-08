package com.example.marketdataservice.marketData.controller;

import com.example.marketdataservice.marketData.dto.MarketData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class MarketDataController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RestTemplate restTemplate;

    List<MarketData> md;

    @GetMapping("/md")
    public List<MarketData> getMarketData(){
        md = restTemplate.getForObject(
                "https://exchange.matraining.com/md", List.class);
        logger.info("Getting market data from exchange 1");
        return md;
    }

    @GetMapping("/md2")
    public List<MarketData> getMarketData2(){
        md = restTemplate.getForObject(
                "https://exchange2.matraining.com/md", List.class);
        logger.info("Getting market data from exchange 2");
        return md;
    }

    @GetMapping("/md/{ticker}")
    public MarketData getMarketDataForTicker(@PathVariable String ticker){
        MarketData md = restTemplate.getForObject(
                "https://exchange.matraining.com/md/" + ticker, MarketData.class);
        logger.info("Getting market data for "+ ticker + " from exchange 1");
        return md;
    }

    @GetMapping("/md2/{ticker}")
    public MarketData getMarketDataForTicker2(@PathVariable String ticker){
        MarketData md = restTemplate.getForObject(
                "https://exchange2.matraining.com/md/" + ticker, MarketData.class);
        logger.info("Getting market data for "+ ticker + " from exchange 2");
        return md;
    }


}
