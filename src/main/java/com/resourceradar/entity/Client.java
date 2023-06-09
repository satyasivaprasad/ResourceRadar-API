package com.resourceradar.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client_tlb")
public class Client {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "status")
	private String status;

	@Column(name = "org_id")
	private String orgId;

	@Column(name = "start_date")
	private LocalDateTime startDate;

	@Column(name = "end_date")
	private LocalDateTime endDate;

//	@OneToMany(mappedBy = "client",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JsonIgnore
//	private List<Project> projects = new ArrayList<>();


//	@OneToOne(cascade = CascadeType.ALL, mappedBy = "client")
//	@JsonManagedReference
//	@JsonIgnore
//	private Manager manager;
//
//	


}
