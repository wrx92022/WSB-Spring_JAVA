package com.capgemini.wsb.fitnesstracker.user.api;

import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    UserDto createUser(UserDto userDto);

    User createUser(User user);

    List<User> getUsersByAge(int age);

    void deleteUser(Long id);

    User editUser(Long id, UserDto userDto);

    List<User> getUserByAgeOlderThanX(LocalDate localDate);

    User updateUser(Long id, User updatedUser);

    Optional<User> findUserById(Long id);

    UserDto updateUser(UserDto userDto);
}