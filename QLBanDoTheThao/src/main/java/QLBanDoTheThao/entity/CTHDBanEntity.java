package QLBanDoTheThao.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CTHDBan")
public class CTHDBanEntity {
	
	@EmbeddedId
	private CTHDBanID id;
	
	@Column(name = "MaKH")
	private String makh;
	
	@Column(name = "SoLuong")
	private Integer soluong;

	public CTHDBanID getId() {
		return id;
	}

	public void setId(CTHDBanID id) {
		this.id = id;
	}

	public String getMakh() {
		return makh;
	}

	public void setMakh(String makh) {
		this.makh = makh;
	}

	public Integer getSoluong() {
		return soluong;
	}

	public void setSoluong(Integer soluong) {
		this.soluong = soluong;
	}

	public CTHDBanEntity(CTHDBanID id, String makh, Integer soluong) {
		super();
		this.id = id;
		this.makh = makh;
		this.soluong = soluong;
	}

	public CTHDBanEntity() {
		super();
	}
	

}

