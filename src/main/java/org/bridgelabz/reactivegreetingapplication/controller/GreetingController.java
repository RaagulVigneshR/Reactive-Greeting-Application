package org.bridgelabz.reactivegreetingapplication.controller;

import org.bridgelabz.reactivegreetingapplication.model.Greeting;
import org.bridgelabz.reactivegreetingapplication.service.ServiceInterface;
import org.bridgelabz.reactivegreetingapplication.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@RestController
@RequestMapping("/greetings")
public class GreetingController {

    @Autowired
    private ServiceInterface services;

    @PostMapping
    public Mono<ResponseEntity<Response>> create(@RequestBody Greeting greeting) {
        return services.createGreeting(greeting)
                .map(savedGreeting -> ResponseEntity.status(HttpStatus.OK)
                        .body(new Response(200, "Greeting created successfully")));
    }

    @GetMapping
    public Flux<Greeting> getAll() {
        return services.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Greeting> getGreetings(@PathVariable int id) {
        return services.getGreetings(id);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Response>> putGreetings(@RequestBody Greeting greeting, @PathVariable int id) {
        return services.putGreetings(greeting, id)
                .map(updatedGreeting -> ResponseEntity.status(HttpStatus.OK)
                        .body(new Response(200, "Greeting updated successfully")));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Response>> deleteGreetings(@PathVariable int id) {
        return services.deleteGreetings(id)
                .then(Mono.just(ResponseEntity.status(HttpStatus.OK)
                        .body(new Response(200, "Greeting deleted successfully"))));
    }
}