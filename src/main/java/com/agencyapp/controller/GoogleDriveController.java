package com.agencyapp.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.agencyapp.dto.ResponseObjectDTO;
import com.agencyapp.model.GoogleDriveFileDTO;
import com.agencyapp.model.GoogleDriveFoldersDTO;
import com.agencyapp.service.impl.GoogleDriveFileService;
import com.agencyapp.service.impl.GoogleDriveFolderService;

@RestController(value = "googleDriveController")
public class GoogleDriveController {

	@Autowired
	GoogleDriveFileService googleDriveFileService;

	@Autowired
	GoogleDriveFolderService googleDriveFolderService;
	

	// Get all file
	@GetMapping("/listAllFile")
	public List<GoogleDriveFileDTO> getAllFile() throws IOException, GeneralSecurityException {
		ModelAndView mav = new ModelAndView("index");

		List<GoogleDriveFileDTO> listFile = googleDriveFileService.getAllFile();

		return listFile;
	}

	// Get all folder
	@GetMapping("/listAllFolder")
	public List<GoogleDriveFoldersDTO> getAllFolder() throws IOException, GeneralSecurityException {
		ModelAndView mav = new ModelAndView("index");

		List<GoogleDriveFoldersDTO> listFolder = googleDriveFolderService.getAllFolder();

		return listFolder;
	}

	// Upload file
	@PostMapping(value = "/upload/file", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseObjectDTO> uploadFile(@RequestParam("fileUpload") List<MultipartFile> filesUpload,
			@RequestParam("filePath") String pathFile, @RequestParam("shared") String shared) {

		if (pathFile.equals("")) {
			pathFile = "Agency Image";
		}

		List<String> idImage = googleDriveFileService.uploadFile(filesUpload, pathFile, Boolean.parseBoolean(shared));
		if (!idImage.get(0).contains("fail")) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "upload file successfully", idImage));
		} else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("fail", "upload file fail", idImage));
		}
	}

	// Update Image

	@PutMapping(value = "/update/file/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseObjectDTO> updatedFile(@RequestParam("fileUpload") MultipartFile fileUpload,
			@RequestParam("filePath") String pathFile, @RequestParam("shared") String shared, @PathVariable String id) {

		if (pathFile.equals("")) {
			pathFile = "Agency Image";
		}

		String idImage = googleDriveFileService.updateFile(fileUpload, pathFile, Boolean.parseBoolean(shared), id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObjectDTO("ok", "upload file successfully", idImage));
	}

	// Delete file
	@DeleteMapping("/delete/file/{id}")
	public ResponseEntity<ResponseObjectDTO> deleteFile(@PathVariable String id) throws Exception {
		googleDriveFileService.deleteFile(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObjectDTO("ok", "Deleted file successfully", ""));
	}

	// Download file
	@GetMapping("/download/file/{id}")
	public void downloadFile(@PathVariable String id, HttpServletResponse response)
			throws IOException, GeneralSecurityException {
		googleDriveFileService.downloadFile(id, response.getOutputStream());
	}

	// Create folder
	@PostMapping("/create/folder")
	public ModelAndView createFolder(@RequestParam("folderName") String folderName) throws Exception {
		googleDriveFolderService.createFolder(folderName);
		return new ModelAndView("redirect:" + "/list/folders");
	}

	// Delete folder by id
	@GetMapping("/delete/folder/{id}")
	public ModelAndView deleteFolder(@PathVariable String id) throws Exception {
		googleDriveFolderService.deleteFolder(id);
		return new ModelAndView("redirect:" + "/list/folders");
	}
	
}
