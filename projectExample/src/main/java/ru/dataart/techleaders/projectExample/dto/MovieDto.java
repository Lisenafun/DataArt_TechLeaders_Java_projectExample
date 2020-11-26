package ru.dataart.techleaders.projectExample.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieDto {

    private Integer id;
    private String name;
    private String releaseDate;
    private String country;
    private String director;
    private String genre;
}
