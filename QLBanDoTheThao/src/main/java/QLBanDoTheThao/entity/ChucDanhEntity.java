package QLBanDoTheThao.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ChucDanh")
public class ChucDanhEntity {

	@Id
	@Column(name = "MaCD")
	private String macd;
	
	@Column(name = "TenChucDanh")
	private String tencd;

//	@OneToMany(mappedBy = "chucdanh", fetch = FetchType.EAGER)
//	private Collection<NhanVienEntity> nhanvien;
	
	public String getMacd() {
		return macd;
	}

	public void setMacd(String macd) {
		this.macd = macd;
	}

	public String getTencd() {
		return tencd;
	}

	public void setTencd(String tencd) {
		this.tencd = tencd;
	}

	public ChucDanhEntity(String macd, String tencd) {
		super();
		this.macd = macd;
		this.tencd = tencd;
	}

	public ChucDanhEntity() {
		super();
	}
}
