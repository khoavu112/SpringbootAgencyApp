package com.agencyapp.service;



import org.springframework.web.multipart.MultipartFile;

import com.agencyapp.model.GoogleDriveFileDTO;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.List;

public interface IGoogleDriveFile {
    List<GoogleDriveFileDTO> getAllFile() throws IOException, GeneralSecurityException;
    void deleteFile(String id) throws Exception;
    List<String> uploadFile(List<MultipartFile> files, String filePath, boolean isPublic);
    void downloadFile(String id, OutputStream outputStream) throws IOException, GeneralSecurityException;
    String updateFile(MultipartFile file, String filePath, boolean isPublic, String id);
}