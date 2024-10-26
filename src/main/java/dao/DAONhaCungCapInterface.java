package dao;
import java.util.List;

import entities.NhaCungCap;

public interface DAONhaCungCapInterface {
	public boolean addNhaCungCap(NhaCungCap ncc);
	public List<NhaCungCap> getAllNCC();
	public NhaCungCap getNCCById(int id);
}
