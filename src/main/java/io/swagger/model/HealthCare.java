package io.swagger.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * User
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-20T13:31:05.475Z")
@Entity
@Table(name = "health_care")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class HealthCare{
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id = null;

  @JsonProperty("vaccination")
  @Column
  private String vaccination = null;

  @JsonProperty("deseases")
  @Column
  private String deseases = null;

  @JsonProperty("region")
  @Column
  private String region = null;

  @JsonProperty("medic_description")
  @Column
  private String medic_description = null;
  
  	@JsonProperty("medic")
	@OneToOne(cascade=CascadeType.MERGE)
	private Medic medic = null;
  
  @JsonProperty("Pet")
	@OneToOne(cascade=CascadeType.MERGE)
	private Pet pet = null;
  
  
  @JsonProperty("updatedAt")
	@Column
	@UpdateTimestamp
	@ApiModelProperty(hidden=true)
	private LocalDateTime updatedAt = null;
	
	@JsonProperty("createdAt")
	@Column(updatable=false)
	@CreationTimestamp
	@ApiModelProperty(hidden=true)
	private Timestamp createdAt = null;

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getVaccination() {
	return vaccination;
}

public void setVaccination(String vaccination) {
	this.vaccination = vaccination;
}

public String getDeseases() {
	return deseases;
}

public void setDeseases(String deseases) {
	this.deseases = deseases;
}

public String getRegion() {
	return region;
}

public void setRegion(String region) {
	this.region = region;
}

public String getMedic_description() {
	return medic_description;
}

public void setMedic_description(String medic_description) {
	this.medic_description = medic_description;
}

public Medic getMedic() {
	return medic;
}

public void setMedic(Medic medic) {
	this.medic = medic;
}

public Pet getPet() {
	return pet;
}

public void setPet(Pet pet) {
	this.pet = pet;
}

  

  
}

