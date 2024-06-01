package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserProvider {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }
    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User editUser(Long id, UserDto userDto) {
        Optional<User> byId = userRepository.findById(id);
        if(byId.isPresent()) {
            User user = byId.get();
            user.setFirstName(userDto.firstName());
            user.setLastName(userDto.lastName());
            user.setEmail(userDto.email());
            user.setBirthdate(userDto.birthdate());
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public List<User> getUsersByAge(int age) {
        return List.of();
    }

    @Override
    public List<User> getUserByAgeOlderThanX(LocalDate date) {
        return userRepository.findUsersByBirthdateBefore(date);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        return null;
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.Id())
                .orElseThrow(() -> new RuntimeException(String.valueOf(userDto.Id())));
        userMapper.updateUserFromDto(userDto, existingUser);
        existingUser = userRepository.save(existingUser);
        return userMapper.toDto(existingUser);
    }
}