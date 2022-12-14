package com.example.projectboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createAt"),
        @Index(columnList = "createBy")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter @Column(nullable = false) private String title; // 제목
    @Setter private String hashtag; // 해시내그
    @Setter @Column(nullable = false, length = 10000) private String content; // 본문

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @OrderBy("id")
    @ToString.Exclude
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    @CreatedDate @Column(nullable = false) private LocalDateTime createAt; // 생성날짜
    @CreatedBy @Column(nullable = false, length = 100) private String createBy; // 생성자
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt; // 수정시간
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy; // 수정자

    protected Article() {
    }

    private Article(String title, String hashtag, String content) {
        this.title = title;
        this.hashtag = hashtag;
        this.content = content;
    }
    public static Article of(String title, String hashtag, String content) {
        return new Article(title, content, hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
