package jp.groupb.shop.web;
import jakarta.validation.Valid; import jp.groupb.shop.model.Product; import jp.groupb.shop.repository.ProductRepository; import jp.groupb.shop.web.form.ProductForm; import lombok.RequiredArgsConstructor; import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.validation.BindingResult; import org.springframework.web.bind.annotation.*;
@Controller @RequestMapping("/admin/products") @RequiredArgsConstructor
public class AdminController {
 private final ProductRepository repo;
 @GetMapping String list(Model m){m.addAttribute("products",repo.findAll());return "admin-products";}
 @GetMapping("/new") String create(Model m){m.addAttribute("productForm",new ProductForm());m.addAttribute("productId",null);return "admin-product-form";}
 @GetMapping("/{id}/edit") String edit(@PathVariable Long id,Model m){Product p=repo.findById(id).orElseThrow();ProductForm f=new ProductForm();f.setName(p.getName());f.setDescription(p.getDescription());f.setPrice(p.getPrice());f.setStock(p.getStock());f.setImageUrl(p.getImageUrl());m.addAttribute("productForm",f);m.addAttribute("productId",id);return "admin-product-form";}
 @PostMapping("/save") String save(@RequestParam(required=false) Long productId,@Valid @ModelAttribute ProductForm f,BindingResult br,Model m){if(br.hasErrors()){m.addAttribute("productId",productId);return "admin-product-form";}Product p=productId==null?new Product():repo.findById(productId).orElseThrow();p.setName(f.getName());p.setDescription(f.getDescription());p.setPrice(f.getPrice());p.setStock(f.getStock());p.setImageUrl(f.getImageUrl());repo.save(p);return "redirect:/admin/products";}
 @PostMapping("/{id}/delete") String delete(@PathVariable Long id){repo.deleteById(id);return "redirect:/admin/products";}
}

