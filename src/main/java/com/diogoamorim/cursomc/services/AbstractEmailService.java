package com.diogoamorim.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.diogoamorim.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendOrderConfirmationEmail(Pedido email) {
		SimpleMailMessage sm =  prepareSimpleMailMessageFromPedido(email);
		sendEmail(sm);
		
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido email) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(email.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado ! Código: " + email.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		return sm;
	}	
	
	
}
