package ru.clevertect.testing.testshomework.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertect.testing.testshomework.entity.UserEntity;
import ru.clevertect.testing.testshomework.exception.NoSuchUserException;
import ru.clevertect.testing.testshomework.listener.MessageSender;
import ru.clevertect.testing.testshomework.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MessageSender messageSender;

    public UserEntity getById(UUID id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> NoSuchUserException.byId(id));
        messageSender.send("Get by id method", userEntity);
        return userEntity;
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public UserEntity save(UserEntity user) {
        UserEntity userEntity = userRepository.save(user);
        messageSender.send("Save user", userEntity);
        return userEntity;
    }

    public UserEntity update(UUID id, UserEntity user) {
        UserEntity update = userRepository.update(id, user);
        messageSender.send("Update user", update);
        return update;
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}
