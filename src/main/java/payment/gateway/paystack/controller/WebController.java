package payment.gateway.paystack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class WebController {
    
    @GetMapping("/payment-form")
    public String showPaymentForm() {
        return "payment-form"; 
    }
}