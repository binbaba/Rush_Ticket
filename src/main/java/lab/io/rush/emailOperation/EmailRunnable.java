package emailOperation;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class EmailRunnable implements Runnable{
	private String email;
	private String userName;
	private String movieName;

	public EmailRunnable(String userName,String email,String movieName){
		this.userName = userName;
		this.email = email;
		this.movieName = movieName;
	}
	@Override
	public void run() {
		String subject = "抢票成功！";
		String messageText = "亲爱的"+userName+",恭喜您抢到“"+movieName+"”电影票一张，可前往深圳市各大万达影院进行免费观看，详情到店咨询。";
		if(email.matches(".+@qq\\.com")){
			sendMessage("smtp.qq.com","495743151@qq.com",
					"rceluwabxdofbjhi", email, subject, messageText);
		}else{
			sendMessage("smtp.163.com","13751824823@163.com",
					"a963s741", email, subject, messageText);
		}
	}

	public void sendMessage(String smtpHost,String sender,  
			String senderPassword, String receiver, String subject,  
			String messageText){
		Properties properties = new Properties();
		properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", "465"); 
        properties.put("mail.smtp.socketFactory.fallback", "false"); 
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "false");
        properties.put("mail.smtp.ssl.enable", "true");

		Session session = Session.getInstance(properties, new MyAuthenticator(sender,senderPassword));

		Message message = new MimeMessage(session);
		InternetAddress senderAddress = null;
		InternetAddress receiverAddress = null;
		try {
			senderAddress = new InternetAddress(sender);
			receiverAddress = new InternetAddress(receiver);
			message.setFrom(senderAddress);
			message.setRecipient(RecipientType.TO, receiverAddress);

			message.setSubject(subject);
			message.setSentDate(new Date());
			message.setText(messageText);

			Transport.send(message);
			System.out.println("发送成功");  
		} catch (AddressException e) {
			System.out.println("发送有误");  
			e.printStackTrace();
		}catch (MessagingException e) {
			System.out.println("发送有误");
			e.printStackTrace();
		}		
	}
}

class MyAuthenticator extends Authenticator{
	String userName;  
	String password;  

	public MyAuthenticator(String userName,String password){  
		this.userName=userName;  
		this.password=password;  
	}  
	protected PasswordAuthentication getPasswordAuthentication(){     
		return new PasswordAuthentication(userName, password);     
	}   
} 

