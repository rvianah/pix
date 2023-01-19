package com.itau.pix.desafio.repository;

import com.itau.pix.desafio.domain.KeyType;
import com.itau.pix.desafio.domain.PixKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PixKeyRepository extends JpaRepository<PixKey, UUID> {

    List<PixKey> findByKeyValue(String keyValue);

    List<PixKey> findByAccount(Integer account);

    @Query (value = "SELECT pixkey FROM PixKey pixkey" +
            " WHERE (:keyType IS NULL OR pixkey.keyType = :keyType)" +
            " AND (:agency IS NULL OR pixkey.agency = :agency)" +
            " AND (:account IS NULL OR pixkey.account = :account)" +
            " AND (:name IS NULL OR pixkey.name = :name)")
    List<PixKey> findByCombinedResources(
            @Param("keyType") KeyType keyType,
            @Param("agency") Integer agency,
            @Param("account") Integer account,
            @Param("name") String name
    );
}
