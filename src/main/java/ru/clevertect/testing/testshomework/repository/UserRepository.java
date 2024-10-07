package ru.clevertect.testing.testshomework.repository;

import org.springframework.stereotype.Repository;
import ru.clevertect.testing.testshomework.entity.UserEntity;
import ru.clevertect.testing.testshomework.exception.NoSuchUserException;
import ru.clevertect.testing.testshomework.exception.UserExistsException;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository {


    private final List<UserEntity> db = new ArrayList<>();

    public Optional<UserEntity> findById(UUID id) {
        return db.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public List<UserEntity> findAll() {
        return new ArrayList<>(db);
    }

    public UserEntity save(UserEntity user) {
        boolean isPresent = db.stream()
                .anyMatch(u -> u.getName().equals(user.getName()) && u.getPassword().equals(user.getPassword()));
        if (isPresent) throw new UserExistsException(MessageFormat.format("User {0} already exists", user.getName()));
        user.setId(UUID.randomUUID());
        db.add(user);
        return user;
    }

    public UserEntity update(UUID id, UserEntity user) {
        UserEntity existedUser = findById(id)
                .orElseThrow(() -> NoSuchUserException.byId(id));

        existedUser.setName(user.getName());
        existedUser.setPassword(user.getPassword());
        existedUser.setPersonalData(user.getPersonalData());

        return existedUser;
    }

    public void deleteById(UUID id) {
        db.removeIf(u -> u.getId().equals(id));
    }


}
