package com.itau.pix.desafio.domain;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pixkey", schema = "pix")
public class PixKey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pixkey", nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "key_type", nullable = false, length = 9)
    @Enumerated(EnumType.STRING)
    private KeyType keyType;

    @Column(name = "key_value", nullable = false, length = 77)
    private String keyValue;

    @Column(name = "account_type", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "agency", nullable = false)
    private Integer agency;

    @Column(name = "account", nullable = false)
    private Integer account;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "last_name", length = 45)
    private String lastName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "disabled_at")
    private LocalDateTime disabledAt;

    @Column(name = "account_status")
    private Boolean accountStatus;

}
