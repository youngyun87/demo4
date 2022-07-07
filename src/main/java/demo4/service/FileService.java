package demo4.service;

//import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import demo4.mybatisRepository.FileMybatisRepository;
import demo4.vo.FileEntity;


@Service
public class FileService {
   // private FileRepository fileRepository;
    private FileMybatisRepository fileRepository;

    public FileService(FileMybatisRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

	public FileEntity getFile(Long fileId) {
		return fileRepository.getFile(fileId);
	}

	public Long saveFile(FileEntity fileEntity) {
		return fileRepository.saveFile(fileEntity);
	}

    
}