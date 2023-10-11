package com.example.eksi.domain.keys;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TopicTagsKey implements Serializable {

    private static final long serialVersionUID = 1224014275776556246L;

    @Column(name = "tag_id")
    int tagId;

    @Column(name = "topic_id")
    Long topicId;

    public TopicTagsKey() {
        super();
    }

    public TopicTagsKey(short tagId, Long topicId) {
        super();
        this.tagId = tagId;
        this.topicId = topicId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, topicId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TopicTagsKey other = (TopicTagsKey) obj;
        return tagId == other.tagId && Objects.equals(topicId, other.topicId);
    }

}
