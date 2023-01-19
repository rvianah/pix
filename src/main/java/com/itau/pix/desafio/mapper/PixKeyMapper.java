package com.itau.pix.desafio.mapper;

import com.itau.pix.desafio.domain.PixKey;
import com.itau.pix.desafio.dto.PixKeyResponseDto;
import com.itau.pix.desafio.dto.PixKeyUpdateDto;
import com.itau.pix.desafio.request.PixKeyPostRequestBody;
import com.itau.pix.desafio.request.PixKeyPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PixKeyMapper {
    public static final PixKeyMapper INSTANCE = Mappers.getMapper(PixKeyMapper.class);

    @Mapping( target = "createdAt",  defaultExpression = "java( java.time.LocalDateTime.now() )")
    public abstract PixKey pixKeyPostRequestToPixKey(PixKeyPostRequestBody pixKeyPostRequestBody);

    @Mapping(target = "id", ignore = true)
    public abstract PixKeyUpdateDto pixKeyUpdateRequestToPixKeyUpdateDto(PixKeyPutRequestBody pixKeyPutRequestBody);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "keyType", ignore = true)
    @Mapping(target = "keyValue", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract PixKey pixKeyUpdateDtoToPixkey(PixKeyUpdateDto pixKeyUpdateDto, @MappingTarget PixKey pixKey);

    public abstract PixKeyUpdateDto pixKeyToPixKeyDto(PixKey pixKey, @MappingTarget PixKeyUpdateDto pixKeyUpdateDto);

    public abstract PixKeyResponseDto pixKeyToPixKeyResponseDto(PixKey pixKey);

}
