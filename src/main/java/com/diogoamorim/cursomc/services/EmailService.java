package com.diogoamorim.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.diogoamorim.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido email);
	
	void sendEmail(SimpleMailMessage msg);

}
