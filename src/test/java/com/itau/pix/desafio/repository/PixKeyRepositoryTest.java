package com.itau.pix.desafio.repository;

import com.itau.pix.desafio.domain.AccountType;
import com.itau.pix.desafio.domain.KeyType;
import com.itau.pix.desafio.domain.PixKey;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

@DataJpaTest
class PixKeyRepositoryTest {

    @Autowired
    private PixKeyRepository pixKeyRepository;
    @Test
    public void save_PersistPixKey_WhenSuccessful(){
        PixKey pixKeyToBeSaved = createPixKey();
        PixKey pixKeySaved = this.pixKeyRepository.save(pixKeyToBeSaved);

        Assertions.assertThat(pixKeySaved).isNotNull();
        Assertions.assertThat(pixKeySaved.getKeyType()).isEqualTo(pixKeyToBeSaved.getKeyType());
        Assertions.assertThat(pixKeySaved.getKeyValue()).isEqualTo(pixKeyToBeSaved.getKeyValue());
        Assertions.assertThat(pixKeySaved.getAgency()).isEqualTo(pixKeyToBeSaved.getAgency());
        Assertions.assertThat(pixKeySaved.getAccount()).isEqualTo(pixKeyToBeSaved.getAccount());
        Assertions.assertThat(pixKeySaved.getName()).isEqualTo(pixKeyToBeSaved.getName());
        Assertions.assertThat(pixKeySaved.getLastName()).isEqualTo(pixKeyToBeSaved.getLastName());
        Assertions.assertThat(pixKeySaved.getCreatedAt()).isEqualTo(pixKeyToBeSaved.getCreatedAt());
        Assertions.assertThat(pixKeySaved.getDisabledAt()).isNull();
        Assertions.assertThat(pixKeySaved.getAccountStatus()).isNull();
    }

    @Test
    @DisplayName("Save update PixKey when sucessful")
    public void save_UpdatePixKey_WhenSuccessful(){
        PixKey pixKeyToBeSaved = createPixKey();
        PixKey pixKeySaved = this.pixKeyRepository.save(pixKeyToBeSaved);

        pixKeySaved.setName("Hoshi");

        PixKey pixKeyUpdated = this.pixKeyRepository.save(pixKeySaved);

        Assertions.assertThat(pixKeyUpdated).isNotNull();
        Assertions.assertThat(pixKeyUpdated.getKeyType()).isEqualTo(pixKeyToBeSaved.getKeyType());
        Assertions.assertThat(pixKeyUpdated.getKeyValue()).isEqualTo(pixKeyToBeSaved.getKeyValue());
        Assertions.assertThat(pixKeyUpdated.getAgency()).isEqualTo(pixKeyToBeSaved.getAgency());
        Assertions.assertThat(pixKeyUpdated.getAccount()).isEqualTo(pixKeyToBeSaved.getAccount());
        Assertions.assertThat(pixKeyUpdated.getName()).isEqualTo(pixKeyToBeSaved.getName());
        Assertions.assertThat(pixKeyUpdated.getLastName()).isEqualTo(pixKeyToBeSaved.getLastName());
        Assertions.assertThat(pixKeyUpdated.getCreatedAt()).isEqualTo(pixKeyToBeSaved.getCreatedAt());
        Assertions.assertThat(pixKeyUpdated.getDisabledAt()).isNull();
        Assertions.assertThat(pixKeyUpdated.getAccountStatus()).isNull();

    }

    private PixKey createPixKey(){
        return PixKey.builder()
                .keyType(KeyType.EMAIL)
                .keyValue("teste@gmail.com")
                .accountType(AccountType.CORRENTE)
                .agency(1)
                .account(1)
                .name("Testerson")
                .lastName("Test")
                .createdAt(LocalDateTime.now())
                .build();
    }

}