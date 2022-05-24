package com.kusuma.springboot.projectproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int id;

    @Column(name = "movie_name")
    @NotNull(message="is required")
    @Size(min=5, message="should have 5 or more characters")
    private String movieName;

    @Column(name = "total_tickets")
    @NotNull(message="is required")
    @Size(min=3, message="should have 3 or more characters")
    private String totalTickets;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Users> usersList;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "movie_cinephile",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "cinephile_id")
    )
    private List<Cinephiles> cinephileList;

    public Movies(String movieName, String totalTickets) {
        this.movieName = movieName;
        this.totalTickets = totalTickets;
    }

    public void addUser(Users tempUser){
        if(this.usersList == null){
            this.usersList = new ArrayList<>();
        }

        this.usersList.add(tempUser);
    }

    public void addSpeaker(Cinephiles cinephile){
        if(this.cinephileList == null){
            this.cinephileList = new ArrayList<>();
        }

        this.cinephileList.add(cinephile);
    }
}
