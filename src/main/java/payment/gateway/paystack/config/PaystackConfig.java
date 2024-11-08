package payment.gateway.paystack.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PaystackConfig {
    
    @Value("${paystack.secret.key}")
    private String paystackSecretKey;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public String getPaystackSecretKey() {
        return paystackSecretKey;
    }

}
