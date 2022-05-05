package com.project.creditcardservice.service;

import com.project.creditcardservice.models.dto.Account;
import com.project.creditcardservice.models.dto.Transaction;
import com.project.creditcardservice.models.entity.CreditCardEntity;
import com.project.creditcardservice.models.request.CreditCardConsumeCreditCardRequest;
import com.project.creditcardservice.models.request.CreditCardCreateRequest;
import com.project.creditcardservice.models.request.CreditCardPaymentCreditCardRequest;
import com.project.creditcardservice.models.request.CreditCardUpdateRequest;
import com.project.creditcardservice.models.response.AccountPassiveServiceResponse;
import com.project.creditcardservice.models.response.ClientClientServiceResponse;
import com.project.creditcardservice.repository.ICreditCardRepository;
import com.project.creditcardservice.utilities.constants.Constants;
import com.project.creditcardservice.utilities.errors.ClientNotFoundException;
import com.project.creditcardservice.utilities.errors.ElementBlockedException;
import com.project.creditcardservice.utilities.util.IAccountUtils;
import com.project.creditcardservice.utilities.util.ICreditCardUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditCardServiceImpl implements ICreditCardService{

    private final ICreditCardRepository creditCardRepository;
    private final Constants constants;
    private final IAccountUtils accountUtils;
    private final ICreditCardUtils creditCardUtils;

    @Autowired
    ReactiveCircuitBreaker clientsServiceReactiveCircuitBreaker;
    @Autowired
    ReactiveCircuitBreaker passiveServiceReactiveCircuitBreaker;

    @Override
    public Mono<CreditCardEntity> create(CreditCardCreateRequest creditCard) {
        if (creditCard.getClientId() == null)
            return Mono.error(new IllegalArgumentException("No id specified for credit card"));
        if (!creditCard.getClientId().isBlank()){
            Mono<CreditCardEntity> createdCreditCard = findByIdClientService(creditCard.getClientId())
                    .flatMap(retrievedClient -> creditCardToCreateGenericValidation(retrievedClient))
                    .flatMap(genericValidatedAccount -> {
                        if (genericValidatedAccount.getStatus().contentEquals(constants.getStatusBlocked()) || genericValidatedAccount.getAccountNumber() == null){
                            return Mono.error(new ElementBlockedException("Not supported account type"));
                        } else {
                            return Mono.just(genericValidatedAccount);
                        }
                    })
                    .flatMap(validateAccountToCredit ->{
                        Account account = accountUtils.accountPassiveServiceToAccount(validateAccountToCredit);
                        CreditCardEntity creditToCreate = creditCardUtils.creditCardCreateRequestToCreditCard(creditCard);
                        creditToCreate.setAccount(account);

                        return creditCardRepository.insert(creditToCreate);

                    })
                    .switchIfEmpty(Mono.error(new NoSuchElementException("Credit card does not exist")));
            return createdCreditCard;
        } else {
            return Mono.error(new IllegalArgumentException("Credit Card does not contain client id"));
        }
    }

    @Override
    public Flux<CreditCardEntity> findByClientId(String id) {
        return creditCardRepository.findCreditCardsByClientId(id);
    }

    @Override
    public Mono<CreditCardEntity> findById(String id) {
        return creditCardRepository.findById(id);
    }

    @Override
    public Flux<CreditCardEntity> findAll() {
        return creditCardRepository.findAll();
    }

    @Override
    public Mono<CreditCardEntity> update(CreditCardUpdateRequest creditCard) {
        return null;
    }

    @Override
    public Mono<CreditCardEntity> removeById(String id) {
        return null;
    }

    @Override
    public Mono<ClientClientServiceResponse> findByIdClientService(String id) {
        String url = constants.getServicesPrefix() + constants.getServicesUrlGateway()
                + constants.getServicesPathClient() + "/getById/" + id;

        return clientsServiceReactiveCircuitBreaker.run(
                WebClient.create(url)
                        .get()
                        .retrieve()
                        .bodyToMono(ClientClientServiceResponse.class),
                throwable -> {
                    // return Mono.error(new CircuitBreakerException("CLIENT-SERVICE NO AVAILABLE"));
                    return Mono.just(new ClientClientServiceResponse());
                });
    }

    @Override
    public Mono<AccountPassiveServiceResponse> findByClientIdSavingPassiveService(String id) {
        String url = constants.getServicesPrefix() + constants.getServicesUrlGateway()
                + constants.getServicesPathPassive() + "/client/" + id + "/accounts";

        return passiveServiceReactiveCircuitBreaker.run(
                WebClient.create(url)
                        .get()
                        .retrieve()
                        .bodyToFlux(AccountPassiveServiceResponse.class)
                        .filter(retrieveAccount -> retrieveAccount.getAccountType().getDescription().contentEquals(constants.getAccountSavings()))
                        .flatMap(r -> r.getId() != null ? Mono.just(r):Mono.error(new ElementBlockedException("")))
                        .take(1).single(),
                throwable -> {
                    // return Mono.error(new CircuitBreakerException("CLIENT-SERVICE NO AVAILABLE"));
                    return Mono.just(new AccountPassiveServiceResponse());
                });
    }

    @Override
    public Mono<CreditCardEntity> consumeCard(CreditCardConsumeCreditCardRequest creditCardConsume) {
        return null;
    }

    @Override
    public Mono<CreditCardEntity> payCard(CreditCardPaymentCreditCardRequest creditCardPayment) {
        return null;
    }

    @Override
    public Flux<Transaction> findTransactionsByCreditId(String id) {
        return null;
    }

    private Mono<AccountPassiveServiceResponse> creditCardToCreateGenericValidation(ClientClientServiceResponse retrievedClient){

        if(retrievedClient.getId() == null)
            return Mono.error(new ClientNotFoundException("The client id is null or does not exist"));
        // Check the status of the client
        if (retrievedClient.getStatus().contentEquals(constants.getStatusBlocked()))
            return Mono.error(new ElementBlockedException("The client have blocked status"));

        return findByClientIdSavingPassiveService(retrievedClient.getId());

    }


}
