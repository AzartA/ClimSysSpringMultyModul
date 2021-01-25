package com.orioninc.training.rest.dtos;

import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private long id;
    @NotNull(message = "{login.absent}")
    @Pattern(regexp = "^[0-9a-zA-Z-_]{3,48}$", message = "Login must contain from 3 to 48 letters or digits only")
    private String login;
    @Size(min = 3, max = 48, message = "Name must contain from 3 to 48 symbols")
    private String name;
    //@JsonIgnore
    private String password;

    private Set<String> properties;
    private Set<SensorDTO> sensors;
    private Set<EntityDTO> roles;

    @ToString.Include
    public String sensors() {
        return "[" + sensors.stream().map(SensorDTO::getName).collect(Collectors.joining(",")) + "]";
    }

    @ToString.Include
    public String roles() {
        return "[" + roles.stream().map(EntityDTO::getName).collect(Collectors.joining(",")) + "]";
    }

}