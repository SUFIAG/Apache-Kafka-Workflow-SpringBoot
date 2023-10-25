package io.dmullandev.kafka.consumer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entity class for the Wikimedia type
 * 
 * @author dmullandev
 *
 */
@Entity
@Table(name = "wikimedia_recentchange")
@Data
public class WikimediaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "wikimediaEventData", length = 32000)
    private String wikimediaEventData;
}
