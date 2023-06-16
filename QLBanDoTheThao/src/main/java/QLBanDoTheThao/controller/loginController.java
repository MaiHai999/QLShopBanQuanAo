package QLBanDoTheThao.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import QLBanDoTheThao.entity.NhanVienEntity;
import QLBanDoTheThao.entity.SanPhamEntity;
import QLBanDoTheThao.entity.TaiKhoanEntity;





@Transactional
@Controller
@RequestMapping("/login")
public class loginController {
	
	@Autowired
	SessionFactory fatory;
	
	@RequestMapping()
	public String login(ModelMap model, HttpServletRequest request) {
		return "login";
	}
	
	@RequestMapping(params = "dangnhap" )
	public String checklogin(ModelMap model ) {
		return "login";
	}
	
	@RequestMapping(params = "dangnhap" , method = RequestMethod.POST )
	public String checklogin(ModelMap model,HttpServletRequest request , @RequestParam("login") String userName , @RequestParam("password") String matKhau) {

		List<TaiKhoanEntity> list = this.getAll();
		for(TaiKhoanEntity tk : list) {
			if(tk.getLogin() != null) {
				if(tk.getLogin().strip().equals(userName)) {
					if(this.checkPassword(matKhau.strip(),tk.getPassword().strip())) {
						
						NhanVienEntity nv = this.getNV(tk.getMatk());
						String hoten = nv.getHonv().strip() + " " + nv.getTennv();
						String manv = nv.getManv();
						String macd = nv.getMacd();
						

						
						HttpSession session = request.getSession();
						session.setAttribute("var", "true");
						session.setAttribute("tennv", hoten);
						session.setAttribute("manv", manv);
						
						
						if(macd.strip().equals("cd02")) {
							session.setAttribute("cd", 1);
							return "redirect:/banhang.htm";
						}else if (macd.strip().equals("cd03")) {
							session.setAttribute("cd", 2);
							return "redirect:/hoadonban.htm";
						}else {
							session.setAttribute("cd", 3);
							return "redirect:/banhang.htm";
						}
						
						
						
					}
				}
			}
		}
		System.out.println("  hai");
		model.addAttribute("message", "Đăng nhập thất bại!!");
		return "login";
	}
	

	public static String hashPassword(String password) {
        // Mã hóa mật khẩu với salt mặc định
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(password, salt);
        return hashedPassword;
    }
	
	public static boolean checkPassword(String password, String hashedPassword) {
        // So sánh mật khẩu đã mã hóa với mật khẩu người dùng nhập vào
        return BCrypt.checkpw(password, hashedPassword);
    }
	
	
	
	
	
	public List<TaiKhoanEntity> getAll(){
		Session session  = fatory.getCurrentSession();
		String hql = "FROM TaiKhoanEntity";
		Query query = session.createQuery(hql);
		List<TaiKhoanEntity> list = query.list();
		return list;
	}
	
	public List<SanPhamEntity> getAllSP(){
		Session session  = fatory.getCurrentSession();
		String hql = "FROM SanPhamEntity";
		Query query = session.createQuery(hql);
		List<SanPhamEntity> list = query.list();
		return list;
	}
	
	
	// dùng để lấy 1 nhân viên nếu biết mã nhân vien
	public NhanVienEntity getNV(String matk) {
		Session session = fatory.getCurrentSession();
		String hql = "FROM NhanVienEntity where MaTK = :matk";
		Query query = session.createQuery(hql);
		query.setParameter("matk", matk);
		NhanVienEntity list = (NhanVienEntity) query.list().get(0);
		return list;
	}
	
	
	

}
