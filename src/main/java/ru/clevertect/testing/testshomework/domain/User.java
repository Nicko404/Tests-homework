package ru.clevertect.testing.testshomework.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode
public class User {

    private UUID id;

    private String name;

    private String password;
}
