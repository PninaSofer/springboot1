package com.dev.objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="associations")
public class AssociationObject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public int id;

	@Column
	private String name;

	public int getId(){
		return this.id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}


	@Override
	public boolean equals(Object obj){
		if(obj == null || !(obj instanceof AssociationObject) ) return false;

		AssociationObject other = (AssociationObject) obj;
		return other.hashCode() == this.hashCode();
	}

	public int hashCode(){
		return this.id;
	}

}
