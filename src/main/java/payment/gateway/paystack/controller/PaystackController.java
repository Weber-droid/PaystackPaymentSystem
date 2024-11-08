package payment.gateway.paystack.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import payment.gateway.paystack.services.PaystackService;


@RestController
@RequestMapping("/payment")
public class PaystackController {
    private final PaystackService paystackService;

    @Autowired
    public PaystackController(PaystackService paystackService) {
        this.paystackService = paystackService;
    }


    // Initialize payment
    @PostMapping("/initialize")
    public String initializePayment(@RequestParam double amount, @RequestParam String email) {
        return paystackService.initializePayment(amount, email);
    }

    // Verify payment after completion
    @GetMapping("/verify/{reference}")
    public String verifyPayment(@PathVariable String reference) {
        boolean isVerified = paystackService.verifyPayment(reference);
        return isVerified ? "Payment Successful!" : "Payment Failed!";
    }

}
