package controller;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import utils.EntityManagerFactoryUtil;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import daoImpl.DAODienThoaiImpl;
import daoImpl.DAONhaCungCapImpl;
import entities.DienThoai;

/**
 * Servlet implementation class ViewServlet
 */
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EntityManagerFactoryUtil util;
	private EntityManager manager;
	private DAONhaCungCapImpl daoNCC;
	private DAODienThoaiImpl daoDienThoai;
       
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ViewServlet() {
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
		String show = request.getParameter("show");
		switch (show) {
		case "list": 
			doGetList(request, response);
			break;
			
		case "add": 
			doGetAdd(request, response);
			break;
			
		case "manage": 
			doGetManage(request, response);
			break;
		}
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGetList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<DienThoai> listDTLoc = (List<DienThoai>) request.getAttribute("listDTLoc");
		List<DienThoai> listDTSearch = (List<DienThoai>) request.getAttribute("listDTSearch");
		
		if (listDTLoc == null && listDTSearch == null) 
			request.setAttribute("listDT", daoDienThoai.getAllDienThoai());
		 else if (listDTLoc != null)
			request.setAttribute("listDT", listDTLoc);
		 else if (listDTSearch != null) 
			request.setAttribute("listDT", listDTSearch);
		
		
		request.setAttribute("listNCC", daoNCC.getAllNCC());
		request.setAttribute("page", "DanhSachDienThoaiNCC.jsp");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGetAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Set<ConstraintViolation<DienThoai>> violations = (Set<ConstraintViolation<DienThoai>>) request.getAttribute("errors");
		String errorNamSX = (String) request.getAttribute("errorNamSX");
		request.setAttribute("errors", violations);
		request.setAttribute("errorNamSX", errorNamSX);
		request.setAttribute("listNCC", daoNCC.getAllNCC());
		request.setAttribute("page", "DienThoaiForm.jsp");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGetManage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listDT", daoDienThoai.getAllDienThoai());
		request.setAttribute("page", "QuanLyForm.jsp");	
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}