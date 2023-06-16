package QLBanDoTheThao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "KhachHang")
public class KhachHangEntity {
	
	@Id
	@Column(name = "MaKH")
	private String makh;
	
	@Column(name = "Ten")
	private String ten;
	
	@Column(name = "GioiTinh")
	private String gioitinh;
	
	@Column(name = "TongChiTieu")
	private Double tongchitieu;
	
	@Column(name = "SDT")
	private String sdt;
	
	@Column(name = "TrangThai")
	private String trangthai;
	
	public String getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}
	public KhachHangEntity() {
		super();
	}
	public KhachHangEntity(String makh, String ten, String gioitinh, Double tongchitieu, String sdt) {
		super();
		this.makh = makh;
		this.ten = ten;
		this.gioitinh = gioitinh;
		this.tongchitieu = tongchitieu;
		this.sdt = sdt;
	}
	public String getMakh() {
		return makh;
	}
	public void setMakh(String makh) {
		this.makh = makh;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getGioitinh() {
		return gioitinh;
	}
	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}
	public Double getTongchitieu() {
		return tongchitieu;
	}
	public void setTongchitieu(Double tongchitieu) {
		this.tongchitieu = tongchitieu;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	
	

}
