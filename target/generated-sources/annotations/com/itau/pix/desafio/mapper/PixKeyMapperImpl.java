package com.itau.pix.desafio.mapper;

import com.itau.pix.desafio.domain.PixKey;
import com.itau.pix.desafio.dto.PixKeyResponseDto;
import com.itau.pix.desafio.dto.PixKeyUpdateDto;
import com.itau.pix.desafio.request.PixKeyPostRequestBody;
import com.itau.pix.desafio.request.PixKeyPutRequestBody;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-19T09:43:35-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.16 (Eclipse Adoptium)"
)
@Component
public class PixKeyMapperImpl extends PixKeyMapper {

    @Override
    public PixKey pixKeyPostRequestToPixKey(PixKeyPostRequestBody pixKeyPostRequestBody) {
        if ( pixKeyPostRequestBody == null ) {
            return null;
        }

        PixKey.PixKeyBuilder pixKey = PixKey.builder();

        if ( pixKeyPostRequestBody.getCreatedAt() != null ) {
            pixKey.createdAt( pixKeyPostRequestBody.getCreatedAt() );
        }
        else {
            pixKey.createdAt( java.time.LocalDateTime.now() );
        }
        pixKey.keyType( pixKeyPostRequestBody.getKeyType() );
        pixKey.keyValue( pixKeyPostRequestBody.getKeyValue() );
        pixKey.accountType( pixKeyPostRequestBody.getAccountType() );
        pixKey.agency( pixKeyPostRequestBody.getAgency() );
        pixKey.account( pixKeyPostRequestBody.getAccount() );
        pixKey.name( pixKeyPostRequestBody.getName() );
        pixKey.lastName( pixKeyPostRequestBody.getLastName() );

        return pixKey.build();
    }

    @Override
    public PixKeyUpdateDto pixKeyUpdateRequestToPixKeyUpdateDto(PixKeyPutRequestBody pixKeyPutRequestBody) {
        if ( pixKeyPutRequestBody == null ) {
            return null;
        }

        PixKeyUpdateDto.PixKeyUpdateDtoBuilder pixKeyUpdateDto = PixKeyUpdateDto.builder();

        pixKeyUpdateDto.accountType( pixKeyPutRequestBody.getAccountType() );
        pixKeyUpdateDto.agency( pixKeyPutRequestBody.getAgency() );
        pixKeyUpdateDto.account( pixKeyPutRequestBody.getAccount() );
        pixKeyUpdateDto.name( pixKeyPutRequestBody.getName() );
        pixKeyUpdateDto.lastName( pixKeyPutRequestBody.getLastName() );

        return pixKeyUpdateDto.build();
    }

    @Override
    public PixKey pixKeyUpdateDtoToPixkey(PixKeyUpdateDto pixKeyUpdateDto, PixKey pixKey) {
        if ( pixKeyUpdateDto == null ) {
            return pixKey;
        }

        pixKey.setAccountType( pixKeyUpdateDto.getAccountType() );
        pixKey.setAgency( pixKeyUpdateDto.getAgency() );
        pixKey.setAccount( pixKeyUpdateDto.getAccount() );
        pixKey.setName( pixKeyUpdateDto.getName() );
        pixKey.setLastName( pixKeyUpdateDto.getLastName() );

        return pixKey;
    }

    @Override
    public PixKeyUpdateDto pixKeyToPixKeyDto(PixKey pixKey, PixKeyUpdateDto pixKeyUpdateDto) {
        if ( pixKey == null ) {
            return pixKeyUpdateDto;
        }

        pixKeyUpdateDto.setId( pixKey.getId() );
        pixKeyUpdateDto.setKeyType( pixKey.getKeyType() );
        pixKeyUpdateDto.setKeyValue( pixKey.getKeyValue() );
        pixKeyUpdateDto.setAccountType( pixKey.getAccountType() );
        pixKeyUpdateDto.setAgency( pixKey.getAgency() );
        pixKeyUpdateDto.setAccount( pixKey.getAccount() );
        pixKeyUpdateDto.setName( pixKey.getName() );
        pixKeyUpdateDto.setLastName( pixKey.getLastName() );
        pixKeyUpdateDto.setCreatedAt( pixKey.getCreatedAt() );

        return pixKeyUpdateDto;
    }

    @Override
    public PixKeyResponseDto pixKeyToPixKeyResponseDto(PixKey pixKey) {
        if ( pixKey == null ) {
            return null;
        }

        PixKeyResponseDto.PixKeyResponseDtoBuilder pixKeyResponseDto = PixKeyResponseDto.builder();

        pixKeyResponseDto.id( pixKey.getId() );
        pixKeyResponseDto.keyType( pixKey.getKeyType() );
        pixKeyResponseDto.keyValue( pixKey.getKeyValue() );
        pixKeyResponseDto.accountType( pixKey.getAccountType() );
        pixKeyResponseDto.agency( pixKey.getAgency() );
        pixKeyResponseDto.account( pixKey.getAccount() );
        pixKeyResponseDto.name( pixKey.getName() );
        pixKeyResponseDto.lastName( pixKey.getLastName() );
        if ( pixKey.getCreatedAt() != null ) {
            pixKeyResponseDto.createdAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( pixKey.getCreatedAt() ) );
        }
        if ( pixKey.getDisabledAt() != null ) {
            pixKeyResponseDto.disabledAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( pixKey.getDisabledAt() ) );
        }

        return pixKeyResponseDto.build();
    }
}
