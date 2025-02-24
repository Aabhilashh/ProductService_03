package dev.abhi.project_03.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category extends BaseModel {
    private String name;
    public String description;
}
