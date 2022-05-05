package com.project.creditcardservice.models.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Quote {
    private Double netQuote;
    private Double dailyInterest;
}
