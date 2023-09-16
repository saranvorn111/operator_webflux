package com.example.test_mono_flux;

import com.example.test_mono_flux.student.Student;
import com.example.test_mono_flux.student.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestMonoFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestMonoFluxApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(StudentService studentService){
        return args ->{
            for(int i = 0; i < 100; i++){
                studentService.save(Student.builder()
                                .firstname("saran" + i)
                                .lastname("vorn" + i)
                                .age(i)
                        .build()).subscribe();
            }
        };


    }

}
