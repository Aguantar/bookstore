package member;

import javax.servlet.http.HttpSession;

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
	
	@GetMapping("/mypage")
    public String mypage(HttpSession session, Model model) {

        // 세션에서 로그인 사용자 확인
        Member loginUser = (Member) session.getAttribute("loginMember");

        if (loginMember == null) {
            return "redirect:/login"; // 로그인 안 했을 경우 로그인 페이지로
        }

        // DB에서 사용자 정보 조회 (세션값으로도 가능하지만 최신화 목적)
        Member member = service.findByUserName(loginUser.getUsername());

        // JSP로 사용자 정보 전달
        model.addAttribute("user", Member);

        return "mypage"; // → /WEB-INF/views/mypage.jsp
    }
	
}
