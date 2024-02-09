package com.Image.Entity;

import java.sql.Blob;

import javax.sql.rowset.serial.SerialBlob;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Image 
{
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long imageId;

	    private String imageName;

	    @Lob
	    private Blob imageData;

	
	    

}
