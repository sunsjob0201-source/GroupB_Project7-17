package jp.groupb.shop.web;

import jakarta.validation.Valid;
import jp.groupb.shop.model.*;
import jp.groupb.shop.repository.MemberRepository;
import jp.groupb.shop.web.form.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller @RequiredArgsConstructor
public class MemberController {
 private final MemberRepository repo; private final PasswordEncoder encoder;
 @ModelAttribute("paymentMethods") PaymentMethod[] methods(){return PaymentMethod.values();}
 @GetMapping("/register") String form(Model m){m.addAttribute("memberForm",new MemberForm()); return "register";}
 @PostMapping("/register") String register(@Valid @ModelAttribute MemberForm f, BindingResult br){
  if(!f.getPassword().equals(f.getPasswordConfirm())) br.rejectValue("passwordConfirm","mismatch","パスワードが一致していません");
  if(repo.existsById(f.getMemberId())) br.rejectValue("memberId","duplicate","この会員IDは使用されています"); if(br.hasErrors()) return "register";
  Member x=new Member(); x.setMemberId(f.getMemberId()); x.setPassword(encoder.encode(f.getPassword())); copy(f,x); repo.save(x); return "redirect:/login?registered";
 }
 @GetMapping("/profile") String profile(Authentication a,Model m){Member x=repo.findById(a.getName()).orElseThrow(); ProfileForm f=new ProfileForm(); f.setName(x.getName());f.setPostalCode(x.getPostalCode());f.setAddress(x.getAddress());f.setPhone(x.getPhone());f.setBirthDate(x.getBirthDate());f.setEmail(x.getEmail());f.setPaymentMethod(x.getPaymentMethod());m.addAttribute("profileForm",f);m.addAttribute("memberId",x.getMemberId());return "profile";}
 @PostMapping("/profile") String update(Authentication a,@Valid @ModelAttribute ProfileForm f,BindingResult br,Model m){if(br.hasErrors()){m.addAttribute("memberId",a.getName());return "profile";} Member x=repo.findById(a.getName()).orElseThrow();if(f.getPassword()!=null&&!f.getPassword().isBlank())x.setPassword(encoder.encode(f.getPassword()));x.setName(f.getName());x.setPostalCode(f.getPostalCode());x.setAddress(f.getAddress());x.setPhone(f.getPhone());x.setBirthDate(f.getBirthDate());x.setEmail(f.getEmail());x.setPaymentMethod(f.getPaymentMethod());repo.save(x);return "redirect:/profile?saved";}
 private void copy(MemberForm f,Member x){x.setName(f.getName());x.setPostalCode(f.getPostalCode());x.setAddress(f.getAddress());x.setPhone(f.getPhone());x.setBirthDate(f.getBirthDate());x.setEmail(f.getEmail());x.setPaymentMethod(f.getPaymentMethod());x.setRole(Role.USER);}
}
