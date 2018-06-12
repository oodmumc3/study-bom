package com.crdev.bom.services;

import com.crdev.bom.model.Board;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 게시판 서비스
 */
@Service
public class BoardService {

    /**
     * 목록 반환
     * @return
     */
    public List<Board> getList() {
        List<Board> list = new ArrayList<>();

        Board board1 = new Board();
        board1.setId(1);
        board1.setSubject("제목 1 입니다.");
        board1.setContent("내용 1 입니다.");
        board1.setRegDate(LocalDateTime.now());
        board1.setDeleted(false);
        list.add(board1);

        Board board2 = new Board();
        board2.setId(2);
        board2.setSubject("제목 2 입니다.");
        board2.setContent("내용 2 입니다.");
        board2.setRegDate(LocalDateTime.now());
        board2.setDeleted(false);
        list.add(board2);

        Board board3 = new Board();
        board3.setId(3);
        board3.setSubject("제목 3 입니다.");
        board3.setContent("내용 3 입니다.");
        board3.setRegDate(LocalDateTime.now());
        board3.setDeleted(false);
        list.add(board3);

        return list;
    }

}
