package jp.groupb.shop.repository;
import jp.groupb.shop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
 List<CartItem> findByMemberMemberIdOrderById(String memberId);
 Optional<CartItem> findByMemberMemberIdAndProductId(String memberId, Long productId);
 long countByMemberMemberId(String memberId);
 void deleteByMemberMemberId(String memberId);
}

