package QLBanDoTheThao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CTKhuyenMaiID implements Serializable {
	@Column(name = "MaKM")
	private String makm;
	
	@Column(name = "MaSP")
	private String masp;
	
	public CTKhuyenMaiID() {
		super();
	}

	public CTKhuyenMaiID(String makm, String masp) {
		super();
		this.makm = makm;
		this.masp = masp;
	}

	public String getMakm() {
		return makm;
	}

	public void setMakm(String makm) {
		this.makm = makm;
	}

	public String getMasp() {
		return masp;
	}

	public void setMasp(String masp) {
		this.masp = masp;
	}
	
	
	

}
