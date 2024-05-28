package com.cdac.service;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cdac.exception.FileNotFoundException;
import com.cdac.exception.FileStorageException;
import com.cdac.model.DatabaseFiles;
import com.cdac.repository.DatabaseFilesRepository;

import org.springframework.util.StringUtils;

@Transactional
@Service
public class DatabaseFileService {
	
	@Autowired
    private DatabaseFilesRepository dbFileRepository;

    public DatabaseFiles storeFile(MultipartFile file) {
        // Normalize file name
    	
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Check if the file's name contains invalid characters
        	
        	String fileType ="application/pdf";
        	String contentType= file.getContentType();
        	
            if ((fileName.contains("..")) || (!(fileType.equals(contentType)))) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DatabaseFiles dbFile = new DatabaseFiles(fileName, file.getContentType(), file.getBytes());

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DatabaseFiles getFile(String fileName) {
        //return dbFileRepository.findByFileName(fileId);
    	
    	DatabaseFiles file = dbFileRepository.findByFileName(fileName);
    	if (file==null) 
    		throw new FileNotFoundException("File not found with name "+fileName);
    	return file;
    	}
    	
    	
    	
    
}
