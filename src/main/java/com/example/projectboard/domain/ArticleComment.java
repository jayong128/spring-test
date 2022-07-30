package com.example.projectboard.domain;

import java.time.LocalDateTime;

public class ArticleComment {
    private Long id;
    private Article article; // 게시글
    private String content; // 본문

    private LocalDateTime createAt; // 생성날짜
    private String createBy; // 생성자
    private LocalDateTime modifiedBy; // 수정시간
    private String modifiedAt; // 수정자
}
