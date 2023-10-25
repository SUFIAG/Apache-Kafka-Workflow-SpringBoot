package io.dmullandev.kafka.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.dmullandev.kafka.consumer.entity.WikimediaData;

/**
 * Wikimedia JPA interface
 * 
 * @author dmullandev
 *
 */
@Repository
public interface WikimediaDataRepository extends JpaRepository<WikimediaData, Long> {

}
