package com.samgau.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genre")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long genreId;

    @Column(name = "genre_name")
    private String genreName;

    @OneToMany(mappedBy = "genre", fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

    @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
    private List<Borrowed> borroweds = new ArrayList<>();
}
