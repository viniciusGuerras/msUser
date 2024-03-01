package com.compassuol.sp.challenge.msuser.repository;


import com.compassuol.sp.challenge.msuser.domain.jwt.JwtAuthenticationFilter;
import com.compassuol.sp.challenge.msuser.domain.model.User;
import com.compassuol.sp.challenge.msuser.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static com.compassuol.sp.challenge.msuser.commons.UserConstants.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTests {

    @Mock
    private  UserRepository userRepository;

    @Mock
    private UserRepository userRepositoryMock;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    public void saveUser(){
        when(userRepository.save(any())).thenReturn(USER);
        User user = userRepository.save(USER);
        assertEquals(user,USER);
    }

    @Test
    public void findUser(){
        when(userRepository.findById(any())).thenReturn(Optional.of(USER));
        Optional<User> user = userRepository.findById(USER.getId());
        assertEquals(user, Optional.of(USER));
    }

    @Test
    public void findByEmail(){
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(USER));
        Optional<User> user = userRepository.findByEmail(USER.getEmail());
        assertEquals(user, Optional.of(USER));
    }
}
