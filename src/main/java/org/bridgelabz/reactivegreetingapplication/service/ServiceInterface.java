package org.bridgelabz.reactivegreetingapplication.service;

import org.bridgelabz.reactivegreetingapplication.model.Greeting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ServiceInterface {
    Mono<Greeting> createGreeting(Greeting greeting);
    Flux<Greeting> getAll();
    Mono<Greeting> getGreetings(int id);
    Mono<Greeting> putGreetings(Greeting greeting,int id);
    Mono<Boolean> deleteGreetings(int id);
}
