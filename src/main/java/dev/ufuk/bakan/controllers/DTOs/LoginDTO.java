package dev.ufuk.bakan.controllers.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginDTO {
    @NotEmpty
    private String companyName;
    @NotEmpty
    private String password;

}
