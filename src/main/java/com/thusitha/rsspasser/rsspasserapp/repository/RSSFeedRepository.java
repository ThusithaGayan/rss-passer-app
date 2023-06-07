package com.thusitha.rsspasser.rsspasserapp.repository;

import com.thusitha.rsspasser.rsspasserapp.entity.RSSFeed;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Save,Delete,update,retrieve data
 */
public interface RSSFeedRepository extends JpaRepository<RSSFeed, String> {
}
