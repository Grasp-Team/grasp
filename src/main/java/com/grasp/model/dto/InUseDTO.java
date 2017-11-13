package com.grasp.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InUseDTO {

    private boolean inUse = false;
    private String message;

    public InUseDTO(boolean inUse, String message) {
        this.inUse = inUse;
        this.message = message;
    }

    public InUseDTO() {
    }
}
