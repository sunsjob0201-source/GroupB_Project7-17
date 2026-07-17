package jp.groupb.shop.repository;
import jp.groupb.shop.model.ShopOrder;
import org.springframework.data.jpa.repository.*;
import java.util.*;
public interface OrderRepository extends JpaRepository<ShopOrder,Long> {
 @EntityGraph(attributePaths="items") List<ShopOrder> findByMemberMemberIdOrderByOrderedAtDesc(String memberId);
}

