package com.poly.controller.admin;

import com.poly.entity.User;
import com.poly.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/account")
public class AccountAController {
    private final AccountService accountService;

    public AccountAController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("accounts", accountService.findAll());
        return "admin/account";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("account", accountService.findById(id).orElse(null));
        return "admin/account-form";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute User account) {
        accountService.save(account);
        return "redirect:/admin/account/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        accountService.deleteById(id);
        return "redirect:/admin/account/index";
    }
}
