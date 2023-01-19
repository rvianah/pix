package com.itau.pix.desafio.dto;

import com.itau.pix.desafio.domain.AccountType;
import com.itau.pix.desafio.domain.KeyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class PixKeyResponseDto {
    private UUID id;
    private KeyType keyType;
    private String keyValue;

    private AccountType accountType;

    private Integer agency;

    private Integer account;

    private String name;

    private String lastName;

    private String createdAt;

    private String disabledAt;

}
