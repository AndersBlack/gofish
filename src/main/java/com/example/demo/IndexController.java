package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Controller
public class IndexController {

    @GetMapping("/")
    public String homePage(Model model) {
        String dtNow = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(LocalDateTime.now());
        model.addAttribute("dtnow", dtNow);
        return "index.html";
    }

    @GetMapping("/test/testeryo")
    public String testme(Model model){
        System.out.println("Are you getting it?");
        String straaang = "proof of concept indeed!";
        String dtNow = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(LocalDateTime.now());
        model.addAttribute("dtnow", dtNow);
        model.addAttribute("gimmeStuff",straaang);
        return "index.html";
    }
}
