package com.project.creditcardservice.service;

import com.project.creditcardservice.models.dto.Transaction;
import com.project.creditcardservice.models.entity.CreditCardEntity;
import com.project.creditcardservice.models.request.CreditCardConsumeCreditCardRequest;
import com.project.creditcardservice.models.request.CreditCardCreateRequest;
import com.project.creditcardservice.models.request.CreditCardPaymentCreditCardRequest;
import com.project.creditcardservice.models.request.CreditCardUpdateRequest;
import com.project.creditcardservice.models.response.AccountPassiveServiceResponse;
import com.project.creditcardservice.models.response.ClientClientServiceResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICreditCardService {

    // #################################
    Mono<CreditCardEntity> create(CreditCardCreateRequest creditCard);
    Flux<CreditCardEntity> findByClientId(String id);
    Mono<CreditCardEntity> findById(String id);
    Flux<CreditCardEntity> findAll();
    Mono<CreditCardEntity> update(CreditCardUpdateRequest creditCard);
    Mono<CreditCardEntity> removeById(String id);

    // #################################
    Mono<ClientClientServiceResponse> findByIdClientService(String id);
    Mono<AccountPassiveServiceResponse> findByClientIdSavingPassiveService(String id);

    // #################################
    Mono<CreditCardEntity> consumeCard(CreditCardConsumeCreditCardRequest creditCardConsume);
    Mono<CreditCardEntity> payCard(CreditCardPaymentCreditCardRequest creditCardPayment);
    // Mono<CreditCardEntity> generateBillingOrder(String creditId);
    Flux<Transaction> findTransactionsByCreditId(String id);
  //  Flux<CreditCardFindBalancesResponse> findBalancesByClientId(String id);



}
