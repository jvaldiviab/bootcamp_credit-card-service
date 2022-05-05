package com.project.creditcardservice.models.entity;

import com.project.creditcardservice.models.dto.Account;
import com.project.creditcardservice.models.dto.Client;
import com.project.creditcardservice.models.dto.PendingPayment;
import com.project.creditcardservice.models.dto.Transaction;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document(collection = "credit-card")
public class CreditCardEntity {
    @Id
    private String id;
    private Client client;
    private String creditCardNumber;
    private String status;
    private Account account;
    private int billingCycle;
    private int paymentDay;
    private Double creditLine; // Monto max
    private Double availableAmount; // Monto available ############
    private Date membershipDate;
    private Date dueDate; // due date
    private ArrayList<Transaction> transaction; // transaction on credit card
    private ArrayList<PendingPayment> pendingPayments;
}
