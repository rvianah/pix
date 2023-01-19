package com.itau.pix.desafio.factory;

import com.itau.pix.desafio.domain.PixKey;
import com.itau.pix.desafio.exception.ValidationRequestException;
import com.itau.pix.desafio.repository.PixKeyRepository;

import java.util.List;

public class PixKeyTypeEmail implements PixKeyType {
    public final int MAX_KEYS_PF = 5;
    @Override
    public void isValid(String keyValue, PixKeyRepository pixKeyRepository, Integer account) {
        if (!pixKeyRepository.findByKeyValue(keyValue).isEmpty())
            throw new ValidationRequestException("PixKey already in usage");

        List<PixKey> pixKeyList = pixKeyRepository.findByAccount(account);
        if (keyValue.length() > 77 || !keyValue.matches("^(.+)@(.+)$"))
            throw new ValidationRequestException("PixKey email not valid");

        if (pixKeyList.size() > MAX_KEYS_PF)
            throw new ValidationRequestException("Pixkey limit for this account is 5");
    }
}
