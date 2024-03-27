package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message="Street is required")
    @Size(min=3, message="Name must be at least 5 characters long")
    private String name;
    @NotEmpty(message="Street is required")
    @Size(min=3, message="Name must be at least 5 characters long")
    private String text;

    public Long getId() {return id;}

    public String getName() {return name;}

    public String getText() {return text;}

    public void setId(Long id) {this.id = id;}

    public void setName(String name) {this.name = name;}

    public void setText(String text) {this.text = text;}

    public Post(){}

    public Post(String name, String text){
        this.name = name;
        this.text = text;
    }

}
