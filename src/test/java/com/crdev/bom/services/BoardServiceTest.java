package com.crdev.bom.services;

import com.crdev.bom.config.WebMvcConfiguration;
import com.crdev.bom.model.Board;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.util.List;

/**
 * BoardService 단위테스트 클래스
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebMvcConfiguration.class })
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void getListTest() {

        // given
        Board board1 = new Board();
        board1.setId(1);
        board1.setSubject("제목 1 입니다.");
        board1.setContent("내용 1 입니다.");
        board1.setRegDate(LocalDateTime.now());
        board1.setDeleted(false);

        // when
        List<Board> list = boardService.getList();
        list.forEach(System.out::println);

        // then
        Assert.assertNotNull("목록은 null 일 수 없습니다.", list);
        Assert.assertTrue("목록은 1개 이상 존재합니다.", 0 < list.size());
        Assert.assertTrue("1번 게시물이 동일하지 않습니다.", list.get(0).equals(board1));

    }

}