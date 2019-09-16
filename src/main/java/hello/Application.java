package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@RestController
public class Application {

    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        restTemplate = builder.build();
        return restTemplate;
    }

    @RequestMapping("/")
    public String home() {
        return "Tenant 2- App" + "--> Repsonse from Tenant1" +
                restTemplate.getForObject("http://ab65fdc6-tenantthreenamesp-dc0f-1048919814.us-west-2.elb.amazonaws.com",
                        String.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
