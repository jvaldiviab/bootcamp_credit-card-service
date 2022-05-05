package com.project.creditcardservice.repository;

import com.project.creditcardservice.models.entity.CreditCardEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ICreditCardRepository extends ReactiveMongoRepository<CreditCardEntity, String> {
    Flux<CreditCardEntity> findCreditCardsByClientId(String id);
}
