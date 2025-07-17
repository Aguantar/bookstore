package adminUser;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")  // 웹.xml의 /admin/* 매핑 아래에서 /users 로 들어옴 → 실제 URL: /admin/users
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /** 전체 사용자 목록 */
    @GetMapping
    public String listUsers(Model model) throws SQLException {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userList";         // → /WEB-INF/views/admin/userList.jsp
    }

    /** 등록 폼 보여주기 */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "userForm";         // → /WEB-INF/views/admin/userForm.jsp
    }

    /** 등록 처리 */
    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user,
                          BindingResult binding) throws SQLException {
        if (binding.hasErrors()) {
            return "userForm";
        }
        userService.registerUser(user);
        // 리다이렉트는 클라이언트가 바라보는 전체 경로 기준
        return "redirect:/admin/users";
    }

    /** 수정 폼 보여주기 */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long userId,
                               Model model) throws SQLException {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "userForm";
    }

    /** 수정 처리 */
    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user,
                             BindingResult binding) throws SQLException {
        if (binding.hasErrors()) {
            return "userForm";
        }
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    /** 삭제 처리 */
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long userId) throws SQLException {
        userService.deleteUser(userId);
        return "redirect:/admin/users";
    }
}