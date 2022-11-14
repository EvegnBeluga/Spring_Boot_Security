package ru.kata.spring.boot_security.demo.util.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositpries.UserRepository;


@Component
public class EmailAlreadyExistsValidator implements Validator {

    private final UserRepository userRepository;

    @Autowired
    public EmailAlreadyExistsValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            if (userRepository.findByEmail(user.getEmail()).get().getId() != user.getId()) {
                errors.rejectValue("email", "", "Юзер с таким имэйлом уже есть!");
            }
        }

    }


}
