package com.itau.pix.desafio.service;

import com.itau.pix.desafio.domain.KeyType;
import com.itau.pix.desafio.domain.PixKey;
import com.itau.pix.desafio.dto.PixKeyResponseDto;
import com.itau.pix.desafio.dto.PixKeyUpdateDto;
import com.itau.pix.desafio.exception.NotFoundRequestException;
import com.itau.pix.desafio.exception.ValidationRequestException;
import com.itau.pix.desafio.factory.PixKeyType;
import com.itau.pix.desafio.factory.PixKeyTypeFactory;
import com.itau.pix.desafio.mapper.PixKeyMapper;
import com.itau.pix.desafio.repository.PixKeyRepository;
import com.itau.pix.desafio.request.PixKeyPostRequestBody;
import com.itau.pix.desafio.request.PixKeyPutRequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PixKeyService {

    private final PixKeyRepository pixKeyRepository;

    public UUID save(PixKeyPostRequestBody pixKeyPostRequestBody) {
        log.info("Dados recebidos: PixKeyPostRequestBody={}", pixKeyPostRequestBody);
        try {
            PixKeyType pixKeyType = PixKeyTypeFactory.createAccountType(pixKeyPostRequestBody.getKeyType());
            validatePixKeyType(pixKeyType, pixKeyPostRequestBody.getKeyValue(), pixKeyPostRequestBody.getAccount());
            PixKey pixKey = PixKeyMapper.INSTANCE.pixKeyPostRequestToPixKey(pixKeyPostRequestBody);
            return pixKeyRepository.save(pixKey).getId();
        } catch (Exception e) {
            log.error("Erro ao salvar registro, errorMessage={}, exception={}", e.getMessage(), e);
            throw e;
        }

    }

    public PixKeyUpdateDto update (PixKeyPutRequestBody pixKeyPutRequestBody) {
        log.info("Dados recebidos: PixKeyPutRequestBody={}", pixKeyPutRequestBody);
        try {
            PixKey pixKey = findByIdOrThrowBadRequestException(pixKeyPutRequestBody.getId());
            PixKeyUpdateDto pixKeyUpdateDto = PixKeyMapper.INSTANCE.pixKeyUpdateRequestToPixKeyUpdateDto(pixKeyPutRequestBody);
            pixKey = PixKeyMapper.INSTANCE.pixKeyUpdateDtoToPixkey(pixKeyUpdateDto, pixKey);
            validatePixKeyUpdate(pixKey);
            pixKeyRepository.save(pixKey);
            return PixKeyMapper.INSTANCE.pixKeyToPixKeyDto(pixKey, pixKeyUpdateDto);
        } catch (Exception e) {
            log.error("Erro ao salvar registro, errorMessage={}, exception={}", e.getMessage(), e);
            throw e;
        }
    }

    public PixKeyResponseDto findById(UUID id) {
        log.info("Dados recebidos: ID={}", id);
        try {
            PixKey pixKey = findByIdOrThrowBadRequestException(id);
            PixKeyResponseDto pixKeyResponseDto = PixKeyMapper.INSTANCE.pixKeyToPixKeyResponseDto(pixKey);
            fillNullableFields(pixKeyResponseDto);
            return pixKeyResponseDto;
        } catch (Exception e) {
            log.error("Erro ao recuperar registro, errorMessage={}, exception={}", e.getMessage(), e);
            throw e;
        }
    }

    public List<PixKeyResponseDto> listByCombinedResources(KeyType keyType, Integer agency, Integer account, String name) {
        log.info("Dados recebidos: KeyType={}, Agency={}, Account={}, Name={}", keyType, agency, account, name);
        try {
            List<PixKey> pixKeyList = pixKeyRepository.findByCombinedResources(keyType, agency, account, name);
            validatePixKeyEmptyList(pixKeyList);
            List<PixKeyResponseDto> pixKeyResponseDtoList = pixKeyList.stream().map(pixKey -> PixKeyMapper.INSTANCE.pixKeyToPixKeyResponseDto(pixKey)).collect(Collectors.toList());
            pixKeyResponseDtoList.stream().forEach((item -> fillNullableFields(item)));
            return pixKeyResponseDtoList;
        } catch (Exception e){
            log.error("Erro ao recuperar lista de registros, errorMessage={}, exception={}", e.getMessage(), e);
            throw e;
        }
    }

    private static void fillNullableFields(PixKeyResponseDto pixKeyResponseDto){
        if(Objects.isNull(pixKeyResponseDto.getLastName()))
            pixKeyResponseDto.setLastName("");

        if(Objects.isNull(pixKeyResponseDto.getDisabledAt()))
            pixKeyResponseDto.setDisabledAt("");

        if(Objects.nonNull(pixKeyResponseDto.getCreatedAt())){
            LocalDateTime dateTime = LocalDateTime.parse(pixKeyResponseDto.getCreatedAt());
            pixKeyResponseDto.setCreatedAt(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(dateTime));
        } else
            pixKeyResponseDto.setCreatedAt("");
    }

    private void validatePixKeyType(PixKeyType pixKeyType, String keyValue, Integer account){
        pixKeyType.isValid(keyValue, pixKeyRepository, account);
    }

    private void validatePixKeyUpdate(PixKey pixKey) {
        if (Objects.nonNull(pixKey.getAccountStatus()) && !pixKey.getAccountStatus())
            throw new ValidationRequestException("PixKey inactive");

        if (pixKey.getAgency() > 9999)
            throw new ValidationRequestException("PixKey agency range is 0 to 9999");

        if (pixKey.getAccount() > 99999999)
            throw new ValidationRequestException("PixKey account range is 0 to 99999999");

        if (pixKey.getName().length() > 30)
            throw new ValidationRequestException("PixKey name maximum size is 30");

        if(Objects.nonNull(pixKey.getLastName()) && pixKey.getLastName().length() > 45)
            throw new ValidationRequestException("PixKey lastname maximum size is 45");
    }

    private static void validatePixKeyEmptyList(List<PixKey> pixKeyList) {
        if (pixKeyList.isEmpty())
            throw new NotFoundRequestException("PixKey not found");
    }

    private PixKey findByIdOrThrowBadRequestException(UUID id){
        return pixKeyRepository.findById(id).orElseThrow(() -> new NotFoundRequestException("PixKey ID not found"));
    }
}
