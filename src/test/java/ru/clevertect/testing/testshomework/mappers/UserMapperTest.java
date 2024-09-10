package ru.clevertect.testing.testshomework.mappers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.clevertect.testing.testshomework.domain.User;
import ru.clevertect.testing.testshomework.entity.UserEntity;
import ru.clevertect.testing.testshomework.utils.TestData;

import java.util.List;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    void entityToDomain() {
        UserEntity entity = TestData.getUserEntity();
        User expected = TestData.castUserEntity(entity);

        User actual = mapper.entityToDomain(entity);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void domainToEntity() {
        UserEntity expected = TestData.getUserEntity();
        User domain = TestData.castUserEntity(expected);

        UserEntity actual = mapper.domainToEntity(domain);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void domainToEntities() {
        List<UserEntity> expected = TestData.getUserEntities();
        List<User> domains = TestData.getUserDomains(expected);

        List<UserEntity> actual = mapper.domainToEntities(domains);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void entityToDomains() {
        List<UserEntity> entities = TestData.getUserEntities();
        List<User> expected = TestData.getUserDomains(entities);

        List<User> actual = mapper.entityToDomains(entities);

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}