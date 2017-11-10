package net.narcissu5.loadmonitor;

import net.narcissu5.loadmonitor.config.SbaLoadMonitorConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.annotation.*;

/**
 * Created by 曾浩 on 2017/10/19.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@EnableScheduling
@Import(SbaLoadMonitorConfiguration.class)
public @interface EnableLoadMonitor {
}
