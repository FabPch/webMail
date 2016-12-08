package org.webmail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webmail.model.Mail;
import org.webmail.model.UserMail;
import org.webmail.repository.MailRepository;
import org.webmail.service.MailService;

/**
 * Created by formation on 06/12/2016.
 */
@Component
public class MailServiceImpl implements MailService {

    @Autowired
    private MailRepository mailRepository;

    @Override
    public Mail findOne(int id) {
        return mailRepository.findOne(id);
    }

    @Override
    public Iterable<Mail> findAll() {
        return mailRepository.findAll();
    }

    @Override
    public void save(Mail mail) {
        mailRepository.save(mail);
    }

    @Override
    public void delete(int id) {
        mailRepository.delete(id);
    }

}
