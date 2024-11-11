package com.blaze.semaphore.domain.docs.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Table(name = "docs")
@Entity
@EntityListeners(value = AuditingEntityListener.class)
public class Docs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "docs_id", columnDefinition = "int(11)")
    private Long docsId;

    @Column(name = "title", columnDefinition = "varchar(100)")
    private String title;

    @Column(name = "content", columnDefinition = "varchar(500)")
    private String content;

    @Column(name = "view_count", columnDefinition = "int(11)")
    private Long viewCount;

    @CreatedDate
    @Column(name = "created_at", columnDefinition = "timestamp(3)")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "timestamp(3)")
    private LocalDateTime updatedAt;

    @Builder
    public Docs(Long docsId, String title, String content, Long viewCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.docsId = docsId;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void viewCountIncrease() {
        this.viewCount = getViewCount() + 1;
    }

}
