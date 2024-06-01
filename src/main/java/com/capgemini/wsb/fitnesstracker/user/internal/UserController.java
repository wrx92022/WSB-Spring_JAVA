package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.training.internal.TrainingServiceImpl;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@Slf4j
class UserController {

    private final UserServiceImpl userServiceImpl;

    private final UserMapper userMapper;

    private final TrainingServiceImpl trainingServiceImpl;



    @GetMapping
    public List<UserDto> getAllUsers() {
        return userServiceImpl.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserDto userDto) {
        return userServiceImpl.createUser(userMapper.toEntity(userDto));
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        trainingServiceImpl.deleteUserTrainingByUserId(userId);
        userServiceImpl.deleteUser(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        return userServiceImpl.editUser(userId, userDto);
    }

    @GetMapping("/email")
    public ResponseEntity<List<User>> getUserByMail(@RequestParam String email) {
        Optional<User> userOptional = userServiceImpl.getUserByEmail(email);
        if (userOptional.isPresent()) {
            List<User> userList = new ArrayList<>();
            userList.add(userOptional.get());
            return ResponseEntity.ok().body(userList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}")
    public Optional<User> getUserByUserId(@PathVariable Long userId) {
        return userServiceImpl.getUser(userId);
    }

    @GetMapping("/older/{date}")
    public ResponseEntity<List<User>> getUserByAge(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<User> users = userServiceImpl.getUserByAgeOlderThanX(date);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/basic")
    public List<SimpleUser> getAllBasicUser() {
        return userServiceImpl.findAllUsers().stream()
                .map(UserMapper::simpleToDTO)
                .toList();
    }
}