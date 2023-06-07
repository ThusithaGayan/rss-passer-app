package com.thusitha.rsspasser.rsspasserapp.schedulers;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.thusitha.rsspasser.rsspasserapp.configs.RssConfig;
import com.thusitha.rsspasser.rsspasserapp.entity.RSSFeed;
import com.thusitha.rsspasser.rsspasserapp.repository.RSSFeedRepository;
import com.thusitha.rsspasser.rsspasserapp.utils.HashUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
@RequiredArgsConstructor
@Slf4j
/**
 * This feeder will read the RSSFeed and update the database
 */
public class RSSFeeder {
    private final RSSFeedRepository rssFeedRepository;
    private final RssConfig rssConfig;

    @Scheduled(fixedRateString = "${rss.feeder.interval}")
    @SneakyThrows
    public void rssReader() {
        log.info("Feeding database with the latest feed");
        URL url = new URL(rssConfig.getUrl());
        XmlReader xmlReader = new XmlReader(url);
        SyndFeed feed = new SyndFeedInput().build(xmlReader);
        feed.getEntries().stream()
                .filter(entry -> entry.getDescription() != null && entry.getPublishedDate() != null)
                .map(this::buildRssFeed)
                .forEach(rssFeedRepository::save);
    }

    @SneakyThrows
    private RSSFeed buildRssFeed(SyndEntry entry) {
        String id = HashUtils.sha256(entry.getUri());
        return RSSFeed.builder()
                .id(id)
                .date(entry.getPublishedDate())
                .description(entry.getDescription().getValue())
                .title(entry.getTitle())
                .build();
    }
}
