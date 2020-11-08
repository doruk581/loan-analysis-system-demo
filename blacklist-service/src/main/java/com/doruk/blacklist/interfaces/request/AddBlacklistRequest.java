package com.doruk.blacklist.interfaces.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AddBlacklistRequest {

    @NotBlank(message = "IdentityNumber cannot be empty!")
    private String identityNumber;
    @NotBlank(message = "Name cannot be empty!")
    private String name;
    @NotBlank(message = "Surname cannot be empty!")
    private String surname;
}
