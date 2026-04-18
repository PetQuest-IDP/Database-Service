package org.acs.idp.databaseservice.mapper;

import org.acs.idp.databaseservice.model.dto.UserDto;
import org.acs.idp.databaseservice.model.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto entityToDto(UserEntity user) {
        return new UserDto(user.getId().toString(), user.getEmail(), user.getPasswordHash());
    }

    public UserEntity dtoToEntity(UserDto userDto) {
        UserEntity user = new UserEntity();
        user.setEmail(userDto.email());
        user.setPasswordHash(userDto.passwordHash());

        return user;
    }
}
