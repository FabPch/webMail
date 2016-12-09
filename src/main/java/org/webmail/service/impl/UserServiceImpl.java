package org.webmail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webmail.model.UserMail;
import org.webmail.repository.UserRepository;
import org.webmail.service.UserService;

/**
 * Created by formation on 09/12/2016.
 */
@Component
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserMail getOne(int id) {
        return userRepository.getOne(id);
    }

    @Override
    public Iterable<UserMail> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Integer getByLog(String firstName, String lastName) {
        return userRepository.getByLog(firstName, lastName);
    }

    @Override
    public Iterable<UserMail> findByConnected() {
        return userRepository.findByConnected(true);
    }

    @Override
    public void save(UserMail userMail) {
        userRepository.save(userMail);
    }

    @Override
    public void delete(int id) {
        UserMail userMail = userRepository.getOne(id);
        userRepository.delete(userMail);
    }
}
