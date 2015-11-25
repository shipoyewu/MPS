package com.zzu.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

//发送邮件处理
public class EmailUtil {
	public static boolean sendEmail(String eamil){
		String qm = "zhou0110..."; // 您的QQ密码
		String tu = "qq.com"; // 你邮箱的后缀域名uv
		//String tto = "209152096@qq.com"; // 接收邮件的邮箱
		String tto=eamil;//赋值给要发送的邮箱地址
		String ttitle = "您好,"+eamil+ "先生";
		String tcontent = "如果不是您本人操作说明您的密码已经泄漏,请立即和我们联系!请点击后面的链接重置密码:http://localhost:8080/MPS/jsp/resetPassword.jsp?email="+eamil;
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp." + tu);// 发信的主机，这里我填写的是我们公司的主机！可以不用修改！
		props.put("mail.smtp.auth", "true");
		Session s = Session.getInstance(props);
		s.setDebug(true);
		MimeMessage message = new MimeMessage(s);
		// 给消息对象设置发件人/收件人/主题/发信时间
		try {
			InternetAddress from = new InternetAddress("362842353@" + tu); // 这里的115798090
																			// 改为您发信的QQ号
			message.setFrom(from);
			InternetAddress to = new InternetAddress(tto);
			message.setRecipient(Message.RecipientType.TO, to);
			message.setSubject(ttitle);
			message.setSentDate(new Date());
			// 给消息对象设置内容
			BodyPart mdp = new MimeBodyPart();// 新建一个存放信件内容的BodyPart对象
			mdp.setContent(tcontent, "text/html;charset=gb2312");// 给BodyPart对象设置内容和格式/编码方式
			Multipart mm = new MimeMultipart();// 新建一个MimeMultipart对象用来存放BodyPart对
			// 象(事实上可以存放多个)
			mm.addBodyPart(mdp);// 将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)
			message.setContent(mm);// 把mm作为消息对象的内容
			message.saveChanges();
			Transport transport = s.getTransport("smtp");
			transport.connect("smtp." + tu, "362842353", qm);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
