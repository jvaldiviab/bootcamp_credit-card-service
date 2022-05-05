package com.project.creditcardservice.models.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ClientTypeClientServiceResponse {
    private String description;
    private String type;
}