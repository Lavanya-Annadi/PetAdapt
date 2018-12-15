package io.swagger.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * User
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-20T13:31:05.475Z")
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt","password" }, allowGetters = false,allowSetters=true)
public class User {
	

	@JsonProperty("id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

	@JsonProperty("username")
	@Column(unique = true)
	private String username = null;

	@JsonProperty("firstName")
	@Column
	private String firstName = null;

	@JsonProperty("lastName")
	@Column
	private String lastName = null;

	@JsonProperty("email")
	@Column(nullable = false)
	private String email = null;
	@JsonIgnore
	@JsonProperty("password")
	@Column
	private String password = null;

	@JsonProperty("phone")
	@Column(unique = true)
	private String phone = null;

	@JsonProperty("userStatus")
	@Column
	private Integer userStatus = null;

	@JsonProperty("address")
	@OneToMany(cascade=CascadeType.ALL)
	private List<Address> address = null;
	
	
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
	@ApiModelProperty(hidden=true)
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public User id(Long id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 * 
	 * @return id
	 **/
	@ApiModelProperty(value = "")

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User username(String username) {
		this.username = username;
		return this;
	}

	/**
	 * Get username
	 * 
	 * @return username
	 **/
	@ApiModelProperty(value = "")

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	/**
	 * Get firstName
	 * 
	 * @return firstName
	 **/
	@ApiModelProperty(value = "")

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public User lastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	/**
	 * Get lastName
	 * 
	 * @return lastName
	 **/
	@ApiModelProperty(value = "")

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public User email(String email) {
		this.email = email;
		return this;
	}

	/**
	 * Get email
	 * 
	 * @return email
	 **/
	@ApiModelProperty(value = "")

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User password(String password) {
		this.password = password;
		return this;
	}

	/**
	 * Get password
	 * 
	 * @return password
	 **/
	@ApiModelProperty(value = "")
	public String getPassword() {
		return password;
		
	}

	public void setPassword(String password) {
		this.password = password;
		
	}

	public User phone(String phone) {
		this.phone = phone;
		return this;
	}

	/**
	 * Get phone
	 * 
	 * @return phone
	 **/
	@ApiModelProperty(value = "")

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User userStatus(Integer userStatus) {
		this.userStatus = userStatus;
		return this;
	}

	/**
	 * User Status
	 * 
	 * @return userStatus
	 **/
	@ApiModelProperty(value = "User Status")

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", password=" + password + ", phone=" + phone + ", userStatus=" + userStatus
				+ ", address=" + address + ", updatedAt=" + updatedAt + ", createdAt=" + createdAt + "]";
	}

	
}
