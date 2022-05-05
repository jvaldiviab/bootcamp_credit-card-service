package com.project.creditcardservice.models.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AccountTypePassiveServiceResponse {
    private String description;
    private String type;
}
