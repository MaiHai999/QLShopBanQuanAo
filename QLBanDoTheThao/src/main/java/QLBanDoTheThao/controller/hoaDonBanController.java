package QLBanDoTheThao.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import QLBanDoTheThao.entity.CTHDBanEntity;
import QLBanDoTheThao.entity.HoaDonEntity;
import QLBanDoTheThao.entity.KhachHangEntity;
import QLBanDoTheThao.entity.KhuyenMaiEntity;
import QLBanDoTheThao.entity.SanPhamEntity;

@Transactional
@Controller
@RequestMapping("/hoadonban")
public class hoaDonBanController {

	@Autowired
	SessionFactory fatory3;

	@RequestMapping()
	public String home(HttpServletRequest request, ModelMap model) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 1) {
			
			model.addAttribute("check" , cd);
			
			//đẩy tên hiển thị trên thanh tìm kiếm
			model.addAttribute("hoten", tennv);
			
			// đẩy dữ liệu vào bảng danh sách sản phẩm
			List<HoaDonEntity> listHD = this.getALLHD();
			PagedListHolder pagedListHolder = new PagedListHolder(listHD);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(7);
			model.addAttribute("pagedListHolderBH", pagedListHolder);
			return "listHoaDonBanInterface";
		} else {
			return "login";
		}

	}

	@RequestMapping(params = "btnDetal")
	public String addSP(HttpServletRequest request, ModelMap model) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");
		
		if (myCheck != null && cd != 1) {
			model.addAttribute("check" , cd);
			
			//đẩy tên hiển thị trên thanh tìm kiếm
			model.addAttribute("hoten", tennv);
			
			// đẩy dữ liệu từ form về biến
			String mahd = request.getParameter("mahd");
			List<CTHDBanEntity> listCTHD = this.searchHD(mahd);

			// đẩy dữ liệu vào bảng danh sách sản phẩm
			List<HoaDonEntity> listHD = this.getALLHD();
			PagedListHolder pagedListHolder = new PagedListHolder(listHD);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(7);
			model.addAttribute("pagedListHolderBH", pagedListHolder);

			// sau khi có list thì đẩy dữ liệu lên bảng
			PagedListHolder pagedListHolder1 = new PagedListHolder(listCTHD);
			int page1 = ServletRequestUtils.getIntParameter(request, "p1", 0);
			pagedListHolder1.setPage(page1);
			pagedListHolder1.setMaxLinkedPages(5);
			pagedListHolder1.setPageSize(50);
			model.addAttribute("pagedListHolderGH", pagedListHolder1);

			return "listHoaDonBanInterface";
		} else {
			return "login";
		}

	}

	@RequestMapping(params = "btnsearchKH")
	public String search(HttpServletRequest request, ModelMap model) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");
		
		
		if (myCheck != null && cd != 1) {
			
			model.addAttribute("check" , cd);
			
			//đẩy tên hiển thị trên thanh tìm kiếm
			model.addAttribute("hoten", tennv);
			
			String key = request.getParameter("searchHD");
			List<HoaDonEntity> listHD = this.searchHDKey(key);

			// dùng để đổ dữ liệu vào bảng
			PagedListHolder pagedListHolder = new PagedListHolder(listHD);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(7);
			model.addAttribute("pagedListHolderBH", pagedListHolder);

			return "listHoaDonBanInterface";
		} else {
			return "login";
		}

	}

	// hàm này lấy toàn bộ hóa đơn
	public List<HoaDonEntity> getALLHD() {
		Session session = fatory3.getCurrentSession();
		String hql = "FROM HoaDonEntity";
		Query query = session.createQuery(hql);
		List<HoaDonEntity> list = query.list();
		return list;
	}

	// hàm này dùng để tìm chi tiết sản phẩm thông qua mã hóa đơn
	public List<CTHDBanEntity> searchHD(String mahd) {
		Session session = fatory3.getCurrentSession();
		String hql = "FROM CTHDBanEntity";
		Query query = session.createQuery(hql);
		List<CTHDBanEntity> list = query.list();

		List<CTHDBanEntity> listHD = new ArrayList<>();

		for (CTHDBanEntity ct : list) {
			if (ct.getId().getMahdb().strip().equals(mahd.strip())) {
				listHD.add(ct);
			}
		}
		return listHD;
	}

	// hàm này dùng để lấy khi search
	public List<HoaDonEntity> searchHDKey(String key) {
		List<HoaDonEntity> list = this.getALLHD();
		List<HoaDonEntity> listHDB = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		for (HoaDonEntity hd : list) {
			String dateString = formatter.format(hd.getNgaylap());
			if (dateString.strip().contains(key.strip())) {
				listHDB.add(hd);
			}
		}
		return listHDB;
	}

	// tạo contractor không đối số
	public hoaDonBanController() {
		super();
	}

}
