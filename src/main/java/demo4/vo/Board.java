package demo4.vo;

import lombok.*;

import java.time.LocalDateTime;

import demo4.util.Pagination;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends Pagination{

    private Long id;
    private String author;
    private String title;
    private String content;
    private Long fileId;
    private LocalDateTime regdate;
    private LocalDateTime modifiedDate;
    private String sample3;
}