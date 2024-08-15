package com.tia102g4.restType.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import com.tia102g4.model.Restaurant;

@Entity
@Table(name = "restaurant_type")
public class RestType implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "type_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long typeId;

	@Column(name = "type_name", nullable = false, unique = true)
	private String typeName;

	public RestType() {
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "RestType [typeId=" + typeId + ", typeName=" + typeName + "]";
	}

}
