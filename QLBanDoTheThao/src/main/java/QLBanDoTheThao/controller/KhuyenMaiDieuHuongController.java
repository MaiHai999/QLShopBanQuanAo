package QLBanDoTheThao.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dieuhuong")
public class KhuyenMaiDieuHuongController {

	@RequestMapping()
	public String home(HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		String tennv = (String) session.getAttribute("tennv");

		// đẩy tên hiển thị trên thanh tìm kiếm
		model.addAttribute("hoten", tennv);

		return "DieuHuongKMInterface";
	}

	@RequestMapping(params = "btnKMSP")
	public String addSP(HttpServletRequest request, ModelMap model) {
		
		HttpSession session = request.getSession();
		String tennv = (String) session.getAttribute("tennv");

		// đẩy tên hiển thị trên thanh tìm kiếm
		model.addAttribute("hoten", tennv);
		return "redirect:/khuyenmaisp.htm";
	}

	@RequestMapping(params = "btnKhoaKMHD")
	public String addSP1(HttpServletRequest request, ModelMap model) {
		
		HttpSession session = request.getSession();
		String tennv = (String) session.getAttribute("tennv");

		// đẩy tên hiển thị trên thanh tìm kiếm
		model.addAttribute("hoten", tennv);
		return "redirect:/khuyenmai.htm";
	}

}
