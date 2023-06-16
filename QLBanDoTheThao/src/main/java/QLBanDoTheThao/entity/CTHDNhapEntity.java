package QLBanDoTheThao.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CTHDNhap")
public class CTHDNhapEntity {
	
	@EmbeddedId
	private CTHDNhapID id;
	
	@Column(name = "SoLuong")
	private Integer soluong;

	@Column(name = "GiaCungCap")
	private Double gia;
	

	public CTHDNhapEntity(CTHDNhapID id, Integer soluong, Double gia) {
		super();
		this.id = id;
		this.soluong = soluong;
		this.gia = gia;
	}

	public CTHDNhapEntity() {
		super();
	}

	public CTHDNhapID getId() {
		return id;
	}

	public void setId(CTHDNhapID id) {
		this.id = id;
	}

	public Integer getSoluong() {
		return soluong;
	}

	public void setSoluong(Integer soluong) {
		this.soluong = soluong;
	}

	public Double getGia() {
		return gia;
	}

	public void setGia(Double gia) {
		this.gia = gia;
	}
	
	

}
