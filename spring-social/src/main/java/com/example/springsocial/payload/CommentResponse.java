package com.example.springsocial.payload;

import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private String comment;
    private Long postId;
    private Long userId;
    private Date commentDate;
}
