package adminUser;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("") // DispatcherServlet이 /admin/users/* 기준으로 진입함
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    /** 전체 사용자 목록 */
    @GetMapping("/")  // 🔥 중요! 빈 경로 말고 "/"로 설정해야 DispatcherServlet이 매핑함
    public String listUsers(Model model) throws SQLException {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/userList"; // → /WEB-INF/views/admin/users/userList.jsp
    }

    /** 등록 폼 보여주기 */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "users/userForm";
    }

    /** 등록 처리 */
    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user,
                          BindingResult binding) throws SQLException {
        if (binding.hasErrors()) {
            return "users/userForm";
        }
        userService.registerUser(user);
        return "redirect:/admin/users/"; // 꼭 끝에 슬래시 붙여줘야 정확하게 작동해
    }

    /** 수정 폼 보여주기 */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long userId,
                               Model model) throws SQLException {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "users/userForm";
    }

    /** 수정 처리 */
    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user,
                             BindingResult binding) throws SQLException {
        if (binding.hasErrors()) {
            return "users/userForm";
        }
        userService.updateUser(user);
        return "redirect:/admin/users/";
    }

    /** 삭제 처리 */
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long userId) throws SQLException {
        userService.deleteUser(userId);
        return "redirect:/admin/users/";
    }
}