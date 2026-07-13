package com.artur.jobaggregator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDto {
    private String message;
    private String detailedMessage;
    private LocalDateTime localDateTime;
    private int status;
    private String path;

}
