package com.example.test_mono_flux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

public class MonoFlux {

    //mono
    @Test
    public void testMono(){
        Mono<?> monoString= Mono.just("JDBC").log();
        monoString.subscribe(s -> System.out.println("value :" + s));
    }

    @Test
    public void Mono(){
        Mono<String> mono = Mono.just("Hello, Mono!");

        mono.subscribe(
                value -> System.out.println("Received: " + value)
//                error -> System.err.println("Error: " + error),
//                () -> System.out.println("Completed")
        );

    }
    //flux
    @Test
    public void testFlux(){
       Flux<String> stringFlux = Flux.just("sprnig","spring boot","Spring hateoas").log();
       stringFlux.subscribe(s -> System.out.println("value of : "+s));

    }

    //filter
    @Test
    public void testFilteringFlux() {
        Flux<Integer> fluxFromJust = Flux.just(1, 2,3,4,5,6,7,8,9,10).log();
        Flux<Integer> filter = fluxFromJust.filter(i -> i % 2 == 0);//filter the even numbers only
        StepVerifier
                .create(filter)
                .expectNext()
                .verifyComplete();
    }

    //map use for transforming element
    @Test
    public void testMapOperationFlux() {

        Flux<String> fluxFromJust = Flux.just("RandomString", "SecondString","XCDFRG").log();
        Flux<Integer> filter = fluxFromJust.map(String::length);
        StepVerifier
                .create(filter)
                .expectNext(12,12,6)
                .verifyComplete();
    }
    //flatMapMany
    @Test
    public void flatMapManyTest() {
        Mono<List<Integer>> just = Mono.just(Arrays.asList(1, 2, 3)).log();
        Flux<Integer> integerFlux = just.flatMapMany(Flux::fromIterable);
        StepVerifier
                .create(integerFlux)
                .expectNext(1, 2, 3)
                .verifyComplete();
    }

    //conCateWith
    @Test
    public void  concatWith(){
        Flux<Integer> just = Flux.just(1, 2, 3);
        Flux<Integer> integerFlux = just.concatWith(Flux.just(4,5)).log();
        StepVerifier.create(integerFlux)
                .expectNext(1,2,3,4,5)
                .verifyComplete();
    }
    //zip
    @Test
    public void zip() {
        Flux<Integer> firsFlux = Flux.just(1, 2, 3);
        Flux<Integer> secondFlux = Flux.just(10, 20, 30, 40);
        Flux<Integer> zip = Flux.zip(firsFlux, secondFlux, Integer::sum).log();
        StepVerifier
                .create(zip)
                .expectNext(11, 22, 33)
                .verifyComplete();
    }

    //buffer
    @Test
    public void bufferTest() {
        Flux<List<Integer>> buffer = Flux
                .just(1, 2, 3, 4, 5, 6, 7)
                .buffer(2);

        StepVerifier
                .create(buffer)
                .expectNext(Arrays.asList(1, 2))
                .expectNext(Arrays.asList(3, 4))
                .expectNext(Arrays.asList(5, 6))
                .expectNext(Arrays.asList(7))
                .verifyComplete();

    }

}
