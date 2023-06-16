package QLBanDoTheThao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TaiKhoan")
public class TaiKhoanEntity {
	
	@Id
	@Column(name = "MaTK")
	private String matk;
	
	@Column(name = "Login")
	private String login;
	
	@Column(name = "PassWord")
	private String password;
	
	@Column(name = "MaNV")
	private String manv;
	
	@Column(name = "TrangThai")
	private String trangthai;

	public TaiKhoanEntity(String matk, String login, String password, String manv, String trangthai) {
		super();
		this.matk = matk;
		this.login = login;
		this.password = password;
		this.manv = manv;
		this.trangthai = trangthai;
	}

	public String getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	public String getMatk() {
		return matk;
	}

	public void setMatk(String matk) {
		this.matk = matk;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getManv() {
		return manv;
	}

	public void setManv(String manv) {
		this.manv = manv;
	}

	

	public TaiKhoanEntity() {
		super();
	}
	
	

	

}
