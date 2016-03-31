package ru.amfitel.task.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by Bublik on 31.03.2016.
 */
@Entity
@Table(name = "user")
@SequenceGenerator(name = "default_gen", sequenceName = "id_user_seq", allocationSize = 1)
public class User extends AbstractEntity {

    @Column(name = "name")
    private  String name;

    @Column(name = "password")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
