package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
class UserMapper {

    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }

    static SimpleUser simpleToDTO(User user) {
        return new SimpleUser(user.getId(), user.getFirstName(), user.getLastName());
    }

    public void updateUserFromDto(UserDto dto, User user) {
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setBirthdate(dto.birthdate());
        user.setEmail(dto.email());
    }
}