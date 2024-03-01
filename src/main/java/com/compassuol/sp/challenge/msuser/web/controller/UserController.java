package com.compassuol.sp.challenge.msuser.web.controller;


import com.compassuol.sp.challenge.msuser.domain.model.User;
import com.compassuol.sp.challenge.msuser.domain.service.UserService;
import com.compassuol.sp.challenge.msuser.web.dto.*;
import com.compassuol.sp.challenge.msuser.web.dto.mapper.UserMapper;
import com.compassuol.sp.challenge.msuser.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
@Tag(name = "User Controller", description = "Endpoints for managing users")
public class UserController {

    private final UserService service;

    @Operation(
            summary = "Create a new User",
            description = "Creates a new User on the database",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User created successfully"
                            ,content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "409", description = "Duplicated fields"
                            ,content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "Invalid request body"
                            ,content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            }
    )
    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody @Valid UserCreateDto dto){
        User createdUser = service.create(UserMapper.toUser(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(createdUser));
    }

    @Operation(summary = "Find User by ID",
            security = @SecurityRequirement(name = "bearerAuth"),
            description = "Retrieve User details by providing its ID",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID of the user to be retrieved",
                            required = true,
                            example = "12"
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "User found and returned"
                            ,content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"
                            ,content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "404", description = "User not found"
                            ,content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable("id") Long id){
        User foundUser = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toDto(foundUser));
    }

    @Operation(summary = "Update User Information",
            security = @SecurityRequirement(name = "bearerAuth"),
            description = "Update User information by providing its ID and new data",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID of the user to be retrieved",
                            required = true,
                            example = "12"
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "User information updated successfully",
                        content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "422", description = "Invalid request body",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "404", description = "User not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserInfo(@PathVariable("id") Long id, @RequestBody @Valid UserUpdateDto dto){
        service.updateInfo(id, UserMapper.toUser(dto));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Update User Password",
            security = @SecurityRequirement(name = "bearerAuth"),
            description = "Update User password by providing its ID and new password",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID of the user to be retrieved",
                            required = true,
                            example = "12"
                    ),
                    @Parameter(
                            name = "password",
                            description = "New password for the user",
                            required = true
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "User password updated successfully",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "422", description = "Invalid request body",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "404", description = "User not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })

    @PutMapping("/{id}/password")
    public ResponseEntity<Void> updateUserPassword(@PathVariable("id") Long id, @Valid @RequestBody UserPasswordDto dto){
        service.updatePassword(id, UserMapper.toUser(dto));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
