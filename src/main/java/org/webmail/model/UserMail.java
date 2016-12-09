package org.webmail.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by formation on 06/12/2016.
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "usermail")
public class UserMail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column
    private boolean connected;

    @ManyToMany(mappedBy = "recipients")
    @JsonIgnore
    private Set<Mail> recMails;

    @OneToMany(mappedBy = "writer")
    @JsonIgnore
    private Set<Mail> sendMails;

    //Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Set<Mail> getSendMails() {
        return sendMails;
    }

    public void setSendMails(Set<Mail> sendMails) {
        this.sendMails = sendMails;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

        public Set<Mail> getRecMails() {
        return recMails;
    }

    public void setRecMails(Set<Mail> recMails) {
        this.recMails = recMails;
    }
}
