package QLBanDoTheThao.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "NhanVien")
public class NhanVienEntity {
	
	@Id
	@Column(name = "MaNV")
	private String manv;
	
	@Column(name = "Ho")
	private String honv;
	
	@Column(name = "Ten")
	private String tennv;
	
	@Column(name = "CMND")
	private String cmnd;

	@Column(name = "Email")
	private String email;
	
	@Column(name = "SDT")
	private String sdt;
	
	@Column(name = "gioitinh")
	private String gioitinh;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "NgaySinh")
	private Date ngaysinh;
	
	@Column(name = "MaCD")
	private String macd;
	
	@Column(name = "MaTK")
	private String matk;
	
	@Column(name = "TrangThai")
	private String trangthai;
	
	
	

	public String getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	public String getManv() {
		return manv;
	}
	
//	@ManyToOne
//	@JoinColumn(name = "MaCD")
//	private ChucDanhEntity chucdanh;
//	
//	@OneToOne
//	@JoinColumn(name = "MaTK")
//	private TaiKhoanEntity taikhoan;

	public void setManv(String manv) {
		this.manv = manv;
	}

	public String getHonv() {
		return honv;
	}

	public void setHonv(String honv) {
		this.honv = honv;
	}

	public String getTennv() {
		return tennv;
	}

	public void setTennv(String tennv) {
		this.tennv = tennv;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}

	public Date getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public String getMacd() {
		return macd;
	}

	public void setMacd(String macd) {
		this.macd = macd;
	}

	public String getMatk() {
		return matk;
	}

	public void setMatk(String matk) {
		this.matk = matk;
	}
	
	public NhanVienEntity(String manv, String honv, String tennv, String cmnd, String email, String sdt, String gioitinh, Date ngaysinh, String macd, String matk) {
		super();
		this.manv = manv;
		this.honv = honv;
		this.tennv = tennv;
		this.cmnd = cmnd;
		this.email = email;
		this.sdt = sdt;
		this.gioitinh = gioitinh;
		this.ngaysinh = ngaysinh;
		this.macd = macd;
		this.matk = matk;
	}
	
	public NhanVienEntity() {
		super();
	}
}
