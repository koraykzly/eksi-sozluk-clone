package com.example.eksi.domain.keys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FavoriteEntriesKey implements Serializable {

    private static final long serialVersionUID = -9149059266368185663L;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "entry_id")
    Long entryId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }
}
