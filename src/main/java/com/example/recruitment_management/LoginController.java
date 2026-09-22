package com.example.recruitment_management;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    // ログイン画面を表示
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // ログイン処理
    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            HttpSession session) {

        // Sessionにログインユーザーを保存
        session.setAttribute("loginUser", username);

        // ブラウザにもう一度 /candidates へアクセスさせる
        return "redirect:/candidates";
    }

    // ログアウト処理
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        // Sessionからログインユーザーを削除
        session.removeAttribute("loginUser");

        return "redirect:/login";
    }
}