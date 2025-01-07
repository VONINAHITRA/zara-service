package com.zara.zaraservice.validation;

import com.zara.zaraservice.constants.ErrorMessages;
import com.zara.zaraservice.exception.customexceptions.NotFoundException;
import com.zara.zaraservice.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class ClientValidation {

    private final UserRepository userRepository;

    public ClientValidation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void checkUserCompte(Long id){
        userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.USER_ID_REQUIRED));
    }
}
