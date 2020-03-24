package com.example.luntan.dto;

import lombok.Data;

@Data
public class CommentDTO {

    private Long parentId;
    private String commentText;
    private Integer type;
}
