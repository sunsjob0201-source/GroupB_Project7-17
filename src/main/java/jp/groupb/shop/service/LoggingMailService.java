package jp.groupb.shop.service;
import jp.groupb.shop.model.ShopOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Service @Slf4j
public class LoggingMailService implements MailService {
 public void sendOrderConfirmation(ShopOrder order){
  log.info("[仮メール送信/Bグループ] 宛先={}, 注文番号={}, 合計={}円",order.getEmail(),order.getId(),order.getTotalAmount());
 }
}

