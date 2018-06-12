package com.crdev.bom.controllers;

import com.crdev.bom.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 게시판 컨트롤러
 */
@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public String root(Model model) {
        model.addAttribute("boards", boardService.getList());
        return "board/list";
    }

}
