package com.itau.pix.desafio.util;

import com.itau.pix.desafio.domain.AccountType;
import com.itau.pix.desafio.domain.KeyType;
import com.itau.pix.desafio.dto.PixKeyResponseDto;
import com.itau.pix.desafio.dto.PixKeyUpdateDto;
import com.itau.pix.desafio.request.PixKeyPostRequestBody;
import com.itau.pix.desafio.request.PixKeyPutRequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PixKeyDataForTest {

    public static PixKeyResponseDto createPixKeyResponseDtoValid(){
        return PixKeyResponseDto.builder()
                .id(UUID.fromString("0000-00-00-00-000000"))
                .keyType(KeyType.EMAIL)
                .keyValue("teste@gmail.com")
                .accountType(AccountType.CORRENTE)
                .agency(1)
                .account(1)
                .name("Testerson")
                .lastName("Test")
                .createdAt(String.valueOf(LocalDateTime.now()))
                .build();
    }

    public static PixKeyUpdateDto createPixKeyUpdateDtoValid(){
        return PixKeyUpdateDto.builder()
                .id(UUID.fromString("0000-00-00-00-000000"))
                .keyType(KeyType.CPF)
                .keyValue("67234319018")
                .accountType(AccountType.CORRENTE)
                .agency(1)
                .account(1)
                .name("Testerson")
                .lastName("Test")
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static List<PixKeyResponseDto> createPixKeyResponseDtoValidList(){
        return List.of(createPixKeyResponseDtoValid());
    }

    public static PixKeyPostRequestBody createPostPixKeyRequestBodyValid(){
        return PixKeyPostRequestBody.builder()
                .keyType(KeyType.CNPJ)
                .keyValue("19383298000194")
                .accountType(AccountType.CORRENTE)
                .agency(1)
                .account(1)
                .name("Testerson")
                .lastName("Test")
                .build();
    }

    public static PixKeyPostRequestBody createPostPixKeyRequestBodyInvalidCNPJ(){
        return PixKeyPostRequestBody.builder()
                .keyType(KeyType.CNPJ)
                .keyValue("19383298000191")
                .accountType(AccountType.CORRENTE)
                .agency(1)
                .account(1)
                .name("Testerson")
                .lastName("Test")
                .build();
    }

    public static PixKeyPostRequestBody createPostPixKeyRequestBodyInvalidCPF(){
        return PixKeyPostRequestBody.builder()
                .keyType(KeyType.CPF)
                .keyValue("11111111111")
                .accountType(AccountType.CORRENTE)
                .agency(1)
                .account(1)
                .name("Testerson")
                .lastName("Test")
                .build();
    }

    public static PixKeyPutRequestBody createPutPixKeyUpdateDtoValid(){
        return PixKeyPutRequestBody.builder()
                .id(UUID.fromString("0000-00-00-00-000000"))
                .accountType(AccountType.CORRENTE)
                .agency(1)
                .account(1)
                .name("Testerson")
                .lastName("Test")
                .build();
    }
}
