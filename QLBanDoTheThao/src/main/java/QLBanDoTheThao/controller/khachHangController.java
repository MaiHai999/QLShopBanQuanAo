package QLBanDoTheThao.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import QLBanDoTheThao.entity.KhachHangEntity;
import QLBanDoTheThao.entity.TaiKhoanEntity;

@Transactional
@Controller
@RequestMapping("/khachhang")
public class khachHangController {
	@Autowired
	SessionFactory fatory2;

	// khi truy cập vào thì "/sanpham" thì nó sẽ đi đến cái này đầu tiên hàm khỏi
	// tạo
	@RequestMapping()
	public String home(HttpServletRequest request, ModelMap model) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 2) {
			
			model.addAttribute("check" , cd);
			
			//đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);
			
			// lấy toàn bộ dự liệu
			List<KhachHangEntity> listSP1 = this.getAllKH();
			// kĩ thuật phân trang
			PagedListHolder pagedListHolder = new PagedListHolder(listSP1);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(10);

			// thay đổi tên nút save thành "btnADD" để biết lúc này thì nó sẽ tạo mới chứ
			// không phải chỉnh sửa
			model.addAttribute("btnStatus", "btnAdd");
			model.addAttribute("pagedListHolder", pagedListHolder);

			return "khachHangInterface";
		} else {
			return "login";
		}

	}

	// khi nhấn vào nút search thì form sẽ gửi về sever params= "btnsearch" thừ đó
	// ánh xạ vào hàm này
	@RequestMapping(params = "btnsearch")
	public String search(HttpServletRequest request, ModelMap model) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 2) {
			
			model.addAttribute("check" , cd);
			
			//đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);
			
			// tạo một list lấy toàn bộ dự liệu tìm đc
			List<KhachHangEntity> listSP = this.searchKH(request.getParameter("searchInput"));

			// kĩ thuật phân trang (không hiểu thì xem clip)
			PagedListHolder pagedListHolder = new PagedListHolder(listSP);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(10);
			model.addAttribute("pagedListHolder", pagedListHolder);
			return "khachHangInterface";
		} else {
			return "login";
		}

	}

	// dùng để khi nhấn vào nút edit thì sẽ đẩy dữ liệu hiện thị lên các thanh đồng
	// thời nó sẽ thay đổi tên biến của nút save để biết mình chuẩn bị update chứ
	// không phải là tạo mới
	@RequestMapping(value = "/{makh}.htm", params = "linkEdit")
	public String editSP(HttpServletRequest request, ModelMap model, @PathVariable("makh") String makh) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 2) {
			
			model.addAttribute("check" , cd);
			
			//đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);
			
			List<KhachHangEntity> listSP = this.getAllKH();
			PagedListHolder pagedListHolder = new PagedListHolder(listSP);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(10);

			model.addAttribute("pagedListHolder", pagedListHolder);
			// thay đổi tên nút save để biết mình chỉnh sửa
			model.addAttribute("btnStatus", "btnEdit");
			model.addAttribute("product", this.getKH(makh));
			return "khachHangInterface";
		} else {
			return "login";
		}

	}

	// tương tự cái xóa
	@RequestMapping(params = "btnEdit")
	public String edit_Product(HttpServletRequest request, ModelMap model, @RequestParam("makh") String makh,
			@RequestParam("tenkh") String ten, @RequestParam("gioitinh") String gioitinh,
			@RequestParam("tongchitieu") String tongchitieu, @RequestParam("sdt") String sdt) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 2) {
			model.addAttribute("check" , cd);
			
			//đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);
			
			if (ten.length() == 0 || gioitinh.length() == 0 || sdt.length() == 0) {
				model.addAttribute("message", "Update thất bại!");

				PagedListHolder pagedListHolder = new PagedListHolder(this.getAllKH());
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);
				pagedListHolder.setPage(page);
				pagedListHolder.setMaxLinkedPages(5);
				pagedListHolder.setPageSize(10);

				model.addAttribute("pagedListHolder", pagedListHolder);

			} else {

				try {
					Double tongchitieu1 = Double.valueOf(tongchitieu);
					KhachHangEntity product = new KhachHangEntity(makh, ten, gioitinh, tongchitieu1, sdt);
					product.setTrangthai("yes");

					Integer temp = this.updateKH(product);
					if (temp != 0) {
						model.addAttribute("message", "Update thành công");
					} else {
						model.addAttribute("message", "Update thất bại!");
					}

					PagedListHolder pagedListHolder = new PagedListHolder(this.getAllKH());
					int page = ServletRequestUtils.getIntParameter(request, "p", 0);
					pagedListHolder.setPage(page);
					pagedListHolder.setMaxLinkedPages(5);
					pagedListHolder.setPageSize(10);

					model.addAttribute("pagedListHolder", pagedListHolder);
				} catch (Exception e) {
					PagedListHolder pagedListHolder = new PagedListHolder(this.getAllKH());
					int page = ServletRequestUtils.getIntParameter(request, "p", 0);
					pagedListHolder.setPage(page);
					pagedListHolder.setMaxLinkedPages(5);
					pagedListHolder.setPageSize(10);

					model.addAttribute("pagedListHolder", pagedListHolder);
					model.addAttribute("message", "Update thất bại!");
					return "khachHangInterface";
				}

			}

			return "khachHangInterface";
		} else {
			return "login";
		}

	}

	// khi nhấn vào nút delete thì url sẽ gửi một param là tên của nút lấy tên đó để
	// ánh xạ vào hàm này và gửi thêm tên mã sảm phẩm về sever để biết muốn xóa sản
	// phẩm nào
	@RequestMapping(value = "/{makh}.htm", params = "linkDelete")
	public String deleteSP(HttpServletRequest request, ModelMap model, @PathVariable("makh") String makh) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");
		
		if (myCheck != null && cd != 2) {
			
			model.addAttribute("check" , cd);
			
			//đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);
			KhachHangEntity kh = this.getKH(makh);
			kh.setTrangthai("no");
			Integer temp = this.insertKH(kh);
			if (temp != 0) {
				model.addAttribute("message", "Delete thành công");
			} else {
				model.addAttribute("message", "Delete thất bại!");
			}

			PagedListHolder pagedListHolder = new PagedListHolder(this.getAllKH());
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);

			pagedListHolder.setPageSize(10);
			model.addAttribute("pagedListHolder", pagedListHolder);

			return "khachHangInterface";
		} else {
			return "login";
		}

	}

	// khi nhấn vô nút save thì url sẽ nhân thêm một tham số là "btnADD" từ đó sẽ
	// ánh xạ đến hàm này
	@RequestMapping(params = "btnAdd")
	public String insertProduct(HttpServletRequest request, ModelMap model, @RequestParam("tenkh") String ten,
			@RequestParam("gioitinh") String gioitinh, @RequestParam("tongchitieu") String tongchitieu,
			@RequestParam("sdt") String sdt) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 2) {
			
			model.addAttribute("check" , cd);
			
			//đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);
			
			
			if (ten.length() == 0 || gioitinh.length() == 0 || sdt.length() == 0 || tongchitieu.length() == 0) {
				model.addAttribute("message", "Thêm thất bại!");

				PagedListHolder pagedListHolder = new PagedListHolder(this.getAllKH());
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);
				pagedListHolder.setPage(page);
				pagedListHolder.setMaxLinkedPages(5);
				pagedListHolder.setPageSize(10);

				model.addAttribute("pagedListHolder", pagedListHolder);

			} else {

				try {
					String makh = this.creatMaKH();

					Double tongchitieu1 = Double.valueOf(tongchitieu);
					KhachHangEntity product = new KhachHangEntity(makh, ten, gioitinh, tongchitieu1, sdt);
					product.setTrangthai("yes");

					Integer temp = this.insertKH(product);
					if (temp != 0) {
						model.addAttribute("message", "Thêm thành công");
					} else {
						model.addAttribute("message", "Thêm thất bại!");
					}

					PagedListHolder pagedListHolder = new PagedListHolder(this.getAllKH());
					int page = ServletRequestUtils.getIntParameter(request, "p", 0);
					pagedListHolder.setPage(page);
					pagedListHolder.setMaxLinkedPages(5);
					pagedListHolder.setPageSize(10);

					model.addAttribute("pagedListHolder", pagedListHolder);
				} catch (Exception e) {
					model.addAttribute("message", "Thêm thất bại!");
					PagedListHolder pagedListHolder = new PagedListHolder(this.getAllKH());
					int page = ServletRequestUtils.getIntParameter(request, "p", 0);
					pagedListHolder.setPage(page);
					pagedListHolder.setMaxLinkedPages(5);
					pagedListHolder.setPageSize(10);
					model.addAttribute("pagedListHolder", pagedListHolder);
					return "khachHangInterface";
				}

			}

			return "khachHangInterface";
		} else {
			return "login";
		}

	}

	// lấy toàn bộ dữ liệu để hiện thị
	public List<KhachHangEntity> getAllKH() {
		Session session = fatory2.getCurrentSession();
		String hql = "FROM KhachHangEntity where trangthai = 'yes'";
		Query query = session.createQuery(hql);
		List<KhachHangEntity> list = query.list();
		return list;
	}

	// dùng để lấy 1 sản phẩm nếu biết mã sản phẩm
	public KhachHangEntity getKH(String makh) {
		Session session = fatory2.getCurrentSession();
		String hql = "FROM KhachHangEntity where trangthai = 'yes' and MaKH = :makh";
		Query query = session.createQuery(hql);
		query.setParameter("makh", makh);
		KhachHangEntity list = (KhachHangEntity) query.list().get(0);
		return list;
	}

	// hàm này chỉ cần nhập tên sản phẩm thì nó sẽ đưa ra một list sản phẩm có tên
	// giống như vậy
	public List<KhachHangEntity> searchKH(String tenkh) {
		Session session = fatory2.getCurrentSession();
		String hql = "FROM KhachHangEntity where trangthai = 'yes' and ten LIKE :ten";
		Query query = session.createQuery(hql);
		query.setParameter("ten", "%" + tenkh + "%");
		List<KhachHangEntity> list = query.list();
		return list;
	}

	// hàm này dùng để chỉnh sửa thông tin
	public Integer updateKH(KhachHangEntity pd) {
		Session session = fatory2.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(pd);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	// hàm này dùng để chèn vào cơ sở dữ liệu
	public Integer insertKH(KhachHangEntity pd) {

		Session session = fatory2.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.save(pd);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	// hàm này mở phiên làm việc rồi xóa
	public Integer deleteKH(KhachHangEntity pd) {
		Session session = fatory2.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.delete(pd);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	// hàm này dùng để tự động tạo mã sản phẩm mới
	public String creatMaKH() {
		Session session = fatory2.getCurrentSession();
		String hql = "FROM KhachHangEntity";
		Query query = session.createQuery(hql);
		List<KhachHangEntity> list = query.list();
		int max = 0;
		for (KhachHangEntity kh : list) {
			String a = kh.getMakh().replace("kh", "");
			int b = Integer.parseInt(a.strip());
			if (b > max) {
				max = b;
			} else {
				max = max;
			}

		}

		String makh = "kh" + String.valueOf(max + 1);
		return makh;

	}
}
