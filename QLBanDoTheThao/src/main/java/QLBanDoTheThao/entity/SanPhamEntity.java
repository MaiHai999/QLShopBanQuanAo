package QLBanDoTheThao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "SanPham")
public class SanPhamEntity {
	
	@Id
	@Column(name = "MaSP")
	private String masp;
	
	@Column(name = "TenSP")
	private String tensp;
	
	@Column(name = "GiaBan")
	private double gia;
	
	@Column(name = "SoLuongSPCon")
	private int soluong;
	
	@Column(name = "Size")
	private String size;
	
	@Column(name = "Hang")
	private String hang;
	
	@Transient
	private double ptkm;
	
	public double getPtkm() {
		return ptkm;
	}
	public void setPtkm(double ptkm) {
		this.ptkm = ptkm;
	}
	public SanPhamEntity(String masp, String tensp, double gia, int soluong, String size, String hang) {
		super();
		this.masp = masp;
		this.tensp = tensp;
		this.gia = gia;
		this.soluong = soluong;
		this.size = size;
		this.hang = hang;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getHang() {
		return hang;
	}
	public void setHang(String hang) {
		this.hang = hang;
	}
	public String getMasp() {
		return masp;
	}
	public void setMasp(String masp) {
		this.masp = masp;
	}
	public String getTensp() {
		return tensp;
	}
	public void setTensp(String tensp) {
		this.tensp = tensp;
	}
	public double getGia() {
		return gia;
	}
	public void setGia(double gia) {
		this.gia = gia;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public SanPhamEntity(String masp, String tensp, double gia, int soluong) {
		super();
		this.masp = masp;
		this.tensp = tensp;
		this.gia = gia;
		this.soluong = soluong;
	}
	public SanPhamEntity() {
		super();
	}
	
	

}
