package app.web.controllers;

import app.models.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showAllUser(Model model) {
        List<User> listAllUsers = userService.getAllUsers();
        model.addAttribute("users", listAllUsers);
        return "users";
    }

    @GetMapping("/create")
    public String create(Model model) {
        return "create";
    }

    @PostMapping("/create")
    public String createUser(@RequestParam(value = "email") String email,
                             @RequestParam(value = "firstname") String firstname,
                             @RequestParam(value = "lastname") String lastname,
                             @RequestParam(value = "birthdate") LocalDate birthdate,
                             Model model) {

        User user = new User(email, firstname, lastname, birthdate);
        userService.save(user);

        return "redirect:/users";
    }

    @GetMapping("/change")
    public String changeUser(@RequestParam(value = "id", required = false) Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);

        return "change";
    }

    @PostMapping("/change")
    public String updateUSer(@RequestParam("id") Long id,
                             @RequestParam(value = "email", required = false) String email,
                             @RequestParam(value = "firstname", required = false) String firstname,
                             @RequestParam(value = "lastname", required = false) String lastname,
                             @RequestParam(value = "birthdate", required = false) String birthdate,
                             Model model) {

        User user = userService.findById(id);

        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);

        List<Integer> bd = Arrays.stream(birthdate.split("-"))
                .map(Integer::valueOf)
                .toList();

        user.setBirthdate(LocalDate.of(bd.get(0), bd.get(1), bd.get(2)));

        userService.update(user);

        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id, Model model) {
        userService.delete(id);

        return "redirect:/users";
    }

}
