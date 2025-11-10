package com.example.mobilerecharge.controller;
import com.example.mobilerecharge.model.RechargePlan; import com.example.mobilerecharge.model.Transaction; import com.example.mobilerecharge.model.User;
import com.example.mobilerecharge.service.RechargeService; import com.example.mobilerecharge.service.UserService;
import jakarta.servlet.http.HttpSession; import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.*; import java.util.List;
@Controller public class RechargeController {
    private final RechargeService rechargeService; private final UserService userService;
    public RechargeController(RechargeService r, UserService u){this.rechargeService=r; this.userService=u;}
    @GetMapping("/dashboard") public String dashboard(HttpSession s, Model m){ User user=(User)s.getAttribute("user"); if(user==null) return "redirect:/login"; m.addAttribute("user", userService.findById(user.getId()).get()); m.addAttribute("plans", rechargeService.allPlans()); return "dashboard"; }
    @GetMapping("/recharge") public String form(HttpSession s, Model m){ User user=(User)s.getAttribute("user"); if(user==null) return "redirect:/login"; m.addAttribute("plans", rechargeService.allPlans()); return "recharge"; }
    @PostMapping("/recharge") public String doRecharge(@RequestParam String operator, @RequestParam String mobileNumber, @RequestParam Double amount, @RequestParam String paymentMethod, HttpSession s, Model m){
        User user=(User)s.getAttribute("user"); if(user==null) return "redirect:/login";
        if(paymentMethod.equals("WALLET") && user.getWalletBalance()<amount){ m.addAttribute("error","Insufficient balance"); m.addAttribute("plans", rechargeService.allPlans()); return "recharge"; }
        if(paymentMethod.equals("WALLET")){ user.setWalletBalance(user.getWalletBalance()-amount); userService.save(user); s.setAttribute("user", userService.findById(user.getId()).get()); }
        Transaction t=new Transaction(); t.setUserId(user.getId()); t.setOperator(operator); t.setMobileNumber(mobileNumber); t.setAmount(amount); t.setPaymentMethod(paymentMethod); rechargeService.saveTxn(t);
        m.addAttribute("msg","Recharge successful"); m.addAttribute("plans", rechargeService.allPlans()); return "recharge";
    }
    @GetMapping("/history") public String history(HttpSession s, Model m){ User user=(User)s.getAttribute("user"); if(user==null) return "redirect:/login"; List<Transaction> tx=rechargeService.history(user.getId()); m.addAttribute("tx",tx); return "history"; }
}
