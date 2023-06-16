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
@Table(name = "KhuyenMai")
public class KhuyenMaiEntity {
	
	@Id
	@Column(name = "MaKM")
	private String makm;
	
	@Column(name = "TenKM")
	private String tenkm;
	
	@Column(name = "PhanTramKM")
	private Float phantramkm;
	
	@Column(name = "DieuKienKM")
	private Double dieukienkm;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "NgayBatDau")
	private Date ngaybatdau;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "NgayKetThuc")
	private Date ngayketthuc;
	
	
	@Column(name = "LoaiKhuyenMai")
	private String loaikm;
	
	public void clear() {
		this.makm = null;
		this.tenkm = null;
		this.ngaybatdau = null;
		this.ngayketthuc = null;
		this.loaikm = null;
	}
	
	
	
	
	public KhuyenMaiEntity(String makm, String tenkm, Date ngaybatdau, Date ngayketthuc, String loaikm) {
		super();
		this.makm = makm;
		this.tenkm = tenkm;
		this.ngaybatdau = ngaybatdau;
		this.ngayketthuc = ngayketthuc;
		this.loaikm = loaikm;
	}
	public KhuyenMaiEntity(String makm, String tenkm, Float phantramkm, Double dieukienkm, Date ngaybatdau,
			Date ngayketthuc, String loaikm) {
		super();
		this.makm = makm;
		this.tenkm = tenkm;
		this.phantramkm = phantramkm;
		this.dieukienkm = dieukienkm;
		this.ngaybatdau = ngaybatdau;
		this.ngayketthuc = ngayketthuc;
		this.loaikm = loaikm;
	}
	public KhuyenMaiEntity() {
		super();
	}
	public KhuyenMaiEntity(String makm, String tenkm, Float phantramkm, Double dieukienkm, Date ngaybatdau,
			Date ngayketthuc) {
		super();
		this.makm = makm;
		this.tenkm = tenkm;
		this.phantramkm = phantramkm;
		this.dieukienkm = dieukienkm;
		this.ngaybatdau = ngaybatdau;
		this.ngayketthuc = ngayketthuc;
	}
	public String getMakm() {
		return makm;
	}
	public void setMakm(String makm) {
		this.makm = makm;
	}
	public String getTenkm() {
		return tenkm;
	}
	public void setTenkm(String tenkm) {
		this.tenkm = tenkm;
	}
	public Float getPhantramkm() {
		return phantramkm;
	}
	public void setPhantramkm(Float phantramkm) {
		this.phantramkm = phantramkm;
	}
	public Double getDieukienkm() {
		return dieukienkm;
	}
	public void setDieukienkm(Double dieukienkm) {
		this.dieukienkm = dieukienkm;
	}
	public Date getNgaybatdau() {
		return ngaybatdau;
	}
	public void setNgaybatdau(Date ngaybatdau) {
		this.ngaybatdau = ngaybatdau;
	}
	public Date getNgayketthuc() {
		return ngayketthuc;
	}
	public void setNgayketthuc(Date ngayketthuc) {
		this.ngayketthuc = ngayketthuc;
	}
	
	
	

}
