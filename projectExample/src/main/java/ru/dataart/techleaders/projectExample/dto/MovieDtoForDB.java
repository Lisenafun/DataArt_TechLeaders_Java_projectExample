package ru.dataart.techleaders.projectExample.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class MovieDtoForDB {

    private Integer id;
    private String name;
    private String releaseDate;
    private String country;
    private Integer directorId;
}
