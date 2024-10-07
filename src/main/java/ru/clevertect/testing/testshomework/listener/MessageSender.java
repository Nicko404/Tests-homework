package ru.clevertect.testing.testshomework.listener;

import org.springframework.stereotype.Component;
import ru.clevertect.testing.testshomework.entity.UserEntity;

@Component
public class MessageSender {

    //Pattern Observer implemented
    public void send(String message, UserEntity user) {
        System.out.println(user.getName() + ": " + message);
    }
}
