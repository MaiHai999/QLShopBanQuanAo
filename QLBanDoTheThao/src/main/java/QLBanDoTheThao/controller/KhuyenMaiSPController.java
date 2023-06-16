package QLBanDoTheThao.controller;

import java.text.ParseException;
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

import QLBanDoTheThao.entity.CTKhuyenMaiEntity;
import QLBanDoTheThao.entity.CTKhuyenMaiID;
import QLBanDoTheThao.entity.KhuyenMaiEntity;
import QLBanDoTheThao.entity.SanPhamEntity;

@Transactional
@Controller
@RequestMapping("/khuyenmaisp")
public class KhuyenMaiSPController {
	@Autowired
	SessionFactory fatory3;

	// biến toàn cục
	String loaikm = "KM2SP";
	List<SanPhamEntity> listSP1 = new ArrayList<>();
	KhuyenMaiEntity km99 = new KhuyenMaiEntity();
	String tenbutton;

	@RequestMapping()
	public String home(HttpServletRequest request, ModelMap model) {

		HttpSession session = request.getSession();
		String tennv = (String) session.getAttribute("tennv");

		// đẩy tên hiển thị trên thanh tìm kiếm
		model.addAttribute("hoten", tennv);

		// reset lại
		listSP1.clear();
		km99.clear();

		// dùng để đẩy dự liệu vào bảng chi tiết hóa đơn
		PagedListHolder pagedListHolderCTKM = new PagedListHolder(listSP1);
		int page2 = ServletRequestUtils.getIntParameter(request, "p2", 0);
		pagedListHolderCTKM.setPage(page2);
		pagedListHolderCTKM.setMaxLinkedPages(5);
		pagedListHolderCTKM.setPageSize(5);
		model.addAttribute("pagedListHolderCTKM", pagedListHolderCTKM);

		// đẩy dữ liệu vào bảng danh sách sản phẩm
		List<SanPhamEntity> listSP = this.getAllSP();
		PagedListHolder pagedListHolder = new PagedListHolder(listSP);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolderBH", pagedListHolder);

		// đẩy dữ liêu vào bảng khuyến mãi
		List<KhuyenMaiEntity> listKM = this.getAllKM();
		PagedListHolder pagedListHolderKM = new PagedListHolder(listKM);
		int page1 = ServletRequestUtils.getIntParameter(request, "p1", 0);
		pagedListHolderKM.setPage(page1);
		pagedListHolderKM.setMaxLinkedPages(5);
		pagedListHolderKM.setPageSize(10);
		model.addAttribute("pagedListHolderKM", pagedListHolderKM);

		// thiết lập tên nút save để biết khi nào lưu và khi nào tạo mới
		model.addAttribute("btnStatus", "btnAdd");

		return "KhuyenMaiForSPInterface";

	}

	@RequestMapping(params = "btnAdd")
	public String addSP(HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		String tennv = (String) session.getAttribute("tennv");

		// đẩy tên hiển thị trên thanh tìm kiếm
		model.addAttribute("hoten", tennv);

		// đẩy dữ liệu từ form về biến
		String tenKMSP = request.getParameter("tenKMSP");
		String ngaybatdauKM = request.getParameter("ngaybatdauKM");
		String ngayketthucKM = request.getParameter("ngayketthucKM");

		if (tenKMSP.length() == 0 || ngaybatdauKM.length() == 0 || ngayketthucKM.length() == 0) {
			model.addAttribute("message1", "Thêm thất bại!");
		} else {
			try {
				// tạo một mã km mới
				String makm = this.creatMaKM();

				// ép kiểu dữ liệu
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date ngayBD = sdf.parse(ngaybatdauKM);
				SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
				Date ngayKT = sdf2.parse(ngayketthucKM);

				// tạo thực thể vào thêm vào cơ sở dữ liệu
				KhuyenMaiEntity product = new KhuyenMaiEntity(makm, tenKMSP, ngayBD, ngayKT, loaikm);
				Integer temp = this.insertKH(product);
				if (temp != 0) {
					model.addAttribute("message1", "Thêm thành công");

				} else {
					model.addAttribute("message1", "Thêm thất bại!");
				}

				// chèn vào bảng chi tiết khuyễn mãi
				for (SanPhamEntity sp : listSP1) {
					CTKhuyenMaiID id = new CTKhuyenMaiID(makm, sp.getMasp());
					CTKhuyenMaiEntity ctkm = new CTKhuyenMaiEntity(id, sp.getGia());
					Integer temp1 = this.insertCTKM(ctkm);
					if (temp1 != 0) {
						model.addAttribute("message1", "Thêm CTKM thành công");
					} else {
						model.addAttribute("message1", "Thêm CTKM thất bại!");
					}

				}

				// xóa danh sách
				listSP1.clear();

			} catch (Exception e) {
				model.addAttribute("message1", "Thêm thất bại!");
			}
		}

		model.addAttribute("btnStatus", "btnAdd");

		// dùng để đẩy dự liệu vào bảng chi tiết hóa đơn
		PagedListHolder pagedListHolderCTKM = new PagedListHolder(listSP1);
		int page2 = ServletRequestUtils.getIntParameter(request, "p2", 0);
		pagedListHolderCTKM.setPage(page2);
		pagedListHolderCTKM.setMaxLinkedPages(5);
		pagedListHolderCTKM.setPageSize(5);
		model.addAttribute("pagedListHolderCTKM", pagedListHolderCTKM);

		// đẩy dữ liệu vào bảng danh sách sản phẩm
		List<SanPhamEntity> listSP = this.getAllSP();
		PagedListHolder pagedListHolder = new PagedListHolder(listSP);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolderBH", pagedListHolder);

		// đẩy dữ liêu vào bảng khuyến mãi
		List<KhuyenMaiEntity> listKM = this.getAllKM();
		PagedListHolder pagedListHolderKM = new PagedListHolder(listKM);
		int page1 = ServletRequestUtils.getIntParameter(request, "p1", 0);
		pagedListHolderKM.setPage(page1);
		pagedListHolderKM.setMaxLinkedPages(5);
		pagedListHolderKM.setPageSize(10);
		model.addAttribute("pagedListHolderKM", pagedListHolderKM);

		return "KhuyenMaiForSPInterface";
	}

	@RequestMapping(params = "btnDeletePD")
	public String deleteSP(HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		String tennv = (String) session.getAttribute("tennv");

		// đẩy tên hiển thị trên thanh tìm kiếm
		model.addAttribute("hoten", tennv);

		// đẩy dữ liệu lên filed
		model.addAttribute("product", km99);

		// lấy vị trí rồi xóa nó trong list
		String masp = request.getParameter("masp");
		int index = this.index(masp);
		listSP1.remove(index);

		// cập nhật lại tên cho button
		tenbutton = request.getParameter("tenbutton");
		model.addAttribute("btnStatus", tenbutton);

		// dùng để đẩy dự liệu vào bảng chi tiết hóa đơn
		PagedListHolder pagedListHolderCTKM = new PagedListHolder(listSP1);
		int page2 = ServletRequestUtils.getIntParameter(request, "p2", 0);
		pagedListHolderCTKM.setPage(page2);
		pagedListHolderCTKM.setMaxLinkedPages(5);
		pagedListHolderCTKM.setPageSize(5);
		model.addAttribute("pagedListHolderCTKM", pagedListHolderCTKM);

		// đẩy dữ liệu vào bảng danh sách sản phẩm
		List<SanPhamEntity> listSP = this.getAllSP();
		PagedListHolder pagedListHolder = new PagedListHolder(listSP);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolderBH", pagedListHolder);

		// đẩy dữ liêu vào bảng khuyến mãi
		List<KhuyenMaiEntity> listKM = this.getAllKM();
		PagedListHolder pagedListHolderKM = new PagedListHolder(listKM);
		int page1 = ServletRequestUtils.getIntParameter(request, "p1", 0);
		pagedListHolderKM.setPage(page1);
		pagedListHolderKM.setMaxLinkedPages(5);
		pagedListHolderKM.setPageSize(10);
		model.addAttribute("pagedListHolderKM", pagedListHolderKM);

		return "KhuyenMaiForSPInterface";
	}

	@RequestMapping(params = "btnEditMakm")
	public String updateKMSP(HttpServletRequest request, ModelMap model) {
		
		HttpSession session = request.getSession();
		String tennv = (String) session.getAttribute("tennv");

		// đẩy tên hiển thị trên thanh tìm kiếm
		model.addAttribute("hoten", tennv);

		// xóa toàn bộ phần tử trong list1
		listSP1.clear();

		// đẩy dữ liệu từ form về biến
		String makm = request.getParameter("makm");

		// hàm này dùng để thay đổi tên của nút
		model.addAttribute("btnStatus", "btnEdit");

		// dùng để đẩy dữ liệu khi tìm kiếm được
		km99 = this.getKH(makm);
		model.addAttribute("product", km99);

		// dùng để đẩy vào bảng chi tiết khuyến mãi
		List<CTKhuyenMaiEntity> listKM1 = this.getAllCTKM(makm);

		// dùng để lấy khuyến mãi và thêm nó vào trong list
		for (CTKhuyenMaiEntity ctkm : listKM1) {
			SanPhamEntity sp = this.getSP(ctkm.getId().getMasp());
			sp.setPtkm(ctkm.getSoluong());
			listSP1.add(sp);
		}

		// dùng để đẩy dự liệu vào bảng chi tiết hóa đơn
		PagedListHolder pagedListHolderCTKM = new PagedListHolder(listSP1);
		int page2 = ServletRequestUtils.getIntParameter(request, "p2", 0);
		pagedListHolderCTKM.setPage(page2);
		pagedListHolderCTKM.setMaxLinkedPages(5);
		pagedListHolderCTKM.setPageSize(5);
		model.addAttribute("pagedListHolderCTKM", pagedListHolderCTKM);

		// đẩy dữ liệu vào bảng danh sách sản phẩm
		List<SanPhamEntity> listSP = this.getAllSP();
		PagedListHolder pagedListHolder = new PagedListHolder(listSP);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolderBH", pagedListHolder);

		// đẩy dữ liêu vào bảng khuyến mãi
		List<KhuyenMaiEntity> listKM = this.getAllKM();
		PagedListHolder pagedListHolderKM = new PagedListHolder(listKM);
		int page1 = ServletRequestUtils.getIntParameter(request, "p1", 0);
		pagedListHolderKM.setPage(page1);
		pagedListHolderKM.setMaxLinkedPages(5);
		pagedListHolderKM.setPageSize(10);
		model.addAttribute("pagedListHolderKM", pagedListHolderKM);

		return "KhuyenMaiForSPInterface";
	}

	@RequestMapping(params = "btnEdit")
	public String updateCTKM(HttpServletRequest request, ModelMap model) {
		
		HttpSession session = request.getSession();
		String tennv = (String) session.getAttribute("tennv");

		// đẩy tên hiển thị trên thanh tìm kiếm
		model.addAttribute("hoten", tennv);

		// đẩy dữ liệu từ form về biến
		String makm = request.getParameter("makm");
		String tenKMSP = request.getParameter("tenKMSP");
		String ngaybatdauKM = request.getParameter("ngaybatdauKM");
		String ngayketthucKM = request.getParameter("ngayketthucKM");

		if (tenKMSP.length() == 0 || ngaybatdauKM.length() == 0 || ngayketthucKM.length() == 0) {
			model.addAttribute("message1", "Thêm thất bại!");
		} else {
			try {

				// ép kiểu dữ liệu
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date ngayBD = sdf.parse(ngaybatdauKM);
				SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
				Date ngayKT = sdf2.parse(ngayketthucKM);

				// tạo thực thể vào thêm vào cơ sở dữ liệu
				KhuyenMaiEntity product = new KhuyenMaiEntity(makm, tenKMSP, ngayBD, ngayKT, loaikm);
				Integer temp = this.updateKM(product);
				if (temp != 0) {
					model.addAttribute("message1", "Update thành công");

				} else {
					model.addAttribute("message1", "Update thất bại!");
					model.addAttribute("product", km99);
					model.addAttribute("btnStatus", "btnEdit");
				}

				// hàm này để chèn vào chi tiết khuyến mãi
				for (SanPhamEntity sp : listSP1) {
					CTKhuyenMaiID id = new CTKhuyenMaiID(makm, sp.getMasp());
					CTKhuyenMaiEntity ctkm = new CTKhuyenMaiEntity(id, sp.getGia());
					System.out.println(ctkm.getId().getMasp() + "    đây là kết quả mong muốn123");
					Integer temp1 = this.insertCTKM(ctkm);
					if (temp1 != 0) {
						model.addAttribute("message1", "Thêm CTKM thành công");
					} else {
						model.addAttribute("message1", "Thêm CTKM thất bại!");
					}

				}
				
				//xóa danh sách
				listSP1.clear();

			} catch (Exception e) {
				model.addAttribute("message1", "Update thất bại11!");
			}
		}

		// dùng để đẩy dự liệu vào bảng chi tiết hóa đơn
		PagedListHolder pagedListHolderCTKM = new PagedListHolder(listSP1);
		int page2 = ServletRequestUtils.getIntParameter(request, "p2", 0);
		pagedListHolderCTKM.setPage(page2);
		pagedListHolderCTKM.setMaxLinkedPages(5);
		pagedListHolderCTKM.setPageSize(5);
		model.addAttribute("pagedListHolderCTKM", pagedListHolderCTKM);

		// đẩy dữ liệu vào bảng danh sách sản phẩm
		List<SanPhamEntity> listSP = this.getAllSP();
		PagedListHolder pagedListHolder = new PagedListHolder(listSP);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolderBH", pagedListHolder);

		// đẩy dữ liêu vào bảng khuyến mãi
		List<KhuyenMaiEntity> listKM = this.getAllKM();
		PagedListHolder pagedListHolderKM = new PagedListHolder(listKM);
		int page1 = ServletRequestUtils.getIntParameter(request, "p1", 0);
		pagedListHolderKM.setPage(page1);
		pagedListHolderKM.setMaxLinkedPages(5);
		pagedListHolderKM.setPageSize(10);
		model.addAttribute("pagedListHolderKM", pagedListHolderKM);

		return "KhuyenMaiForSPInterface";
	}

	@RequestMapping(params = "btnSale")
	public String addCTSP(HttpServletRequest request, ModelMap model) {
		
		HttpSession session = request.getSession();
		String tennv = (String) session.getAttribute("tennv");

		// đẩy tên hiển thị trên thanh tìm kiếm
		model.addAttribute("hoten", tennv);

		// đẩy dữ liệu lên filed
		model.addAttribute("product", km99);

		// cập nhật lại tên cho button
		tenbutton = request.getParameter("tenbutton");
		model.addAttribute("btnStatus", tenbutton);

		// đẩy dữ liệu từ form về biến
		String ptkm = request.getParameter("ptkm");
		String price = request.getParameter("price");
		String masp = request.getParameter("masp");
		String tenProduct = request.getParameter("tenProduct");
		String SLProduct = request.getParameter("SLProduct");
		String sizeProduct = request.getParameter("sizeProduct");
		String hangProduct = request.getParameter("hangProduct");

		if (ptkm.length() == 0 || this.exisSP(masp) == true) {
			model.addAttribute("message", "Thêm thất bại!!");
		} else {
			try {
				// ép kiểu dữ liệu
				Double ptkm1 = Double.valueOf(ptkm);
				Double price1 = Double.valueOf(price);
				int tonkho = Integer.valueOf(SLProduct);

				// tạo một sản phẩm thêm nó vào listsp1
				SanPhamEntity sp = new SanPhamEntity(masp, tenProduct, price1, tonkho, sizeProduct, hangProduct);
				sp.setPtkm(ptkm1);
				listSP1.add(sp);
				model.addAttribute("message", "Thêm thành công!!");

			} catch (Exception e) {
				model.addAttribute("message", "Thêm thất bại!!");
			}
		}

		// dùng để đẩy dự liệu vào bảng chi tiết hóa đơn
		PagedListHolder pagedListHolderCTKM = new PagedListHolder(listSP1);
		int page2 = ServletRequestUtils.getIntParameter(request, "p2", 0);
		pagedListHolderCTKM.setPage(page2);
		pagedListHolderCTKM.setMaxLinkedPages(5);
		pagedListHolderCTKM.setPageSize(5);
		model.addAttribute("pagedListHolderCTKM", pagedListHolderCTKM);

		// đẩy dữ liệu vào bảng danh sách sản phẩm
		List<SanPhamEntity> listSP = this.getAllSP();
		PagedListHolder pagedListHolder = new PagedListHolder(listSP);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolderBH", pagedListHolder);

		// đẩy dữ liêu vào bảng khuyến mãi
		List<KhuyenMaiEntity> listKM = this.getAllKM();
		PagedListHolder pagedListHolderKM = new PagedListHolder(listKM);
		int page1 = ServletRequestUtils.getIntParameter(request, "p1", 0);
		pagedListHolderKM.setPage(page1);
		pagedListHolderKM.setMaxLinkedPages(5);
		pagedListHolderKM.setPageSize(10);
		model.addAttribute("pagedListHolderKM", pagedListHolderKM);

		return "KhuyenMaiForSPInterface";
	}

	@RequestMapping(params = "btnDeleteMakm")
	public String deleteKM12(HttpServletRequest request, ModelMap model) {
		
		HttpSession session = request.getSession();
		String tennv = (String) session.getAttribute("tennv");

		// đẩy tên hiển thị trên thanh tìm kiếm
		model.addAttribute("hoten", tennv);

		// lấy vị trí rồi xóa nó trong list
		String makm = request.getParameter("makm");

		Integer temp = this.deleteKM(this.getKH(makm));
		if (temp != 0) {
			model.addAttribute("message2", "Delete thành công");
		} else {
			model.addAttribute("message2", "Delete thất bại!");
		}

		// cập nhật lại tên cho button
		model.addAttribute("btnStatus", "btnAdd");

		// dùng để đẩy dự liệu vào bảng chi tiết hóa đơn
		PagedListHolder pagedListHolderCTKM = new PagedListHolder(listSP1);
		int page2 = ServletRequestUtils.getIntParameter(request, "p2", 0);
		pagedListHolderCTKM.setPage(page2);
		pagedListHolderCTKM.setMaxLinkedPages(5);
		pagedListHolderCTKM.setPageSize(5);
		model.addAttribute("pagedListHolderCTKM", pagedListHolderCTKM);

		// đẩy dữ liệu vào bảng danh sách sản phẩm
		List<SanPhamEntity> listSP = this.getAllSP();
		PagedListHolder pagedListHolder = new PagedListHolder(listSP);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolderBH", pagedListHolder);

		// đẩy dữ liêu vào bảng khuyến mãi
		List<KhuyenMaiEntity> listKM = this.getAllKM();
		PagedListHolder pagedListHolderKM = new PagedListHolder(listKM);
		int page1 = ServletRequestUtils.getIntParameter(request, "p1", 0);
		pagedListHolderKM.setPage(page1);
		pagedListHolderKM.setMaxLinkedPages(5);
		pagedListHolderKM.setPageSize(10);
		model.addAttribute("pagedListHolderKM", pagedListHolderKM);

		return "KhuyenMaiForSPInterface";
	}

	// khi nhấn vào nút search thì form sẽ gửi về sever params= "btnsearch" thừ đó
	// ánh xạ vào hàm này
	@RequestMapping(params = "btnsearchKMSP")
	public String search(HttpServletRequest request, ModelMap model) {
		
		HttpSession session = request.getSession();
		String tennv = (String) session.getAttribute("tennv");

		// đẩy tên hiển thị trên thanh tìm kiếm
		model.addAttribute("hoten", tennv);

		// tạo một list lấy toàn bộ dự liệu tìm đc
		List<SanPhamEntity> listSP = this.searchSP(request.getParameter("searchInputKMSP"));

		// cập nhật lại tên cho button
		tenbutton = request.getParameter("tenbutton");
		model.addAttribute("btnStatus", tenbutton);
		model.addAttribute("product", km99);

		// dùng để đẩy dự liệu vào bảng chi tiết hóa đơn
		PagedListHolder pagedListHolderCTKM = new PagedListHolder(listSP1);
		int page2 = ServletRequestUtils.getIntParameter(request, "p2", 0);
		pagedListHolderCTKM.setPage(page2);
		pagedListHolderCTKM.setMaxLinkedPages(5);
		pagedListHolderCTKM.setPageSize(5);
		model.addAttribute("pagedListHolderCTKM", pagedListHolderCTKM);

		// đẩy dữ liệu vào bảng danh sách sản phẩm
		PagedListHolder pagedListHolder = new PagedListHolder(listSP);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolderBH", pagedListHolder);

		// đẩy dữ liêu vào bảng khuyến mãi
		List<KhuyenMaiEntity> listKM = this.getAllKM();
		PagedListHolder pagedListHolderKM = new PagedListHolder(listKM);
		int page1 = ServletRequestUtils.getIntParameter(request, "p1", 0);
		pagedListHolderKM.setPage(page1);
		pagedListHolderKM.setMaxLinkedPages(5);
		pagedListHolderKM.setPageSize(10);
		model.addAttribute("pagedListHolderKM", pagedListHolderKM);

		return "KhuyenMaiForSPInterface";

	}

	// khi nhấn vào nút search thì form sẽ gửi về sever params= "btnsearch" thừ đó
	// ánh xạ vào hàm này
	@RequestMapping(params = "btnsearch")
	public String searchKM(HttpServletRequest request, ModelMap model) {
		
		HttpSession session = request.getSession();
		String tennv = (String) session.getAttribute("tennv");

		// đẩy tên hiển thị trên thanh tìm kiếm
		model.addAttribute("hoten", tennv);



		// cập nhật tên nút
		tenbutton = request.getParameter("tenbutton");
		model.addAttribute("btnStatus", tenbutton);
		model.addAttribute("product", km99);
		
		// dùng để đẩy dự liệu vào bảng chi tiết hóa đơn
		PagedListHolder pagedListHolderCTKM = new PagedListHolder(listSP1);
		int page2 = ServletRequestUtils.getIntParameter(request, "p2", 0);
		pagedListHolderCTKM.setPage(page2);
		pagedListHolderCTKM.setMaxLinkedPages(5);
		pagedListHolderCTKM.setPageSize(5);
		model.addAttribute("pagedListHolderCTKM", pagedListHolderCTKM);

		// đẩy dữ liệu vào bảng danh sách sản phẩm
		List<SanPhamEntity> listSP = this.getAllSP();
		PagedListHolder pagedListHolder = new PagedListHolder(listSP);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolderBH", pagedListHolder);

		// đẩy dữ liêu vào bảng khuyến mãi
		List<KhuyenMaiEntity> listKM = this.searchKH(request.getParameter("searchInputKMSP"));
		PagedListHolder pagedListHolderKM = new PagedListHolder(listKM);
		int page1 = ServletRequestUtils.getIntParameter(request, "p1", 0);
		pagedListHolderKM.setPage(page1);
		pagedListHolderKM.setMaxLinkedPages(5);
		pagedListHolderKM.setPageSize(10);
		model.addAttribute("pagedListHolderKM", pagedListHolderKM);



		return "KhuyenMaiForSPInterface";

	}

	// lấy toàn bộ sản phẩm
	public List<SanPhamEntity> getAllSP() {
		Session session = fatory3.getCurrentSession();
		String hql = "FROM SanPhamEntity";
		Query query = session.createQuery(hql);
		List<SanPhamEntity> list = query.list();
		return list;
	}

	// lấy toàn bộ dữ liệu để hiện thị
	public List<KhuyenMaiEntity> getAllKM() {
		Session session = fatory3.getCurrentSession();
		String hql = "FROM KhuyenMaiEntity where LoaiKhuyenMai = 'KM2SP'";
		Query query = session.createQuery(hql);
		List<KhuyenMaiEntity> list = query.list();
		return list;
	}

	// hàm này kiểm tra sự tồn tại của sản phẩm
	public Boolean exisSP(String masp) {
		for (SanPhamEntity sp : listSP1) {
			if (sp.getMasp().strip().equals(masp.strip())) {
				return true;
			}
		}
		return false;
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

	// hàm này dùng để chèn vào cơ sở dữ liệu
	public Integer insertKH(KhuyenMaiEntity pd) {

		Session session = fatory3.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.evict(pd);
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

	// hàm này dùng để chèn vào bảng chi tiết khuyến mãi
	public Integer insertCTKM(CTKhuyenMaiEntity pd) {

		Session session = fatory3.openSession();
		Transaction t = session.beginTransaction();

		try {
			// session.evict(pd);
			session.saveOrUpdate(pd);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	// dùng để lấy 1 khuyến mãi nếu biết mã km
	public KhuyenMaiEntity getKH(String makm) {
		Session session = fatory3.getCurrentSession();
		String hql = "FROM KhuyenMaiEntity where MaKM = :makm";
		Query query = session.createQuery(hql);
		query.setParameter("makm", makm);
		KhuyenMaiEntity list = (KhuyenMaiEntity) query.list().get(0);
		return list;
	}

	// dùng để lấy trong bảng chi tiết khuyến mãi
	public List<CTKhuyenMaiEntity> getAllCTKM(String makm) {
		Session session = fatory3.getCurrentSession();
		String hql = "FROM CTKhuyenMaiEntity cm WHERE cm.id.makm = :makm";
		Query query = session.createQuery(hql);
		query.setParameter("makm", makm);
		List<CTKhuyenMaiEntity> list = query.list();
		return list;
	}

	// dùng để lấy 1 sản phẩm nếu biết mã sản phẩm
	public SanPhamEntity getSP(String masp) {
		Session session = fatory3.getCurrentSession();
		String hql = "FROM SanPhamEntity where MaSP = :masp";
		Query query = session.createQuery(hql);
		query.setParameter("masp", masp);
		SanPhamEntity list = (SanPhamEntity) query.list().get(0);
		return list;
	}

	// hàm này dùng để chỉnh sửa lại khuyến mãi
	public Integer updateKM(KhuyenMaiEntity pd) {
		Session session = fatory3.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.evict(pd);
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

	// hàm này dùng để chỉnh sửa lại khuyến mãi
	public Integer updateCTKM(CTKhuyenMaiEntity pd) {
		Session session = fatory3.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.evict(pd);
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

	// hàm này để xóa các km
	public void deleteCTKM(String makm) {
		Session session = fatory3.getCurrentSession();
		String hql = "DELETE FROM CTKhuyenMaiEntity cm WHERE cm.id.makm = :makm";
		Query query = session.createQuery(hql);
		query.setParameter("makm", makm);
		query.executeUpdate(); // Thêm dòng này để thực hiện xóa dữ liệu
		return;
	}

	// hàm này mở phiên làm việc rồi xóa
	public Integer deleteKM(KhuyenMaiEntity pd) {
		Session session = fatory3.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.evict(pd);
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

	// hàm này chỉ cần nhập tên sản phẩm thì nó sẽ đưa ra một list sản phẩm có tên
	// giống như vậy
	public List<SanPhamEntity> searchSP(String tensp) {
		Session session = fatory3.getCurrentSession();
		String hql = "FROM SanPhamEntity where tensp LIKE :tensp";
		Query query = session.createQuery(hql);
		query.setParameter("tensp", "%" + tensp + "%");
		List<SanPhamEntity> list = query.list();
		return list;
	}
	
	// hàm này chỉ cần nhập tên sản phẩm thì nó sẽ đưa ra một list sản phẩm có tên
	// giống như vậy
	public List<KhuyenMaiEntity> searchKH(String tenkm) {
		Session session = fatory3.getCurrentSession();
		String hql = "FROM KhuyenMaiEntity where LoaiKhuyenMai = 'KM2SP' and TenKM LIKE :TenKM ";
		Query query = session.createQuery(hql);
		query.setParameter("TenKM", "%" + tenkm + "%");
		List<KhuyenMaiEntity> list = query.list();
		return list;
	}


}