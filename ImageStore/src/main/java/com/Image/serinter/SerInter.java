package com.Image.serinter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Image.Entity.Image;
import com.Image.Repositery.Repo;
import com.Image.customeException.IdNotFound;
import com.Image.customeException.Unsupportedfileformat;
@Service
public class SerInter implements SerInterfaceMethod
{
	@Autowired
   public Repo reposi;
	

	public String uploadImages(MultipartFile[] files) throws IOException, SerialException, SQLException, Unsupportedfileformat { 
	    List<Image> images = new ArrayList<>();
	    // Define the allowed extensions
	    List<String> allowedExtensions = Arrays.asList("png", "pdf");

	    for (MultipartFile file : files) {
	        String originalFilename = file.getOriginalFilename();
	        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
	        // Check if the file extension is allowed
	        if (!allowedExtensions.contains(fileExtension)) {
	            throw new Unsupportedfileformat("Unsupported file format. Supported formats are: " + allowedExtensions);
	        }
	        Image image = new Image();
	        image.setImageName(originalFilename);
	        image.setImageData(new javax.sql.rowset.serial.SerialBlob(file.getBytes()));
	        images.add(image);
	    }
         
	   
	    // Save images to the database
	    reposi.saveAll(images);

	    return "Images uploaded successfully.";
	}
       
	
	/* public String uploadImages(MultipartFile[] files) throws IOException, SerialException, SQLException {
	        List<Image> images = new ArrayList<>();
	        for (MultipartFile file : files) {
	        	
	        	System.out.println("service ------"+file.getOriginalFilename());
	            Image image = new Image();
	            image.setImageName(file.getOriginalFilename());
	            image.setImageData(new javax.sql.rowset.serial.SerialBlob(file.getBytes()));
	            images.add(image);
	        }
	        reposi.saveAll(images);
	        return "Images uploaded successfully.";
	    }
*/
	
	public List<Image> getImg(String img) throws IdNotFound {
		
		
	 List<Image> byimageName = reposi.findByimageName(img);
	 
	 if (byimageName.isEmpty()) {
		
		 throw new IdNotFound("User id not found in Database "+img);
	}
     return byimageName;
	}
	 
	
/*
    public List<byte[]> getImagesByOwnerId(Long ownerId) {
        List<Image> images = imageRepository.findAllByOwnerId(ownerId);
        List<byte[]> imageDataList = new ArrayList<>();
        for (Image image : images) {
            imageDataList.add(image.getData());
        }
        return imageDataList;
    }
	
*/
}
