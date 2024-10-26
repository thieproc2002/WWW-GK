package utils;


import daoImpl.DAONhaCungCapImpl;
import entities.NhaCungCap;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryUtil {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public EntityManagerFactoryUtil() {
		entityManagerFactory = Persistence.createEntityManagerFactory("quanlydienthoai");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void close() {
		entityManager.close();
		entityManagerFactory.close();
	}
	
	public static void main(String[] args) {
		EntityManagerFactoryUtil util = new EntityManagerFactoryUtil();
		
		try {
			EntityManager manager = util.getEntityManager();
			if (manager.isOpen()) {
				System.out.println("Connection successful");
				DAONhaCungCapImpl daoNCC = new DAONhaCungCapImpl(manager);
				daoNCC.addNhaCungCap(new NhaCungCap("Thế giới di động", "Gò Vấp", "0933888888"));
				daoNCC.addNhaCungCap(new NhaCungCap("Cellphone S", "Bình Thạnh", "0933333333"));
				daoNCC.addNhaCungCap(new NhaCungCap("Táo 24h", "Gò Vấp", "0933666666"));
				daoNCC.addNhaCungCap(new NhaCungCap("Topzone", "Phú Nhuận", "0933686868"));
				daoNCC.addNhaCungCap(new NhaCungCap("Minh Tuấn Mobile", "Quận 10", "0933939939"));
				daoNCC.addNhaCungCap(new NhaCungCap("FPT shop", "Gò Vấp", "0933222222"));
				daoNCC.addNhaCungCap(new NhaCungCap("Apple store", "Tân Bình", "0933123456"));
			} else 
				System.out.println("Connection failed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
