package QLBanDoTheThao.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "CTKhuyenMai")
public class CTKhuyenMaiEntity {
	
	@EmbeddedId
	private CTKhuyenMaiID id;
	
	@Column(name = "PhanTramKM")
	private Double soluong;
	
	public CTKhuyenMaiEntity() {
		super();
	}

	public CTKhuyenMaiID getId() {
		return id;
	}

	public void setId(CTKhuyenMaiID id) {
		this.id = id;
	}

	public Double getSoluong() {
		return soluong;
	}

	public void setSoluong(Double soluong) {
		this.soluong = soluong;
	}

	public CTKhuyenMaiEntity(CTKhuyenMaiID id, Double soluong) {
		super();
		this.id = id;
		this.soluong = soluong;
	}

	
}
