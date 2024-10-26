package dao;

import java.util.List;

import entities.DienThoai;

public interface DAODienThoaiInterface {
	public boolean addDienThoai(DienThoai dt);
	public List<DienThoai> getAllDienThoai();
	public List<DienThoai> getDienThoaiByIdNCC(int id);
	public List<DienThoai> getDienThoaiByCondition(String search);
	public boolean deleteDienThoai(int id, String uploadDir);
}