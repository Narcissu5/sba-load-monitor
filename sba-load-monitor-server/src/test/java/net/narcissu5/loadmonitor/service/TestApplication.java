package net.narcissu5.loadmonitor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.EurekaClient;
import net.narcissu5.loadmonitor.dao.SbaLoad1MDAO;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

/**
 * Created by 曾浩 on 2017/11/20.
 */
@SpringBootApplication(scanBasePackages = "net.narcissu5.loadmonitor")
public class TestApplication {
    public static void main(String... args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public SbaLoad1MDAO sbaLoad1MDAO() {
        return new SbaLoad1MDAO();
    }

    @Bean
    public SaveLoadService saveLoadService() {
        return new SaveLoadService();
    }
}
