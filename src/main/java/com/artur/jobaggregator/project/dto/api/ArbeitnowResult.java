package com.artur.jobaggregator.project.dto.api;
import lombok.Getter;

import java.util.List;

@Getter
public class ArbeitnowResult{

    private String slug;

    private List<String> tags;

    private String title;

    private String companyName;

    private String description;

    private String url;

    private String location;

    private boolean remote;
}
