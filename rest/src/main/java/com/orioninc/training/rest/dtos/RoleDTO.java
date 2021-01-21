package com.orioninc.training.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private long id;
    private String name;
    private Set<EntityDTO> users;

    @ToString.Include
    public String users() {
        return "[" + users.stream().map(EntityDTO::getName).collect(Collectors.joining(",")) + "]";
    }
}
