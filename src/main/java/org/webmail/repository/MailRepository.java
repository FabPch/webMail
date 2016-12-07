package org.webmail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.webmail.model.Mail;

/**
 * Created by formation on 06/12/2016.
 */
public interface MailRepository extends JpaRepository<Mail, Integer> {
}
