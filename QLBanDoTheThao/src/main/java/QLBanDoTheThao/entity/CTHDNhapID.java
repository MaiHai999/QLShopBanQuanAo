package QLBanDoTheThao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CTHDNhapID implements Serializable{
	
	@Column(name = "MaHDN")
	private String mahdn;
	
	@Column(name = "MaSP")
	private String masp;
	
	

	public CTHDNhapID() {
		super();
	}

	public CTHDNhapID(String mahdn, String masp) {
		super();
		this.mahdn = mahdn;
		this.masp = masp;
	}

	public String getMahdn() {
		return mahdn;
	}

	public void setMahdn(String mahdn) {
		this.mahdn = mahdn;
	}

	public String getMasp() {
		return masp;
	}

	public void setMasp(String masp) {
		this.masp = masp;
	}
	
	

}
