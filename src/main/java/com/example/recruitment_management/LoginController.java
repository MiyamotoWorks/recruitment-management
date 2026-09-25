package com.example.recruitment_management;

import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ログイン画面を表示
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // ログイン処理
    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {
        // DBからユーザー名でユーザーを検索
        Optional<User> user = userRepository.findByUsername(username);
        // ユーザーが存在する場合
        if (user.isPresent()) {
            // OptionalからUserオブジェクトを取り出す
            User foundUser = user.get();
            // DBのパスワードと入力されたパスワードを比較
            if (foundUser.getPassword().equals(password)) {
                // Sessionにログインユーザーを保存
                session.setAttribute("loginUser", username);
                // candidates画面へ
                return "redirect:/candidates";
            }
        }
        // ログイン失敗
        model.addAttribute("error", "ユーザー名またはパスワードが間違っています。");
        // login.htmlをもう一度表示
        return "login";
    }
    
    // ログアウト処理
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        // Sessionからログインユーザーを削除
        session.removeAttribute("loginUser");

        return "redirect:/login";
    }
}