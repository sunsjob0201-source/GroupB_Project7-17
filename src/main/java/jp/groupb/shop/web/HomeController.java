package jp.groupb.shop.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller public class HomeController {
 @GetMapping({"/", "/home"}) String index(){ return "index"; }
 @GetMapping("/login") String login(){ return "login"; }
 @GetMapping("/menu") String menu(){ return "menu"; }
}
