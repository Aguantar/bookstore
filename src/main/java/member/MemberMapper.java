package member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MemberMapper {
	public int save(Member member);
	public Member findByUserName(String username);
	public int update(RegisterForm form);
	public int delete(String username);
}
