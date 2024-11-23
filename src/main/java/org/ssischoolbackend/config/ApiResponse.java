package org.ssischoolbackend.config;

import lombok.Getter;
import lombok.Setter;
import org.ssischoolbackend.model.Etudiant;

import java.util.List;

@Getter
@Setter
public class ApiResponse<T> {
    private List<T> content;
    private int totalPages;

    public ApiResponse(List<T> content, int totalPages) {
        this.content = content;
        this.totalPages = totalPages;
    }
}

