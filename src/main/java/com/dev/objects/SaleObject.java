package com.dev.objects;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sales")
public class SaleObject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public int id;

	@ManyToOne
	@JoinColumn (name = "storeId")
	private StoreObject storeObject;

	@Column
	String description;

	@Column
	LocalDate saleStart;

	@Column
	LocalDate saleEnd;

	@Column
	boolean isGlobal;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getSaleStart() {
		return saleStart;
	}

	public void setSaleStart(LocalDate saleStart) {
		this.saleStart = saleStart;
	}

	public LocalDate getSaleEnd() {
		return saleEnd;
	}

	public void setSaleEnd(LocalDate saleEnd) {
		this.saleEnd = saleEnd;
	}

	public boolean isGlobal() {
		return isGlobal;
	}

	public void setGlobal(boolean isGlobal) {
		this.isGlobal = isGlobal;
	}

	public StoreObject getStoreObject() {
		return storeObject;
	}

	public void setStoreObject(StoreObject storeObject) {
		this.storeObject = storeObject;
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null || !(obj instanceof SaleObject) ) return false;

		SaleObject other = (SaleObject) obj;
		return other.hashCode() == this.hashCode();
	}

	public int hashCode(){
		return this.id;
	}

}
