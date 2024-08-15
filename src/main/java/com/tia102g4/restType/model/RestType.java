package com.tia102g4.restType.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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

//	    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="rest_type")
//	    @OrderBy("rest_id asc")
//		private Set<Restaurant> rests = new HashSet<Restaurant>();

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

//		public Set<Restaurant> getRests() {
//			return rests;
//		}
	//
//		public void setRests(Set<Restaurant> rests) {
//			this.rests = rests;
//		}

	@Override
	public String toString() {
		return "RestType [typeId=" + typeId + ", typeName=" + typeName + "]";
	}

}
