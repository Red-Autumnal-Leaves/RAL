package com.ral.service.mail.impl;

import com.ral.service.mail.IJMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 邮件发送Service实现类
 */
@Service
public class JMailServiceImpl implements IJMailService {

	@Autowired
	private JavaMailSender sender;

	@Value("${spring.mail.username}")
	private String from;

	@Override
	public void send(String to, String subject, String html) throws Exception {
		MimeMessage message = sender.createMimeMessage();
		message.setContent(html, "text/html;charset=UTF-8");
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(from);
		helper.setTo(new InternetAddress(to));
		helper.setSubject(subject);
		helper.setText(html, true);
		sender.send(message);
	}

	@Override
	public void send(String to, String subject, String html, List<File> files) throws Exception {
		MimeMessage message = sender.createMimeMessage();
		message.setFrom(from);
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		Multipart mp = new MimeMultipart();
		BodyPart bp = new MimeBodyPart();
		bp.setContent(html, "text/html;charset=UTF-8");
		mp.addBodyPart(bp);
		this.addFiles(files,bp,mp);
		message.setContent(mp);
		message.saveChanges();
		sender.send(message);
	}

	@Override
	public void send(List<String> toes, List<String> copyTo, String subject, String html, List<File> files)
			throws Exception {
		MimeMessage message = sender.createMimeMessage();
		message.setSubject(subject);
		message.setFrom(from);
		for (String to : toes) {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		}
		if (copyTo != null) {
			for (String copy : copyTo) {
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(copy));
			}
		}
		Multipart mp = new MimeMultipart();
		BodyPart bp = new MimeBodyPart();
		bp.setContent(html, "text/html;charset=UTF-8");
		mp.addBodyPart(bp);
		this.addFiles(files,bp,mp);
		message.setContent(mp);
		message.saveChanges();
		sender.send(message);
	}


	private void addFiles(List<File> files,BodyPart bp,Multipart mp) throws MessagingException,UnsupportedEncodingException {
		if (files != null) {
			for (File file : files) {
				bp = new MimeBodyPart();
				FileDataSource fileds = new FileDataSource(file);
				bp.setDataHandler(new DataHandler(fileds));
				bp.setFileName(MimeUtility.encodeText(fileds.getName(), "UTF-8", "B"));
				mp.addBodyPart(bp);
			}
		}
	}

	@Override
	public void send(List<String> to, String subject, String html, List<File> files) throws Exception {
		this.send(to, null, subject, html, files);
	}
	
}
