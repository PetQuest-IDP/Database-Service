package org.acs.idp.databaseservice.controller;

import org.acs.idp.databaseservice.model.dto.UserDto;
import org.acs.idp.databaseservice.model.request.SaveUserRequest;
import org.acs.idp.databaseservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserDto> findByEmail(@RequestParam String email) {
        UserDto user = userService.findByEmail(email);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody SaveUserRequest request) {
        userService.save(request);
        return ResponseEntity.ok().build();
    }
}
