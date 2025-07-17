package adminBookManage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Book;

@Service
public class BookManageService {

    @Autowired
    private BookManageRepository repo;

    public List<Book> getAll() {
        return repo.findAll();
    }

    public void save(Book book) {
        repo.insert(book);
    }

    public Book getById(Long id) {
        return repo.findById(id);
    }

    public void update(Book book) {
        repo.update(book);
    }

    public void delete(Long id) {
        repo.delete(id);
    }

    // ✅ 검색어에 따른 총 도서 수
    public int getTotalCount(String keyword) {
        return repo.countByKeyword(keyword);
    }

    // ✅ 검색어 + 페이지네이션에 따른 도서 목록
    public List<Book> getPagedBooks(String keyword, int page, int pageSize) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;
        return repo.findByKeywordWithPaging(keyword, startRow, endRow);
    }
}