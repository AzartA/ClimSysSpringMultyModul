package com.orioninc.training.controller.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SensorSDTO {
    private long id;
    private String name;
    private String locationName;
}