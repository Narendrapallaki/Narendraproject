package com.Fstore.FileEntity;

import java.sql.Blob;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class FileEntity 
{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	    public Long fileId;
	    
	    public String fileName;
	    
	    public String fileExtension;
	    
	    public Blob fileData;

		/**
		 * @return the fileId
		 */
		public Long getFileId() {
			return fileId;
		}

		/**
		 * @param fileId the fileId to set
		 */
		public void setFileId(Long fileId) {
			this.fileId = fileId;
		}

		/**
		 * @return the fileName
		 */
		public String getFileName() {
			return fileName;
		}

		/**
		 * @param fileName the fileName to set
		 */
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		/**
		 * @return the fileextension
		 */
		public String getFileextension() {
			return fileExtension;
		}

		/**
		 * @param fileextension the fileextension to set
		 */
		public void setFileextension(String fileExtension) {
			this.fileExtension = fileExtension;
		}

		/**
		 * @return the fileData
		 */
		public Blob getFileData() {
			return fileData;
		}

		/**
		 * @param fileData the fileData to set
		 */
		public void setFileData(Blob fileData) {
			this.fileData = fileData;
		}
	    
}
