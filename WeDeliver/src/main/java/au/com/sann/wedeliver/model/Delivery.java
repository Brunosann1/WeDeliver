package au.com.sann.wedeliver.model;

import java.sql.Time;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Delivery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private LocalDate creationDate;
	
	@NotNull
	private LocalDate scheduledDate;
	
	@NotNull
	private Time scheduledTime;
	
	@NotNull
	private String address;
	
	@NotNull
	@ManyToOne
	private Deliverer deliverer;
	
	@NotNull
	private String customer;
	
	

	public Delivery() {
		this.creationDate = LocalDate.now();
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDate getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(LocalDate scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public Time getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(Time scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Deliverer getDeliverer() {
		return deliverer;
	}

	public void setDeliverer(Deliverer deliverer) {
		this.deliverer = deliverer;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}
}
