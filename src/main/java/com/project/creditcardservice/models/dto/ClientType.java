package com.project.creditcardservice.models.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ClientType {
    private String description;
    private String type;
}
