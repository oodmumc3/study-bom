package com.crdev.bom.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 게시판 모델
 */
public class Board {

    public Board() {}

    public Board(Integer id, String subject, String content, LocalDateTime regDate, Boolean deleted) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.regDate = regDate;
        this.deleted = deleted;
    }

    /* 번호 */
    private Integer id;

    /* 제목 */
    private String subject;

    /* 내용 */
    private String content;

    /* 등록일 */
    private LocalDateTime regDate;

    /* 삭제여부 */
    private Boolean deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Board{");
        sb.append("id=").append(id);
        sb.append(", subject='").append(subject).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", regDate=").append(regDate);
        sb.append(", deleted=").append(deleted);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(id, board.id) && Objects.equals(subject, board.subject) && Objects.equals(content, board.content) && Objects.equals(regDate, board.regDate) && Objects.equals(deleted, board.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, content, regDate, deleted);
    }

}
