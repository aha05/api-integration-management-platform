package com.apibridge.integration.user.controller;

import com.apibridge.integration.user.dto.ChangePasswordRequest;
import com.apibridge.integration.user.dto.UpdateUserRequest;
import com.apibridge.integration.user.dto.UserRequest;
import com.apibridge.integration.user.dto.UserResponse;
import com.apibridge.integration.user.mapper.UserMapper;
import com.apibridge.integration.user.repository.UserRepository;
import com.apibridge.integration.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody UserRequest request,
            UriComponentsBuilder uriBuilder
    ){
        var userDto = userService.registerUser(request);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserResponse update(
            @PathVariable(name = "id") Long id,
            UpdateUserRequest request
    ) {
        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(
            @PathVariable Long id,
            ChangePasswordRequest request){

        userService.changePassword(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
