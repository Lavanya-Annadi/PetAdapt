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
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * User
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-20T13:31:05.475Z")
@Entity
@Table(name = "session",uniqueConstraints=
	@UniqueConstraint(columnNames={"sessionId", "ipAddress"}))
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Session{
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id = null;
  
  	
  	
  	@JsonProperty("session")
	@Column(unique=true)
	
	private String sessionId = null;
  	
  	@JsonProperty("ipAddress")
	@Column
	private String ipAddress = null;
  	
  	@JsonProperty("status")
	@Column
	private Boolean status = null;
  	
  	
  	@JsonProperty("user")
	@OneToOne
	private User user = null;
  	
  	@JsonProperty("medic")
	@OneToOne
	private Medic medic = null;
  	
  	@JsonProperty("provider")
	@OneToOne
	private Provider provider = null;
  	
  	@JsonProperty("user_type")
  	@Column
  	private String type;
  	
  	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


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


	public String getSessionId() {
		return sessionId;
	}


	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}


	public String getIpAddress() {
		return ipAddress;
	}


	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}


	public Boolean getStatus() {
		return status;
	}


	public void setStatus(Boolean status) {
		this.status = status;
	}


	

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Medic getMedic() {
		return medic;
	}


	public void setMedic(Medic medic) {
		this.medic = medic;
	}


	public Provider getProvider() {
		return provider;
	}


	public void setProvider(Provider provider) {
		this.provider = provider;
	}


	


	
  	
  	
  	
  
  
}

