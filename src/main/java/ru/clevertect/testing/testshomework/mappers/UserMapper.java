package ru.clevertect.testing.testshomework.mappers;

import org.mapstruct.Mapper;
import ru.clevertect.testing.testshomework.domain.User;
import ru.clevertect.testing.testshomework.entity.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User entityToDomain(UserEntity userEntity);

    UserEntity domainToEntity(User user);

    List<UserEntity> domainToEntities(List<User> users);

    List<User> entityToDomains(List<UserEntity> userEntities);
}
