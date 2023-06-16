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
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import QLBanDoTheThao.entity.CTHDBanEntity;
import QLBanDoTheThao.entity.ChucDanhEntity;
import QLBanDoTheThao.entity.KhuyenMaiEntity;
import QLBanDoTheThao.entity.NhanVienEntity;
import QLBanDoTheThao.entity.SanPhamEntity;
import QLBanDoTheThao.entity.TaiKhoanEntity;

@Transactional
@Controller
@RequestMapping("/nhanvien")
public class nhanVienController {

	@Autowired
	SessionFactory fatory1;

	// khi truy cập vào thì "/nhanvien" thì nó sẽ đi đến cái này đầu tiên hàm khỏi
	// tạo
	@RequestMapping()
	public String home(HttpServletRequest request, ModelMap model) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd == 3) {

			model.addAttribute("check", cd);

			// đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);

			// lấy toàn bộ dự liệu
			List<NhanVienEntity> listNV = this.getAllNV();
			List<ChucDanhEntity> listCD = this.getAllCD();
			model.addAttribute("listCD", listCD);

			// kĩ thuật phân trang
			PagedListHolder pagedListHolder = new PagedListHolder(listNV);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(10);

			// thay đổi tên nút save thành "btnADD" để biết lúc này thì nó sẽ tạo mới chứ
			// không phải chỉnh sửa
			model.addAttribute("btnStatus", "btnAdd");
			model.addAttribute("pagedListHolder", pagedListHolder);

			return "nhanVienInterface";
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

		if (myCheck != null && cd == 3) {

			model.addAttribute("check", cd);

			// đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);

			// tạo một list lấy toàn bộ dự liệu tìm đc
			List<NhanVienEntity> listNV = this.searchNV(request.getParameter("searchInput"));

			// chức đẩy chức danh lên form
			List<ChucDanhEntity> listCD = this.getAllCD();
			model.addAttribute("listCD", listCD);

			// kĩ thuật phân trang
			PagedListHolder pagedListHolder = new PagedListHolder(listNV);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(10);
			model.addAttribute("pagedListHolder", pagedListHolder);
			return "nhanVienInterface";
		} else {
			return "login";
		}

	}

	// khi nhấn vô nút save thì url sẽ nhân thêm một tham số là "btnADD" từ đó sẽ
	// ánh xạ đến hàm này
	@RequestMapping(params = "btnAdd")
	public String addNV(HttpServletRequest request, ModelMap model, @RequestParam("manv") String manv,
			@RequestParam("honv") String honv, @RequestParam("tennv") String tennv, @RequestParam("cmnd") String cmnd,
			@RequestParam("email") String email, @RequestParam("sdt") String sdt,
			@RequestParam("gioitinh") String gioitinh, @RequestParam("ngaysinh") String ngaysinh,
			@RequestParam("comboboxTenCD") String macd) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv1 = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		String matk = this.creatMaTK();

		if (myCheck != null && cd == 3) {

			model.addAttribute("check", cd);

			// đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv1);

			if (honv.length() == 0 || tennv.length() == 0 || cmnd.length() == 0 || email.length() == 0
					|| sdt.length() == 0 || gioitinh.length() == 0 || macd.length() == 0 || matk.length() == 0) {
				model.addAttribute("message", "Thêm thất bại!");
				PagedListHolder pagedListHolder = new PagedListHolder(this.getAllNV());
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);
				pagedListHolder.setPage(page);
				pagedListHolder.setMaxLinkedPages(5);
				pagedListHolder.setPageSize(10);

				model.addAttribute("pagedListHolder", pagedListHolder);

			} else {

				try {
					String ma_nv = this.creatMaNV();
					String ma_tk = this.creatMaTK();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date ngay_sinh = sdf.parse(ngaysinh);

					// chức đẩy chức danh lên form
					List<ChucDanhEntity> listCD = this.getAllCD();
					model.addAttribute("listCD", listCD);

					// chèn nhân viên vào csdl
					NhanVienEntity nhanvien = new NhanVienEntity(ma_nv, honv, tennv, cmnd, email, sdt, gioitinh,
							ngay_sinh, macd, ma_tk);
					
					nhanvien.setTrangthai("yes");
					Integer temp = this.addNhanVien(nhanvien);
					if (temp != 0) {
						model.addAttribute("message", "Thêm thành công");
					} else {
						model.addAttribute("message", "Thêm thất bại!");
					}

					// chèn tài khoản vào bảng tài khoản
					String login = null;
					String password = null;
					String trangthai = "inactive";
					TaiKhoanEntity tk = new TaiKhoanEntity(ma_tk, login, password, ma_nv, trangthai);
					System.out.println(tk.getMatk() + "     đây là mã tk");
					Integer temp1 = this.addTaiKhoan(tk);

					// đẩy dữ liệu lên bảng
					PagedListHolder pagedListHolder = new PagedListHolder(this.getAllNV());
					int page = ServletRequestUtils.getIntParameter(request, "p", 0);
					pagedListHolder.setPage(page);
					pagedListHolder.setMaxLinkedPages(5);
					pagedListHolder.setPageSize(10);

					model.addAttribute("pagedListHolder", pagedListHolder);

				} catch (Exception e) {
					model.addAttribute("message", "Thêm thất bại!");
					PagedListHolder pagedListHolder = new PagedListHolder(this.getAllNV());
					int page = ServletRequestUtils.getIntParameter(request, "p", 0);
					pagedListHolder.setPage(page);
					pagedListHolder.setMaxLinkedPages(5);
					pagedListHolder.setPageSize(10);
					model.addAttribute("pagedListHolder", pagedListHolder);
					return "nhanVienInterface";
				}

			}

			return "nhanVienInterface";
		} else {
			return "login";
		}
	}

	// dùng để khi nhấn vào nút edit thì sẽ đẩy dữ liệu hiện thị lên các thanh đồng
	// thời nó sẽ thay đổi tên biến của nút save để biết mình chuẩn bị update chứ
	// không phải là tạo mới
	@RequestMapping(params = "btnEditManv")
	public String editNV(HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd == 3) {

			model.addAttribute("check", cd);

			// đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);

			String manv = request.getParameter("manv");

			List<NhanVienEntity> listNV = this.getAllNV();
			PagedListHolder pagedListHolder = new PagedListHolder(listNV);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(10);

			model.addAttribute("pagedListHolder", pagedListHolder);
			// thay đổi tên nút save để biết mình chỉnh sửa
			model.addAttribute("btnStatus", "btnEdit");

			// đẩy danh sách chức danh
			List<ChucDanhEntity> listCD = this.getAllCD();
			model.addAttribute("listCD", listCD);

			// đẩy dữ liệu nhân viên
			model.addAttribute("nhanvien", this.getNV(manv));
			model.addAttribute("selectedId", this.getNV(manv).getMacd());

			return "nhanVienInterface";
		} else {
			return "login";
		}
	}

	@RequestMapping(params = "btnEdit")
	public String editNV(HttpServletRequest request, ModelMap model, @RequestParam("manv") String manv,
			@RequestParam("honv") String honv, @RequestParam("tennv") String tennv, @RequestParam("cmnd") String cmnd,
			@RequestParam("email") String email, @RequestParam("sdt") String sdt,
			@RequestParam("gioitinh") String gioitinh, @RequestParam("ngaysinh") String ngaysinh,
			@RequestParam("comboboxTenCD") String macd, @RequestParam("matk") String matk) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv1 = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd == 3) {

			model.addAttribute("check", cd);

			// đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv1);

			if (honv.length() == 0 || tennv.length() == 0 || cmnd.length() == 0 || email.length() == 0
					|| sdt.length() == 0 || gioitinh.length() == 0 || macd.length() == 0 || matk.length() == 0) {
				model.addAttribute("message", "Update thất bại!");

				PagedListHolder pagedListHolder = new PagedListHolder(this.getAllNV());
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);
				pagedListHolder.setPage(page);
				pagedListHolder.setMaxLinkedPages(5);
				pagedListHolder.setPageSize(10);

				model.addAttribute("pagedListHolder", pagedListHolder);

			} else {

				try {

					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date ngay_sinh = sdf.parse(ngaysinh);

					// chức đẩy chức danh lên form
					List<ChucDanhEntity> listCD = this.getAllCD();
					model.addAttribute("listCD", listCD);

					NhanVienEntity nhanvien = new NhanVienEntity(manv, honv, tennv, cmnd, email, sdt, gioitinh,
							ngay_sinh, macd, matk);
					
					nhanvien.setTrangthai("yes");

					Integer temp = this.updateNhanVien(nhanvien);
					

					if (temp != 0) {
						model.addAttribute("message", "Update thành công");
					} else {
						model.addAttribute("message", "Update thất bại!");
					}

					PagedListHolder pagedListHolder = new PagedListHolder(this.getAllNV());
					int page = ServletRequestUtils.getIntParameter(request, "p", 0);
					pagedListHolder.setPage(page);
					pagedListHolder.setMaxLinkedPages(5);
					pagedListHolder.setPageSize(10);

					model.addAttribute("pagedListHolder", pagedListHolder);
				} catch (Exception e) {

					PagedListHolder pagedListHolder = new PagedListHolder(this.getAllNV());
					int page = ServletRequestUtils.getIntParameter(request, "p", 0);
					pagedListHolder.setPage(page);
					pagedListHolder.setMaxLinkedPages(5);
					pagedListHolder.setPageSize(10);

					model.addAttribute("pagedListHolder", pagedListHolder);
					model.addAttribute("message", "Update thất bại!");
					return "nhanVienInterface";
				}

			}

			return "nhanVienInterface";
		} else {
			return "login";
		}
	}

	// khi nhấn vào nút delete thì url sẽ gửi một param là tên của nút lấy tên đó để
	// ánh xạ vào hàm này và gửi thêm tên mã sảm phẩm về sever để biết muốn xóa sản
	// phẩm nào
	@RequestMapping(params = "btnDeleteManv")
	public String deleteNV(HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd == 3) {

			model.addAttribute("check", cd);

			// chức đẩy chức danh lên form
			List<ChucDanhEntity> listCD = this.getAllCD();
			model.addAttribute("listCD", listCD);

			// đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);

			String manv = request.getParameter("manv");
			
			NhanVienEntity nv = this.getNV(manv);
			nv.setTrangthai("no");

			Integer temp = this.updateNhanVien(nv);
			if (temp != 0) {
				model.addAttribute("message", "Delete thành công");
				TaiKhoanEntity tk = this.getTK(nv.getMatk());
				tk.setTrangthai("inactive");
				this.updateTaiKhoan(tk);
			} else {
				model.addAttribute("message", "Delete thất bại!");
			}

			PagedListHolder pagedListHolder = new PagedListHolder(this.getAllNV());
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);

			pagedListHolder.setPageSize(10);
			model.addAttribute("pagedListHolder", pagedListHolder);

			return "nhanVienInterface";
		} else {
			return "login";
		}
	}

	@RequestMapping(params = "btnTK")
	public String creatTK(ModelMap model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd == 3) {

			model.addAttribute("check", cd);
			// chức đẩy chức danh lên form
			List<ChucDanhEntity> listCD = this.getAllCD();
			model.addAttribute("listCD", listCD);

			// đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);
			// lấy tài khoản
			String matk = request.getParameter("matk");
			TaiKhoanEntity tk = this.getTK(matk);
			
			
			if (tk.getTrangthai().strip().equals("active")) {
				model.addAttribute("message", "Tài khoản đang hoạt động");

			} else {
				model.addAttribute("message", "Tài khoản ngưng hoạt động");

			}

			// đẩy tài khoản lên
			model.addAttribute("taikhoan", tk);

			return "taiKhoanInterface";
		} else {
			return "login";
		}

	}

	@RequestMapping(params = "btnThietLap")
	public String ThietLap(ModelMap model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd == 3) {

			model.addAttribute("check", cd);

			// chức đẩy chức danh lên form
			List<ChucDanhEntity> listCD = this.getAllCD();
			model.addAttribute("listCD", listCD);

			// đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);

			model.addAttribute("message", "Thiết lập tài khoản thành công");

			// lấy tài khoản
			String matk = request.getParameter("matk");
			String trangthai = request.getParameter("trangthai");
			String login = request.getParameter("login1");
			String matkhau = request.getParameter("matkhau");
			String manv = request.getParameter("manv");

			// kiểm tra xem tên tài khoản đã tồn tại hay chưa
			if (this.tonTaiTK(login, matk) == true || login.strip().length() == 0 || matkhau.strip().length() == 0) {
				model.addAttribute("message", "Thiết lập thất bại !!");
			} else {

				try {
					// chỉnh sửa dữ liệu
					String matkhaumahoa = this.hashPassword(matkhau.strip());

					TaiKhoanEntity tk1 = new TaiKhoanEntity(matk, login, matkhaumahoa, manv, trangthai);
					Integer temp = this.updateTaiKhoan(tk1);
					if (temp != 0) {
						model.addAttribute("message", "Thiết lập thành công!");
					} else {
						model.addAttribute("message", "Thiết lập thất bại!");
					}
				} catch (Exception e) {
					model.addAttribute("message", "Thiết lập thất bại !!");
				}

			}

			// đảy dữ liệu để hiện thị
			TaiKhoanEntity tk5 = new TaiKhoanEntity(matk, login, null, manv, trangthai);
			model.addAttribute("taikhoan", tk5);

			return "taiKhoanInterface";
		} else {
			return "login";
		}

	}

	@RequestMapping(params = "btnKhoa")
	public String KhoaTK(ModelMap model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd == 3) {

			model.addAttribute("check", cd);

			// đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);

			model.addAttribute("message", "Thiết lập tài khoản thành công");

			// lấy tài khoản
			String matk = request.getParameter("matk");
			String trangthai = "inactive";
			String login = request.getParameter("login1");
			String matkhau = request.getParameter("matkhau");
			String manv = request.getParameter("manv");

			TaiKhoanEntity tk1 = new TaiKhoanEntity(matk, login, matkhau, manv, trangthai);
			Integer temp = this.updateTaiKhoan(tk1);
			if (temp != 0) {
				model.addAttribute("message", "Khóa thành công");
			} else {
				model.addAttribute("message", "Khóa thất bại!");
			}

			// đảy dữ liệu để hiện thị
			TaiKhoanEntity tk = this.getTK(matk);
			model.addAttribute("taikhoan", tk);

			return "taiKhoanInterface";
		} else {
			return "login";
		}

	}

	@RequestMapping(params = "btnMoKhoa")
	public String MoKhoaTK(ModelMap model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd == 3) {

			model.addAttribute("check", cd);

			// đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);

			model.addAttribute("message", "Thiết lập tài khoản thành công");

			// lấy tài khoản
			String matk = request.getParameter("matk");
			String trangthai = "active";
			String login = request.getParameter("login1");
			String matkhau = request.getParameter("matkhau");
			String manv = request.getParameter("manv");

			TaiKhoanEntity tk1 = new TaiKhoanEntity(matk, login, matkhau, manv, trangthai);
			Integer temp = this.updateTaiKhoan(tk1);
			if (temp != 0) {
				model.addAttribute("message", "Mở khóa thành công");
			} else {
				model.addAttribute("message", "Mở khóa thất bại!");
			}

			// đảy dữ liệu để hiện thị
			TaiKhoanEntity tk = this.getTK(matk);
			model.addAttribute("taikhoan", tk);

			return "taiKhoanInterface";
		} else {
			return "login";
		}

	}

	@RequestMapping(params = "btnBack")
	public String back(ModelMap model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd == 3) {

			model.addAttribute("check", cd);

			// đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);
			// lấy toàn bộ dự liệu
			List<NhanVienEntity> listNV = this.getAllNV();
			List<ChucDanhEntity> listCD = this.getAllCD();
			model.addAttribute("listCD", listCD);

			// kĩ thuật phân trang
			PagedListHolder pagedListHolder = new PagedListHolder(listNV);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(10);

			// thay đổi tên nút save thành "btnADD" để biết lúc này thì nó sẽ tạo mới chứ
			// không phải chỉnh sửa
			model.addAttribute("btnStatus", "btnAdd");
			model.addAttribute("pagedListHolder", pagedListHolder);

			return "nhanVienInterface";
		} else {
			return "login";
		}

	}

	// hàm này dùng để tìm nhân viên
	// lấy toàn bộ dữ liệu để hiện thị
	public List<NhanVienEntity> getAllNV() {
		Session session = fatory1.getCurrentSession();
		String hql = "FROM NhanVienEntity where trangthai = 'yes'";
		Query query = session.createQuery(hql);
		List<NhanVienEntity> list = query.list();
		return list;
	}

	// dùng để lấy 1 nhân viên nếu biết mã nhân vien
	public NhanVienEntity getNV(String manv) {
		Session session = fatory1.getCurrentSession();
		String hql = "FROM NhanVienEntity where trangthai = 'yes' and MaNV = :manv";
		Query query = session.createQuery(hql);
		query.setParameter("manv", manv);
		NhanVienEntity list = (NhanVienEntity) query.list().get(0);
		return list;
	}

	// hàm này dùng để tìm nhân viên

	// hàm này chỉ cần nhập tên nhân viên thì nó sẽ đưa ra một list nhân viên có tên
	// giống như vậy
	public List<NhanVienEntity> searchNV(String tennv) {
		Session session = fatory1.getCurrentSession();
		String hql = "FROM NhanVienEntity where trangthai = 'yes' and Ten LIKE :tennv";
		Query query = session.createQuery(hql);
		query.setParameter("tennv", "%" + tennv + "%");
		List<NhanVienEntity> list = query.list();
		return list;
	}

	// hàm này sửa nhân viên

	// hàm này dùng để chỉnh sửa thông tin
	public Integer updateNhanVien(NhanVienEntity nv) {
		Session session = fatory1.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(nv);
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
	public Integer addNhanVien(NhanVienEntity nv) {

		Session session = fatory1.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.save(nv);
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
	public Integer deleteNhanVien(NhanVienEntity nv) {
		Session session = fatory1.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.delete(nv);
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
	public String creatMaNV() {
		Session session = fatory1.getCurrentSession();
		String hql = "FROM NhanVienEntity";
		Query query = session.createQuery(hql);
		List<NhanVienEntity> list = query.list();
		int max = 0;
		for (NhanVienEntity nv : list) {
			String a = nv.getManv().replace("nv", "");
			int b = Integer.parseInt(a.strip());
			if (b > max) {
				max = b;
			} else {
				max = max;
			}

		}

		String manv = "nv" + String.valueOf(max + 1);
		return manv;

	}

	// hàm này tạo list tên chức danh truyền vào combobox
	public List<ChucDanhEntity> getAllCD() {
		Session session = fatory1.getCurrentSession();
		String hql = "FROM ChucDanhEntity";
		Query query = session.createQuery(hql);
		List<ChucDanhEntity> list = query.list();

		return list;
	}

	// dùng để lấy 1 tàikhoanr nếu biết mã tài khoản
	public TaiKhoanEntity getTK(String matk) {
		Session session = fatory1.getCurrentSession();
		String hql = "FROM TaiKhoanEntity where MaTK = :matk";
		Query query = session.createQuery(hql);
		query.setParameter("matk", matk);
		TaiKhoanEntity list = (TaiKhoanEntity) query.list().get(0);

		return list;
	}

	// hàm này dùng để tại matk
	// hàm này dùng để tự động tạo mã tài khoản mới
	public String creatMaTK() {
		Session session = fatory1.getCurrentSession();
		String hql = "FROM TaiKhoanEntity";
		Query query = session.createQuery(hql);
		List<TaiKhoanEntity> list = query.list();
		int max = 0;
		for (TaiKhoanEntity tk : list) {
			String a = tk.getMatk().replace("tk", "");
			int b = Integer.parseInt(a.strip());
			if (b > max) {
				max = b;
			} else {
				max = max;
			}

		}
		String matk = "tk" + String.valueOf(max + 1);
		return matk;

	}

	// hàm này dùng để chèn tài khoản vào csdl
	public Integer addTaiKhoan(TaiKhoanEntity nv) {

		Session session = fatory1.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.save(nv);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	// hàm này dùng để chỉnh sửa thông tin tài khoản
	public Integer updateTaiKhoan(TaiKhoanEntity nv) {
		Session session = fatory1.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(nv);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	// xác định tên tài khoản đã tồn tại
	public boolean tonTaiTK(String login, String matk) {
		Session session = fatory1.getCurrentSession();
		String hql = "FROM TaiKhoanEntity";
		Query query = session.createQuery(hql);
		List<TaiKhoanEntity> list = query.list();
		for (TaiKhoanEntity tk : list) {
			if (tk.getMatk().strip().equals(matk.strip())) {
			} else {
				if (tk.getLogin() != null) {
					if (tk.getLogin().strip().equals(login.strip())) {
						return true;
					}
				}

			}

		}
		return false;

	}

	// hàm này dùng đễ mã hóa mật khẩu
	public static String hashPassword(String password) {
		// Mã hóa mật khẩu với salt mặc định
		String salt = BCrypt.gensalt();
		String hashedPassword = BCrypt.hashpw(password, salt);
		return hashedPassword;
	}

}
