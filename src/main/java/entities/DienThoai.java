package entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "dienthoai")
public class DienThoai {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maDT;
	@NotBlank(message = "Tên điện thoại không được để trống")
	private String tenDT;
	@NotNull(message = "Năm sản xuất không được để trống")
	@Min(value = 1000, message = "Năm sản xuất phải từ năm 1000 trở lên")
    @Max(value = 2024, message = "Năm sản xuất phải nhỏ hơn hoặc bằng 2024")
	private int namSanXuat;
	@NotBlank(message = "Cấu hình không được để trống")
	private String cauHinh;
	@ManyToOne
	@JoinColumn(name = "maNCC")
	private NhaCungCap nhaCungCap;
	@NotBlank(message = "Hỉnh ảnh không được để trống")
	@Pattern(regexp = ".+\\.(png|jpg|jpeg)$", message = "Hình ảnh chỉ chấp nhận định dạng png, jpg hoặc jpeg.")
	private String hinhAnh;
	
	public DienThoai() {
		super();
	}

	public DienThoai(int maDT, String tenDT, int namSanXuat, String cauHinh, NhaCungCap nhaCungCap, String hinhAnh) {
		super();
		this.maDT = maDT;
		this.tenDT = tenDT;
		this.namSanXuat = namSanXuat;
		this.cauHinh = cauHinh;
		this.nhaCungCap = nhaCungCap;
		this.hinhAnh = hinhAnh;
	}

	public DienThoai(String tenDT, int namSanXuat, String cauHinh, NhaCungCap nhaCungCap, String hinhAnh) {
		super();
		this.tenDT = tenDT;
		this.namSanXuat = namSanXuat;
		this.cauHinh = cauHinh;
		this.nhaCungCap = nhaCungCap;
		this.hinhAnh = hinhAnh;
	}

	public int getMaDT() {
		return maDT;
	}

	public void setMaDT(int maDT) {
		this.maDT = maDT;
	}

	public String getTenDT() {
		return tenDT;
	}

	public void setTenDT(String tenDT) {
		this.tenDT = tenDT;
	}

	public int getNamSanXuat() {
		return namSanXuat;
	}

	public void setNamSanXuat(int namSanXuat) {
		this.namSanXuat = namSanXuat;
	}

	public String getCauHinh() {
		return cauHinh;
	}

	public void setCauHinh(String cauHinh) {
		this.cauHinh = cauHinh;
	}

	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	@Override
	public String toString() {
		return "DienThoai [maDT=" + maDT + ", tenDT=" + tenDT + ", namSanXuat=" + namSanXuat + ", cauHinh=" + cauHinh
				+ ", nhaCungCap=" + nhaCungCap + ", hinhAnh=" + hinhAnh + "]";
	}
	
	
}