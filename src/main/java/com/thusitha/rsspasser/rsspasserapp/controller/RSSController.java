package com.thusitha.rsspasser.rsspasserapp.controller;

import com.thusitha.rsspasser.rsspasserapp.entity.RSSFeed;
import com.thusitha.rsspasser.rsspasserapp.service.RssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RSSController {
    @Autowired
    RssService rssService;
    @GetMapping("/items")
    public List<RSSFeed> getAllFeed()
    {
        return rssService.getAll();
    }
}
