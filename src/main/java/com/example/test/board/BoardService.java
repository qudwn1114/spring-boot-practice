package com.example.test.board;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import com.example.test.file.*;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Board> getList(){
        return this.boardRepository.findAll();
    }

    public void create(String subject, String content, MultipartFile file) throws Exception {

        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();

        
        FileUtil fileUtil = new FileUtil();
        String uploadPath = "/media/board/";
        uploadPath += fileUtil.getDatePath();

        String uploadFilePath =  fileUtil.getOrCreateDirectory(uploadPath);
        
        File saveFile = new File(uploadFilePath, fileName);
        file.transferTo(saveFile);

        Board b = new Board();
        b.setSubject(subject);
        b.setContent(content);
        b.setFilename(fileName);
        b.setFilepath(uploadPath + "/" + fileName);
        b.setCreateDate(LocalDateTime.now());
        this.boardRepository.save(b);
    }

    public Board getBoard(Integer id){
        Optional<Board> board = this.boardRepository.findById(id);
        if(board.isPresent()){
            return board.get();
        }
        else{
            throw new RuntimeException("Data Not Found");
        }
    }
}