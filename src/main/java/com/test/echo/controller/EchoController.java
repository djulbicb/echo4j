package com.test.echo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class EchoController {

    @Value("${echo.name}")
    String echoNameDefault;

    Logger logger = LoggerFactory.getLogger(EchoController.class);

    @GetMapping("/")
    public String homeGet(Model model) {
        model.addAttribute("word", echoNameDefault);
        return "index.html";
    }

    @GetMapping("/{sayMyName}")
    public String echoGet(@PathVariable String sayMyName, Model model) {
        if (sayMyName == null || sayMyName.trim().isEmpty() || sayMyName.contains("index")) {
            sayMyName = echoNameDefault;
        }

        model.addAttribute("word", sayMyName);

        logger.info(sayMyName);

        return "index.html";
    }

    @PostMapping("/")
    public String homePost(@PathVariable String sayMyName) {
        return echoNameDefault;
    }

    @PostMapping("/{sayMyName}")
    public String echoPost(@PathVariable String sayMyName) {
        if (sayMyName == null || sayMyName.trim().isEmpty() || sayMyName.contains("index")) {
            sayMyName = echoNameDefault;
        }

        logger.info(sayMyName);

        return sayMyName;
    }
}
