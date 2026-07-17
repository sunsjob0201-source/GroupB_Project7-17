package jp.groupb.shop.repository;
import jp.groupb.shop.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MemberRepository extends JpaRepository<Member,String> {}

