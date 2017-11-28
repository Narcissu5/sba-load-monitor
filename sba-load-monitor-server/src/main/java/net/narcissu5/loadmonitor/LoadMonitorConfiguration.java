package net.narcissu5.loadmonitor;

import net.narcissu5.loadmonitor.controller.LoadController;
import net.narcissu5.loadmonitor.dao.SbaLoad1MAggrDAO;
import net.narcissu5.loadmonitor.dao.SbaLoad1MDAO;
import net.narcissu5.loadmonitor.service.LoadExtractService;
import net.narcissu5.loadmonitor.service.SaveLoadService;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

/**
 * Created by 曾浩 on 2017/11/15.
 */
@Configuration
@ConditionalOnBean(DataSource.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@EnableScheduling
public class LoadMonitorConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(LoadMonitorConfiguration.class);

    @Scheduled(cron = "10 * * * * *")
    public void recordLoad() {
        try {
            saveLoadService().recordLoad();
        } catch (URISyntaxException | IOException e) {
            logger.warn("Unexpected error", e);
        }
    }

    @Bean
    public SaveLoadService saveLoadService() {
        return new SaveLoadService();
    }

    @Bean
    public LoadExtractService loadExtractService() {
        return new LoadExtractService();
    }

    @Bean
    public CloseableHttpClient httpClient() {
        SocketConfig sc = SocketConfig.custom().setSoTimeout(1000).build();

        return HttpClients.custom()
                .setDefaultSocketConfig(sc)
                .setConnectionTimeToLive(120, TimeUnit.SECONDS)
                .build();
    }

    @Bean
    public LoadController loadController() {
        return new LoadController();
    }

    @Bean
    public SbaLoad1MDAO sbaLoad1MDAO() {
        return new SbaLoad1MDAO();
    }

    @Bean
    public SbaLoad1MAggrDAO sbaLoad1MAggrDAO() {
        return new SbaLoad1MAggrDAO();
    }
}
