package ru.clevertect.testing.testshomework.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class User {

    private UUID id;

    private String name;

    private String password;
}
