package controller;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.EntityManagerFactoryUtil;

import java.io.IOException;

import daoImpl.DAODienThoaiImpl;
import daoImpl.DAONhaCungCapImpl;

/**
 * Servlet implementation class DanhSachDienThoaiNCCServlet
 */
public class DanhSachDienThoaiNCCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EntityManagerFactoryUtil util;
	private EntityManager manager;
	private DAONhaCungCapImpl daoNCC;
	private DAODienThoaiImpl daoDienThoai;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DanhSachDienThoaiNCCServlet() {
        super();
        this.util = new EntityManagerFactoryUtil();
		this.manager = util.getEntityManager();
		this.daoNCC = new DAONhaCungCapImpl(manager);
		this.daoDienThoai = new DAODienThoaiImpl(manager);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maNCC = request.getParameter("maNCC");
		if (maNCC != null) {
			if (!maNCC.equals("tatCa")) {
				request.setAttribute("listDTLoc", daoDienThoai.getDienThoaiByIdNCC(Integer.parseInt(maNCC)));
			} else {
				request.setAttribute("listDTLoc", daoDienThoai.getAllDienThoai());
			}
		}
		
		String search = request.getParameter("search");
		if (search != null) {
			request.setAttribute("listDTSearch", daoDienThoai.getDienThoaiByCondition(search));
		}
		
		
		request.setAttribute("listNCC", daoNCC.getAllNCC());
		request.getRequestDispatcher("/show-view?show=list").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
