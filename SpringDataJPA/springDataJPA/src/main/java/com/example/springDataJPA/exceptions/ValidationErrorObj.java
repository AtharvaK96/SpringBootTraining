package com.example.springDataJPA.exceptions;

import lombok.Data;

import java.util.Map;
@Data
public class ValidationErrorObj {
    public Map<String,String> error;
}
