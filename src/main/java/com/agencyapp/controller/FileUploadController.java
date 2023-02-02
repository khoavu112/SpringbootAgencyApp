package com.agencyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.agencyapp.dto.ResponseObjectDTO;
import com.agencyapp.service.IStorageService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class FileUploadController {
	@Autowired
	private IStorageService storageService;

	@PostMapping(value = "/FileUpload")
	public ResponseEntity<ResponseObjectDTO> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			// save files to a folder => use a service
			String generatedFileName = storageService.storeFile(file);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "upload file successfully", generatedFileName));
			// 06a290064eb94a02a58bfeef36002483.png => how to open this file in Web Browser
			// ?
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObjectDTO("fail!", exception.getMessage(), ""));
		}
	}

	// get image's url
	@GetMapping("/files/{fileName:.+}")
	// /files/06a290064eb94a02a58bfeef36002483.png
	public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
		try {
			byte[] bytes = storageService.readFileContent(fileName);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
		} catch (Exception exception) {
			return ResponseEntity.noContent().build();
		}
	}
	
	 @GetMapping("files/loadAll")
	    public ResponseEntity<ResponseObjectDTO> getUploadedFiles() {
	        try {
	            List<String> urls = storageService.loadAll()
	                    .map(path -> {
	                        //convert fileName to url(send request "readDetailFile")
	                        String urlPath = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
	                                "readDetailFile", path.getFileName().toString()).build().toUri().toString();
	                        return urlPath;
	                    })
	                    .collect(Collectors.toList());
	            return ResponseEntity.ok(new ResponseObjectDTO("ok", "List files successfully", urls));
	        }catch (Exception exception) {
	            return ResponseEntity.ok(new
	            		ResponseObjectDTO("failed", "List files failed", new String[] {}));
	        }
	    }
}
