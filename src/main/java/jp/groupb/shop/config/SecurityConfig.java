package jp.groupb.shop.config;

import jakarta.servlet.DispatcherType;
import jp.groupb.shop.repository.MemberRepository;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
 @Bean PasswordEncoder passwordEncoder(){
  BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
  return new PasswordEncoder(){
   @Override public String encode(CharSequence raw){ return bcrypt.encode(raw); }
   @Override public boolean matches(CharSequence raw,String encoded){
    if(encoded != null && (encoded.startsWith("$2a$") || encoded.startsWith("$2b$") || encoded.startsWith("$2y$"))) return bcrypt.matches(raw,encoded);
    return encoded != null && encoded.equals(raw.toString());
   }
  };
 }
 @Bean UserDetailsService userDetailsService(MemberRepository repo){
  return id -> repo.findById(id).map(m -> User.withUsername(m.getMemberId()).password(m.getPassword()).roles(m.getRole().name()).build())
   .orElseThrow(() -> new UsernameNotFoundException("会員IDが見つかりません"));
 }
 @Bean SecurityFilterChain security(HttpSecurity http) throws Exception {
  http.authorizeHttpRequests(a -> a
    .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
    .requestMatchers("/", "/home", "/index.jsp", "/login", "/register/**", "/css/**", "/js/**", "/images/**", "/error").permitAll()
    .requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated())
   .formLogin(f -> f.loginPage("/login").usernameParameter("memberId").defaultSuccessUrl("/menu",true).failureUrl("/login?error").permitAll())
   .logout(l -> l.logoutSuccessUrl("/login?logout").permitAll());
  return http.build();
 }
}
