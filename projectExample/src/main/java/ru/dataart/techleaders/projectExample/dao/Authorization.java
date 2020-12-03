package ru.dataart.techleaders.projectExample.dao;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Authorization {

    private final String url = "jdbc:mysql://localhost:3306/movies_db?useUnicode=true&serverTimezone=UTC&characterEncoding=utf8";
    private final String user = "root";
    private final String password = "123";
}
