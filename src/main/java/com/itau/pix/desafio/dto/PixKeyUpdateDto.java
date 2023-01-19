package com.itau.pix.desafio.dto;

import com.itau.pix.desafio.domain.AccountType;
import com.itau.pix.desafio.domain.KeyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class PixKeyUpdateDto {
    private UUID id;
    private KeyType keyType;
    private String keyValue;

    private AccountType accountType;

    private Integer agency;

    private Integer account;

    private String name;

    private String lastName;

    private LocalDateTime createdAt;
}
