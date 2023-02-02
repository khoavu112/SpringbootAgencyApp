package com.agencyapp.service;



import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.agencyapp.model.GoogleDriveFoldersDTO;

public interface IGoogleDriveFolder {
    List<GoogleDriveFoldersDTO> getAllFolder() throws IOException, GeneralSecurityException;
    void createFolder(String folderName) throws Exception;
    void deleteFolder(String id) throws Exception;
}