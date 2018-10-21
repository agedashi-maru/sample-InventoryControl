package loginmodel;

import java.io.Serializable;

public class Account implements Serializable {
	String userid;
	String pass;

	public Account() {
		super();
	}

	public Account(String userid, String pass) {
		super();
		this.userid = userid;
		this.pass = pass;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
