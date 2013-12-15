package net.ostis.confman.model.mail.entity;

public class MailDto {

    private String email;

    private String password;

    public MailDto() {

        super();
    }

    public String getEmail() {

        return this.email;
    }

    public void setEmail(final String email) {

        this.email = email;
    }

    public String getPassword() {

        return this.password;
    }

    public void setPassword(final String password) {

        this.password = password;
    }
}
