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


    /**
     * Wyświetla listę wszystkich użytkowników
     * @return lista użytkowników
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userServiceImpl.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Tworzy użytkownika wykorzystując parametr:
     * @param userDto
     * @return stworzonego użytkownika
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto userDto) {
        UserDto createdUserDto = userServiceImpl.createUser(userDto);
        System.out.println("User with e-mail: " + userDto.email() + " passed to the request");
        return createdUserDto;
    }

    /**
     * Usuwa użytkownika o ID równym:
     * @param userId
     */
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        trainingServiceImpl.deleteUserTrainingByUserId(userId);
        userServiceImpl.deleteUser(userId);
    }

    /**
     * Modyfikuje użytkonika:
     * @param userId
     * i nadaje mu nowe wartości:
     * @param userDto
     * @return zmodyfikowanego użytkonika
     */
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        return userServiceImpl.editUser(userId, userDto);
    }

    /**
     *Wyszukuje użytkownika po jego e-mailu:
     * @param email
     * @return dane użytkownika
     */
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

    /**
     *Wyszukuje użytkownika po jego ID:
     * @param userId
     * @return dane użytkownika
     */
    @GetMapping("/{userId}")
    public Optional<User> getUserByUserId(@PathVariable Long userId) {
        return userServiceImpl.getUser(userId);
    }

    /**
     * Wyszukuje użytkowników starszych niż:
     * @param date
     * @return listę starszych użytkowników
     */
    @GetMapping("/older/{date}")
    public ResponseEntity<List<User>> getUserByAge(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<User> users = userServiceImpl.getUserByAgeOlderThanX(date);
        return ResponseEntity.ok(users);
    }

    /**
     * Wyszukuje podstawowe informacje o użytkownikach
     * @return lista postawowych informacji (ID i nazwa klienta)
     */
    @GetMapping("/simple")
    public List<SimpleUser> getAllBasicUser() {
        return userServiceImpl.findAllUsers().stream()
                .map(UserMapper::simpleToDTO)
                .toList();
    }
}