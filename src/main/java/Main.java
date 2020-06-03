
import config.AppConfig;
import java.util.List;
import model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.UserService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        userService.add(new User("ADMIN", "11"));
        userService.add(new User("USER", "22"));

        List<User> userList = userService.listUsers();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
