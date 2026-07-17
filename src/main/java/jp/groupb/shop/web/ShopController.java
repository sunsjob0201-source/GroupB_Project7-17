package jp.groupb.shop.web;

import jp.groupb.shop.model.*; import jp.groupb.shop.repository.*; import jp.groupb.shop.service.ShopService; import jp.groupb.shop.web.form.CheckoutForm;
import jakarta.validation.Valid; import lombok.RequiredArgsConstructor; import org.springframework.security.core.Authentication; import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.validation.BindingResult; import org.springframework.web.bind.annotation.*; import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.*;

@Controller @RequiredArgsConstructor
public class ShopController {
 private final ProductRepository products; private final MemberRepository members; private final OrderRepository orders; private final ShopService shop;
 @ModelAttribute("paymentMethods") PaymentMethod[] methods(){return PaymentMethod.values();}
 @GetMapping("/products") String products(Model m){m.addAttribute("products",products.findAll());return "products";}
 @PostMapping("/cart/add") String add(Authentication a,@RequestParam Long productId,@RequestParam(defaultValue="1") int quantity,RedirectAttributes ra){try{shop.addToCart(a.getName(),productId,quantity);ra.addFlashAttribute("message","カートに追加しました");}catch(RuntimeException e){ra.addFlashAttribute("error",e.getMessage());}return "redirect:/products";}
 @GetMapping("/cart") String cart(Authentication a,Model m){List<CartItem> items=shop.cart(a.getName());m.addAttribute("items",items);m.addAttribute("total",shop.total(items));return "cart";}
 @PostMapping("/cart/update") String update(Authentication a,@RequestParam Long itemId,@RequestParam int quantity,RedirectAttributes ra){try{shop.updateCart(a.getName(),itemId,quantity);}catch(RuntimeException e){ra.addFlashAttribute("error",e.getMessage());}return "redirect:/cart";}
 @PostMapping("/cart/remove") String remove(Authentication a,@RequestParam Long itemId){shop.removeCart(a.getName(),itemId);return "redirect:/cart";}
 @GetMapping("/checkout") String checkout(Authentication a,Model m){if(shop.cart(a.getName()).isEmpty())return "redirect:/cart"; if(!m.containsAttribute("checkoutForm")){Member x=members.findById(a.getName()).orElseThrow();CheckoutForm f=new CheckoutForm();f.setCustomerName(x.getName());f.setPostalCode(x.getPostalCode());f.setAddress(x.getAddress());f.setPhone(x.getPhone());f.setEmail(x.getEmail());f.setPaymentMethod(x.getPaymentMethod());m.addAttribute("checkoutForm",f);}return "checkout";}
 @PostMapping("/checkout/confirm") String confirm(Authentication a,@Valid @ModelAttribute CheckoutForm f,BindingResult br,Model m){if(br.hasErrors())return "checkout";List<CartItem> items=shop.cart(a.getName());if(items.isEmpty())return "redirect:/cart";m.addAttribute("items",items);m.addAttribute("total",shop.total(items));return "confirm";}
 @PostMapping("/checkout/complete") String complete(Authentication a,@Valid @ModelAttribute CheckoutForm f,BindingResult br,Model m){if(br.hasErrors())return "checkout";try{ShopOrder o=shop.placeOrder(a.getName(),f);return "redirect:/orders/"+o.getId()+"/complete";}catch(RuntimeException e){m.addAttribute("error",e.getMessage());m.addAttribute("items",shop.cart(a.getName()));m.addAttribute("total",shop.total(shop.cart(a.getName())));return "confirm";}}
 @GetMapping("/orders/{id}/complete") String completePage(Authentication a,@PathVariable Long id,Model m){ShopOrder o=orders.findById(id).filter(x->x.getMember().getMemberId().equals(a.getName())).orElseThrow();m.addAttribute("order",o);return "complete";}
 @GetMapping("/orders") String history(Authentication a,Model m){m.addAttribute("orders",orders.findByMemberMemberIdOrderByOrderedAtDesc(a.getName()));return "orders";}
}

