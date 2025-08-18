package com.systech.systech.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    public FileStorageService() {
        // Configure your file storage location
        this.fileStorageLocation = Paths.get("uploads")
                .toAbsolutePath()
                .normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException(
                    "Could not create upload directory", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        // Validate file
        if (file.isEmpty()) {
            throw new RuntimeException("Failed to store empty file");
        }

        try {
            // Generate unique filename
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName != null ?
                    originalFileName.substring(originalFileName.lastIndexOf(".")) : "";
            String fileName = UUID.randomUUID() + fileExtension;

            // Resolve path and copy file
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation,
                    StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException(
                    "Failed to store file " + file.getOriginalFilename(), ex);
        }
    }

    // Optional: Method to load files
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found " + fileName);
            }
        } catch (Exception ex) {
            throw new RuntimeException("File not found " + fileName, ex);
        }
    }

    // Optional: Method to delete files
    public void deleteFile(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Files.deleteIfExists(filePath);
        } catch (IOException ex) {
            throw new RuntimeException("Could not delete file " + fileName, ex);
}
}
}