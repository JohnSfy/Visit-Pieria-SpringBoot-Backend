package com.DiscoverHellas.model.Destination;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "DestinationStatistics")
@Data
public class DestinationStat {

    @Id
    private String stat_id;
    private String destination_id;
    private int total_visits;
    private float average_rating;
    private int total_wishlist_items;
    private int total_feedback_given;

    @Column(updatable = false) // Prevent overwriting during updates
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


    public DestinationStat() {}

    public DestinationStat(String destination_id, int total_visits, float average_rating, int total_wishlist_items, int total_feedback_given) {
        this.stat_id = generateId();
        this.destination_id = destination_id;
        this.total_visits = total_visits;
        this.average_rating = average_rating;
        this.total_wishlist_items = total_wishlist_items;
        this.total_feedback_given = total_feedback_given;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    private String generateId() {
        return "dstst_" + UUID.randomUUID().toString().replace("-", "").substring(0, 8); // Prefix with 'destcat'
    }

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = new Date();
        }
        if (this.stat_id == null) {
            this.stat_id = generateId();
        }
        this.updatedAt = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date(); // Update the updatedAt field
    }
}
