package controller;

import java.util.ArrayList;
import java.util.List;
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

    @GetMapping("/get/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        User user = userService.get(id);
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setName(user.getName());
        return userResponseDto;
    }

    @GetMapping("/getall")
    public List<UserResponseDto> getAll() {
        List<UserResponseDto> allUsersDto = new ArrayList<>();
        for (User user : userService.listUsers()) {
            allUsersDto.add(get(user.getId()));
        }
        return allUsersDto;
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
}
