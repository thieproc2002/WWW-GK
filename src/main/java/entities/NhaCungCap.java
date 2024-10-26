package entities;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "nhacungcap")
public class NhaCungCap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maNCC;
	private String tenNhaCC;
	private String diaChi;
	private String soDienThoai;
	
	@OneToMany(mappedBy = "nhaCungCap")
	private List<DienThoai> listSDT;

	public NhaCungCap(int maNCC, String tenNhaCC, String diaChi, String soDienThoai, List<DienThoai> listSDT) {
		super();
		this.maNCC = maNCC;
		this.tenNhaCC = tenNhaCC;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.listSDT = listSDT;
	}

	public NhaCungCap() {
		super();
	}

	public NhaCungCap(String tenNhaCC, String diaChi, String soDienThoai) {
		super();
		this.tenNhaCC = tenNhaCC;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
	}

	public int getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(int maNCC) {
		this.maNCC = maNCC;
	}

	public String getTenNhaCC() {
		return tenNhaCC;
	}

	public void setTenNhaCC(String tenNhaCC) {
		this.tenNhaCC = tenNhaCC;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public List<DienThoai> getListSDT() {
		return listSDT;
	}

	public void setListSDT(List<DienThoai> listSDT) {
		this.listSDT = listSDT;
	}

	@Override
	public String toString() {
		return "NhaCungCap [maNCC=" + maNCC + ", tenNhaCC=" + tenNhaCC + ", diaChi=" + diaChi + ", soDienThoai="
				+ soDienThoai + "]";
	}
	
	
}