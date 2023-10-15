package com.geekster.InstaBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;


    @Column(name = "create_Date")
    private LocalDateTime createDate;


    @Column(name = "update_Date")
    private LocalDateTime updateDate;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User postOwner;
}
