package io.swagger.model;

//import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.hibernate.annotations.CreationTimestamp;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-20T13:31:05.475Z")
@Entity
@Table(name = "statistics")
@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
  //      allowGetters = true)
public class Statistics {
	@JsonProperty("id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	@ApiModelProperty(value = "")

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonProperty("methodcalls")
	@Column
	private Integer methodcalls = 0;
	
	
	public Integer getMethodcalls() {
		return methodcalls;
	}

	public Integer setmethodcalls(Integer methodcalls) {
		return this.methodcalls = methodcalls;
	}
	
	@JsonProperty("servicename")
	@Column
	private String servicename = null;

	
//	@JsonProperty("createdAt")
//	@Column(updatable=false)
//	@CreationTimestamp
//	@ApiModelProperty(hidden=true)
//	private Timestamp createdAt = null;
//	
	
	
	
	public Statistics servicename(String servicename) {
		this.servicename = servicename;
		return this;
	}
	
	
	@ApiModelProperty(value = "")

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	


}

