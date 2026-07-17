package jp.groupb.shop.service;

import jp.groupb.shop.model.*;
import jp.groupb.shop.repository.*;
import jp.groupb.shop.web.form.CheckoutForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service @RequiredArgsConstructor
public class ShopService {
 private final MemberRepository members; private final ProductRepository products; private final CartItemRepository carts;
 private final OrderRepository orders; private final MailService mailService;
 @Transactional public void addToCart(String memberId, Long productId, int quantity){
  if(quantity < 1) throw new IllegalArgumentException("数量は1以上です");
  Product p=products.findById(productId).orElseThrow();
  CartItem item=carts.findByMemberMemberIdAndProductId(memberId,productId).orElseGet(() -> { CartItem c=new CartItem(); c.setMember(members.getReferenceById(memberId)); c.setProduct(p); return c; });
  int newQty=item.getId()==null ? quantity : item.getQuantity()+quantity;
  if(p.getStock()<newQty) throw new IllegalArgumentException("在庫数を超えています");
  item.setQuantity(newQty); carts.save(item);
 }
 @Transactional public void updateCart(String memberId, Long itemId, int quantity){
  CartItem item=carts.findById(itemId).filter(c->c.getMember().getMemberId().equals(memberId)).orElseThrow();
  if(quantity<1 || quantity>item.getProduct().getStock()) throw new IllegalArgumentException("数量は1以上、在庫数以下で入力してください");
  item.setQuantity(quantity);
 }
 @Transactional public void removeCart(String memberId, Long itemId){ carts.findById(itemId).filter(c->c.getMember().getMemberId().equals(memberId)).ifPresent(carts::delete); }
 public List<CartItem> cart(String memberId){ return carts.findByMemberMemberIdOrderById(memberId); }
 public BigDecimal total(List<CartItem> items){ return items.stream().map(i->i.getProduct().getPrice().multiply(BigDecimal.valueOf(i.getQuantity()))).reduce(BigDecimal.ZERO,BigDecimal::add); }
 @Transactional public ShopOrder placeOrder(String memberId, CheckoutForm f){
  List<CartItem> current=carts.findByMemberMemberIdOrderById(memberId); if(current.isEmpty()) throw new IllegalStateException("カートが空です");
  ShopOrder order=new ShopOrder(); order.setMember(members.getReferenceById(memberId)); order.setOrderedAt(LocalDateTime.now());
  order.setCustomerName(f.getCustomerName()); order.setPostalCode(f.getPostalCode()); order.setAddress(f.getAddress()); order.setPhone(f.getPhone()); order.setEmail(f.getEmail()); order.setPaymentMethod(f.getPaymentMethod());
  BigDecimal total=BigDecimal.ZERO;
  for(CartItem c:current){
   Product p=products.findByIdForUpdate(c.getProduct().getId()).orElseThrow();
   if(p.getStock()<c.getQuantity()) throw new IllegalStateException(p.getName()+"の在庫が不足しています");
   p.setStock(p.getStock()-c.getQuantity());
   OrderItem oi=new OrderItem(); oi.setProductId(p.getId()); oi.setProductName(p.getName()); oi.setUnitPrice(p.getPrice()); oi.setQuantity(c.getQuantity()); order.addItem(oi); total=total.add(oi.getSubtotal());
  }
  order.setTotalAmount(total); ShopOrder saved=orders.save(order); carts.deleteAll(current); mailService.sendOrderConfirmation(saved); return saved;
 }
}

