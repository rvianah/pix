package com.itau.pix.desafio.factory;

import com.itau.pix.desafio.domain.PixKey;
import com.itau.pix.desafio.exception.ValidationRequestException;
import com.itau.pix.desafio.repository.PixKeyRepository;

import java.util.InputMismatchException;
import java.util.List;

public class PixKeyCnpj implements PixKeyType{
    public final int MAX_KEYS_PJ = 20;

    @Override
    public void isValid(String keyValue, PixKeyRepository pixKeyRepository, Integer account) {
        if (!pixKeyRepository.findByKeyValue(keyValue).isEmpty())
            throw new ValidationRequestException("PixKey already in usage");

        if ((keyValue.equals("00000000000000") || keyValue.equals("11111111111111") ||
                keyValue.equals("22222222222222") || keyValue.equals("33333333333333") ||
                keyValue.equals("44444444444444") || keyValue.equals("55555555555555") ||
                keyValue.equals("66666666666666") || keyValue.equals("77777777777777") ||
                keyValue.equals("88888888888888") || keyValue.equals("99999999999999") ||
                (keyValue.length() != 14)))
            throw new ValidationRequestException("PixKey cnpj not valid");

        char dig13, dig14;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 2;
            for (i=11; i>=0; i--) {
                num = (keyValue.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';
            else dig13 = (char)((11-r) + 48);

            sm = 0;
            peso = 2;
            for (i=12; i>=0; i--) {
                num = (keyValue.charAt(i)- 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else dig14 = (char)((11-r) + 48);

            if (!((dig13 == keyValue.charAt(12)) && (dig14 == keyValue.charAt(13))))
                throw new ValidationRequestException("PixKey cnpj not valid");

            List<PixKey> pixKeyList = pixKeyRepository.findByAccount(account);
            if (pixKeyList.size() > MAX_KEYS_PJ)
                throw new ValidationRequestException("Pixkey limit for this account is 5");

        } catch (InputMismatchException erro) {
            throw new ValidationRequestException("PixKey cnpj not valid");
        }
    }
}
