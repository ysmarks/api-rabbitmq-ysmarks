package com.example.apirabbitmqysmarks.domain;

public class Notification {
	
	private String notificationType;
	private String mensagem;
	
	public Notification() {}
	
	public String getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String toString() {
		return "Notification [notificationType=" + notificationType + ", mensagem=" + mensagem + "]";
	}
	
	
}
