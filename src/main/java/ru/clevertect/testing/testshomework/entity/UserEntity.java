package ru.clevertect.testing.testshomework.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode
public class UserEntity {

    private UUID id;

    private String name;

    private String password;

    private String personalData;
}
