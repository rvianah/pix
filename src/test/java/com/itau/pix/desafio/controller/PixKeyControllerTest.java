package com.itau.pix.desafio.controller;

import com.itau.pix.desafio.util.PixKeyDataForTest;
import com.itau.pix.desafio.domain.KeyType;
import com.itau.pix.desafio.dto.PixKeyResponseDto;
import com.itau.pix.desafio.dto.PixKeyUpdateDto;
import com.itau.pix.desafio.request.PixKeyPostRequestBody;
import com.itau.pix.desafio.request.PixKeyPutRequestBody;
import com.itau.pix.desafio.service.PixKeyService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.UUID;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PixKeyControllerTest {

    @InjectMocks
    PixKeyController pixKeyController;

    @Mock
    PixKeyService pixKeyService;

    @Test
    void findById_ReturnPixKeyResponseDto_WhenSuccessful(){
        BDDMockito.when(pixKeyService.findById(UUID.fromString("0000-00-00-00-000000")))
                .thenReturn(PixKeyDataForTest.createPixKeyResponseDtoValid());

        PixKeyResponseDto pixKeyResponseDtoValid = PixKeyDataForTest.createPixKeyResponseDtoValid();

        PixKeyResponseDto responseDto = pixKeyController.findById(UUID.fromString("0000-00-00-00-000000")).getBody();

        Assertions.assertThat(responseDto).isNotNull();

        Assertions.assertThat(responseDto.getId()).isEqualTo(pixKeyResponseDtoValid.getId());
        Assertions.assertThat(responseDto.getKeyType()).isEqualTo(pixKeyResponseDtoValid.getKeyType());
        Assertions.assertThat(responseDto.getKeyValue()).isEqualTo(pixKeyResponseDtoValid.getKeyValue());
        Assertions.assertThat(responseDto.getAccountType()).isEqualTo(pixKeyResponseDtoValid.getAccountType());
        Assertions.assertThat(responseDto.getAgency()).isEqualTo(pixKeyResponseDtoValid.getAgency());
        Assertions.assertThat(responseDto.getAccount()).isEqualTo(pixKeyResponseDtoValid.getAccount());
        Assertions.assertThat(responseDto.getName()).isEqualTo(pixKeyResponseDtoValid.getName());
        Assertions.assertThat(responseDto.getLastName()).isEqualTo(pixKeyResponseDtoValid.getLastName());
    }


    @Test
    void save_ReturnUUID_WhenSuccessful() {
        BDDMockito.when(pixKeyService.save(ArgumentMatchers.any(PixKeyPostRequestBody.class)))
                .thenReturn(UUID.fromString("0000-00-00-00-000000"));

        UUID validUuid = UUID.fromString("0000-00-00-00-000000");

        UUID responseUUID = pixKeyController.save(PixKeyDataForTest.createPostPixKeyRequestBodyValid()).getBody();

        Assertions.assertThat(responseUUID).isNotNull();
        Assertions.assertThat(responseUUID).isEqualTo(validUuid);
    }

    @Test
    void update_ReturnPixKeyUpdateDto_WhenSuccessful(){
        BDDMockito.when(pixKeyService.update(ArgumentMatchers.any(PixKeyPutRequestBody.class)))
                .thenReturn(PixKeyDataForTest.createPixKeyUpdateDtoValid());

        PixKeyUpdateDto pixKeyUpdateDto = PixKeyDataForTest.createPixKeyUpdateDtoValid();

        PixKeyUpdateDto responseDto = pixKeyController.update(PixKeyDataForTest.createPutPixKeyUpdateDtoValid()).getBody();

        Assertions.assertThat(responseDto).isNotNull();
        Assertions.assertThat(responseDto.getId()).isEqualTo(pixKeyUpdateDto.getId());
        Assertions.assertThat(responseDto.getKeyType()).isEqualTo(pixKeyUpdateDto.getKeyType());
        Assertions.assertThat(responseDto.getKeyValue()).isEqualTo(pixKeyUpdateDto.getKeyValue());
        Assertions.assertThat(responseDto.getAccountType()).isEqualTo(pixKeyUpdateDto.getAccountType());
        Assertions.assertThat(responseDto.getAgency()).isEqualTo(pixKeyUpdateDto.getAgency());
        Assertions.assertThat(responseDto.getAccount()).isEqualTo(pixKeyUpdateDto.getAccount());
        Assertions.assertThat(responseDto.getName()).isEqualTo(pixKeyUpdateDto.getName());
        Assertions.assertThat(responseDto.getLastName()).isEqualTo(pixKeyUpdateDto.getLastName());
    }

    @Test
    void listByCombinedResources_ReturnListPixKeyResponseDto_WhenSuccessful(){
        BDDMockito.when(pixKeyService.listByCombinedResources(ArgumentMatchers.any(KeyType.class),
                        ArgumentMatchers.any(Integer.class),
                        ArgumentMatchers.any(Integer.class),
                        ArgumentMatchers.any(String.class)))
                .thenReturn(PixKeyDataForTest.createPixKeyResponseDtoValidList());

        List<PixKeyResponseDto> pixKeyResponseDtoValid = List.of(PixKeyDataForTest.createPixKeyResponseDtoValid());

        List<PixKeyResponseDto> responseDtoList = pixKeyController.listByCombinedResources(KeyType.EMAIL, 1, 1, "Testerson").getBody();

        Assertions.assertThat(responseDtoList).isNotNull();
        Assertions.assertThat(responseDtoList).isNotEmpty();

        Assertions.assertThat(responseDtoList.get(0).getId()).isEqualTo(pixKeyResponseDtoValid.get(0).getId());
        Assertions.assertThat(responseDtoList.get(0).getKeyType()).isEqualTo(pixKeyResponseDtoValid.get(0).getKeyType());
        Assertions.assertThat(responseDtoList.get(0).getKeyValue()).isEqualTo(pixKeyResponseDtoValid.get(0).getKeyValue());
        Assertions.assertThat(responseDtoList.get(0).getAccountType()).isEqualTo(pixKeyResponseDtoValid.get(0).getAccountType());
        Assertions.assertThat(responseDtoList.get(0).getAgency()).isEqualTo(pixKeyResponseDtoValid.get(0).getAgency());
        Assertions.assertThat(responseDtoList.get(0).getAccount()).isEqualTo(pixKeyResponseDtoValid.get(0).getAccount());
        Assertions.assertThat(responseDtoList.get(0).getName()).isEqualTo(pixKeyResponseDtoValid.get(0).getName());
        Assertions.assertThat(responseDtoList.get(0).getLastName()).isEqualTo(pixKeyResponseDtoValid.get(0).getLastName());

    }
}