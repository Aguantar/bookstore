package adminBookManage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Book;

@Controller
@RequestMapping("") // 이게 있어야 URL이 명확해짐
public class BookManageController {

    @Autowired
    private BookManageService service;

    /** 도서 목록 */
    @GetMapping("/admin/bookManage")
    public String list(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {

        int pageSize = 8;
        int totalCount = service.getTotalCount(keyword);
        int totalPage = (int) Math.ceil((double) totalCount / pageSize);

        int blockSize = 5;
        int startPage = ((page - 1) / blockSize) * blockSize + 1;
        int endPage = Math.min(startPage + blockSize - 1, totalPage);

        List<Book> books = service.getPagedBooks(keyword, page, pageSize);

        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("bodyPage", "list.jsp");
        model.addAttribute("pageTitle", "관리자 도서 목록");

        return "layout"; // JSP 경로에 맞게 변경하세요
    }

    /** 등록 폼 (빈 폼) */
    @GetMapping("/form")
    public String emptyForm(Model model) {
        model.addAttribute("book", new Book());
        return "form"; // /WEB-INF/views/adminBookManage/form.jsp
    }

    /** 수정 폼 (기존 데이터 로드) */
    @GetMapping("/form/{bookId}")
    public String editForm(@PathVariable Long bookId, Model model) {
        Book book = service.getById(bookId);
        model.addAttribute("book", book);
        return "form";
    }

    /** 저장 처리 (신규 등록 or 수정) */
    @PostMapping("/save")
    public String save(@ModelAttribute Book book) {
        if (book.getBookId() == null) {
            service.save(book); // 신규 등록
        } else {
            service.update(book); // 기존 데이터 수정
        }
        return "redirect:/admin/bookManage";
    }

    /** 삭제 */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/admin/bookManage";
    }
}