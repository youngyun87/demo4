package demo4.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.context.annotation.Role;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import demo4.service.BoardService;
import demo4.service.FileService;
import demo4.util.MD5Generator;
import demo4.util.Pagination;
import demo4.vo.Board;
import demo4.vo.FileEntity;

import javax.servlet.http.HttpSession;

@Controller
public class BoardController {

	private BoardService boardService;

	private FileService fileService;

    public BoardController(BoardService boardService, FileService fileService) {
        this.boardService = boardService;
        this.fileService = fileService;
    }
	
    @GetMapping("/")
    public String list(Model model
    		, @RequestParam(required = false, defaultValue = "1") int page
    		, @RequestParam(required = false, defaultValue = "1") int range) {
        //List<BoardDto> boardDtoList = boardService.getBoardList();
    	int listCnt = boardService.getBoardCnt();
    	
    	//Pagination 객체생성
    	Pagination pagination = new Pagination();
    	pagination.pageInfo(page, range, listCnt);
  	
    	List<Board> boardList = boardService.getBoardList(pagination);
    	
    	
    	model.addAttribute("pagination", pagination);
        model.addAttribute("postList", boardList);
        return "thymeleaf/board/list.html";
    }

    @GetMapping("/post")
    public String post() {
        return "thymeleaf/board/post.html";
    }

    @GetMapping("/post/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Board board = boardService.getPost(id);
        FileEntity fileEntity = fileService.getFile(board.getFileId());
        
        model.addAttribute("post", board);
        model.addAttribute("fileInfo", fileEntity);
        return "thymeleaf/board/detail.html";
    }

    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
    	Board boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "thymeleaf/board/edit.html";
    }

    @PostMapping("/post/edit/{id}")
    public String update(Board board) {
        boardService.updatePost(board);
        return "redirect:/";
    }
    
    @PostMapping("/post")
    public String write(@RequestParam("file") MultipartFile files, Board board) {
        try {
            String origFilename = files.getOriginalFilename();
            
            if(origFilename != null && !origFilename.equals("")) {
            	 String filename = new MD5Generator(origFilename).toString();
                 /* �떎�뻾�릺�뒗 �쐞移섏쓽 'files' �뤃�뜑�뿉 �뙆�씪�씠 ���옣�맗�땲�떎. */
                 String savePath = System.getProperty("user.dir") + "\\files";
                 /* �뙆�씪�씠 ���옣�릺�뒗 �뤃�뜑媛� �뾾�쑝硫� �뤃�뜑瑜� �깮�꽦�빀�땲�떎. */
                 if (!new File(savePath).exists()) {
                     try{
                         new File(savePath).mkdir();
                     }
                     catch(Exception e){
                         e.getStackTrace();
                     }
                 }
                 String filePath = savePath + "\\" + filename;
                 files.transferTo(new File(filePath));

                 FileEntity fileEntity = new FileEntity();
                 fileEntity.setOrigFilename(origFilename);
                 fileEntity.setFilename(filename);
                 fileEntity.setFilePath(filePath);

                 Long fileId = fileService.saveFile(fileEntity);
                 board.setFileId(fileId);
            }
    
            boardService.insertPost(board);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
    
    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> fileDownload(@PathVariable("fileId") Long fileId) throws IOException {
    	FileEntity fileDto = fileService.getFile(fileId);
        Path path = Paths.get(fileDto.getFilePath());
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getOrigFilename() + "\"")
                .body(resource);
    }

    @PostMapping("/post/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.deletePost(id);
        return "redirect:/";
    }
    
    @RequestMapping("/hello")
    public String hello() {

        return "thymeleaf/hello.html";
    }
    
    @RequestMapping("/preview")
    public String preview() {

        return "thymeleaf/preview.html";
    }

}