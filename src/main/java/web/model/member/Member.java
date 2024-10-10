package web.model.member;

import java.io.Serializable;

public class Member implements Serializable { // Serializable -> java bean 형태 
	private String id, pw, name; // properties
	
	public Member() {// 필수 
		// TODO Auto-generated constructor stub
	}

	public Member(String id, String pw, String name) {
		super();
		setId(id);
		setPw(pw);
		setName(name);
	}

	// getProperty, setProperty
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", pw=" + pw + ", name=" + name + "]";
	}
	
}
