package member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	
}
