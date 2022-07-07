package demo4.mybatisRepository;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import demo4.util.Pagination;
import demo4.vo.Board;


@Mapper
public interface BoardMybatisRepository {
	List<Board> getBoardList(Pagination pagination);

	Board getPost(Long id);

	void updateBoard(Board board);

	void insertPost(Board board);

	void deletePost(Long id);

	int getBoardCnt();

}