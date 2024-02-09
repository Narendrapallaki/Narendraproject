package com.Image.Controle;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Image.Entity.Image;
import com.Image.customeException.IdNotFound;
import com.Image.customeException.Unsupportedfileformat;
import com.Image.serinter.SerInter;
import com.Image.serinter.SerInterfaceMethod;


@RestController
public class ImageController {
	
	
	  @Autowired
      public SerInterfaceMethod interfaceMethod;
	  
	
		/*
		 * @PostMapping("/saveImage") public
		 * ResponseEntity<Image>imageUploding(@RequestParam("file") MultipartFile[] file
		 * ) throws SerialException, SQLException, IOException { return new
		 * ResponseEntity<Image>(interfaceMethod.saveImage(file), HttpStatus.OK);
		 * 
		 * }
		 */
	  
	  
	@Autowired
	public SerInter serInter;

	@GetMapping("/getByName/{imageName}")
	public ResponseEntity<byte[]> getByNameImage(@PathVariable("imageName") String imageName) throws SQLException, IdNotFound {

		List<Image> imgByName = serInter.getImg(imageName);

		if (!imgByName.isEmpty()) {
			ByteArrayOutputStream ab = new ByteArrayOutputStream();
			try {
				for (Image image : imgByName) {

					byte[] bytes = image.getImageData().getBytes(1, (int) image.getImageData().length());
					ab.write(bytes);
				}
				byte[] allImageData = ab.toByteArray();
				return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
						.contentType(MediaType.IMAGE_PNG).body(allImageData);
				
				//return ResponseEntity.ok(allImageData);

			} catch (IOException e) { 
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} else {
			return ResponseEntity.notFound().build();
		
		}
	}
	 
	
	
	@PostMapping("/saveImage")
	public ResponseEntity<String>imageUploding(@RequestParam("file") MultipartFile[] file ) throws SerialException, SQLException, IOException, Unsupportedfileformat  
	{
		return new ResponseEntity<String>(serInter.uploadImages(file), HttpStatus.OK);

	}
}
