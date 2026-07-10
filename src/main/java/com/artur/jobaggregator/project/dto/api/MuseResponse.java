package com.artur.jobaggregator.project.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class MuseResponse {
    private List<MuseResult> results;

}
