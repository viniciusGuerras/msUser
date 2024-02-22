package com.compassuol.sp.challenge.msuser.web.controller;

import com.compassuol.sp.challenge.msuser.domain.service.AuthService;
import com.compassuol.sp.challenge.msuser.jwt.dto.AuthenticationResponseDto;
import com.compassuol.sp.challenge.msuser.web.dto.LoginRequestDto;
import com.compassuol.sp.challenge.msuser.web.dto.mapper.UserMapper;
import com.compassuol.sp.challenge.msuser.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/login")
@Tag(name = "Authentication Controller", description = "Endpoint for authenticating users")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "User Login",
            description = "Authenticate user credentials and generate an authentication token",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User authenticated successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request body",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PostMapping
    public ResponseEntity<AuthenticationResponseDto> userLogin(@Valid @RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(authService.authenticate(UserMapper.toUser(dto)));
    }
}
