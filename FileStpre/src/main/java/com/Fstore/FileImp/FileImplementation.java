package com.Fstore.FileImp;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Fstore.FileEntity.FileEntity;
import com.Fstore.FileRepositery.FileRepositery;

@Service
public class FileImplementation implements FileInterface{

	
	@Autowired
	public FileRepositery fileRepositery;

	@Override
	public FileEntity fileSave(String fileName, String fileEx, MultipartFile file) throws SerialException, SQLException, IOException {
		// TODO Auto-generated method stub
		
		FileEntity fe=new FileEntity();
		
		fe.setFileName(fileName);
		fe.setFileextension(fileEx);
		fe.setFileData(new javax.sql.rowset.serial.SerialBlob(file.getBytes()));
		
		FileEntity save = fileRepositery.save(fe);
		
		
		return save;
	}
	
	
	public FileEntity findBy(String fName)
	{
		return fileRepositery.findByfileName(fName);
		
	}
	
	
	
	

}
