package com.itau.pix.desafio.factory;

import com.itau.pix.desafio.repository.PixKeyRepository;

public interface PixKeyType {

    void isValid(String keyValue, PixKeyRepository pixKeyRepository, Integer account);
}
