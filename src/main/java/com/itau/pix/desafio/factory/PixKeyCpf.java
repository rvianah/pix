package com.itau.pix.desafio.factory;

import com.itau.pix.desafio.domain.PixKey;
import com.itau.pix.desafio.exception.ValidationRequestException;
import com.itau.pix.desafio.repository.PixKeyRepository;

import java.util.InputMismatchException;
import java.util.List;

public class PixKeyCpf implements PixKeyType{
    public final int MAX_KEYS_PF = 5;
    @Override
    public void isValid(String keyValue, PixKeyRepository pixKeyRepository, Integer account) {
        if (!pixKeyRepository.findByKeyValue(keyValue).isEmpty())
            throw new ValidationRequestException("PixKey already in usage");

        if ((keyValue.equals("00000000000") ||
                keyValue.equals("11111111111") ||
                keyValue.equals("22222222222") || keyValue.equals("33333333333") ||
                keyValue.equals("44444444444") || keyValue.equals("55555555555") ||
                keyValue.equals("66666666666") || keyValue.equals("77777777777") ||
                keyValue.equals("88888888888") || keyValue.equals("99999999999") ||
                (keyValue.length() != 11)))
            throw new ValidationRequestException("PixKey cpf not valid");

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                num = (keyValue.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);

            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (keyValue.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            if (!(dig10 == keyValue.charAt(9)) && (dig11 == keyValue.charAt(10)))
                throw new ValidationRequestException("PixKey cpf not valid");

            List<PixKey> pixKeyList = pixKeyRepository.findByAccount(account);
            if (pixKeyList.size() > MAX_KEYS_PF)
                throw new ValidationRequestException("Pixkey limit for this account is 5");

        } catch (InputMismatchException erro) {
            throw new ValidationRequestException("PixKey cpf not valid");
        }
    }
}

