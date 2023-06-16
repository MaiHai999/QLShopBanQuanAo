package QLBanDoTheThao.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import QLBanDoTheThao.entity.KhuyenMaiEntity;

@Transactional
@Controller
@RequestMapping("/khuyenmai")
public class khuyenMaiController {
	@Autowired
	SessionFactory fatory3;
	
	//biết toàn cục 
	String loaikm = "KM2HD";

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
			List<KhuyenMaiEntity> listSP1 = this.getAllKM();
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

			return "khuyenMaiInterface";
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
			List<KhuyenMaiEntity> listSP = this.searchKH(request.getParameter("searchInputKM"));

			// kĩ thuật phân trang (không hiểu thì xem clip)
			PagedListHolder pagedListHolder = new PagedListHolder(listSP);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(10);
			model.addAttribute("pagedListHolder", pagedListHolder);
			return "khuyenMaiInterface";
		} else {
			return "login";
		}

	}

	// dùng để khi nhấn vào nút edit thì sẽ đẩy dữ liệu hiện thị lên các thanh đồng
	// thời nó sẽ thay đổi tên biến của nút save để biết mình chuẩn bị update chứ
	// không phải là tạo mới
	@RequestMapping(value = "/{makm}.htm", params = "linkEdit")
	public String editSP(HttpServletRequest request, ModelMap model, @PathVariable("makm") String makh) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 2) {
			
			model.addAttribute("check" , cd);
			
			
			//đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);

			List<KhuyenMaiEntity> listSP = this.getAllKM();
			PagedListHolder pagedListHolder = new PagedListHolder(listSP);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(10);

			model.addAttribute("pagedListHolder", pagedListHolder);
			// thay đổi tên nút save để biết mình chỉnh sửa
			model.addAttribute("btnStatus", "btnEdit");
			model.addAttribute("product", this.getKH(makh));

			return "khuyenMaiInterface";
		} else {
			return "login";
		}

	}

	// tương tự cái xóa
	@RequestMapping(params = "btnEdit")
	public String edit_Product(HttpServletRequest request, ModelMap model, @RequestParam("makm") String makm,
			@RequestParam("tenkm") String tenkm, @RequestParam("phantramkm") String phantramkm,
			@RequestParam("dieukienkm") String dieukienkm, @RequestParam("ngaybatdau") String ngaybatdau,
			@RequestParam("ngayketthuc") String ngayketthuc) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 2) {
			
			model.addAttribute("check" , cd);
			
			//đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);

			if (tenkm.length() == 0 || phantramkm.length() == 0 || dieukienkm.length() == 0 || ngaybatdau.length() == 0
					|| ngayketthuc.length() == 0) {
				model.addAttribute("message", "Update thất bại!");

				PagedListHolder pagedListHolder = new PagedListHolder(this.getAllKM());
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);
				pagedListHolder.setPage(page);
				pagedListHolder.setMaxLinkedPages(5);
				pagedListHolder.setPageSize(10);

				model.addAttribute("pagedListHolder", pagedListHolder);

			} else {

				try {
					Float phantramkm1 = Float.valueOf(phantramkm);
					Double dieukienkm1 = Double.valueOf(dieukienkm);
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date ngayBD = sdf.parse(ngaybatdau);

					SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
					Date ngayKT = sdf2.parse(ngayketthuc);
					KhuyenMaiEntity product = new KhuyenMaiEntity(makm, tenkm, phantramkm1, dieukienkm1, ngayBD,
							ngayKT ,loaikm );

					Integer temp = this.updateKH(product);

					if (temp != 0) {
						model.addAttribute("message", "Update thành công");
					} else {
						model.addAttribute("message", "Update thất bại!");
					}

					PagedListHolder pagedListHolder = new PagedListHolder(this.getAllKM());
					int page = ServletRequestUtils.getIntParameter(request, "p", 0);
					pagedListHolder.setPage(page);
					pagedListHolder.setMaxLinkedPages(5);
					pagedListHolder.setPageSize(10);

					model.addAttribute("pagedListHolder", pagedListHolder);
				} catch (Exception e) {
					PagedListHolder pagedListHolder = new PagedListHolder(this.getAllKM());
					int page = ServletRequestUtils.getIntParameter(request, "p", 0);
					pagedListHolder.setPage(page);
					pagedListHolder.setMaxLinkedPages(5);
					pagedListHolder.setPageSize(10);

					model.addAttribute("pagedListHolder", pagedListHolder);
					model.addAttribute("message", "Update thất bại!");
					return "khuyenMaiInterface";
				}

			}

			return "khuyenMaiInterface";
		} else {
			return "login";
		}

	}

	// khi nhấn vào nút delete thì url sẽ gửi một param là tên của nút lấy tên đó để
	// ánh xạ vào hàm này và gửi thêm tên mã sảm phẩm về sever để biết muốn xóa sản
	// phẩm nào
	@RequestMapping(value = "/{makm}.htm", params = "linkDelete")
	public String deleteSP(HttpServletRequest request, ModelMap model, @PathVariable("makm") String makm) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 2) {
			
			model.addAttribute("check" , cd);
			
			//đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);

			Integer temp = this.deleteKH(this.getKH(makm));
			if (temp != 0) {
				model.addAttribute("message", "Delete thành công");
			} else {
				model.addAttribute("message", "Delete thất bại!");
			}

			PagedListHolder pagedListHolder = new PagedListHolder(this.getAllKM());
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);

			pagedListHolder.setPageSize(10);
			model.addAttribute("pagedListHolder", pagedListHolder);

			return "khuyenMaiInterface";
		} else {
			return "login";
		}

	}

	// khi nhấn vô nút save thì url sẽ nhân thêm một tham số là "btnADD" từ đó sẽ
	// ánh xạ đến hàm này
	@RequestMapping(params = "btnAdd")
	public String insertProduct(HttpServletRequest request, ModelMap model, @RequestParam("tenkm") String tenkm,
			@RequestParam("phantramkm") String phantramkm, @RequestParam("dieukienkm") String dieukienkm,
			@RequestParam("ngaybatdau") String ngaybatdau, @RequestParam("ngayketthuc") String ngayketthuc) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 2) {
			
			model.addAttribute("check" , cd);
			
			
			//đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);
			
			if (tenkm.length() == 0 || phantramkm.length() == 0 || dieukienkm.length() == 0 || ngaybatdau.length() == 0
					|| ngayketthuc.length() == 0) {
				model.addAttribute("message", "Thêm thất bại!");

				PagedListHolder pagedListHolder = new PagedListHolder(this.getAllKM());
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);
				pagedListHolder.setPage(page);
				pagedListHolder.setMaxLinkedPages(5);
				pagedListHolder.setPageSize(10);

				model.addAttribute("pagedListHolder", pagedListHolder);

			} else {

				try {
					String makm = this.creatMaKM();

					Float phantramkm1 = Float.valueOf(phantramkm);
					Double dieukienkm1 = Double.valueOf(dieukienkm);
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date ngayBD = sdf.parse(ngaybatdau);

					SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
					Date ngayKT = sdf2.parse(ngayketthuc);

					KhuyenMaiEntity product = new KhuyenMaiEntity(makm, tenkm, phantramkm1, dieukienkm1, ngayBD,
							ngayKT , loaikm);

					Integer temp = this.insertKH(product);
					if (temp != 0) {
						model.addAttribute("message", "Thêm thành công");
					} else {
						model.addAttribute("message", "Thêm thất bại!");
					}

					PagedListHolder pagedListHolder = new PagedListHolder(this.getAllKM());
					int page = ServletRequestUtils.getIntParameter(request, "p", 0);
					pagedListHolder.setPage(page);
					pagedListHolder.setMaxLinkedPages(5);
					pagedListHolder.setPageSize(10);

					model.addAttribute("pagedListHolder", pagedListHolder);
				} catch (Exception e) {
					model.addAttribute("message", "Thêm thất bại!");
					PagedListHolder pagedListHolder = new PagedListHolder(this.getAllKM());
					int page = ServletRequestUtils.getIntParameter(request, "p", 0);
					pagedListHolder.setPage(page);
					pagedListHolder.setMaxLinkedPages(5);
					pagedListHolder.setPageSize(10);
					model.addAttribute("pagedListHolder", pagedListHolder);
					return "khuyenMaiInterface";
				}

			}

			return "khuyenMaiInterface";
		} else {
			return "login";
		}

	}

	// lấy toàn bộ dữ liệu để hiện thị
	public List<KhuyenMaiEntity> getAllKM() {
		Session session = fatory3.getCurrentSession();
		String hql = "FROM KhuyenMaiEntity where LoaiKhuyenMai = 'KM2HD'";
		Query query = session.createQuery(hql);
		List<KhuyenMaiEntity> list = query.list();
		return list;
	}

	// dùng để lấy 1 sản phẩm nếu biết mã sản phẩm
	public KhuyenMaiEntity getKH(String makm) {
		Session session = fatory3.getCurrentSession();
		String hql = "FROM KhuyenMaiEntity where MaKM = :makm";
		Query query = session.createQuery(hql);
		query.setParameter("makm", makm);
		KhuyenMaiEntity list = (KhuyenMaiEntity) query.list().get(0);
		return list;
	}

	// hàm này chỉ cần nhập tên sản phẩm thì nó sẽ đưa ra một list sản phẩm có tên
	// giống như vậy
	public List<KhuyenMaiEntity> searchKH(String tenkm) {
		Session session = fatory3.getCurrentSession();
		String hql = "FROM KhuyenMaiEntity where LoaiKhuyenMai = 'KM2HD' and TenKM LIKE :TenKM ";
		Query query = session.createQuery(hql);
		query.setParameter("TenKM", "%" + tenkm + "%");
		List<KhuyenMaiEntity> list = query.list();
		return list;
	}

	// hàm này dùng để chỉnh sửa thông tin
	public Integer updateKH(KhuyenMaiEntity pd) {
		Session session = fatory3.openSession();
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
	public Integer insertKH(KhuyenMaiEntity pd) {

		Session session = fatory3.openSession();
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
	public Integer deleteKH(KhuyenMaiEntity pd) {
		Session session = fatory3.openSession();
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
	public String creatMaKM() {
		Session session = fatory3.getCurrentSession();
		String hql = "FROM KhuyenMaiEntity";
		Query query = session.createQuery(hql);
		List<KhuyenMaiEntity> list = query.list();
		int max = 0;
		for (KhuyenMaiEntity km : list) {
			String a = km.getMakm().replace("km", "");
			int b = Integer.parseInt(a.strip());
			if (b > max) {
				max = b;
			} else {
				max = max;
			}

		}

		String makm = "km" + String.valueOf(max + 1);
		return makm;

	}
}
