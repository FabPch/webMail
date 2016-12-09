package org.webmail.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by formation on 06/12/2016.
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table
public class Mail {

    //Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private byte[] attached;

    @ManyToOne
    @JoinColumn(name = "usermail_id")
    private UserMail writer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="mailrec",
            joinColumns=
            @JoinColumn(name="mail_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="usermail_id", referencedColumnName="id")
    )
    private Set<UserMail> recipients;

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getAttached() {
        return attached;
    }

    public void setAttached(byte[] attached) {
        this.attached = attached;
    }

    public UserMail getWriter() {
        return writer;
    }

    public void setWriter(UserMail writer) {
        this.writer = writer;
    }

    public Set<UserMail> getRecipients() {
        return recipients;
    }

    public void setRecipients(Set<UserMail> recipients) {
        this.recipients = recipients;
    }
}
