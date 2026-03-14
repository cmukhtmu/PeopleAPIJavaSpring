package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "\"AddressHistory\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"ID\"")
    private Long id;

    @Column(name = "\"Address1\"", length = 50, nullable = false)
    private String address1;

    @Column(name = "\"Address2\"", length = 50)
    private String address2;

    @Column(name = "\"City\"", length = 50)
    private String city;

    @Column(name = "\"State\"", length = 2)
    private String state;

    @Column(name = "\"Zip\"", length = 5)
    private String zip;

    @Column(name = "\"Active\"")
    private Boolean active;

    @Column(name = "\"PeopleID\"")
    private Long peopleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"PeopleID\"", insertable = false, updatable = false)
    @JsonBackReference
    private Person person;
}