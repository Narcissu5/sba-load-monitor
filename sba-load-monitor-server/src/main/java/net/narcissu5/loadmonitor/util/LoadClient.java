package net.narcissu5.loadmonitor.util;

import net.narcissu5.loadmonitor.model.LoadModel;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 曾浩 on 2017/11/17.
 */
public interface LoadClient {
    @RequestMapping("load")
    LoadModel getLoad();
}
