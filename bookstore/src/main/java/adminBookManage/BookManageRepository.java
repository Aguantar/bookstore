package adminBookManage;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import domain.Book;

@Mapper
public interface BookManageRepository {

    @Select("SELECT book_id AS bookId, title, author, price, stock, description, cover_image AS coverImage FROM books")
    List<Book> findAll();

    @Select("SELECT book_id AS bookId, title, author, price, stock, description, cover_image AS coverImage FROM books WHERE book_id = #{id}")
    Book findById(Long bookId);

    @Insert("INSERT INTO books (title, author, price, stock, description, cover_image) " +
            "VALUES (#{title}, #{author}, #{price}, #{stock}, #{description}, #{coverImage})")
    void insert(Book book);

    @Update("UPDATE books SET title=#{title}, author=#{author}, price=#{price}, stock=#{stock}, description=#{description}, cover_image=#{coverImage} WHERE book_id=#{bookId}")
    void update(Book book);

    @Delete("DELETE FROM books WHERE book_id = #{id}")
    void delete(Long bookId);


    // ✅ 검색어 기반 총 개수
    @Select("SELECT COUNT(*) FROM books " +
            "WHERE (#{keyword, jdbcType=VARCHAR} IS NULL OR #{keyword, jdbcType=VARCHAR} = '' " +
            "   OR LOWER(title) LIKE '%'||LOWER(#{keyword, jdbcType=VARCHAR})||'%' " +
            "   OR LOWER(author) LIKE '%'||LOWER(#{keyword, jdbcType=VARCHAR})||'%')")
    int countByKeyword(@Param("keyword") String keyword);

    // ✅ Oracle 기준 페이징 검색 결과
    @Select(
    	    "SELECT * FROM ( " +
    	    "  SELECT b.*, ROWNUM rnum FROM ( " +
    	    "    SELECT book_id AS bookId, title, author, price, stock, description, cover_image AS coverImage " +
    	    "    FROM books " +
    	    "    WHERE (#{keyword, jdbcType=VARCHAR} IS NULL OR #{keyword, jdbcType=VARCHAR} = '' " +
    	    "       OR LOWER(title) LIKE '%'||LOWER(#{keyword, jdbcType=VARCHAR})||'%' " +
    	    "       OR LOWER(author) LIKE '%'||LOWER(#{keyword, jdbcType=VARCHAR})||'%') " +
    	    "    ORDER BY book_id DESC " +
    	    "  ) b WHERE ROWNUM <= #{endRow} " +
    	    ") WHERE rnum > #{startRow}"
    	)
    	List<Book> findByKeywordWithPaging(@Param("keyword") String keyword,
    	                                   @Param("startRow") int startRow,
    	                                   @Param("endRow") int endRow);
}