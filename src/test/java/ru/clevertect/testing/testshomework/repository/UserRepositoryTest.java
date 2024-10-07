package ru.clevertect.testing.testshomework.repository;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.clevertect.testing.testshomework.entity.UserEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserRepositoryTest {

    private final UserRepository userRepository = new UserRepository();

    @ParameterizedTest
    @ValueSource(strings = {"Name", "NewName", "AwesomeName"})
    void save_parameterizedTest(String name) {
        UserEntity entity = new UserEntity();
        entity.setName(name);
        UserEntity saved = userRepository.save(entity);

        assertNotNull(saved.getId());
        assertEquals(name, saved.getName());
    }
}