package ru.clevertect.testing.testshomework.utils;

import ru.clevertect.testing.testshomework.domain.User;
import ru.clevertect.testing.testshomework.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestData {

    public static UserEntity getUserEntity() {
        UserEntity entity = new UserEntity();
        entity.setId(UUID.randomUUID());
        entity.setName("Some Name");
        entity.setPassword("Some Password");
        return entity;
    }

    public static List<UserEntity> getUserEntities() {
        List<UserEntity> userEntities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserEntity entity = TestData.getUserEntity();
            entity.setName("Some Name " + i);
            entity.setPassword("Some Password " + i);
            userEntities.add(entity);
        }
        return userEntities;
    }

    public static List<User> getUserDomains(List<UserEntity> userEntities) {
        return userEntities.stream()
                .map(TestData::castUserEntity)
                .toList();
    }

    public static User castUserEntity(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setName(entity.getName());
        user.setPassword(entity.getPassword());
        return user;
    }
}
