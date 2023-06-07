package com.thusitha.rsspasser.rsspasserapp.service;

import com.thusitha.rsspasser.rsspasserapp.entity.RSSFeed;
import com.thusitha.rsspasser.rsspasserapp.repository.RSSFeedRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RssServiceTest {

    @Mock
    RSSFeedRepository rssFeedRepository;
    @InjectMocks
    RssService rssService;

    @Test
    public void testGetAll_WhenWithDefaultPagination_VerifyReturnCorrectData() {
        List<RSSFeed> rssFeeds=new ArrayList<>();
        rssFeeds.add(0,new RSSFeed("1","Title1","description1",new Date(),null,null));
        rssFeeds.add(1,new RSSFeed("2","Title2","description2",new Date(),null,null));
        rssFeeds.add(2,new RSSFeed("3","Title3","description3",new Date(),null,null));

        Page<RSSFeed> rssFeedPage=new PageImpl<>(rssFeeds);
        PageRequest pageRequest=PageRequest.of(0,10, Sort.by(Sort.Direction.fromString("asc"),"id"));
        when(rssFeedRepository.findAll(pageRequest)).thenReturn(rssFeedPage);
        List<RSSFeed> responseRssFeeds=rssService.getAll(0,10,"id","asc").stream().toList();

        assertEquals("Title1",responseRssFeeds.get(0).getTitle());
        assertEquals("Title2",responseRssFeeds.get(1).getTitle());
        assertEquals("Title3",responseRssFeeds.get(2).getTitle());
    }
}