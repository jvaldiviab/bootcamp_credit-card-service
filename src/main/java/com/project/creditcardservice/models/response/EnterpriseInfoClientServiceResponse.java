package com.project.creditcardservice.models.response;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EnterpriseInfoClientServiceResponse {
    private String name;
    private String ruc;
    private ArrayList<PersonInfoClientServiceResponse> holders;
    private ArrayList<PersonInfoClientServiceResponse> signers;
}