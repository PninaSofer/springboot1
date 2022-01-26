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
@Table(name="storeAssociations")
public class StoreAssociation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public int id;
	
	@ManyToOne
  @JoinColumn (name = "storeId")
  private StoreObject storeObject;

	@ManyToOne
	@JoinColumn (name = "associationId")
	private AssociationObject associationObject;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StoreObject getStoreObject() {
		return storeObject;
	}

	public void setStoreObject(StoreObject storeObject) {
		this.storeObject = storeObject;
	}

	public AssociationObject getAssociationObject() {
		return associationObject;
	}

	public void setAssociationObject(AssociationObject associationObject) {
		this.associationObject = associationObject;
	}

	
}
