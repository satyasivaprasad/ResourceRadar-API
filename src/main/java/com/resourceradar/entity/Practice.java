package com.resourceradar.entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.resourceradar.model.Auditable;
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

@Entity
@Table(name = "practice")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Practice extends Auditable {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "org_id")
	private String orgId;

	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@Column(name = "modified_date")
	private LocalDateTime modifiedDate;
	


	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setCreatedDateTime(Instant createdDateTime) {
		this.createdDate = LocalDateTime.ofInstant(createdDateTime, ZoneOffset.UTC);
	}

	public void setModifiedDateTime(Instant modifiedDateTime) {
		this.modifiedDate = LocalDateTime.ofInstant(modifiedDateTime, ZoneOffset.UTC);
	}

	
	public Instant getCreatedDateTime() {
        return createdDate.atZone(ZoneOffset.UTC).toInstant();
    }

	public Instant getModifiedDateTime() {
		 return modifiedDate.atZone(ZoneOffset.UTC).toInstant();
    }


}
