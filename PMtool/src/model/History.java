package model;

import java.io.Serializable;

public class History implements Serializable {
	private int historyid;
	private String historytime;
	private String category;
	private int num;
	private int id;

	public History(String category, int num, int id) {
		super();
		this.category = category;
		this.num = num;
		this.id = id;
	}

	public History() {
		super();
	}

	public History(String category, int num) {
		super();
		this.category = category;
		this.num = num;
	}

	public History(int historyid, String historytime, String category, int num) {
		super();
		this.historyid = historyid;
		this.historytime = historytime;
		this.category = category;
		this.num = num;
	}

	public History(int historyid, String historytime, String category, int num, int id) {
		super();
		this.historyid = historyid;
		this.historytime = historytime;
		this.category = category;
		this.num = num;
		this.id = id;
	}

	public int getHistoryid() {
		return historyid;
	}

	public void setHistoryid(int historyid) {
		this.historyid = historyid;
	}

	public String getHistorytime() {
		return historytime;
	}

	public void setHistorytime(String historytime) {
		this.historytime = historytime;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
