package com.poly.controller.admin;

import com.poly.entity.Order;
import com.poly.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/order")
public class OrderAController {
    private final OrderRepository orderRepository;

    public OrderAController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "admin/order";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderRepository.findById(id).orElse(null));
        return "admin/order-form";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Order order) {
        orderRepository.save(order);
        return "redirect:/admin/order/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        orderRepository.deleteById(id);
        return "redirect:/admin/order/index";
    }
}
