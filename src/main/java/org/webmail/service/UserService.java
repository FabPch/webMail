package org.webmail.service;

import org.springframework.stereotype.Component;
import org.webmail.model.UserMail;

/**
 * Created by formation on 09/12/2016.
 */
public interface UserService {

    public UserMail getOne(int id);

    public Iterable<UserMail> findAll();

    public Iterable<UserMail> findByConnected();

    public Integer getByLog(String firstName, String lastName);

    public void save(UserMail userMail);

    public void delete(int id);
}
