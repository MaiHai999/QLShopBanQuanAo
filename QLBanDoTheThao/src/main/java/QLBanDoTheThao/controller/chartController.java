package QLBanDoTheThao.controller;

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

import QLBanDoTheThao.entity.HoaDonEntity;
import QLBanDoTheThao.entity.SanPhamEntity;



@Transactional
@Controller
@RequestMapping("/chart")
public class chartController {
	
	
	@Autowired
	SessionFactory fatory4;
	
	@RequestMapping()
	public String home(HttpServletRequest request, ModelMap model) {
		
		HttpSession session = request.getSession();
		String myCheck = (String) session.getAttribute("var");
		String tennv = (String) session.getAttribute("tennv");
		int cd = (int) session.getAttribute("cd");
		
		if(myCheck != null && cd != 1){
			
			model.addAttribute("check" , cd);

			//đẩy tên hiển thị trên thanh tìm kiếm
			model.addAttribute("hoten", tennv);
			
			String nam = request.getParameter("sonam");
			if(nam == null) {
				nam = "2023";
			}
			model.addAttribute("nam", nam);
			int nam1 = Integer.parseInt(nam);
			
			Double doanhthu1 = this.getDoanhThu(1, nam1);
			model.addAttribute("thang1", doanhthu1);
			
			Double doanhthu2 = this.getDoanhThu(2, nam1);
			model.addAttribute("thang2", doanhthu2);
			
			Double doanhthu3 = this.getDoanhThu(3, nam1);
			model.addAttribute("thang3", doanhthu3);
			
			Double doanhthu4 = this.getDoanhThu(4, nam1);
			model.addAttribute("thang4", doanhthu4);
			
			Double doanhthu5 = this.getDoanhThu(5, nam1);
			model.addAttribute("thang5", doanhthu5);
			
			Double doanhthu6 = this.getDoanhThu(6, nam1);
			model.addAttribute("thang6", doanhthu6);
			
			Double doanhthu7 = this.getDoanhThu(7, nam1);
			model.addAttribute("thang7", doanhthu7);
			
			Double doanhthu8 = this.getDoanhThu(8, nam1);
			model.addAttribute("thang8", doanhthu8);
			
			Double doanhthu9 = this.getDoanhThu(9, nam1);
			model.addAttribute("thang9", doanhthu9);
			
			Double doanhthu10 = this.getDoanhThu(10, nam1);
			model.addAttribute("thang10", doanhthu10);
			
			Double doanhthu11 = this.getDoanhThu(11, nam1);
			model.addAttribute("thang11", doanhthu11);
			
			Double doanhthu12 = this.getDoanhThu(12, nam1);
			model.addAttribute("thang12", doanhthu12);
			
			return "chartIterface";
		
		}else {
			return "login";
		}
		
			
		
	}
	
	
	public Double getDoanhThu(int thang , int nam) {
		Session session = fatory4.getCurrentSession();
		String hql = "FROM HoaDonEntity h where month(h.ngaylap) = :thang AND year(h.ngaylap) = :nam";
		Query query = session.createQuery(hql);
		query.setParameter("thang", thang);
		query.setParameter("nam", nam);
		List<HoaDonEntity> list = query.list();
		Double doanhthu = 0.0;
		for(HoaDonEntity h :list) {
			doanhthu = doanhthu + h.getTongtien();
		}
		
		return doanhthu;
	}
	
	

}
