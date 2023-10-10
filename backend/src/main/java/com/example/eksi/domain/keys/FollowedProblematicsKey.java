package com.example.eksi.domain.keys;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FollowedProblematicsKey implements Serializable {

    private static final long serialVersionUID = -5026618073550544165L;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "problematic_id")
    Long problematicId;

    public FollowedProblematicsKey() {
        super();
    }

    public FollowedProblematicsKey(Long userId, Long problematicId) {
        super();
        this.userId = userId;
        this.problematicId = problematicId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProblematicId() {
        return problematicId;
    }

    public void setProblematicId(Long problematicId) {
        this.problematicId = problematicId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(problematicId, userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FollowedProblematicsKey other = (FollowedProblematicsKey) obj;
        return Objects.equals(problematicId, other.problematicId) && Objects.equals(userId, other.userId);
    }

}