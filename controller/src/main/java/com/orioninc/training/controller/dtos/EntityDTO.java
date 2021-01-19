package com.orioninc.training.controller.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class EntityDTO {
    private long id;
    @NotNull(message = "Name should be present")
    @Pattern(regexp = "^(\\S+)[A-Za-z0-9_ ,-]*$",
            message = "Name must start with 3 letters min; can contain letters, digits, space, \"_\" or \",\" only.")
    @Length(min = 3, max = 48, message = "Name length must be from 3 to 48 symbols")
    private String name;

}
