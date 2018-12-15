package io.swagger.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * Pet
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-20T13:31:05.475Z")
@Entity
@Table(name = "pets")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt","medic" }, allowGetters = false)
public class Pet {
	@JsonProperty("id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

	@JsonProperty("name")
	@Column
	private String name = null;
	
	@JsonProperty("breed")
	@Column
	private String breed = null;
	
	@JsonProperty("age")
	@Column
	private Integer age = null;
	
	
	@JsonProperty("origin")
	@Column
	private String origin = null;
	
	
	@JsonProperty("color")
	@Column
	private String color = null;
	
	
	@JsonProperty("hieght")
	@Column
	private Float hieght = null;
	
	@JsonProperty("life_span")
	@Column
	private Integer life_span = null;
	
	
	@JsonProperty("description")
	@Column
	private String description = null;
	
	
	@JsonProperty("provider")
	@OneToOne
	private Provider provider = null;
	
	@JsonProperty("medic")
	@OneToOne(fetch = FetchType.LAZY)
	private Medic medic = null;
	
	@ElementCollection
	private List<String> images;
	
		

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}
	public Medic getMedic() {
		return medic;
	}

	public void setMedic(Medic medic) {
		this.medic = medic;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Float getHieght() {
		return hieght;
	}

	public void setHieght(Float hieght) {
		this.hieght = hieght;
	}

	public Integer getLife_span() {
		return life_span;
	}

	public void setLife_span(Integer life_span) {
		this.life_span = life_span;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
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

	/**
	 * Get category
	 * 
	 * @return category
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public Pet name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Get name
	 * 
	 * @return name
	 **/
	@ApiModelProperty(example = "doggie", required = true, value = "")
	@NotNull

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
