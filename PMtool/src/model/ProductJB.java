package model;

import java.io.Serializable;

public class ProductJB implements Serializable {
	private int id;
	private String item;
	private String kind;
	private String group;
	private int stock;

	public ProductJB(int id, String item, int stock) {
		super();
		this.id = id;
		this.item = item;
		this.stock = stock;
	}

	public ProductJB() {
		super();
	}

	public ProductJB(int id, int stock) {
		super();
		this.id = id;
		this.stock = stock;
	}

	public ProductJB(int id) {
		super();
		this.id = id;
	}

	public ProductJB(String item, String kind, String group) {
		super();
		this.item = item;
		this.kind = kind;
		this.group = group;
	}

	public ProductJB(String item, String kind, String group, int stock) {
		super();
		this.item = item;
		this.kind = kind;
		this.group = group;
		this.stock = stock;
	}

	public ProductJB(int id, String item, String kind, String group) {
		super();
		this.id = id;
		this.item = item;
		this.kind = kind;
		this.group = group;
	}

	public ProductJB(int id, String item, String kind, String group, int stock) {
		super();
		this.id = id;
		this.item = item;
		this.kind = kind;
		this.group = group;
		this.stock = stock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
