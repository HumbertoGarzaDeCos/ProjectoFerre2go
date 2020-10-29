package com.beto.apps.service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailAdapter {
	 @Autowired
	    private JavaMailSender emailSender;
	 
	
	
	    public void sendSimpleMessage(
	      String to, String subject, String titulo,String contenido) {
	    	 try {
	    	 MimeMessage message = emailSender.createMimeMessage();
		        
		       MimeMessageHelper helper = new MimeMessageHelper(message,true, "utf-8");
	       
		       helper.setFrom("ferre2go@gmail.com");
		       helper.setTo(to); 
		       helper.setSubject(subject); 
		       helper.setText(template.replace("%TITULO%", titulo).replace("%CONTENIDO%", contenido), true);
		       helper.addInline("logo",  new ClassPathResource("static/logo.png"));
	     
			
			 emailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	       
	       
	    }

	    	
	    	
	    	
	    	
	    	
	    	
	    private static final String template=("<!DOCTYPE html PUBLIC k-//W3C//DTD XHTML 1.0 Transitional//ENk khttp://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtdk>" + 
	    		"<html xmlns=khttp://www.w3.org/1999/xhtmlk xmlns:v=kurn:schemas-microsoft-com:vmlk xmlns:o=kurn:schemas-microsoft-com:office:officek>" + 
	    		"<head>"+
	    		"	<meta http-equiv=kContent-typek content=ktext/html; charset=utf-8k />" + 
	    		"	<meta name=kviewportk content=kwidth=device-width, initial-scale=1, maximum-scale=1k />" + 
	    		"    <meta http-equiv=kX-UA-Compatiblek content=kIE=edgek />" + 
	    		
	    		"</head>" + 
	    		"<body class=kbodyk style=kpadding:0 !important; margin:0 !important; display:block !important; min-width:100% !important; width:100% !important; background:#f4f4f4; -webkit-text-size-adjust:none;k>" + 
	    		 
	    		"				<table width=k100%k border=k0k cellspacing=k0k cellpadding=k0k bgcolor=k#ffb300k>" + 
	    		"					<tr>" + 
	    		"						<td align=kcenterk class=kp30-15k style=kpadding: 50px 0px;k>" + 
	    		"							<table width=k650k border=k0k cellspacing=k0k cellpadding=k0k class=kmobile-shellk>" + 
	    		"								<tr>" + 
	    		"									<td class=ktdk style=kwidth:650px; min-width:650px; font-size:0pt; line-height:0pt; padding:0; margin:0; font-weight:normal;k>" + 
	    		"										<table width=k100%k border=k0k cellspacing=k0k cellpadding=k0k>" + 
	    		"											<tr>" + 
	    		"															<td class=kimg m-centerk style=kfont-size:0pt; line-height:0pt; text-align:center;k><img src='cid:logo' width=k70k height=k70k border=k0k alt=kk /></td>" + 
	    		"											</tr>" + 
	    		"											<tr>" + 
	    		"												<td style=kpadding-bottom: 15px; font-family:'Lato', Arial ,sans-serif; font-size:24px; line-height:32px; font-weight:bold; color:#000000; text-align:center;k class=kh3 white centerk>Ferreteria2Go</td>" + 
	    		"											</tr>" + 
	    		"										</table>" + 
	    		"									</td>" + 
	    		"								</tr>" + 
	    		"							</table>" + 
	    		"						</td>" + 
	    		"					</tr>" + 
	    		"				</table>" +
	    		"				<table width=k100%k border=k0k cellspacing=k0k cellpadding=k0k bgcolor=k#ffffffk>" + 
	    		"					<tr>" + 
	    		"						<td valign=ktopk align=kcenterk class=kp30-15k style=kpadding: 60px 0px 60px 0px;k>" + 
	    		"							<table width=k650k border=k0k cellspacing=k0k cellpadding=k0k class=kmobile-shellk>" + 
	    		"								<tr>" + 
	    		"									<td class=ktdk style=kwidth:650px; min-width:650px; font-size:0pt; line-height:0pt; padding:0; margin:0; font-weight:normal;k>" + 
	    		"										<table width=k100%k border=k0k cellspacing=k0k cellpadding=k0k>" + 
	    		"										<tr>" + 
	    		"															<td class=kh3 pb20k style=kcolor:#000000; font-family:'Lato', Arial ,sans-serif; font-size:24px; line-height:32px; text-align:left; font-weight:bold; padding-bottom:20px;k>" + 
	    		"																%TITULO%" + 
	    		"															</td>" + 
	    		"														</tr>" + 
	    		"														<tr>" + 
	    		"															<td class=ktext pb20k style=kcolor:#777777; font-family:'Lato', Arial,sans-serif; font-size:16px; line-height:30px; text-align:left; padding-bottom:20px;k>%CONTENIDO%</td>" + 
	    		"														</tr>" + 
	    		"											" + 
	    		"										</table>" + 
	    		"									</td>" + 
	    		"								</tr>" + 
	    		"							</table>" + 
	    		"						</td>" + 
	    		"					</tr>" + 
	    		"				</table>" + 
	    		"			</td>" + 
	    		"		</tr>" + 
	    		"	</table>" + 
	    		"</body>" + 
	    		"</html>" + 
	    		"").replaceAll("k","\"");
}
