package com.artur.jobaggregator.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class JobEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String slug;

    private List<String> tags;

    private String title;

    @JsonProperty("company_name")
    private String companyName;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String url;

    private String location;

    private boolean remote;



}
