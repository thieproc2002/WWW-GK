package controller;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import utils.EntityManagerFactoryUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import daoImpl.DAODienThoaiImpl;
import daoImpl.DAONhaCungCapImpl;
import entities.DienThoai;
import entities.NhaCungCap;

/**
 * Servlet implementation class DienThoaiFormServlet
 */
@MultipartConfig
public class DienThoaiFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EntityManagerFactoryUtil util;
	private EntityManager manager;
	private DAONhaCungCapImpl daoNCC;
	private DAODienThoaiImpl daoDienThoai;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DienThoaiFormServlet() {
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
		String tenDT = request.getParameter("tenDT");
		String namSX = request.getParameter("namSX");
		String cauHinh = request.getParameter("cauHinh");
		String maNCC = request.getParameter("maNCC");
		NhaCungCap ncc = daoNCC.getNCCById(Integer.parseInt(maNCC));
		Part filePart = request.getPart("hinhAnh");
		int namSanXuat = 0;
		InputStream inputStream = null;
		String fileUploadName = "";
		
//		if (filePart != null && filePart.getSubmittedFileName() != null && !filePart.getSubmittedFileName().isEmpty()) {
            fileUploadName = filePart.getSubmittedFileName();
            inputStream = filePart.getInputStream();
            // Lấy đường dẫn thư mục 'uploads' từ context
            String uploadDir = getServletContext().getInitParameter("uploadDir");
            String realPath = getServletContext().getRealPath("") + File.separator + uploadDir;
            File uploadFolder = new File(realPath);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }
            // Lưu file vào thư mục uploads
            File fileToSave = new File(uploadFolder, fileUploadName);
            
            try {
            	namSanXuat = Integer.parseInt(namSX);
			} catch (Exception e) {
				request.setAttribute("errorNamSX", "Năm sản xuất không được để trống");
			}
            // Tạo đối tượng điện thoại
            DienThoai dt = new DienThoai(tenDT, namSanXuat, cauHinh, ncc, fileUploadName);
            
            
            // Tạo Validator
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            
            // Kiểm tra lỗi
            Set<ConstraintViolation<DienThoai>> violations = validator.validate(dt);
            
            if (!violations.isEmpty()) {
            	request.setAttribute("errors", violations);
            	request.getRequestDispatcher("/show-view?show=add").forward(request, response);
            } else {
                daoDienThoai.addDienThoai(dt);
                filePart.write(fileToSave.getAbsolutePath()); // Lưu file trực tiếp vào thư mục
                System.out.println("File đã được lưu vào: " + fileToSave.getAbsolutePath());
                request.getRequestDispatcher("/show-view?show=list").forward(request, response);
            }
            
            
//        } else 
//        	System.out.println("File part không có dữ liệu");
//		
		
	
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
