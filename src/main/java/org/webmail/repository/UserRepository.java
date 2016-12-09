package org.webmail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.webmail.model.Mail;
import org.webmail.model.UserMail;

import java.util.List;

/**
 * Created by formation on 06/12/2016.
 */
public interface UserRepository extends JpaRepository<UserMail, Integer> {

//    @Query("SELECT CASE WHEN COUNT(u) > 0  THEN 'true' ELSE 'false' END FROM UserMail u WHERE u.firstName = ?1 AND u.lastName = ?2")
//    public boolean existsByUser(String firstName, String lastName);

    @Query("SELECT u.id FROM UserMail u WHERE u.firstName = ?1 AND u.lastName = ?2")
    public Integer getByLog(String firstName, String lastName);

    @Query("SELECT u FROM UserMail u WHERE u.connected = ?1")
    public Iterable<UserMail> findByConnected(boolean connected);
}
