package net.narcissu5.loadmonitor.controller;

import net.narcissu5.loadmonitor.service.SaveLoadService;
import net.narcissu5.loadmonitor.util.LoadModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by 曾浩 on 2017/11/17.
 */
@RestController
@RequestMapping("load")
public class LoadController {
    @Autowired
    SaveLoadService saveLoadService;

    @GetMapping
    public Map<String, LoadModel> getCurrent() {
        return saveLoadService.getCurrent();
    }
}
