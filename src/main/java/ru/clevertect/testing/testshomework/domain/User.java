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

    //Pattern Builder implemented
    public class Builder {

        private Builder() {}

        public static Builder builder() {
            return new User().new Builder();
        }

        public Builder setId(UUID id) {
            User.this.id = id;

            return this;
        }

        public Builder setName(String name) {
            User.this.name = name;

            return this;
        }

        public Builder setPassword(String password) {
            User.this.password = password;

            return this;
        }

        public User build() {
            return User.this;
        }
    }
}
