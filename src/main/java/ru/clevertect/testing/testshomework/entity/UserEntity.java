package ru.clevertect.testing.testshomework.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class UserEntity {

    private UUID id;

    private String name;

    private String password;

    private String personalData;
}
