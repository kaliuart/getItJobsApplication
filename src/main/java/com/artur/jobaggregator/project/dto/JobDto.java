package com.artur.jobaggregator.project.dto;

import lombok.Data;

import java.util.List;

@Data
public class JobDto {

    private List<String> tags;

    private String title;

    private String companyName;

    private String description;

    private String url;

    private String location;

    private boolean remote;
}
