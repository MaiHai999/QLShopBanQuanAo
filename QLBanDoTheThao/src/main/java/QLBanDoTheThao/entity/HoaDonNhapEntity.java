package QLBanDoTheThao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "HoaDonNhap")
public class HoaDonNhapEntity {

	@Id
	@Column(name = "MaHDN")
	private String manhdn;
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "NgayLap")
	private Date ngaylap;
	
	
	@Column(name = "TongTien")
	private Double tongtien;
	
	@Column(name = "MaNV")
	private String manv;
	
	@Column(name = "MaPDH")
	private String maphd;
	
	@Column(name = "MaNCC")
	private String mancc;

	public String getManhdn() {
		return manhdn;
	}
	
	
	

	public HoaDonNhapEntity() {
		super();
	}




	public HoaDonNhapEntity(String manhdn, Date ngaylap, Double tongtien, String manv, String maphd, String mancc) {
		super();
		this.manhdn = manhdn;
		this.ngaylap = ngaylap;
		this.tongtien = tongtien;
		this.manv = manv;
		this.maphd = maphd;
		this.mancc = mancc;
	}




	public void setManhdn(String manhdn) {
		this.manhdn = manhdn;
	}

	public Date getNgaylap() {
		return ngaylap;
	}

	public void setNgaylap(Date ngaylap) {
		this.ngaylap = ngaylap;
	}

	public Double getTongtien() {
		return tongtien;
	}

	public void setTongtien(Double tongtien) {
		this.tongtien = tongtien;
	}

	public String getManv() {
		return manv;
	}

	public void setManv(String manv) {
		this.manv = manv;
	}

	public String getMaphd() {
		return maphd;
	}

	public void setMaphd(String maphd) {
		this.maphd = maphd;
	}

	public String getMancc() {
		return mancc;
	}

	public void setMancc(String mancc) {
		this.mancc = mancc;
	}
	
	
	

	
}
