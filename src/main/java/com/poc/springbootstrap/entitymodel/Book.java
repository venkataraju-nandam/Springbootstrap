package com.poc.springbootstrap.entitymodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "book")
//@RequiredArgsConstructor
public class Book {
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Setter @Getter
    private long id;
 
    @Column(nullable = false, unique = true)
    private String title;
 
    
    @Column(nullable = false)
    private String author;
    

}