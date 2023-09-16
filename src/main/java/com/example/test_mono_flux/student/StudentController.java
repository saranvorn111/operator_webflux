package com.example.test_mono_flux.student;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final  StudentService studentService;

    @PostMapping
    Mono<Student> save(@RequestBody Student student){
        return studentService.save(student);

    }
    @GetMapping()
    Flux<Student> findAll(){
        return studentService.findAllStudent();
    }
    @GetMapping("/{id}")
    Mono<Student> findById(@PathVariable Integer id){
        return studentService.findStudentById(id);
    }
}
