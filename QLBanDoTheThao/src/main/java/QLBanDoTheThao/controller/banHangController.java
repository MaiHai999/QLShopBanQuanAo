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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import QLBanDoTheThao.entity.CTHDBanEntity;
import QLBanDoTheThao.entity.CTHDBanID;
import QLBanDoTheThao.entity.HoaDonEntity;
import QLBanDoTheThao.entity.KhachHangEntity;
import QLBanDoTheThao.entity.KhuyenMaiEntity;
import QLBanDoTheThao.entity.SanPhamEntity;

@Transactional
@Controller
@RequestMapping("/banhang")
public class banHangController {

	@Autowired
	SessionFactory fatory2;

	// các biến toàn cục
	List<SanPhamEntity> listSP1 = new ArrayList<>();

	@RequestMapping()
	public String home(HttpServletRequest request, ModelMap model) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		String manv = (String) session.getAttribute("manv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 2) {

			model.addAttribute("check", cd);

			// đẩy tên hiển thị trên thanh tìm kiếm
			model.addAttribute("hoten", tennv);

			// đẩy dữ liệu vào khuyến mãi
			List<KhuyenMaiEntity> listKM = this.getALLKMDK(this.tongtien(request));
			model.addAttribute("listKM", listKM);

			// đẩy dữ liệu vào bảng danh sách sản phẩm
			List<SanPhamEntity> listSP = this.getAllSP();
			PagedListHolder pagedListHolder = new PagedListHolder(listSP);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(5);
			model.addAttribute("pagedListHolderBH", pagedListHolder);

			// đẩy dữ liệu vào bảng chi tiết sản phẩm
			PagedListHolder pagedListHolder1 = new PagedListHolder(listSP1);
			int page1 = ServletRequestUtils.getIntParameter(request, "p1", 0);
			pagedListHolder1.setPage(page1);
			pagedListHolder1.setMaxLinkedPages(5);
			pagedListHolder1.setPageSize(50);
			model.addAttribute("pagedListHolderGH", pagedListHolder1);

			return "banHangInterface";
		} else {
			return "login";
		}

	}

	@RequestMapping(params = "btnSale")
	public String addSP(HttpServletRequest request, ModelMap model) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		String manv = (String) session.getAttribute("manv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 2) {
			model.addAttribute("check", cd);

			// đẩy tên hiển thị trên thanh tìm kiếm
			model.addAttribute("hoten", tennv);

			// đẩy dữ liệu từ form về biến
			String masp = request.getParameter("masp");
			String soluong = request.getParameter("soluong");
			String gia = request.getParameter("price");
			String tensp = request.getParameter("tenProduct");

			// éo liểu dữ liệu đồng thời ép vào biến toàn cục
			Double gia1 = Double.valueOf(gia);
			int soluong1 = Integer.valueOf(soluong);

			if (soluong1 > 0 && this.exisSP(masp) == false && this.checkSL(masp, soluong1)) {
				SanPhamEntity product = new SanPhamEntity(masp, tensp, gia1, soluong1);
				listSP1.add(product);
			} else {
				model.addAttribute("message", "Thêm vào giỏ hàng thất bại!!!");
			}

			// đẩy dữ liệu lên khuyễn mãi
			List<KhuyenMaiEntity> listKM = this.getALLKMDK(this.tongtien(request));
			model.addAttribute("listKM", listKM);

			// dùng để đẩy dữ liệu lên bảng danh sách sản phẩm
			List<SanPhamEntity> listSP = this.getAllSP();
			PagedListHolder pagedListHolder = new PagedListHolder(listSP);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(5);
			model.addAttribute("pagedListHolderBH", pagedListHolder);

			// dùng để đẩy dữ liệu vào bảng hóa đơn
			PagedListHolder pagedListHolder1 = new PagedListHolder(listSP1);
			int page1 = ServletRequestUtils.getIntParameter(request, "p1", 0);
			pagedListHolder1.setPage(page1);
			pagedListHolder1.setMaxLinkedPages(5);
			pagedListHolder1.setPageSize(50);
			model.addAttribute("pagedListHolderGH", pagedListHolder1);

			return "banHangInterface";
		} else {
			return "login";
		}

	}

	@RequestMapping(params = "btnDeletePD")
	public String deleteSP(HttpServletRequest request, ModelMap model) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		String manv = (String) session.getAttribute("manv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 2) {

			model.addAttribute("check", cd);

			// đẩy tên hiển thị trên thanh tìm kiếm
			model.addAttribute("hoten", tennv);

			String masp = request.getParameter("masp");
			int index = this.index(masp);
			listSP1.remove(index);

			// đẩy dữ liệu lên khuyễn mãi
			List<KhuyenMaiEntity> listKM = this.getALLKMDK(this.tongtien(request));
			model.addAttribute("listKM", listKM);

			// dùng để đẩy dữ liệu lên bảng
			List<SanPhamEntity> listSP = this.getAllSP();
			PagedListHolder pagedListHolder = new PagedListHolder(listSP);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(5);
			model.addAttribute("pagedListHolderBH", pagedListHolder);

			// dùng để đẩy dữ liệu lên bảng
			PagedListHolder pagedListHolder1 = new PagedListHolder(listSP1);
			int page1 = ServletRequestUtils.getIntParameter(request, "p1", 0);
			pagedListHolder1.setPage(page1);
			pagedListHolder1.setMaxLinkedPages(5);
			pagedListHolder1.setPageSize(50);
			model.addAttribute("pagedListHolderGH", pagedListHolder1);

			return "banHangInterface";
		} else {
			return "login";
		}

	}

	@RequestMapping(value = "/{masp}.htm", params = "btnSale")
	public String addSP1(HttpServletRequest request, ModelMap model) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		String manv = (String) session.getAttribute("manv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 2) {

			model.addAttribute("check", cd);

			// đẩy tên hiển thị trên thanh tìm kiếm
			model.addAttribute("hoten", tennv);

			// cài này dùng để lấy dữ liệu về
			String masp = request.getParameter("masp");
			String soluong = request.getParameter("soluong");
			String gia = request.getParameter("price");
			String tensp = request.getParameter("tenProduct");

			// ép dữ liệu vô biến
			Double gia1 = Double.valueOf(gia);
			int soluong1 = Integer.valueOf(soluong);
			if (soluong1 > 0 && this.exisSP(masp) == false && this.checkSL(masp, soluong1) == true) {
				SanPhamEntity product = new SanPhamEntity(masp, tensp, gia1, soluong1);
				listSP1.add(product);
			} else {
				model.addAttribute("message", "Thêm vào giỏ hàng thất bại!!!");
			}

			// đẩy dữ liệu lên khuyễn mãi
			List<KhuyenMaiEntity> listKM = this.getALLKMDK(this.tongtien(request));
			model.addAttribute("listKM", listKM);

			// show dữ liệu lên bảng bán hàng
			List<SanPhamEntity> listSP = this.getAllSP();
			PagedListHolder pagedListHolder = new PagedListHolder(listSP);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(5);
			model.addAttribute("pagedListHolderBH", pagedListHolder);

			// show dữ liệu lên bảng hóa đơn
			PagedListHolder pagedListHolder1 = new PagedListHolder(listSP1);
			int page1 = ServletRequestUtils.getIntParameter(request, "p1", 0);
			pagedListHolder1.setPage(page1);
			pagedListHolder1.setMaxLinkedPages(5);
			pagedListHolder1.setPageSize(50);
			model.addAttribute("pagedListHolderGH", pagedListHolder1);

			return "banHangInterface";

		} else {
			return "login";
		}

	}

	@RequestMapping(params = "xuatHoaDon")
	public String exportBill(HttpServletRequest request, ModelMap model) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		String manv = (String) session.getAttribute("manv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 2) {

			model.addAttribute("check", cd);

			// đẩy tên hiển thị trên thanh tìm kiếm
			model.addAttribute("hoten", tennv);

			// cái này dùng để tính tổng tiền và thiết lập các thuộc tính để hiện thị
			Double thanhtien = this.tongtien(request);
			model.addAttribute("tongtien", thanhtien);

			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String strDate = formatter.format(date);
			model.addAttribute("ngaylap", strDate);

			String mahd = this.creatMaHD();
			model.addAttribute("mahd", mahd);

			String tenkh = request.getParameter("tenKH");
			model.addAttribute("tenkh", tenkh);

			String makm = request.getParameter("comboboxKM");
			model.addAttribute("makm", makm);

			// đẩy dữ liệu lên bảng
			PagedListHolder pagedListHolder12 = new PagedListHolder(listSP1);
			int page12 = ServletRequestUtils.getIntParameter(request, "p1", 0);
			pagedListHolder12.setPage(page12);
			pagedListHolder12.setMaxLinkedPages(5);
			pagedListHolder12.setPageSize(50);
			model.addAttribute("pagedListHolderGH1", pagedListHolder12);

			System.out.println(tenkh.strip().length());
			if (tenkh.strip().length() == 0 || listSP1.size() == 0) {
				// đẩy dữ liệu vào bảng danh sách sản phẩm
				List<SanPhamEntity> listSP = this.getAllSP();
				PagedListHolder pagedListHolder = new PagedListHolder(listSP);
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);
				pagedListHolder.setPage(page);
				pagedListHolder.setMaxLinkedPages(5);
				pagedListHolder.setPageSize(5);
				model.addAttribute("pagedListHolderBH", pagedListHolder);

				// đẩy dữ liệu vào bảng chi tiết sản phẩm
				PagedListHolder pagedListHolder1 = new PagedListHolder(listSP1);
				int page1 = ServletRequestUtils.getIntParameter(request, "p1", 0);
				pagedListHolder1.setPage(page1);
				pagedListHolder1.setMaxLinkedPages(5);
				pagedListHolder1.setPageSize(50);
				model.addAttribute("pagedListHolderGH", pagedListHolder1);

				model.addAttribute("message1", "Không thể xuất được hóa đơn!!!!");
				return "banHangInterface";
			} else {

				// tạo hóa đơn mới và chèn vào csdl
				HoaDonEntity hd;
				if (makm.strip().equals("NULL")) {
					String makm1 = null;
					hd = new HoaDonEntity(mahd, date, thanhtien, manv, makm1);
				} else {
					hd = new HoaDonEntity(mahd, date, thanhtien, manv, makm.strip());
				}
				Integer temp = this.insertHoaDon(hd);

				// chèn vào bảng chi tiết hóa đơn
				String makh = request.getParameter("maKH");
				String makh1;
				if (makh.strip().length() == 0) {
					makh1 = null;
				} else {
					makh1 = makh;
				}
				for (SanPhamEntity sp : listSP1) {
					CTHDBanID ctid = new CTHDBanID(mahd, sp.getMasp());
					CTHDBanEntity cthd = new CTHDBanEntity(ctid, makh1, sp.getSoluong());
					Integer temp1 = this.insertCTHoaDon(cthd);
					if (temp1 != 0) {
						System.out.println("thành công");
					} else {
						System.out.println("thất bại");
					}

				}

				// cập nhật lại giỏ hàng
				List<SanPhamEntity> listUpdateSP = this.getAllSP();
				for (SanPhamEntity sp : listUpdateSP) {
					for (SanPhamEntity sp1 : listSP1) {
						if (sp.getMasp().strip().equals(sp1.getMasp().strip())) {
							int soluongnew = sp.getSoluong() - sp1.getSoluong();
							SanPhamEntity sp5 = new SanPhamEntity(sp.getMasp(), sp.getTensp(), sp.getGia(), soluongnew , sp.getSize() , sp.getHang());
							Integer temp2 = this.updateProduct(sp5);
							if (temp2 != 0) {
								System.out.println("thành công");
							} else {
								System.out.println("thất bại");
							}
						}
					}
				}

				// dùng để cập nhật lại tổng chi tiêu của khách hàng
				if (makh1 != null) {
					KhachHangEntity kh5 = this.searchMKH(makh1);
					double tongchitieu = kh5.getTongchitieu() + thanhtien;
					KhachHangEntity kh9 = new KhachHangEntity(kh5.getMakh(), kh5.getTen(), kh5.getGioitinh(),
							tongchitieu, kh5.getSdt());
					Integer temp5 = this.updateKH(kh9);
					if (temp5 != 0) {
						System.out.println("kh thành công");
					} else {
						System.out.println("kh thất bại");
					}

				}

				// đẩy dữ liệu lên khuyễn mãi
				List<KhuyenMaiEntity> listKM = this.getALLKMDK(this.tongtien(request));
				model.addAttribute("listKM", listKM);

				// return
				return "hoaDonInterface";
			}
		} else {
			return "login";
		}

	}

	@RequestMapping(params = "btnsearchBH")
	public String search(HttpServletRequest request, ModelMap model) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		String manv = (String) session.getAttribute("manv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 2) {

			model.addAttribute("check", cd);

			// đẩy tên hiển thị trên thanh tìm kiếm
			model.addAttribute("hoten", tennv);

			// lấy danh sách sau khi tìm kiếm
			List<SanPhamEntity> listSP = this.searchSP(request.getParameter("searchInputBH"));

			// đẩy dữ liệu lên khuyễn mãi
			List<KhuyenMaiEntity> listKM = this.getALLKMDK(this.tongtien(request));
			model.addAttribute("listKM", listKM);

			// đám code này dùng để đẩy toàn bộ dữ liệu lên các bảng
			PagedListHolder pagedListHolder = new PagedListHolder(listSP);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(5);
			model.addAttribute("pagedListHolderBH", pagedListHolder);

			// dùng để đẩy dữ liệu lên bảng
			PagedListHolder pagedListHolder1 = new PagedListHolder(listSP1);
			int page1 = ServletRequestUtils.getIntParameter(request, "p1", 0);
			pagedListHolder1.setPage(page1);
			pagedListHolder1.setMaxLinkedPages(5);
			pagedListHolder1.setPageSize(50);
			model.addAttribute("pagedListHolderGH", pagedListHolder1);

			return "banHangInterface";

		} else {
			return "login";
		}

	}

	@RequestMapping(params = "btnsearchKH")
	public String searchKH(HttpServletRequest request, ModelMap model) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		String manv = (String) session.getAttribute("manv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 2) {

			model.addAttribute("check", cd);

			// đẩy tên hiển thị trên thanh tìm kiếm
			model.addAttribute("hoten", tennv);

			String sdt = request.getParameter("searchKH");
			System.out.println(sdt);
			try {
				KhachHangEntity kh = this.searchKH(sdt);
				model.addAttribute("tenKH", kh.getTen());
				model.addAttribute("maKH", kh.getMakh());

				// đẩy dữ liệu lên khuyễn mãi
				List<KhuyenMaiEntity> listKM = this.getALLKMDK(this.tongtien(request));
				model.addAttribute("listKM", listKM);

				// đám code này dùng để đẩy toàn bộ dữ liệu lên các bảng
				List<SanPhamEntity> listSP = this.getAllSP();
				PagedListHolder pagedListHolder = new PagedListHolder(listSP);
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);
				pagedListHolder.setPage(page);
				pagedListHolder.setMaxLinkedPages(5);
				pagedListHolder.setPageSize(5);
				model.addAttribute("pagedListHolderBH", pagedListHolder);
				PagedListHolder pagedListHolder1 = new PagedListHolder(listSP1);
				int page1 = ServletRequestUtils.getIntParameter(request, "p1", 0);
				pagedListHolder1.setPage(page1);
				pagedListHolder1.setMaxLinkedPages(5);
				pagedListHolder1.setPageSize(50);
				model.addAttribute("pagedListHolderGH", pagedListHolder1);

			} catch (Exception e) {
				model.addAttribute("maKH", "không tìm thấy khách hàng này");

				// đẩy dữ liệu lên khuyễn mãi
				List<KhuyenMaiEntity> listKM = this.getALLKMDK(this.tongtien(request));
				model.addAttribute("listKM", listKM);

				// đám code này dùng để đẩy toàn bộ dữ liệu lên các bảng
				List<SanPhamEntity> listSP = this.getAllSP();
				PagedListHolder pagedListHolder = new PagedListHolder(listSP);
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);
				pagedListHolder.setPage(page);
				pagedListHolder.setMaxLinkedPages(5);
				pagedListHolder.setPageSize(5);
				model.addAttribute("pagedListHolderBH", pagedListHolder);
				PagedListHolder pagedListHolder1 = new PagedListHolder(listSP1);
				int page1 = ServletRequestUtils.getIntParameter(request, "p1", 0);
				pagedListHolder1.setPage(page1);
				pagedListHolder1.setMaxLinkedPages(5);
				pagedListHolder1.setPageSize(50);
				model.addAttribute("pagedListHolderGH", pagedListHolder1);

			}

			return "banHangInterface";

		} else {
			return "login";
		}

	}

	// lấy toàn bộ khuyến mãi
	public List<SanPhamEntity> getAllSP() {
		Session session = fatory2.getCurrentSession();
		String hql = "FROM SanPhamEntity";
		Query query = session.createQuery(hql);
		List<SanPhamEntity> list = query.list();
		return list;
	}

	// hàm này xác định mã nhân vị trí của masp trong list
	public Integer index(String masp) {
		int index1 = 0;
		int result = 0;
		for (SanPhamEntity sp : listSP1) {
			if (sp.getMasp().strip().equals(masp.strip())) {
				result = index1;
				break;
			}
			index1 = index1 + 1;
		}
		return result;

	}

	// hàm này dùng để tìm sản phẩm theo tên
	public List<SanPhamEntity> searchSP(String tensp) {
		Session session = fatory2.getCurrentSession();
		String hql = "FROM SanPhamEntity where tensp LIKE :tensp";
		Query query = session.createQuery(hql);
		query.setParameter("tensp", "%" + tensp + "%");
		List<SanPhamEntity> list = query.list();
		return list;
	}

	// hàm này dùng để tính tổng tiền
	public Double tongtien(HttpServletRequest request) {
		String makm = request.getParameter("comboboxKM");
		Float phantram = this.phantram(makm);
		Double result = 0.0;
		for (SanPhamEntity sp : listSP1) {
			Double tien = sp.getSoluong() * sp.getGia();
			result = result + tien;
		}
		Double temp = (result * phantram) / 100;
		return result - temp;

	}

	public Float phantram(String makm) {
		List<KhuyenMaiEntity> listKM = this.getALLKM();
		float phantram = 0;
		for (KhuyenMaiEntity km : listKM) {
			if (makm != null) {
				if (km.getMakm().strip().equals(makm.strip())) {
					phantram = km.getPhantramkm();
				}
			}

		}
		return phantram;
	}

	// hàm này dùng để tìm khách hàng theo sdt
	public KhachHangEntity searchKH(String SDT) {
		Session session = fatory2.getCurrentSession();
		String hql = "FROM KhachHangEntity where trangthai = 'yes' and SDT LIKE :SDT";
		Query query = session.createQuery(hql);
		query.setParameter("SDT", "%" + SDT + "%");
		List<KhachHangEntity> list = query.list();
		KhachHangEntity kh = list.get(0);
		return kh;
	}

	// hàm chày dùng để check xem sản phẩm đã tồn tại trong giỏ hàng hay chưa
	public Boolean exisSP(String masp) {
		for (SanPhamEntity sp : listSP1) {
			if (sp.getMasp().strip().equals(masp.strip())) {
				return true;
			}
		}
		return false;
	}

	// hàm này dùng để lấy toàn bộ khuyến mãi
	public List<KhuyenMaiEntity> getALLKM() {
		Session session = fatory2.getCurrentSession();
		String hql = "FROM KhuyenMaiEntity where LoaiKhuyenMai = 'KM2HD'";
		Query query = session.createQuery(hql);
		List<KhuyenMaiEntity> list = query.list();
		return list;
	}

	// hàm này dùng để lấy toàn bộ hóa đơn
	public List<HoaDonEntity> getALLHD() {
		Session session = fatory2.getCurrentSession();
		String hql = "FROM HoaDonEntity";
		Query query = session.createQuery(hql);
		List<HoaDonEntity> list = query.list();
		return list;
	}

	// hàm này dùng để tự động tạo mã hóa đơn mới
	public String creatMaHD() {
		List<HoaDonEntity> list = this.getALLHD();
		int max = 0;
		for (HoaDonEntity sp : list) {
			String a = sp.getMahd().replace("hd", "");
			int b = Integer.parseInt(a.strip());
			if (b > max) {
				max = b;
			} else {
				max = max;
			}

		}

		String mahd = "hd" + String.valueOf(max + 1);
		return mahd;

	}

	// hàm này dùng để lấy toàn bộ dữ liệu từ bảng CTHD bán
	public List<CTHDBanEntity> getALLCTHD() {
		Session session = fatory2.getCurrentSession();
		String hql = "FROM CTHDBanEntity";
		Query query = session.createQuery(hql);
		List<CTHDBanEntity> list = query.list();
		return list;

	}

	// hàm này dùng để chèn hóa đơn vào cơ sở dữ liệu
	public Integer insertHoaDon(HoaDonEntity pd) {

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

	// hàm này dùng để chèn chi tiết hóa đơn
	public Integer insertCTHoaDon(CTHDBanEntity pd) {

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

	// hàm này dùng để cập nhật lại giỏ hàng
	public Integer updateProduct(SanPhamEntity pd) {
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

	// hàm này dùng để tìm khách hàng khi biết mã khách hàng
	public KhachHangEntity searchMKH(String mkh) {
		Session session = fatory2.getCurrentSession();
		String hql = "FROM KhachHangEntity where makh LIKE :SDT";
		Query query = session.createQuery(hql);
		query.setParameter("SDT", "%" + mkh + "%");
		List<KhachHangEntity> list = query.list();
		KhachHangEntity kh = list.get(0);
		return kh;

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

	// hàm này dùng để kiểm tra xem số lượng sản phẩm có vượt quá số lượng tồn kho
	// hay không
	public Boolean checkSL(String masp, int soluong) {
		List<SanPhamEntity> listSPC = this.getAllSP();
		for (SanPhamEntity sp : listSPC) {
			if (sp.getMasp().strip().equals(masp.strip())) {
				if (sp.getSoluong() >= soluong) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	// hàm này dùng để xác định khuyến mãi được xài hay không
	public List<KhuyenMaiEntity> getALLKMDK(double tongtien) {
		
		
		List<KhuyenMaiEntity> listKM1 = new ArrayList<>();
		Date today = new Date();
		List<KhuyenMaiEntity> listKM = this.getALLKM();
		for (KhuyenMaiEntity km : listKM) {
			if (km.getNgaybatdau().compareTo(today) <= 0 && km.getNgayketthuc().compareTo(today) >= 0 &&  tongtien >= km.getDieukienkm()) {
				listKM1.add(km);
			}
		}
		return listKM1;

	}

}
