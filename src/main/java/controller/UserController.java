package controller;

import java.util.List;
import java.util.stream.Collectors;
import model.User;
import model.UserResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return getUserDto(userService.get(id));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.listUsers().stream()
                .map(this::getUserDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String injectUsers() {
        for (int i = 1; i < 10; i++) {
            User user = new User();
            user.setEmail("My email " + i + "@email");
            user.setName("My name " + i);
            user.setPassword(i + "p888");
            userService.add(user);
        }
        return "Injecting done!";
    }

    private UserResponseDto getUserDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setName(user.getName());
        return userResponseDto;
    }
}
