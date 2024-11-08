package payment.gateway.paystack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import payment.gateway.paystack.config.PaystackConfig;

@Service
public class PaystackService {
    private final String PAYSTACK_URL = "https://api.paystack.co";
    private final RestTemplate restTemplate;
    private final PaystackConfig paystackConfig;

    @Autowired
    public PaystackService(RestTemplate restTemplate, PaystackConfig paystackConfig) {
        this.restTemplate = restTemplate;
        this.paystackConfig = paystackConfig;
    }

    public String initializePayment(double amount, String email) {
        String url = PAYSTACK_URL + "/transaction/initialize";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + paystackConfig.getPaystackSecretKey());

        String body = String.format("{\"email\": \"%s\", \"amount\": %.2f}", email, amount * 100); // Amount in kobo (cents)

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }

    public boolean verifyPayment(String reference) {
        String url = PAYSTACK_URL + "/transaction/verify/" + reference;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + paystackConfig.getPaystackSecretKey());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody().contains("\"status\":\"success\"");
    }

}
