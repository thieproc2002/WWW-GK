package daoImpl;

import java.util.ArrayList;
import java.util.List;

import dao.DAONhaCungCapInterface;
import entities.NhaCungCap;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DAONhaCungCapImpl implements DAONhaCungCapInterface{
	private EntityManager en;
	
	public DAONhaCungCapImpl(EntityManager en) {
		super();
		this.en = en;
	}

	@Override
	public boolean addNhaCungCap(NhaCungCap ncc) {
		EntityTransaction trans = null;
		try {
			trans = en.getTransaction();
			trans.begin();
			en.persist(ncc);
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
	public List<NhaCungCap> getAllNCC() {
		List<NhaCungCap> ds = new ArrayList<NhaCungCap>();
		try {
			ds = en.createQuery("SELECT n FROM NhaCungCap n", NhaCungCap.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	@Override
	public NhaCungCap getNCCById(int id) {
		NhaCungCap ncc = null;
		try {
			ncc = en.createQuery("SELECT n FROM NhaCungCap n WHERE n.maNCC = :id", NhaCungCap.class)
					.setParameter("id", id)
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ncc;
	}

}
