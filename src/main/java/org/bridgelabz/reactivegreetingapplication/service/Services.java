package org.bridgelabz.reactivegreetingapplication.service;

import org.bridgelabz.reactivegreetingapplication.model.Greeting;
import org.bridgelabz.reactivegreetingapplication.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class Services implements ServiceInterface{
    @Autowired
    private GreetingRepository repository;
    @Override
    public Mono<Greeting> createGreeting(Greeting greeting) {
        return repository.save(greeting);
    }

    @Override
    public Flux<Greeting> getAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Greeting> getGreetings(int id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Greeting> putGreetings(Greeting greeting, int id) {
        Mono<Greeting>oldGreet=repository.findById(id);
        return oldGreet.flatMap(greet1->{
            greet1.setName(greeting.getName());
            greet1.setMessage(greeting.getMessage());
            return repository.save(greet1);
        });
    }

    @Override
    public Mono<Boolean> deleteGreetings(int id) {
        return repository.existsById(id)
                .flatMap(exists->{
                    if(exists){
                        return repository.deleteById(id)
                                .thenReturn(true);
                    }
                    else {
                        return Mono.just(false);
                    }
                });
    }
}
