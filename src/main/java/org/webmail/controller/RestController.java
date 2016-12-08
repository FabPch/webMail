package org.webmail.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.webmail.model.Mail;
import org.webmail.model.UserMail;
import org.webmail.repository.MailRepository;
import org.webmail.repository.UserRepository;
import org.webmail.service.MailService;

import java.util.List;

/**
 * Created by formation on 06/12/2016.
 */
@RequestMapping("/mail/")
@org.springframework.web.bind.annotation.RestController
public class RestController {

    private boolean connected;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin
    @PostMapping("log")
    public Integer log(@RequestBody UserMail userMail) {
        connected = false;
        Integer id = -1;
        if ((id = userRepository.getByLog(userMail.getFirstName(), userMail.getLastName())) != -1) {
            connected = true;
            System.out.println("connecté");
            System.out.println(id);
            return id;
        } else {
            System.out.println("Non connecté");
            return id;
        }
    }

    @CrossOrigin
    @GetMapping("log/{idUser}")
    public Iterable<Mail> getAll(@PathVariable Integer idUser) {
        System.out.println("demande getAll");
        if (idUser == -1) {
            connected = false;
        }
        System.out.println(connected);
        UserMail userMail = new UserMail();
        if (connected) {
            userMail = userRepository.findOne(idUser);
            return userMail.getRecMails();
        } else {
            return null;
        }
    }

    @CrossOrigin
    @GetMapping("{id}")
    public Mail getMail(@PathVariable Integer id) {
        return mailService.findOne(id);
    }

    @CrossOrigin
    @PostMapping("{id}")
    public void addMail(@PathVariable Integer id, @RequestBody Mail mail) {
        UserMail userMail = userRepository.findOne(id);
        mail.setUserMail(userMail);
        System.out.println(mail.getContent());
        System.out.println(mail.getUserMails());
        mailService.save(mail);
    }

    @CrossOrigin
    @PutMapping("{id}")
    public void updateMail(@PathVariable Integer id, @RequestParam String title, String content, String attached) {

        Mail mail = mailService.findOne(id);
        mail.setTitle(title);
        mail.setContent(content);
        byte[] bytes = Base64.decodeBase64(attached);
        mail.setAttached(bytes);

        mailService.save(mail);
    }

    @CrossOrigin
    @DeleteMapping("{id}")
    public void deleteMail(@PathVariable Integer id) {
        mailService.delete(id);
    }


}
