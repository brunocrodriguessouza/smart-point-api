package com.brunosouza.smartpoint.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.brunosouza.smartpoint.api.enums.ProfileEnum;;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

	private static final long serialVersionUID = -5754246207015712518L;
	
	private Long id;
	private String name;
	private String email;
	private String password;
	private String cpf;
	private BigDecimal hourValue;
	private Float amountHoursWorkDay;
	private Float amountHoursLunch;
	private ProfileEnum profile;
	private Date creationDate;
	private Date modificationDate;
	private Company company;
	private List<AccountingEntry> accountingEntry;

	public Employee() {
	}

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "cpf", nullable = false)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column(name = "hour_value", nullable = true)
	public BigDecimal getValorHora() {
		return hourValue;
	}
	
	@Transient
	public Optional<BigDecimal> getHourValueOpt() {
		return Optional.ofNullable(hourValue);
	}

	public void setHourValue(BigDecimal hourValue) {
		this.hourValue = hourValue;
	}

	@Column(name = "amount_hours_work_day", nullable = true)
	public Float getAmountHoursWorkDay() {
		return amountHoursWorkDay;
	}
	
	@Transient
	public Optional<Float> getAmountHoursWorkDayOpt() {
		return Optional.ofNullable(amountHoursWorkDay);
	}

	public void setAmountHoursWorkDay(Float amountHoursWorkDay) {
		this.amountHoursWorkDay = amountHoursWorkDay;
	}

	@Column(name = "amount_hours_lunch", nullable = true)
	public Float getAmountHoursLunch() {
		return amountHoursLunch;
	}
	
	@Transient
	public Optional<Float> getAmountHoursLunchOpt() {
		return Optional.ofNullable(amountHoursLunch);
	}

	public void setAmountHoursLunch(Float amountHoursLunch) {
		this.amountHoursLunch = amountHoursLunch;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "profile", nullable = false)
	public ProfileEnum getProfile() {
		return profile;
	}

	public void setPerfil(ProfileEnum profile) {
		this.profile = profile;
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

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Company getCompany() {
		return company;
	}

	public void setEmpresa(Company company) {
		this.company = company;
	}

	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<AccountingEntry> getAccountingEntry() {
		return accountingEntry;
	}

	public void setAccountingEntry(List<AccountingEntry> accountingEntry) {
		this.accountingEntry = accountingEntry;
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
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", cpf=" + cpf
				+ ", hourValue=" + hourValue + ", amountHoursWorkDay=" + amountHoursWorkDay + ", amountHoursLunch="
				+ amountHoursLunch + ", profile=" + profile + ", creationDate="
				+ creationDate + ", modificationDate=" + modificationDate + ", company=" + company + "]";
	}

}
