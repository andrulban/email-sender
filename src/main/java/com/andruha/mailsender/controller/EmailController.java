package com.andruha.mailsender.controller;

import com.andruha.mailsender.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Controller
public class EmailController {

    private final EmailSenderService emailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailController(EmailSenderService emailSender,
                           TemplateEngine templateEngine){
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @GetMapping("/send")
    public @ResponseBody String send() {
        Context context = new Context();
        context.setVariable("header", "header");
        context.setVariable("title", "title");
        context.setVariable("description", "description");

        String body = templateEngine.process("template", context);
        emailSender.sendEmail("someone@gmail.com", "Testing app", body);
        return "sent";
    }
}
