package ru.clevertect.testing.testshomework.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode
public class UserEntity {

    private UUID id;

    private String name;

    private String password;

    private String personalData;


    //Pattern Builder implemented
    public class Builder {

        private Builder() {}

        public static Builder builder() {
            return new UserEntity().new Builder();
        }

        public Builder setId(UUID id) {
            UserEntity.this.id = id;

            return this;
        }

        public Builder setName(String name) {
            UserEntity.this.name = name;

            return this;
        }

        public Builder setPassword(String password) {
            UserEntity.this.password = password;

            return this;
        }

        public Builder setPersonalData(String personalData) {
            UserEntity.this.personalData = personalData;

            return this;
        }

        public UserEntity build() {
            return UserEntity.this;
        }
    }
}
