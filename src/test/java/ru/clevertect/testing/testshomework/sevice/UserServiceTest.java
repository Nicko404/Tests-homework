package ru.clevertect.testing.testshomework.sevice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ru.clevertect.testing.testshomework.entity.UserEntity;
import ru.clevertect.testing.testshomework.exception.NoSuchUserException;
import ru.clevertect.testing.testshomework.exception.UserExistsException;
import ru.clevertect.testing.testshomework.repository.UserRepository;
import ru.clevertect.testing.testshomework.utils.TestData;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class UserServiceTest {

    UserRepository userRepository;

    UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void whenGetById_thenGetUser() {
        UserEntity entity = TestData.getUserEntity();
        UUID id = entity.getId();

        Mockito.when(userRepository.findById(id))
                .thenReturn(Optional.of(entity));

        UserEntity actual = userService.getById(id);

        Assertions.assertThat(actual).isEqualTo(entity);
    }

    @Test
    void whenGetById_thenThrowException() {
        UUID id = UUID.randomUUID();

        Mockito.when(userRepository.findById(id))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> userService.getById(id)).isInstanceOf(NoSuchUserException.class);
    }

    @Test
    void whenGetAll_thenGetAllUsers() {
        List<UserEntity> expected = TestData.getUserEntities();

        Mockito.when(userRepository.findAll())
                .thenReturn(expected);

        List<UserEntity> actual = userService.getAll();

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenCreate_thenCreateUser() {
        UserEntity entityToSave = TestData.getUserEntity();
        entityToSave.setId(null);
        UserEntity expected = TestData.getUserEntity();

        Mockito.when(userRepository.save(entityToSave))
                .thenReturn(expected);

        UserEntity actual = userService.save(entityToSave);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenCreateExistedUser_thenThrowException() {
        UserEntity entityToSave = TestData.getUserEntity();

        Mockito.when(userRepository.save(entityToSave))
                .thenThrow(UserExistsException.class);

        Assertions.assertThatThrownBy(() -> userService.save(entityToSave)).isInstanceOf(UserExistsException.class);
    }

    @Test
    void whenUpdate_thenUpdateUser() {
        UserEntity entityToUpdate = TestData.getUserEntity();
        UUID id = entityToUpdate.getId();
        entityToUpdate.setName("New Name");
        entityToUpdate.setPassword("New Password");

        Mockito.when(userRepository.update(id, entityToUpdate))
                .thenReturn(entityToUpdate);

        UserEntity actual = userService.update(id, entityToUpdate);

        Assertions.assertThat(actual).isEqualTo(entityToUpdate);
    }

    @Test
    void whenUpdateNotExisted_thenThrowException() {
        UserEntity entityToUpdate = TestData.getUserEntity();
        UUID id = entityToUpdate.getId();

        Mockito.when(userRepository.update(id, entityToUpdate))
                .thenThrow(NoSuchUserException.class);

        Assertions.assertThatThrownBy(() -> userService.update(id, entityToUpdate))
                .isInstanceOf(NoSuchUserException.class);
    }

    @Test
    void whenDeleteById_thenDeleteUser() {
        userService.deleteById(UUID.randomUUID());

        Mockito.verify(userRepository, Mockito.times(1)).deleteById(Mockito.any());
    }
}