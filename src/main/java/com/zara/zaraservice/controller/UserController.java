package com.zara.zaraservice.controller;

import com.zara.zaraservice.dto.requests.PasswordRequest;
import com.zara.zaraservice.dto.responses.ApiResponse;
import com.zara.zaraservice.dto.UserDTO;
import com.zara.zaraservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

import static com.zara.zaraservice.constants.Constant.REDIRECTION_URL_TO_FRONT;

@Slf4j
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<UserDTO>> getUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<Optional<UserDTO>> getUserByPhoneNumber(@PathVariable String phoneNumber){
        return ResponseEntity.ok(userService.getUserByPhoneNumber(phoneNumber));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserDTO>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(userDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id,userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PatchMapping("/{id}/disable")
    public ResponseEntity<ApiResponse> disableUserAccount(@PathVariable Long id) {
        return ResponseEntity.ok(userService.disableUserAccount(id));
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<ApiResponse> activateUserAccount(@PathVariable Long id) {
        return ResponseEntity.ok(userService.activateUserAccount(id));
    }

    //TODO change auth confirmation
    @GetMapping("/{id}/activate")
    public RedirectView activateUserAccountGet(@PathVariable Long id) {
         ResponseEntity.ok(userService.activateUserAccount(id));
        return new RedirectView(REDIRECTION_URL_TO_FRONT);
    }

    @PostMapping("/compare-password")
    public boolean comparePassword(@RequestBody PasswordRequest passwordRequest) {
        return userService.comparePassword(passwordRequest);
    }
}
