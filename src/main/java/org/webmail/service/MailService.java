package org.webmail.service;

import org.webmail.model.Mail;

import java.util.Set;

/**
 * Created by formation on 06/12/2016.
 */
public interface MailService {

    public Mail findOne(int id);

    public Iterable<Mail> findAll();

    public void save(Mail mail);

    public void delete(int id);


}
