package com.example.test.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping(value="/board")
    public String list(Model model) {
        List<Board> boardList = this.boardService.getList();
        model.addAttribute("boardList", boardList);
        return "board/board_list";
    }


    @GetMapping(value="/board/create")
    public String create(Model model) {
        return "board/board_create";
    }

    @ResponseBody
    @PostMapping(value="/board/create")
    public Map<String, Object> create(@RequestParam String subject, @RequestParam String content, MultipartFile file) throws Exception {
        Map<String, Object> response = new HashMap<>();
        
        this.boardService.create(subject, content, file);

        response.put("success", true);
        response.put("message", "등록되었습니다.");
        response.put("redirect_url", "/board");

        return response;
    }

    @GetMapping(value="/board/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Board board = this.boardService.getBoard(id);
        model.addAttribute("board", board);
        return "board/board_detail";
    }
}
