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
import org.springframework.web.bind.annotation.RequestMapping;

import QLBanDoTheThao.entity.CTHDBanEntity;
import QLBanDoTheThao.entity.CTHDBanID;
import QLBanDoTheThao.entity.CTHDNhapEntity;
import QLBanDoTheThao.entity.CTHDNhapID;
import QLBanDoTheThao.entity.HoaDonEntity;
import QLBanDoTheThao.entity.HoaDonNhapEntity;
import QLBanDoTheThao.entity.KhuyenMaiEntity;
import QLBanDoTheThao.entity.NhaCCEntity;
import QLBanDoTheThao.entity.SanPhamEntity;

@Transactional
@Controller
@RequestMapping("/nhaphang")
public class nhapHangController {

	@Autowired
	SessionFactory fatory9;

	// các biến toàn cục
	List<SanPhamEntity> listSP1 = new ArrayList<>();

	// khi truy cập vào thì "/sanpham" thì nó sẽ đi đến cái này đầu tiên hàm khỏi
	// tạo
	@RequestMapping()
	public String home(HttpServletRequest request, ModelMap model) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 2) {
			model.addAttribute("check", cd);

			// đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);

			// đẩy dữ liệu vào nhà cung cấp
			List<NhaCCEntity> listNCC = this.getAllNCC();
			model.addAttribute("listNCC", listNCC);

			// đẩy dữ liệu vào bảng danh sách sản phẩm
			List<SanPhamEntity> listSP = this.getAllSP();
			PagedListHolder pagedListHolder = new PagedListHolder(listSP);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(5);
			model.addAttribute("pagedListHolderBH", pagedListHolder);

			return "nhapHangInterface";
		} else {
			return "login";
		}

	}

	@RequestMapping(params = "btnSale")
	public String addSP(HttpServletRequest request, ModelMap model) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 2) {

			model.addAttribute("check", cd);

			// đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);

			// đẩy dữ liệu từ form về biến
			String masp = request.getParameter("masp");
			String soluong = request.getParameter("soluong");
			String gia = request.getParameter("giaSP");
			String tensp = request.getParameter("tenProduct");

			// éo liểu dữ liệu đồng thời ép vào biến toàn cục
			Double gia1 = Double.valueOf(gia);
			int soluong1 = Integer.valueOf(soluong);

			if (soluong1 > 0 && this.exisSP(masp) == false) {
				SanPhamEntity product = new SanPhamEntity(masp, tensp, gia1, soluong1);
				listSP1.add(product);
			} else {
				model.addAttribute("message", "Thêm vào giỏ hàng thất bại!!!");
			}

			// đẩy dữ liệu vào nhà cung cấp
			List<NhaCCEntity> listNCC = this.getAllNCC();
			model.addAttribute("listNCC", listNCC);

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

			return "nhapHangInterface";

		} else {
			return "login";
		}

	}

	@RequestMapping(params = "btnDeletePD")
	public String deleteSP(HttpServletRequest request, ModelMap model) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 2) {

			model.addAttribute("check", cd);

			// đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);

			String masp = request.getParameter("masp");
			int index = this.index(masp);
			System.out.println(index);
			listSP1.remove(index);

			// đẩy dữ liệu vào nhà cung cấp
			List<NhaCCEntity> listNCC = this.getAllNCC();
			model.addAttribute("listNCC", listNCC);

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

			return "nhapHangInterface";

		} else {
			return "login";
		}

	}

	@RequestMapping(params = "btnsearchBH")
	public String search(HttpServletRequest request, ModelMap model) {

		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");

		if (myCheck != null && cd != 2) {

			model.addAttribute("check", cd);

			// đẩy tên hiển thị trên thanh menu tổng
			model.addAttribute("hoten", tennv);

			// lấy danh sách sau khi tìm kiếm
			List<SanPhamEntity> listSP = this.searchSP(request.getParameter("searchInputBH"));

			// đẩy dữ liệu vào nhà cung cấp
			List<NhaCCEntity> listNCC = this.getAllNCC();
			model.addAttribute("listNCC", listNCC);

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

			return "nhapHangInterface";
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

			// đẩy tên hiển thị trên thanh menu tổng
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

			String mancc = request.getParameter("comboboxNCC");
			model.addAttribute("mancc", mancc);

			String mapd = null;

			// đẩy dữ liệu lên bảng
			PagedListHolder pagedListHolder12 = new PagedListHolder(listSP1);
			int page12 = ServletRequestUtils.getIntParameter(request, "p1", 0);
			pagedListHolder12.setPage(page12);
			pagedListHolder12.setMaxLinkedPages(5);
			pagedListHolder12.setPageSize(50);
			model.addAttribute("pagedListHolderGH1", pagedListHolder12);

			if (listSP1.size() == 0) {
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
				return "nhapHangInterface";
			} else {

				// tạo hóa đơn mới và chèn vào csdl

				HoaDonNhapEntity hd = new HoaDonNhapEntity(mahd, date, thanhtien, manv, mapd, mancc);

				Integer temp = this.insertHoaDon(hd);
				if (temp != 0) {
					System.out.println("thành công");
				} else {
					System.out.println("thất bại");
				}

				// chèn vào bảng chi tiết hóa đơn
				for (SanPhamEntity sp : listSP1) {
					CTHDNhapID ctid = new CTHDNhapID(mahd, sp.getMasp());
					CTHDNhapEntity cthd = new CTHDNhapEntity(ctid, sp.getSoluong(), sp.getGia());
					Integer temp1 = this.insertCTHoaDon(cthd);
					if (temp1 != 0) {
						System.out.println("thành công");
					} else {
						System.out.println("thất bại");
					}

				}

				// cập nhật lại số lượng sản phẩm
				List<SanPhamEntity> listUpdateSP = this.getAllSP();
				for (SanPhamEntity sp : listUpdateSP) {
					for (SanPhamEntity sp1 : listSP1) {
						if (sp.getMasp().strip().equals(sp1.getMasp().strip())) {
							int soluongnew = sp.getSoluong() + sp1.getSoluong();
							SanPhamEntity sp5 = new SanPhamEntity(sp.getMasp(), sp.getTensp(), sp.getGia(), soluongnew , sp.getSize() , sp.getHang());
							Integer temp2 = this.updateProduct(sp5);
							if (temp2 != 0) {
								System.out.println("cập nhật thành công");
							} else {
								System.out.println("cập nhật thất bại");
							}
						}
					}
				}

				// return
				return "hoaDonNhap";
			}
		} else {
			return "login";
		}

	}

	// lấy toàn bộ sản phẩm
	public List<SanPhamEntity> getAllSP() {
		Session session = fatory9.getCurrentSession();
		String hql = "FROM SanPhamEntity";
		Query query = session.createQuery(hql);
		List<SanPhamEntity> list = query.list();
		return list;
	}

	// lấy toàn bộ nhà cung cấp
	public List<NhaCCEntity> getAllNCC() {
		Session session = fatory9.getCurrentSession();
		String hql = "FROM NhaCCEntity";
		Query query = session.createQuery(hql);
		List<NhaCCEntity> list = query.list();
		return list;
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

	// tìm index của mã sản phẩm đó
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
		Session session = fatory9.getCurrentSession();
		String hql = "FROM SanPhamEntity where tensp LIKE :tensp";
		Query query = session.createQuery(hql);
		query.setParameter("tensp", "%" + tensp + "%");
		List<SanPhamEntity> list = query.list();
		return list;
	}

	// hàm này dùng để tính tổng tiền
	public Double tongtien(HttpServletRequest request) {
		Double result = 0.0;
		for (SanPhamEntity sp : listSP1) {
			Double tien = sp.getSoluong() * sp.getGia();
			result = result + tien;
		}
		return result;

	}

	// hàm này dùng để lấy toàn bộ hóa đơn
	public List<HoaDonNhapEntity> getALLHDN() {
		Session session = fatory9.getCurrentSession();
		String hql = "FROM HoaDonNhapEntity";
		Query query = session.createQuery(hql);
		List<HoaDonNhapEntity> list = query.list();
		return list;
	}

	// hàm này dùng để tự động tạo mã hóa đơn mới
	public String creatMaHD() {
		List<HoaDonNhapEntity> list = this.getALLHDN();
		int max = 0;
		for (HoaDonNhapEntity sp : list) {
			String a = sp.getManhdn().replace("hdn", "");
			int b = Integer.parseInt(a.strip());
			if (b > max) {
				max = b;
			} else {
				max = max;
			}

		}

		String mahd = "hdn" + String.valueOf(max + 1);
		return mahd;

	}

	// hàm này dùng để chèn hóa đơn vào cơ sở dữ liệu
	public Integer insertHoaDon(HoaDonNhapEntity pd) {

		Session session = fatory9.openSession();
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
	public Integer insertCTHoaDon(CTHDNhapEntity pd) {

		Session session = fatory9.openSession();
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
		Session session = fatory9.openSession();
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
}
