package com.neublog.neublogwebapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

//To generate the getters and setters automatically
@Getter
@Setter
@NoArgsConstructor //to create default constructor
@AllArgsConstructor // Paramatried constructor
@Builder //Builder design patter for this class
@Entity
@Table(name = "posts")
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;
    private String url;

    @Lob
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;
    private String shortDescription;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;

    @ManyToOne
    @JoinColumn(name = "createdBy", nullable = false)
    private User createdBy;


    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE) // post is the owner of this connection
    //When post is deleted, all the related Comments will also be deleted
    private Set<Comment> comments =new HashSet<>();
}
