package daoImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import dao.DAODienThoaiInterface;
import entities.DienThoai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DAODienThoaiImpl implements DAODienThoaiInterface{
	private EntityManager en;
	
	public DAODienThoaiImpl(EntityManager en) {
		super();
		this.en = en;
	}

	@Override
	public boolean addDienThoai(DienThoai dt) {
		EntityTransaction trans = null;
		try {
			trans = en.getTransaction();
			trans.begin();
			en.persist(dt);
			trans.commit();
			return true;
		} catch (Exception e) {
			if (trans != null && trans.isActive()) {
				trans.rollback();
			}
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<DienThoai> getAllDienThoai() {
		List<DienThoai> ds = new ArrayList<DienThoai>();
		try {
			ds = en.createQuery("SELECT d FROM DienThoai d", DienThoai.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	@Override
	public List<DienThoai> getDienThoaiByIdNCC(int id) {
		List<DienThoai> ds = new ArrayList<DienThoai>();
		try {
			ds = en.createQuery("SELECT d FROM DienThoai d WHERE d.nhaCungCap.maNCC = :id", DienThoai.class)
					.setParameter("id", id)
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	@Override
	public List<DienThoai> getDienThoaiByCondition(String search) {
	    List<DienThoai> ds = new ArrayList<>();
	    try {
//	        ds = en.createQuery("SELECT d FROM DienThoai d "
//	                + "JOIN d.nhaCungCap n "
//	                + "WHERE (d.nhaCungCap.maNCC LIKE :search "
//	                + "OR n.tenNhaCC LIKE :search "
//	                + "OR n.diaChi LIKE :search "
//	                + "OR n.maNCC LIKE :search)", DienThoai.class)
//	                .setParameter("search", "%" + search + "%") 
//	                .getResultList();
	    	 
	    	ds = en.createQuery("SELECT d FROM DienThoai d "
	                + "JOIN d.nhaCungCap n "
	                + "WHERE CAST(n.maNCC AS string) LIKE :search "
	                + "OR n.tenNhaCC LIKE :search "
	                + "OR n.soDienThoai LIKE :search "
	                + "OR n.diaChi LIKE :search"
	               , DienThoai.class)
	                .setParameter("search", "%" + search + "%") 
	                .getResultList();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return ds;
	}

	@Override
	public boolean deleteDienThoai(int id, String uploadDir) {
		EntityTransaction trans = null;
		try {
			trans = en.getTransaction();
			trans.begin();
			DienThoai dt = en.find(DienThoai.class, id);
			if (dt != null) {
	            File uploadFolder = new File(uploadDir);
	            File fileToDelete = new File(uploadFolder, dt.getHinhAnh());

				en.remove(dt);
				trans.commit();
				
				// Xóa file ảnh
	            if (fileToDelete.exists()) {
	                boolean isDeleted = fileToDelete.delete();
	                if (isDeleted) {
	                    System.out.println("File ảnh đã bị xóa: " + fileToDelete.getAbsolutePath());
	                } else {
	                    System.out.println("Không thể xóa file ảnh: " + fileToDelete.getAbsolutePath());
	                }
	            }
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (trans != null && trans.isActive()) {
				trans.rollback();
			}
		}
		return false;
	}


	
}
