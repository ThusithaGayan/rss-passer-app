package com.thusitha.rsspasser.rsspasserapp.service;

import com.thusitha.rsspasser.rsspasserapp.entity.RSSFeed;
import com.thusitha.rsspasser.rsspasserapp.repository.RSSFeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RssService {
    private final RSSFeedRepository rssFeedRepository;

    public Page<RSSFeed> getAll(int page, int size, String sort, String direction) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sort));
        return rssFeedRepository.findAll(pageable);
    }
}
