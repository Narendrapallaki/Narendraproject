package com.Fstore.FileImp;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Fstore.FileEntity.FileEntity;

@Service
public interface FileInterface {

	    public FileEntity fileSave(String fileName,String fileEx, MultipartFile file)throws SerialException, SQLException, IOException;
}
