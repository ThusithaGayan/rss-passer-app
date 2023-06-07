package com.thusitha.rsspasser.rsspasserapp.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "rss.feeder")
@Data
/**
 * To Get RSS URL and get Schedule time
 */
public class RssConfig {
    private String url;
    private long interval;
}
