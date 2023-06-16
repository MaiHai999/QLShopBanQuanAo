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

import QLBanDoTheThao.entity.SanPhamEntity;
import QLBanDoTheThao.entity.TaiKhoanEntity;

@Transactional
@Controller
@RequestMapping("/sanpham")
public class sanPhamController {

	@Autowired
	SessionFactory fatory1;

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
			List<SanPhamEntity> listSP = this.getAllSP();
			// kĩ thuật phân trang
			PagedListHolder pagedListHolder = new PagedListHolder(listSP);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(10);

			// thay đổi tên nút save thành "btnADD" để biết lúc này thì nó sẽ tạo mới chứ
			// không phải chỉnh sửa
			model.addAttribute("btnStatus", "btnAdd");
			model.addAttribute("pagedListHolder", pagedListHolder);

			return "sanPhamInterface";
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
			List<SanPhamEntity> listSP = this.searchSP(request.getParameter("searchInput"));

			// kĩ thuật phân trang (không hiểu thì xem clip)
			PagedListHolder pagedListHolder = new PagedListHolder(listSP);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(10);
			model.addAttribute("pagedListHolder", pagedListHolder);
			return "sanPhamInterface";
		} else {
			return "login";
		}

	}

	// dùng để khi nhấn vào nút edit thì sẽ đẩy dữ liệu hiện thị lên các thanh đồng
	// thời nó sẽ thay đổi tên biến của nút save để biết mình chuẩn bị update chứ
	// không phải là tạo mới
	@RequestMapping(value = "/{masp}.htm", params = "linkEdit")
	public String editSP(HttpServletRequest request, ModelMap model, @PathVariable("masp") String masp) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		
		if (myCheck != null && cd != 2) {
			
			model.addAttribute("check" , cd);
			
			//đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);
			
			List<SanPhamEntity> listSP = this.getAllSP();
			PagedListHolder pagedListHolder = new PagedListHolder(listSP);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(10);

			model.addAttribute("pagedListHolder", pagedListHolder);
			// thay đổi tên nút save để biết mình chỉnh sửa
			model.addAttribute("btnStatus", "btnEdit");
			model.addAttribute("product", this.getSP(masp));
			return "sanPhamInterface";
		} else {
			return "login";
		}

	}

	// tương tự cái xóa
	@RequestMapping(params = "btnEdit")
	public String edit_Product(HttpServletRequest request, ModelMap model, @RequestParam("product_name") String tensp,
			@RequestParam("soluong") String soluong, @RequestParam("list_price") String gia,
			@RequestParam("masp") String masp ,@RequestParam("size") String size, @RequestParam("hang") String hang) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 2) {
			
			model.addAttribute("check" , cd);
			
			//đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);
			
			if (tensp.length() == 0 || gia.length() == 0 || soluong.length() == 0) {
				model.addAttribute("message", "Update thất bại!");

				PagedListHolder pagedListHolder = new PagedListHolder(this.getAllSP());
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);
				pagedListHolder.setPage(page);
				pagedListHolder.setMaxLinkedPages(5);
				pagedListHolder.setPageSize(10);

				model.addAttribute("pagedListHolder", pagedListHolder);

			} else {

				try {
					Double gia1 = Double.valueOf(gia);
					int soluong1 = Integer.valueOf(soluong);
					SanPhamEntity product = new SanPhamEntity(masp, tensp, gia1, soluong1 , size , hang);

					Integer temp = this.updateProduct(product);
					if (temp != 0) {
						model.addAttribute("message", "Update thành công");
					} else {
						model.addAttribute("message", "Update thất bại!");
					}

					PagedListHolder pagedListHolder = new PagedListHolder(this.getAllSP());
					int page = ServletRequestUtils.getIntParameter(request, "p", 0);
					pagedListHolder.setPage(page);
					pagedListHolder.setMaxLinkedPages(5);
					pagedListHolder.setPageSize(10);

					model.addAttribute("pagedListHolder", pagedListHolder);
				} catch (Exception e) {
					PagedListHolder pagedListHolder = new PagedListHolder(this.getAllSP());
					int page = ServletRequestUtils.getIntParameter(request, "p", 0);
					pagedListHolder.setPage(page);
					pagedListHolder.setMaxLinkedPages(5);
					pagedListHolder.setPageSize(10);

					model.addAttribute("pagedListHolder", pagedListHolder);
					model.addAttribute("message", "Update thất bại!");
					return "sanPhamInterface";
				}

			}

			return "sanPhamInterface";
		} else {
			return "login";
		}

	}

	// khi nhấn vào nút delete thì url sẽ gửi một param là tên của nút lấy tên đó để
	// ánh xạ vào hàm này và gửi thêm tên mã sảm phẩm về sever để biết muốn xóa sản
	// phẩm nào
	@RequestMapping(value = "/{masp}.htm", params = "linkDelete")
	public String deleteSP(HttpServletRequest request, ModelMap model, @PathVariable("masp") String masp) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 2) {
			
			model.addAttribute("check" , cd);
			
			//đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);

			Integer temp = this.deleteProduct(this.getSP(masp));
			if (temp != 0) {
				model.addAttribute("message", "Delete thành công");
			} else {
				model.addAttribute("message", "Delete thất bại!");
			}

			PagedListHolder pagedListHolder = new PagedListHolder(this.getAllSP());
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);

			pagedListHolder.setPageSize(10);
			model.addAttribute("pagedListHolder", pagedListHolder);

			return "sanPhamInterface";
		} else {
			return "login";
		}

	}

	// khi nhấn vô nút save thì url sẽ nhân thêm một tham số là "btnADD" từ đó sẽ
	// ánh xạ đến hàm này
	@RequestMapping(params = "btnAdd")
	public String insertProduct(HttpServletRequest request, ModelMap model, @RequestParam("product_name") String tensp,
			@RequestParam("soluong") String soluong, @RequestParam("list_price") String gia) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 2) {
			
			model.addAttribute("check" , cd);
			
			//đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);
			
			if (tensp.length() == 0 || gia.length() == 0 || soluong.length() == 0) {
				model.addAttribute("message", "Thêm thất bại!");

				PagedListHolder pagedListHolder = new PagedListHolder(this.getAllSP());
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);
				pagedListHolder.setPage(page);
				pagedListHolder.setMaxLinkedPages(5);
				pagedListHolder.setPageSize(10);

				model.addAttribute("pagedListHolder", pagedListHolder);

			} else {

				try {
					String masp = this.creatMaSP();
					Double gia1 = Double.valueOf(gia);
					int soluong1 = Integer.valueOf(soluong);
					SanPhamEntity product = new SanPhamEntity(masp, tensp, gia1, soluong1);

					Integer temp = this.insertProduct(product);
					if (temp != 0) {
						model.addAttribute("message", "Thêm thành công");
					} else {
						model.addAttribute("message", "Thêm thất bại!");
					}

					PagedListHolder pagedListHolder = new PagedListHolder(this.getAllSP());
					int page = ServletRequestUtils.getIntParameter(request, "p", 0);
					pagedListHolder.setPage(page);
					pagedListHolder.setMaxLinkedPages(5);
					pagedListHolder.setPageSize(10);

					model.addAttribute("pagedListHolder", pagedListHolder);
				} catch (Exception e) {
					model.addAttribute("message", "Thêm thất bại!");
					PagedListHolder pagedListHolder = new PagedListHolder(this.getAllSP());
					int page = ServletRequestUtils.getIntParameter(request, "p", 0);
					pagedListHolder.setPage(page);
					pagedListHolder.setMaxLinkedPages(5);
					pagedListHolder.setPageSize(10);
					model.addAttribute("pagedListHolder", pagedListHolder);
					return "sanPhamInterface";
				}

			}

			return "sanPhamInterface";
		} else {
			return "login";
		}

	}

	// lấy toàn bộ dữ liệu để hiện thị
	public List<SanPhamEntity> getAllSP() {
		Session session = fatory1.getCurrentSession();
		String hql = "FROM SanPhamEntity";
		Query query = session.createQuery(hql);
		List<SanPhamEntity> list = query.list();
		return list;
	}

	// dùng để lấy 1 sản phẩm nếu biết mã sản phẩm
	public SanPhamEntity getSP(String masp) {
		Session session = fatory1.getCurrentSession();
		String hql = "FROM SanPhamEntity where MaSP = :masp";
		Query query = session.createQuery(hql);
		query.setParameter("masp", masp);
		SanPhamEntity list = (SanPhamEntity) query.list().get(0);
		return list;
	}

	// hàm này chỉ cần nhập tên sản phẩm thì nó sẽ đưa ra một list sản phẩm có tên
	// giống như vậy
	public List<SanPhamEntity> searchSP(String tensp) {
		Session session = fatory1.getCurrentSession();
		String hql = "FROM SanPhamEntity where tensp LIKE :tensp";
		Query query = session.createQuery(hql);
		query.setParameter("tensp", "%" + tensp + "%");
		List<SanPhamEntity> list = query.list();
		return list;
	}

	// hàm này dùng để chỉnh sửa thông tin
	public Integer updateProduct(SanPhamEntity pd) {
		Session session = fatory1.openSession();
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
	public Integer insertProduct(SanPhamEntity pd) {

		Session session = fatory1.openSession();
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
	public Integer deleteProduct(SanPhamEntity pd) {
		Session session = fatory1.openSession();
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
	public String creatMaSP() {
		Session session = fatory1.getCurrentSession();
		String hql = "FROM SanPhamEntity";
		Query query = session.createQuery(hql);
		List<SanPhamEntity> list = query.list();
		int max = 0;
		for (SanPhamEntity sp : list) {
			String a = sp.getMasp().replace("sp", "");
			int b = Integer.parseInt(a.strip());
			if (b > max) {
				max = b;
			} else {
				max = max;
			}

		}

		String masp = "sp" + String.valueOf(max + 1);
		return masp;

	}

}
