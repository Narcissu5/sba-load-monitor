package net.narcissu5.loadmonitor.config;

import net.narcissu5.loadmonitor.LoadEndPoint;
import net.narcissu5.loadmonitor.util.ContainerCapture;
import net.narcissu5.loadmonitor.util.LoadMonitorInterceptor;
import org.springframework.boot.actuate.autoconfigure.ManagementContextConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.net.UnknownHostException;

/**
 * Created by 曾浩 on 2017/10/19.
 */
@Configuration
@ManagementContextConfiguration
public class SbaLoadMonitorConfiguration extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loadMonitorInterceptor());
    }

    @Bean
    public LoadMonitorInterceptor loadMonitorInterceptor() {
        return new LoadMonitorInterceptor();
    }

    @Bean
    public ContainerCapture  containerCapture() throws UnknownHostException {
        return new ContainerCapture();
    }

    @Bean
    public LoadEndPoint loadEndPoint() {
        return new LoadEndPoint();
    }
}
