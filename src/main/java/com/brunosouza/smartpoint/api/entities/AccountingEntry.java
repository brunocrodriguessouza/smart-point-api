package com.brunosouza.smartpoint.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.brunosouza.smartpoint.api.enums.TypeEnum;

@Entity
@Table(name = "accountingEntry")
public class AccountingEntry implements Serializable {

	private static final long serialVersionUID = 6524560251526772839L;

	private Long id;
	private Date accountingEntryDate;
	private String description;
	private String location;
	private Date creationDate;
	private Date modificationDate;
	private TypeEnum type;
	private Employee employee;

	public AccountingEntry() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "account_entry_date", nullable = false)
	public Date getDateAccountingEntry() {
		return accountingEntryDate;
	}

	public void setDateAccountingEntry(Date accountingEntryDate) {
		this.accountingEntryDate = accountingEntryDate;
	}

	@Column(name = "description", nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "location", nullable = true)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "creation_date", nullable = false)
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "modification_date", nullable = false)
	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false)
	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@PreUpdate
	public void preUpdate() {
		modificationDate = new Date();
	}

	@PrePersist
	public void prePersist() {
		final Date currentDate = new Date();
		creationDate = currentDate;
		modificationDate = currentDate;
	}

	@Override
	public String toString() {
		return "AccountingEntry [id=" + id + ", dateAccountingEntry=" + accountingEntryDate + ", description="
				+ description + ", location=" + location + ", creationDate=" + creationDate + ", modificationDate="
				+ modificationDate + ", type=" + type + ", employee=" + employee + "]";
	}

}
