package com.compassuol.sp.challenge.msuser.domain.service;

import com.compassuol.sp.challenge.msuser.domain.exceptions.EntityNotFoundException;
import com.compassuol.sp.challenge.msuser.domain.exceptions.UniqueFieldValidationException;
import com.compassuol.sp.challenge.msuser.domain.model.User;
import com.compassuol.sp.challenge.msuser.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Transactional
    public User create(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        catch (DataIntegrityViolationException ex)
        {
            throw new UniqueFieldValidationException("Cpf or Email already on system.");
        }
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID:" + id + " not found"));
    }

    @Transactional
    public void updateInfo(Long id, User newUser) {
        User oldUser = findById(id);
        oldUser.setFirstName(newUser.getFirstName());
        oldUser.setLastName(newUser.getLastName());
        oldUser.setCpf(newUser.getCpf());
        oldUser.setBirthdate(newUser.getBirthdate());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setCep(newUser.getCep());
        oldUser.setActive(newUser.getActive());
    }

    @Transactional
    public void updatePassword(Long id, User user) {
        User foundUser = findById(id);
        foundUser.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
