package com.itau.pix.desafio.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.pix.desafio.domain.AccountType;
import com.itau.pix.desafio.domain.KeyType;
import com.itau.pix.desafio.enumvalidator.EnumNamePattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PixKeyPostRequestBody {

    @JsonProperty(value = "key_type")
    @EnumNamePattern( regexp = "CELULAR|EMAIL|CPF|CNPJ|ALEATORIO")
    private KeyType keyType;

    @JsonProperty(value = "key_value")
    @NotNull
    @NotEmpty
    private String keyValue;

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

    private LocalDateTime createdAt;

}
