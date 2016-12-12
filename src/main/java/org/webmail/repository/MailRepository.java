package org.webmail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.webmail.model.Mail;

/**
 * Created by formation on 06/12/2016.
 */
@Repository
public interface MailRepository extends JpaRepository<Mail, Integer> {
}
