package org.webmail.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.webmail.model.Mail;
import org.webmail.model.UserMail;
import org.webmail.repository.MailRepository;
import org.webmail.repository.UserRepository;

import java.util.List;

/**
 * Created by formation on 06/12/2016.
 */
@RequestMapping("/mail/")
@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private MailRepository mailRepository;

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin
    @GetMapping
    public Iterable<Mail> getAll() {
        return mailRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("{id}")
    public Mail getMail(@PathVariable Integer id) {
        return mailRepository.findOne(id);
    }

    @CrossOrigin
    @PostMapping("{id}")
    public void addMail(@PathVariable Integer id, @RequestBody Mail mail) {
        UserMail userMail = userRepository.findOne(id);
        mail.setUserMail(userMail);
        mailRepository.save(mail);
    }

    @CrossOrigin
    @PutMapping("{id}")
    public void updateMail(@PathVariable Integer id, @RequestParam String title, String content, String attached) {

        Mail mail = mailRepository.findOne(id);
        mail.setTitle(title);
        mail.setContent(content);
        byte[] bytes = Base64.decodeBase64(attached);
        mail.setAttached(bytes);

        mailRepository.save(mail);
    }

    @CrossOrigin
    @DeleteMapping("{id}")
    public void deleteMail(@PathVariable Integer id) {
        mailRepository.delete(id);
    }


}
