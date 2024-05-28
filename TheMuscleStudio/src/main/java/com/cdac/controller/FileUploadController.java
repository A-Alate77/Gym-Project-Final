package com.cdac.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cdac.model.DatabaseFiles;
import com.cdac.model.GymMember;
import com.cdac.service.DatabaseFileService;
import com.cdac.service.EmailService;
import com.cdac.service.GymMemberServices;
import com.cdac.service.ResponseDatabase;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class FileUploadController {
	
	@Autowired
    private DatabaseFileService fileStorageService;
	
	@Autowired
	private GymMemberServices gymMemberService;
	
	@Autowired
	private EmailService emailService;

    @PostMapping("/uploadFile/{memberId}")
    public ResponseEntity<ResponseDatabase>  uploadFile(@RequestParam("file") MultipartFile file,@PathVariable("memberId") int id) {
    	//if(file.getContentType()=="application/pdf") {
        DatabaseFiles fileName = fileStorageService.storeFile(file);
    	   //System.out.println("sahime ara kya idhar");
    	   
       
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/downloadFile/")
            .path(fileName.getFileName())
            .toUriString();
        
       // System.out.println(fileDownloadUri);
       // System.out.println(id);
        GymMember gymMember = gymMemberService.findById(id);
      //  System.out.println(gymMember.toString());
        
        //System.out.println(file.getContentType());
       
        gymMember.setDietPlan(fileDownloadUri);
        gymMemberService.update(gymMember);
        
        ResponseDatabase url = new ResponseDatabase(fileName.getFileName(), fileDownloadUri,
            file.getContentType(), file.getSize());
        
        emailService.sendSimpleEmail(gymMember.getEmail(),
				"Workout & Diet Plan Updated",
				"Your Trainer has updated workout and diet plan. please login to download plan");
        
        return ResponseEntity.ok(url);
        
    	//return ResponseEntity.ok(null);
    }

   
}

