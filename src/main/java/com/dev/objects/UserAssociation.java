package com.dev.objects;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="userAssociations")
public class UserAssociation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public int id;
	
	@ManyToOne
  @JoinColumn (name = "userId")
  private UserObject userObject;

	@ManyToOne
	@JoinColumn (name = "associationId")
	private AssociationObject associationObject;

	public int getId(){
		return this.id;
	}

	public void setId(int id){
		this.id = id;
	}

	public UserObject getUserObject(){
		return this.userObject;
	}

	public void setUserObject(UserObject userObject){
		this.userObject = userObject;
	}

	public AssociationObject getAssociationObject(){
		return this.associationObject;
	}

	public void setAssociationObject(AssociationObject associationObject){
		this.associationObject = associationObject;
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null || !(obj instanceof UserAssociation) ) return false;

		UserAssociation other = (UserAssociation) obj;
		return other.hashCode() == this.hashCode();
	}

	public int hashCode(){
		return this.id;
	}
}
