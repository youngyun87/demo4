package demo4.service;

import java.util.List;

//import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import demo4.mybatisRepository.BoardMybatisRepository;
import demo4.util.Pagination;
import demo4.vo.Board;

@Service
public class BoardService {
  //  private BoardRepository boardRepository;    
    private BoardMybatisRepository boardMybatisRepository;
   
    /*
    public BoardService(BoardRepository boardRepository, BoardMybatisRepository boardMybatisRepository) {
    	this.boardRepository = boardRepository;
    	this.boardMybatisRepository = boardMybatisRepository;
    }
    */
    
    public BoardService( BoardMybatisRepository boardMybatisRepository) {
        //	this.boardRepository = boardRepository;
        	this.boardMybatisRepository = boardMybatisRepository;
        }
    
    public List<Board> getBoardList(Pagination pagination) {
    	
    	List<Board> boardList = boardMybatisRepository.getBoardList(pagination);       
        return boardList;
        
    }

	public Board getPost(Long id) {

    	Board board = boardMybatisRepository.getPost(id);       
        return board;
	}

	public void insertPost(Board board) {
		boardMybatisRepository.insertPost(board);
	}
	
	public void updatePost(Board board) {
		boardMybatisRepository.updateBoard(board);
	}

	public void deletePost(Long id) {
		boardMybatisRepository.deletePost(id);
		
	}

	public int getBoardCnt() {
		return boardMybatisRepository.getBoardCnt();
	}
   
    
}