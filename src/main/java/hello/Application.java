package hello;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodList;
import io.kubernetes.client.util.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        restTemplate = builder.build();
        return restTemplate;
    }

    @RequestMapping("/")
    public String home() {
        log.error("Entering here qith corrction--->");
        System.out.println("System out tenanttwonamespace-->");
        String privatep = "private blank";
        String metricsserver = "";
        StringBuffer bf = new StringBuffer();
       // try
       // {

           // ApiClient client = Config.defaultClient();
          //  Configuration.setDefaultApiClient(client);

          //  CoreV1Api api = new CoreV1Api();
          //  V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
          //  for (V1Pod item : list.getItems()) {
          //      bf.append(item.getMetadata().getName());
          //      System.out.println(item.getMetadata().getName());
           // }
           // log.error("came here here ---> " + bf.toString());

           // System.out.println("System out here-->");
           // privatep =  restTemplate.getForObject("http://10.100.43.175:80", String.class);
          //  privatep =  restTemplate.getForObject("http://tenantthreeapp.tenantthreenamespace.svc.cluster.local:80", String.class);
            //metricsserver = restTemplate.getForObject("http://metrics-server.metrics.svc.cluster.local:443/apis/metrics.k8s.io/v1beta1/pods", String.class);
       // }
       // catch (Exception e)
       // {

           // log.error("error here here--->", e);
          //  privatep = "privateExceptionmessage***---------->" + e.getMessage();
          //  System.out.println("System out here-->" + privatep);
       // }
        String publicip = "public blank ";
//        try
//        {
//            publicip = restTemplate.getForObject("http://ab65fdc6-tenantthreenamesp-ccbf-927119695.us-west-2.elb.amazonaws.com",
//                    String.class);
//        }
//        catch (Exception e)
//        {
//            publicip = "publicexceptionmessage-->" + e.getMessage();
//        }

        return "This app belongs to Test Tenant";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
