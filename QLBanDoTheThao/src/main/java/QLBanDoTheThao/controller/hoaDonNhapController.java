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

import QLBanDoTheThao.entity.CTHDBanEntity;
import QLBanDoTheThao.entity.CTHDNhapEntity;
import QLBanDoTheThao.entity.HoaDonEntity;
import QLBanDoTheThao.entity.HoaDonNhapEntity;

@Transactional
@Controller
@RequestMapping("/hoadonnhap")
public class hoaDonNhapController {
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
			
			//đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);
			
			List<HoaDonNhapEntity> listHD = this.getALLHDN();
			PagedListHolder pagedListHolder = new PagedListHolder(listHD);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(7);
			model.addAttribute("pagedListHolderBH", pagedListHolder);

			return "hoaDonNhapInterface";
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
			
			//đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);
			
			// đẩy dữ liệu từ form về biến
			String mahd = request.getParameter("mahdn");
			List<CTHDNhapEntity> listCTHD = this.searchHD(mahd);

			// đẩy dữ liệu vào bảng danh sách sản phẩm
			List<HoaDonNhapEntity> listHD = this.getALLHDN();
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

			return "hoaDonNhapInterface";
		} else {
			return "login";
		}

	}

	@RequestMapping(params = "btnsearchHDN")
	public String search(HttpServletRequest request, ModelMap model) {
		
		
		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");
		
		
		if(myCheck != null && cd != 1){
			model.addAttribute("check" , cd);
			
			//đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);
			
			String key = request.getParameter("searchHDN");
			List<HoaDonNhapEntity> listHD = this.searchHDKey(key);

			// đẩy dữ liệu vào bảng danh sách sản phẩm
			PagedListHolder pagedListHolder = new PagedListHolder(listHD);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(7);
			model.addAttribute("pagedListHolderBH", pagedListHolder);

			return "hoaDonNhapInterface";
		}else {
			return "login";
		}
		

		

	}

	// hàm này dùng để lấy toàn bộ hóa đơn
	public List<HoaDonNhapEntity> getALLHDN() {
		Session session = fatory3.getCurrentSession();
		String hql = "FROM HoaDonNhapEntity";
		Query query = session.createQuery(hql);
		List<HoaDonNhapEntity> list = query.list();
		return list;
	}

	// hàm này dùng để tìm chi tiết sản phẩm thông qua mã hóa đơn
	public List<CTHDNhapEntity> searchHD(String mahd) {
		Session session = fatory3.getCurrentSession();
		String hql = "FROM CTHDNhapEntity";
		Query query = session.createQuery(hql);
		List<CTHDNhapEntity> list = query.list();

		List<CTHDNhapEntity> listHD = new ArrayList<>();

		for (CTHDNhapEntity ct : list) {
			if (ct.getId().getMahdn().strip().equals(mahd.strip())) {
				listHD.add(ct);
			}
		}

		return listHD;
	}

	// hàm này dùng để lấy khi search
	public List<HoaDonNhapEntity> searchHDKey(String key) {
		List<HoaDonNhapEntity> list = this.getALLHDN();
		List<HoaDonNhapEntity> listHDB = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		for (HoaDonNhapEntity hd : list) {
			String dateString = formatter.format(hd.getNgaylap());
			if (dateString.strip().contains(key.strip())) {
				listHDB.add(hd);
			}
		}
		return listHDB;
	}

}
