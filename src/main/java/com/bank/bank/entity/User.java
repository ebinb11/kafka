package com.bank.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(schema = "bank", catalog = "bank", name = "user")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;
	@Column(name = "mobile")
	private String mobile;
	@Column(name = "email")
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "bank_id")
	private Bank bankId;
}
