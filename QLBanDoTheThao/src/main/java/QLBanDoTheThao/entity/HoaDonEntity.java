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
@Table(name = "HoaDonBan")
public class HoaDonEntity {
	
	@Id
	@Column(name = "MaHDB")
	private String mahd;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "NgayLap")
	private Date ngaylap;
	
	@Column(name = "TongTien")
	private Double tongtien;
	
	
	@Column(name = "MaNV")
	private String manv;
	
	@Column(name = "MaKM")
	private String makm;
	
	
	
	public String getMahd() {
		return mahd;
	}
	public void setMahd(String mahd) {
		this.mahd = mahd;
	}
	public HoaDonEntity() {
		super();
	}
	public HoaDonEntity(String mahd, Date ngaylap, Double tongtien, String manv, String makm) {
		super();
		this.mahd = mahd;
		this.ngaylap = ngaylap;
		this.tongtien = tongtien;
		this.manv = manv;
		this.makm = makm;
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
	public String getMakm() {
		return makm;
	}
	public void setMakm(String makm) {
		this.makm = makm;
	}
	

}
