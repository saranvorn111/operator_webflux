package com.example.test_mono_flux.student;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Mono<Student> save(Student student){
        return studentRepository.save(student);
    }
    public Flux<Student> findAllStudent(){
        return studentRepository.findAll();
    }

    public Mono<Student> findStudentById(Integer id){
        return studentRepository.findById(id);
    }
}
