package com.thusitha.rsspasser.rsspasserapp.controller;

import com.thusitha.rsspasser.rsspasserapp.entity.RSSFeed;
import com.thusitha.rsspasser.rsspasserapp.service.RssService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RSSController {
    private final RssService rssService;

    /**To get Rss Feeds
     * @param page      page
     * @param size      number of rss feed
     * @param sort      entity field
     * @param direction ascending or descending
     * @return rss feed items
     */

    @GetMapping("/items")
    public Page<RSSFeed> getAllFeed(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "id") String sort,
            @RequestParam(required = false, defaultValue = "asc") String direction
    ) {
        return rssService.getAll(page, size, sort, direction);
    }
}
