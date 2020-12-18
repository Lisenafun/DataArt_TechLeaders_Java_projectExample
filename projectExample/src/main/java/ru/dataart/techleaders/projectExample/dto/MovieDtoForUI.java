package ru.dataart.techleaders.projectExample.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MovieDtoForUI {

    private Integer id;
    private String name;
    private String releaseDate;
    private String country;
    private String director;
    private List<String> genres;
}
