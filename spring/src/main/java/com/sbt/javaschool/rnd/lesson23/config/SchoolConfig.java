package com.sbt.javaschool.rnd.lesson23.config;

import com.sbt.javaschool.rnd.lesson21.db.school.connection.SchoolDB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchoolConfig {

    @Bean
    public SchoolDB getSchoolDB() {
        return new SchoolDB();
    }

}
