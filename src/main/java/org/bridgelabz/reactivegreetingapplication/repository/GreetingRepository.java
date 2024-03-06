package org.bridgelabz.reactivegreetingapplication.repository;

import org.bridgelabz.reactivegreetingapplication.model.Greeting;
//import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingRepository extends ReactiveCrudRepository<Greeting,Integer> {

}
