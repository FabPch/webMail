package org.webmail.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.webmail.model.Mail;
import org.webmail.model.UserMail;
import org.webmail.service.MailService;
import org.webmail.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by formation on 06/12/2016.
 */
@RequestMapping("/mail/")
@RestController
public class MailRestController {

    private boolean connected;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

    //Log In
    @CrossOrigin
    @PostMapping("log")
    public Integer log(@RequestBody UserMail userMail) {
        connected = false;
        Integer id = -1;
        if ((id = userService.getByLog(userMail.getFirstName(), userMail.getLastName())) != -1) {
            connected = true;
            UserMail userLog = userService.getOne(id);
            userLog.setConnected(true);
            userService.save(userLog);
            System.out.println("connecté");
            System.out.println(id);
            return id;
        } else {
            System.out.println("Non connecté");
            return id;
        }
    }

    //Log Out
    @CrossOrigin
    @PostMapping("logout")
    public void logout(@RequestBody UserMail userMail) {
        connected = false;
        UserMail userlog = userService.getOne(userMail.getId());
        userlog.setConnected(false);
        userService.save(userlog);
    }

    //Charge RecMails and SentMails
    @CrossOrigin
    @GetMapping("log/{idUser}")
    public List<Iterable> getAll(@PathVariable Integer idUser) {
        System.out.println("demande getAll");
        if (idUser == -1) {
            connected = false;
        }
        System.out.println(connected);
        List<Iterable> ls = new ArrayList<Iterable>();
        UserMail userMail = new UserMail();
        if (connected) {
            userMail = userService.getOne(idUser);
            ls.add(userMail.getRecMails());
            ls.add(userMail.getSendMails());
            System.out.println(userService.findByConnected());
            if (userService.findByConnected() != null)
                ls.add(userService.findByConnected());
            return ls;
        } else {
            return null;
        }
    }

    //Charge one mail
    @CrossOrigin
    @GetMapping("{id}")
    public Mail getMail(@PathVariable Integer id) {
        return mailService.findOne(id);
    }

    //Send mail
    @CrossOrigin
    @PostMapping("{id}")
    public void addMail(@PathVariable Integer id, @RequestBody Mail mail) {
        UserMail userMail = userService.getOne(id);
        mail.setWriter(userMail);
        System.out.println(mail.getContent());
        System.out.println(mail.getRecipients());
        mailService.save(mail);
    }

    //Update Mail
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

    //Delete mail
    @CrossOrigin
    @DeleteMapping("{id}")
    public void deleteMail(@PathVariable Integer id) {
        mailService.delete(id);
    }


}
