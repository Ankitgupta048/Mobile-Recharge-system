package com.example.mobilerecharge.controller;
import com.example.mobilerecharge.model.RechargePlan; import com.example.mobilerecharge.model.User; import com.example.mobilerecharge.service.RechargeService; import com.example.mobilerecharge.service.UserService;
import jakarta.servlet.http.HttpSession; import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.*;
@Controller @RequestMapping("/admin") public class AdminController {
    private final RechargeService rechargeService; private final UserService userService; public AdminController(RechargeService r, UserService u){this.rechargeService=r; this.userService=u;}
    @GetMapping("/login") public String login(){ return "admin_login"; }
    @PostMapping("/login") public String doLogin(@RequestParam String email,@RequestParam String password,HttpSession s,Model m){ var opt=userService.findByEmail(email); if(opt.isEmpty()||!opt.get().getPassword().equals(password)||!opt.get().getRole().equals("ADMIN")){ m.addAttribute("error","Invalid"); return "admin_login";} s.setAttribute("admin", opt.get()); return "redirect:/admin/panel"; }
    @GetMapping("/panel") public String panel(HttpSession s, Model m){ User admin=(User)s.getAttribute("admin"); if(admin==null) return "redirect:/admin/login"; m.addAttribute("plans", rechargeService.allPlans()); return "admin_panel"; }
    @PostMapping("/add-plan") public String addPlan(RechargePlan plan){ rechargeService.savePlan(plan); return "redirect:/admin/panel"; }
}
