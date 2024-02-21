package com.compassuol.sp.challenge.msuser.domain.service;

import com.compassuol.sp.challenge.msuser.domain.exceptions.EntityNotFoundException;
import com.compassuol.sp.challenge.msuser.domain.exceptions.UniqueFieldValidationException;
import com.compassuol.sp.challenge.msuser.domain.model.User;
import com.compassuol.sp.challenge.msuser.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
  
    private final UserRepository userRepository;

    @Transactional
    public User create(User user){
        try {
            return userRepository.save(user);
        }
        catch (DataIntegrityViolationException ex)
        {
            throw new UniqueFieldValidationException("Cpf or Email already on system.");
        }
    }

    @Transactional
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID:" + id + " not found"));
    }

    @Transactional
    public User updateInfo(Long id, User newUser) {
        User oldUser = findById(id);
        oldUser.setFirstName(newUser.getFirstName());
        oldUser.setLastName(newUser.getLastName());
        oldUser.setCpf(newUser.getCpf());
        oldUser.setBirthdate(newUser.getBirthdate());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setCep(newUser.getCep());
        oldUser.setActive(newUser.getActive());
        return oldUser;

    }

    @Transactional
    public User updatePassword(Long id, User user) {
        User foundUser = findById(id);
        foundUser.setPassword(user.getPassword());
        return foundUser;
    }
}
