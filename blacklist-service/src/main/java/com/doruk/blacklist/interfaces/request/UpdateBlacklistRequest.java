package com.doruk.blacklist.interfaces.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdateBlacklistRequest {
    @NotBlank(message = "Identity cannot be empty!")
    private String identity;
    private String name;
    private String surname;
    private Boolean isActive;
}
