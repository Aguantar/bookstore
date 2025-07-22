package member;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
//@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService service;

	
	 @GetMapping("register") public void getRegister() {}//WEV-INF/views/member/register.jsp
	 
	@PostMapping("register")
	public String register(RegisterForm form) {
		System.out.println(form);
		service.save(form);
		return "redirect:/login/login";
	}
	
	@GetMapping("login/login")
	public void login() {}
	
	 @GetMapping("mypage")
	    public String mypage(Model model, Principal principal) {
	        // 현재 로그인한 사용자 ID 가져오기
	        String username = principal.getName(); // 로그인된 사용자의 username
       
	        // DB에서 사용자 정보 조회
	        Member member = service.findByUserName(username);
	        
	        // JSP에 전달
	        model.addAttribute("member", member);

	        return "mypage"; 
	    }
	 
	 @GetMapping("updateform")
	    public String updateform(Model model, Principal principal) {
	        // 현재 로그인한 사용자 ID 가져오기
	        String username = principal.getName(); // 로그인된 사용자의 username
    
	        // DB에서 사용자 정보 조회
	        Member member = service.findByUserName(username);
	        
	        // JSP에 전달
	        model.addAttribute("member", member);

	        return "updateform"; 
	    }
	 	@PostMapping("update")
		public String update(RegisterForm form) {
			service.update(form);
			return "redirect:/login/login";
		}
	 	@PostMapping("delete")
		public String delete(Principal principal) {
	 		String username = principal.getName();
			service.delete(username);
			return "redirect:/login/login";
		}
	
}
