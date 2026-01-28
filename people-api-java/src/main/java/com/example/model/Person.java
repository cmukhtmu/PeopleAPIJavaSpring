package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "\"People\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"ID\"")
    private Long id;
    
    @Column(name = "\"First_Name\"")
    private String firstName;
    
    @Column(name = "\"Last_Name\"")
    private String lastName;
    
    @Column(name = "\"Phone\"")
    private String phone;
}