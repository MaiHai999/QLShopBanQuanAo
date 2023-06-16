package QLBanDoTheThao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CTHDBanID implements Serializable {
	
	@Column(name = "MaHDB")
	private String mahdb;
	
	@Column(name = "MaSP")
	private String masp;
	
	public String getMahdb() {
		return mahdb;
	}
	public void setMahdb(String mahdb) {
		this.mahdb = mahdb;
	}
	public String getMasp() {
		return masp;
	}
	public void setMasp(String masp) {
		this.masp = masp;
	}
	public CTHDBanID(String mahdb, String masp) {
		super();
		this.mahdb = mahdb;
		this.masp = masp;
	}
	public CTHDBanID() {
		super();
	}
	

}
