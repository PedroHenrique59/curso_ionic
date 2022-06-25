package Aplicacao.services;

import Aplicacao.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmation(Pedido pedido);

    void sendEmail(SimpleMailMessage simpleMailMessage);

}
