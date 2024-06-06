package dw.editora.controller;

import dw.editora.CognitoClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private CognitoClient cognitoClient = new CognitoClient();

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        cognitoClient.signUp(username, password, email);
        return "Registration successful";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        cognitoClient.signIn(username, password);
        return "Login successful";
    }
}
