package com.hus23.assignment.socialmediaplatform.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "postData")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String post_content;

    @Column(name = "creation_date")
    private Date creationDate = new Date();

    @Column(name = "update_date")
    private Date updateDate = new Date();

    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;
}
