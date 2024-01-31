package kea.dpang.seller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SellerServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SellerServerApplication.class, args);
    }

}
