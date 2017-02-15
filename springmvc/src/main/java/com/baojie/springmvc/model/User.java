package com.baojie.springmvc.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 7419229779731522702L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long user_id;
    
    @Column(length = 50)
    private String account;
    public Long getId() {
        return user_id;
    }

    @Column(name="level")
    private Integer level;
    
    
    public void setId(Long user_id) {
        this.user_id = user_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
    
}
