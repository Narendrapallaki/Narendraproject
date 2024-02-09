package com.Fstore.controll;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Fstore.FileEntity.FileEntity;
import com.Fstore.FileImp.FileImplementation;
import com.Fstore.FileImp.FileInterface;

@RestController
public class FileController
{
	
	@Autowired
	public FileInterface fileInterface;
	
	@PostMapping("/saveFile")
	public ResponseEntity<String>saveFile(@RequestParam("fileName")String fileName,
			                     @RequestParam("fileEx")String fileEx,
			                     @RequestParam("file") MultipartFile file
			                     ) throws SerialException, SQLException, IOException
	{
		
		if (!isValidExten(fileEx)) {
			
			return ResponseEntity.badRequest().body("In valid extension must be in pdf or doc ");
			
		}
		
		FileEntity fileSave = fileInterface.fileSave(fileName, fileEx, file);
		
		
		
		return new ResponseEntity<String>("data inserted", HttpStatus.OK);
		
	}
	
	
	private boolean isValidExten(String extension)
	{
        return extension.equalsIgnoreCase("pdf") || extension.equalsIgnoreCase("doc");
    }
	@Autowired
	public FileImplementation fileImplementation;
	
	@GetMapping("/get")
	public ResponseEntity<byte[]>getByName(@RequestParam("fname")String fileName) throws IOException
	{
		
		FileEntity findByFileName = fileImplementation.findBy(fileName);
		
		
		if (findByFileName!=null) 
		{
			ByteArrayOutputStream bb=new ByteArrayOutputStream();
			
			try {
				byte[] bytes = findByFileName.fileData.getBytes(1, (int) findByFileName.getFileData().length());
				bb.write(bytes);
				return new ResponseEntity<byte[]>(bytes, HttpStatus.OK);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
			
		} else {
			return  ResponseEntity.notFound().build();
		}
		//return null;
		
		
		
	}
		
	}
	
	


