package com.compassuol.sp.challenge.msuser.web.controller;

import com.compassuol.sp.challenge.msuser.domain.model.User;
import com.compassuol.sp.challenge.msuser.domain.service.UserService;
import com.compassuol.sp.challenge.msuser.web.dto.UserCreateDto;
import com.compassuol.sp.challenge.msuser.web.dto.UserResponseDto;
import com.compassuol.sp.challenge.msuser.web.dto.UserUpdateDto;
import com.compassuol.sp.challenge.msuser.web.dto.mapper.UserMapper;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody @Valid UserCreateDto dto){
        User createdUser = service.create(UserMapper.toUser(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(createdUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable("id") Long id){
        User foundUser = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toDto(foundUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUserInfo(@PathVariable("id") Long id, @RequestBody @Valid UserUpdateDto dto){
        User updatedUser = service.updateInfo(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toDto(updatedUser));
    }
}
