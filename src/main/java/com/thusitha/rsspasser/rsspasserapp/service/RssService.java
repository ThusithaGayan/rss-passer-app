package com.thusitha.rsspasser.rsspasserapp.service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.thusitha.rsspasser.rsspasserapp.entity.RSSFeed;
import com.thusitha.rsspasser.rsspasserapp.repository.RSSFeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.net.URL;
import java.util.List;

@Service
public class RssService {

    @Autowired
    RSSFeedRepository rssFeedRepository;

    @Scheduled(fixedRate = 50000)
    public void rssReader() throws IOException {
        URL url=new URL("http://rss.cnn.com/rss/cnn_topstories.rss");
        XmlReader xmlReader=new XmlReader(url);
        try {
            SyndFeed feed=new SyndFeedInput().build(xmlReader);
            for (SyndEntry entry:feed.getEntries())
            {
                if (entry.getDescription()!=null&&entry.getPublishedDate()!=null)
                {
                    rssFeedRepository.save(RSSFeed.builder().date(entry.getPublishedDate()).description(entry.getDescription().getValue()).title(entry.getTitle()).build());
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public List<RSSFeed> getAll()
    {
        return rssFeedRepository.findAll();
    }
}
