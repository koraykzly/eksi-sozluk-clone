package com.example.eksi.domain.keys;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EntryVotedKey implements Serializable {

    private static final long serialVersionUID = -7363807410153846453L;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "entry_id")
    Long entryId;

    public EntryVotedKey() {
        super();
    }

    public EntryVotedKey(Long userId, Long entryId) {
        super();
        this.userId = userId;
        this.entryId = entryId;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(entryId, userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EntryVotedKey other = (EntryVotedKey) obj;
        return Objects.equals(entryId, other.entryId) && Objects.equals(userId, other.userId);
    }

}