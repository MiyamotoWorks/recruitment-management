package com.example.recruitment_management;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 登録画面を表示
    @GetMapping("/users/new")
    public String newUser(Model model) {

        User user = new User();

        model.addAttribute("user", user);

        return "users/new";
    }

    // 登録ボタンが押されたとき
    @PostMapping("/users")
    public String createUser(@ModelAttribute User user) {

        userRepository.save(user);

        return "redirect:/users/new";
    }
}