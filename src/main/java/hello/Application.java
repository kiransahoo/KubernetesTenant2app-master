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
        String privatep = "private blank";
        try
        {
            privatep =  restTemplate.getForObject("http://10.100.43.175:80", String.class);
        }
        catch (Exception e)
        {
            privatep = "privateExceptionmessage---------->" + e.getMessage();
        }
        String publicip = "public blank ";
        try
        {
            publicip = restTemplate.getForObject("http://ab65fdc6-tenantthreenamesp-ccbf-927119695.us-west-2.elb.amazonaws.com",
                    String.class);
        }
        catch (Exception e)
        {
            publicip = "publicexceptionmessage-->" + e.getMessage();
        }

        return "Tenant 2- App" + "--> Repsonse from Tenant1-->" +
                privatep + "-->" + publicip;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
