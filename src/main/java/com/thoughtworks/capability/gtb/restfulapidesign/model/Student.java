package com.thoughtworks.capability.gtb.restfulapidesign.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Student {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    private String name;
    private Gender gender;
    private String note;
}
