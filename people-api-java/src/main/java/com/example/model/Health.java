package com.example.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Health {
    public String status = "OK";
    public String database = "Connected";
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime timestamp = LocalDateTime.now();
}