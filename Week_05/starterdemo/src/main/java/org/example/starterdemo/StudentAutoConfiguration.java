package org.example.starterdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Student.class)
@EnableConfigurationProperties(StudentProperties.class)
public class StudentAutoConfiguration {
    @Autowired
    private StudentProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public Student createStudent() {
        Student student = new Student();
        student.setId(properties.getId());
        student.setName(properties.getName());

        return student;
    }
}
