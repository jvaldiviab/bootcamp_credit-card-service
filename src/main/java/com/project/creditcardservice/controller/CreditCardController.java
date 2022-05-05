package com.project.creditcardservice.controller;

import com.project.creditcardservice.models.entity.CreditCardEntity;
import com.project.creditcardservice.models.request.CreditCardCreateRequest;
import com.project.creditcardservice.service.ICreditCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/creditCard")
public class CreditCardController {

    private final ICreditCardService creditCardService;

    @PostMapping("/create")
    public Mono<ResponseEntity<CreditCardEntity>> createCredit(@RequestBody CreditCardCreateRequest creditCard) {
        log.info("Post operation in /create");
        return creditCardService.create(creditCard)
                .flatMap(createdCredit -> Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(createdCredit)))
                .onErrorResume(IllegalArgumentException.class, error -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()))
                .onErrorResume(NoSuchElementException.class, error -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(null)));
    }

}
