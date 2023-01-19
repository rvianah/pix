package com.itau.pix.desafio.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.pix.desafio.domain.AccountType;
import com.itau.pix.desafio.enumvalidator.EnumNamePattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PixKeyPutRequestBody {

    private UUID id;

    @JsonProperty(value = "account_type")
    @EnumNamePattern( regexp = "CORRENTE|POUPANCA")
    private AccountType accountType;

    @NotNull
    private Integer agency;

    @NotNull
    private Integer account;

    @NotNull
    @NotEmpty
    private String name;

    @JsonProperty(value = "last_name")
    private String lastName;

}

