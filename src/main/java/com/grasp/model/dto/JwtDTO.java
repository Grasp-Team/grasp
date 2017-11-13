package com.grasp.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDTO {

    private String apiToken;

    public JwtDTO(String apiToken) {
        this.apiToken = apiToken;
    }

    public JwtDTO() {
    }
}
