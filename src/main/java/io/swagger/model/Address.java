package io.swagger.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * User
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-20T13:31:05.475Z")
@Entity
@Table(name = "address")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = false,allowSetters=false)
public class Address{
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("permanent")
  @Column
  private String permanent = null;

  @JsonProperty("street")
  @Column
  private String street = null;

  @JsonProperty("city")
  private String city = null;

  @JsonProperty("pin_code")
  @Column
  private String pin_code = null;

  @JsonProperty("country")
  @Column
  private String country = null;
  
  @JsonProperty("type_of_user")
  @Column
  private String type_of_user = null;
  @JsonProperty("updatedAt")
	@Column
	@UpdateTimestamp
	private LocalDateTime updatedAt = null;
	
	@JsonProperty("createdAt")
	@Column(updatable=false)
	@CreationTimestamp
	private Timestamp createdAt = null;

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	@ApiModelProperty(hidden=true)

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	

	public Timestamp getCreatedAt() {
		return createdAt;
	}
	@ApiModelProperty(hidden=true)

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}



public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public String getPermanent() {
	return permanent;
}

public void setPermanent(String permanent) {
	this.permanent = permanent;
}

public String getStreet() {
	return street;
}

public void setStreet(String street) {
	this.street = street;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getPin_code() {
	return pin_code;
}

public void setPin_code(String pin_code) {
	this.pin_code = pin_code;
}

public String getCountry() {
	return country;
}

public void setCountry(String country) {
	this.country = country;
}

public String getType_of_user() {
	return type_of_user;
}

public void setType_of_user(String type_of_user) {
	this.type_of_user = type_of_user;
}
  

  
}

