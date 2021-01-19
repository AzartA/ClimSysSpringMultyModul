package com.orioninc.training.rest.dtos;

import com.orioninc.training.model.dos.UserDO;
import com.orioninc.training.model.entities.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
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
