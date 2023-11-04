package com.example.springsocial.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    private Date commentDate;
}
