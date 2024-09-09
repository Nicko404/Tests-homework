package ru.clevertect.testing.testshomework.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertect.testing.testshomework.entity.UserEntity;
import ru.clevertect.testing.testshomework.exception.NoSuchUserException;
import ru.clevertect.testing.testshomework.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity getById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> NoSuchUserException.byId(id));
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity update(UUID id, UserEntity user) {
        return userRepository.update(id, user);
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}
