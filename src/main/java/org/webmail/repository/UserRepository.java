package org.webmail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.webmail.model.UserMail;

/**
 * Created by formation on 06/12/2016.
 */
public interface UserRepository extends JpaRepository<UserMail, Integer> {
}
