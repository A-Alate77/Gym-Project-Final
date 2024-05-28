package com.cdac.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="databasefiles")
public class DatabaseFiles {
	
	 	@Id
	 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int file_id;

	    private String fileName;

	    private String fileType;

	    @Lob
	    private byte[] data;

		public DatabaseFiles() {
			super();
		}

		public DatabaseFiles(String fileName, String fileType, byte[] data) {
			super();
			this.fileName = fileName;
			this.fileType = fileType;
			this.data = data;
		}

		

		public int getFile_id() {
			return file_id;
		}

		public void setFile_id(int file_id) {
			this.file_id = file_id;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getFileType() {
			return fileType;
		}

		public void setFileType(String fileType) {
			this.fileType = fileType;
		}

		public byte[] getData() {
			return data;
		}

		public void setData(byte[] data) {
			this.data = data;
		}
	    
	    
}
