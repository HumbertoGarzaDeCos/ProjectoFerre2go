package com.beto.apps.service;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class ImageConverter {

	
	
	public static synchronized String  imageConverter(MultipartFile multiPart) {
		try(ByteArrayOutputStream bos = new ByteArrayOutputStream();ByteArrayInputStream bis = new ByteArrayInputStream(multiPart.getBytes())){
		
	      BufferedImage bImage2 = ImageIO.read(bis);
		
		 Image resultingImage = bImage2.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		    BufferedImage outputImage = new BufferedImage(250, 250, BufferedImage.TYPE_INT_RGB);
		    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		    
		    if(bImage2.getWidth()>bImage2.getHeight()) {
		    int w=outputImage.getWidth();
		    int h=outputImage.getHeight();
		    BufferedImage rotated = new BufferedImage(w, h,outputImage.getType());
		    Graphics2D graphic = rotated.createGraphics();
		    graphic.rotate(Math.toRadians(90), w/2, h/2);
		    graphic.drawImage(outputImage, null, 0, 0);
		    graphic.dispose();
		    outputImage=rotated;
		    }
		    ImageIO.write(outputImage, multiPart.getOriginalFilename().split("\\.")[1], bos);
            byte[] imageBytes = bos.toByteArray();

      
            return Base64.getEncoder().encodeToString(imageBytes);
		}catch(Exception e) {
			return "";
		}
	}
}
