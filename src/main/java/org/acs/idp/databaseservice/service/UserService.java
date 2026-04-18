package org.acs.idp.databaseservice.service;

import org.acs.idp.databaseservice.mapper.UserMapper;
import org.acs.idp.databaseservice.model.dto.UserDto;
import org.acs.idp.databaseservice.model.entity.UserEntity;
import org.acs.idp.databaseservice.model.request.SaveUserRequest;
import org.acs.idp.databaseservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::entityToDto)
                .orElse(null);
    }

    public void save(SaveUserRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already in use");
        }

        UserEntity user = new UserEntity();
        user.setEmail(request.email());
        user.setPasswordHash(request.passwordHash());

        userRepository.save(user);
    }
}
