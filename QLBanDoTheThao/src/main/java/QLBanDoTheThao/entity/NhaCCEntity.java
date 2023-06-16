package QLBanDoTheThao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NhaCungCap")
public class NhaCCEntity {
	
	@Id
	@Column(name = "MaNCC")
	private String mancc;
	
	@Column(name = "TenNCC")
	private String tenncc;
	

	public NhaCCEntity() {
		super();
	}

	public NhaCCEntity(String mancc, String tenncc) {
		super();
		this.mancc = mancc;
		this.tenncc = tenncc;
	}

	public String getMancc() {
		return mancc;
	}

	public void setMancc(String mancc) {
		this.mancc = mancc;
	}

	public String getTenncc() {
		return tenncc;
	}

	public void setTenncc(String tenncc) {
		this.tenncc = tenncc;
	}

}
