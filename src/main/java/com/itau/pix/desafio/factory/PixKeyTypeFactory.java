package com.itau.pix.desafio.factory;

import com.itau.pix.desafio.domain.KeyType;

public class PixKeyTypeFactory {

    public static PixKeyType createAccountType(KeyType keyType){

        PixKeyType pixKeyType = null;

        switch (keyType){
            case EMAIL: pixKeyType = new PixKeyTypeEmail();
            break;
            case CPF: pixKeyType = new PixKeyCpf();
            break;
            case CNPJ: pixKeyType = new PixKeyCnpj();
        }
        return pixKeyType;
    }
}
