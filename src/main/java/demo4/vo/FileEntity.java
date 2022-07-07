package demo4.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileEntity {

    private Long id;
    private String origFilename;
    private String filename;
    private String filePath;
}