package org.webmail.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.webmail.model.Mail;
import org.webmail.model.UserMail;
import org.webmail.repository.MailRepository;
import org.webmail.repository.UserRepository;

/**
 * Created by formation on 06/12/2016.
 */
@RequestMapping(value = "/user/")
@RestController
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin
    @GetMapping
    public Iterable<UserMail> getAll() {
        return userRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("{id}")
    public UserMail getUser(@PathVariable Integer id) {
        return userRepository.findOne(id);
    }

    @CrossOrigin
    @PostMapping
    public void addUser(@RequestBody UserMail userMail) {
        userRepository.save(userMail);
    }

    @CrossOrigin
    @PutMapping("{id}")
    public void updateUser(@PathVariable Integer id, @RequestParam String firstName, String lastName) {

        UserMail userMail = userRepository.findOne(id);
        userMail.setFirstName(firstName);
        userMail.setLastName(lastName);

        userRepository.save(userMail);
    }

    @CrossOrigin
    @DeleteMapping
    public void deleteUser(@PathVariable Integer id) {
        userRepository.delete(id);
    }
}
