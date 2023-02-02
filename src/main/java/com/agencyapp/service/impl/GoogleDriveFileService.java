package com.agencyapp.service.impl;

import com.agencyapp.config.ConvertByteToMB;
import com.agencyapp.config.GoogleFileManager;
import com.agencyapp.model.GoogleDriveFileDTO;
import com.agencyapp.service.IGoogleDriveFile;
import com.google.api.services.drive.model.File;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GoogleDriveFileService implements IGoogleDriveFile {
	private final String ID_FOLDER_IMAGE = "1rvdomCS1en4iBxyrRuJhrLiUACyFACA7";
	@Autowired
	GoogleFileManager googleFileManager;

	@Override
	public List<GoogleDriveFileDTO> getAllFile() throws IOException, GeneralSecurityException {

		List<GoogleDriveFileDTO> responseList = null;
		List<File> files = googleFileManager.listFolderContent("1rvdomCS1en4iBxyrRuJhrLiUACyFACA7");
		GoogleDriveFileDTO dto = null;

		if (files != null) {
			responseList = new ArrayList<>();
			for (File f : files) {
				dto = new GoogleDriveFileDTO();
				if (f.getSize() != null) {
					dto.setId(f.getId());
					dto.setName(f.getName());
					dto.setThumbnailLink(f.getThumbnailLink());
					dto.setSize(ConvertByteToMB.getSize(f.getSize()));
					dto.setLink("https://drive.google.com/file/d/" + f.getId() + "/view?usp=sharing");
					dto.setShared(f.getShared());

					responseList.add(dto);
				}
			}
		}
		return responseList;
	}

	@Override
	public void deleteFile(String id) throws Exception {
		googleFileManager.deleteFileOrFolder(id);
	}

	@Override
	public List<String> uploadFile(List<MultipartFile> files, String filePath, boolean isPublic) {
		List<String> mess = new ArrayList<>();
		String type = "";
		String role = "";
		if (isPublic) {
			// Public file of folder for everyone
			type = "anyone";
			role = "reader";
		} else {
			// Private
			type = "private";
			role = "private";
		}

		// check condition of file
		for (int i = 0; i < files.size(); i++) {
			if (files.get(i).isEmpty()) {
				mess.add("Upload fail due to file is empty!");
				return mess;
			}

			if (!isImageFile(files.get(i))) {
				mess.add("Upload fail due to file is not image!");
				return mess;
			}
			float fileSizeInMegabytes = files.get(i).getSize() / 1_000_000.0f;
			if (fileSizeInMegabytes > 5.0f) {
				mess.add("Upload fail due to file must be <= 5Mb!");
				return mess;
			}
		}

		return googleFileManager.uploadFile(files, filePath, type, role);
	}

	@Override
	public String updateFile(MultipartFile file, String filePath, boolean isPublic, String id) {
		String type = "";
		String role = "";
		if (isPublic) {
			// Public file of folder for everyone
			type = "anyone";
			role = "reader";
		} else {
			// Private
			type = "private";
			role = "private";
		}
		String nameFile = getNameFileById(id);
		try {
			googleFileManager.deleteFileOrFolder(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return googleFileManager.updateFile(file, filePath, type, role, id, nameFile);
	}

	@Override
	public void downloadFile(String id, OutputStream outputStream) throws IOException, GeneralSecurityException {
		googleFileManager.downloadFile(id, outputStream);
	}

	public String getNameFileById(String id) {
		List<File> filesFound = new ArrayList<>();
		try {
			filesFound = googleFileManager.listEverything();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (GeneralSecurityException e) {

			e.printStackTrace();
		}

		for (int i = 0; i < filesFound.size(); i++) {
			if (filesFound.get(i).getId().equalsIgnoreCase(id)) {
				return filesFound.get(i).getName();
			}
		}

		return null;
	}
	
	private boolean isImageFile(MultipartFile file) {
        
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.asList(new String[] {"png","jpg","jpeg", "bmp"})
                .contains(fileExtension.trim().toLowerCase());
    }
}