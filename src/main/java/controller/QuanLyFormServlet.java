package controller;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.EntityManagerFactoryUtil;

import java.io.File;
import java.io.IOException;

import daoImpl.DAODienThoaiImpl;
import daoImpl.DAONhaCungCapImpl;

/**
 * Servlet implementation class QuanLyFormServlet
 */
public class QuanLyFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EntityManagerFactoryUtil util;
	private EntityManager manager;
	private DAONhaCungCapImpl daoNCC;
	private DAODienThoaiImpl daoDienThoai;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuanLyFormServlet() {
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
		String id = request.getParameter("id");
		String uploadDir = getServletContext().getInitParameter("uploadDir");
        String realPath = getServletContext().getRealPath("") + File.separator + uploadDir;
		daoDienThoai.deleteDienThoai(Integer.parseInt(id), realPath);
		request.setAttribute("listDT", daoDienThoai.getAllDienThoai());
		request.getRequestDispatcher("/show-view?show=manage").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
