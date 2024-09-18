package com.example.RestTemplateAssignment.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginationResponse {
    List<Movie> movies;
    boolean lastPage;
    Integer totalPages;
    Integer currentPage;
}
