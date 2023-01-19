package com.itau.pix.desafio.service;

import com.itau.pix.desafio.util.PixKeyDataForTest;
import com.itau.pix.desafio.domain.KeyType;
import com.itau.pix.desafio.domain.PixKey;
import com.itau.pix.desafio.dto.PixKeyResponseDto;
import com.itau.pix.desafio.dto.PixKeyUpdateDto;
import com.itau.pix.desafio.exception.NotFoundRequestException;
import com.itau.pix.desafio.exception.ValidationRequestException;
import com.itau.pix.desafio.repository.PixKeyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
class PixKeyServiceTest {

    @InjectMocks
    PixKeyService pixKeyService;

    @Mock
    PixKeyRepository pixKeyRepository;

    @Test
    void findById_ReturnPixKeyResponseDto_WhenSuccessful(){
        BDDMockito.when(pixKeyRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(PixKey.builder().id(UUID.fromString("0000-00-00-00-000000")).build()));

        PixKeyResponseDto pixKeyResponseDtoValid = PixKeyDataForTest.createPixKeyResponseDtoValid();

        PixKeyResponseDto responseDto = pixKeyService.findById(UUID.fromString("0000-00-00-00-000000"));

        Assertions.assertNotNull(responseDto);
        Assertions.assertTrue(responseDto.getId().equals(pixKeyResponseDtoValid.getId()));
    }

    @Test
    void save_ReturnUUID_WhenSuccessful(){

        BDDMockito.when(pixKeyRepository.save(ArgumentMatchers.any(PixKey.class)))
                .thenReturn(PixKey.builder().id(UUID.fromString("0000-00-00-00-000000")).build());

        UUID validUuid = UUID.fromString("0000-00-00-00-000000");

        UUID responseUUID = pixKeyService.save(PixKeyDataForTest.createPostPixKeyRequestBodyValid());

        Assertions.assertNotNull(responseUUID);
        Assertions.assertTrue(responseUUID.equals(validUuid));
    }

    @Test
    void save_ReturnValidationException_WhenCnpjInvalid(){
        Assertions.assertThrows(ValidationRequestException.class, () -> pixKeyService.save(PixKeyDataForTest.createPostPixKeyRequestBodyInvalidCNPJ()));
    }

    @Test
    void save_ReturnValidationException_WhenCpfInvalid(){
        Assertions.assertThrows(ValidationRequestException.class, () -> pixKeyService.save(PixKeyDataForTest.createPostPixKeyRequestBodyInvalidCPF()));
    }

    @Test
    void findById_ReturnNotFoundException_WhenIdNotFound(){
        Assertions.assertThrows(NotFoundRequestException.class, () -> pixKeyService.findById(UUID.fromString("0000-00-00-00-000002")));
    }


    @Test
    void update_ReturnPixKeyUpdateDto_WhenSuccessful(){
        BDDMockito.when(pixKeyRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(PixKey.builder().id(UUID.fromString("0000-00-00-00-000000")).build()));

        PixKeyUpdateDto pixKeyUpdateDto = PixKeyDataForTest.createPixKeyUpdateDtoValid();

        PixKeyUpdateDto responseDto = pixKeyService.update(PixKeyDataForTest.createPutPixKeyUpdateDtoValid());

        Assertions.assertNotNull(responseDto);
        Assertions.assertTrue(responseDto.getId().equals(pixKeyUpdateDto.getId()));
    }

    @Test
    void listByCombinedResources_ReturnListPixKeyResponseDto_WhenSuccessful(){
        BDDMockito.when(pixKeyRepository.findByCombinedResources(ArgumentMatchers.any(KeyType.class),
                        ArgumentMatchers.any(Integer.class),
                        ArgumentMatchers.any(Integer.class),
                        ArgumentMatchers.any(String.class)))
                .thenReturn(List.of(PixKey.builder().id(UUID.fromString("0000-00-00-00-000000")).build()));

        List<PixKeyResponseDto> pixKeyResponseDtoValid = List.of(PixKeyDataForTest.createPixKeyResponseDtoValid());

        List<PixKeyResponseDto> responseDtoList = pixKeyService.listByCombinedResources(KeyType.EMAIL, 1, 1, "Testerson");

        Assertions.assertNotNull(responseDtoList);
        Assertions.assertTrue(!responseDtoList.isEmpty());

        Assertions.assertTrue(responseDtoList.get(0).getId().equals(pixKeyResponseDtoValid.get(0).getId()));

    }

}