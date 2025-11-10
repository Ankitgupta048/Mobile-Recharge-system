package com.example.mobilerecharge.controller;
import com.example.mobilerecharge.model.User; import com.example.mobilerecharge.service.UserService;
import jakarta.servlet.http.HttpSession; import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.*;
@Controller public class AuthController {
    private final UserService userService; public AuthController(UserService s){this.userService=s;}
    @GetMapping({"/","/index"}) public String index(){return "index";}
    @GetMapping("/register") public String regForm(){return "register";}
    @PostMapping("/register") public String register(User user, Model m){ if(userService.findByEmail(user.getEmail()).isPresent()){ m.addAttribute("error","Email exists"); return "register";} user.setWalletBalance(100.0); userService.save(user); return "redirect:/login"; }
    @GetMapping("/login") public String loginForm(){return "login";}
    @PostMapping("/login") public String login(@RequestParam String email,@RequestParam String password, HttpSession session, Model m){ var o=userService.findByEmail(email); if(o.isEmpty()||!o.get().getPassword().equals(password)){ m.addAttribute("error","Invalid"); return "login";} session.setAttribute("user",o.get()); return "redirect:/dashboard";}
    @GetMapping("/logout") public String logout(HttpSession s){s.invalidate(); return "redirect:/";}
}
