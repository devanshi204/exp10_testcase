
package com.example.electricity.usage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.electricity.usage.domain.User;
import com.example.electricity.usage.repository.UserRepository;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register a new user
    @PostMapping("/signup")
    public String signup(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        userRepository.save(user);

        return "redirect:/login.html";
    }

    // Login using email and password
 

    @PostMapping("/login")
    public String login(@RequestParam String email,
                    @RequestParam String password) {
    User user = userRepository.findByEmail(email);

    if (user != null && user.getPassword() != null
            && user.getPassword().equals(password)) {
        return "redirect:/dashboard.html";
    }

    return "redirect:/login.html?error=1";
}
}

