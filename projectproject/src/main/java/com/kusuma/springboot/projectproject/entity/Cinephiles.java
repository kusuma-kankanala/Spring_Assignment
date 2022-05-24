package com.kusuma.springboot.projectproject.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "cinephiles")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Cinephiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cinephile_id")
    private int id;

    @Column(name = "cinephile_name")
    @NotNull(message="is required")
    //@Size(min=3, message="should have 3 or more characters")
    private String cinephileName;

    @Column(name = "seat_no")
    @NotNull(message="is required")
    //@Size(min=10, message="should have 10 or more characters")
    private String seatNumber;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "movie_cinephile",
            joinColumns = @JoinColumn(name = "cinephile_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movies> moviesList;

    public Cinephiles(String cinephileName, String seatNumber) {
        this.cinephileName = cinephileName;
        this.seatNumber = seatNumber;
    }
}
