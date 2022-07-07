package demo4.mybatisRepository;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import demo4.vo.FileEntity;


@Mapper
public interface FileMybatisRepository {

	FileEntity getFile(Long fileId);

	Long saveFile(FileEntity fileEntity);
}