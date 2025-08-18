package com.systech.systech.controller;

import com.systech.systech.Dto.FileStorageProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
@Slf4j
public class ImageController {

    private final FileStorageProperties fileStorageProperties;

    @GetMapping("/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            // Security: Validate filename to prevent path traversal
            if (filename.contains("..") || filename.contains("/") || filename.contains("\\")) {
                log.warn("Invalid filename requested: {}", filename);
                return ResponseEntity.badRequest().build();
            }

            Path fileStorageLocation = Paths.get(fileStorageProperties.getPath())
                    .toAbsolutePath().normalize();

            Path filePath = fileStorageLocation.resolve(filename).normalize();

            // Security: Ensure the resolved path is still within the storage directory
            if (!filePath.startsWith(fileStorageLocation)) {
                log.warn("Path traversal attempt detected for filename: {}", filename);
                return ResponseEntity.badRequest().build();
            }

            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                String contentType = Files.probeContentType(filePath);
                if (contentType == null || !contentType.startsWith("image/")) {
                    contentType = "application/octet-stream";
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CACHE_CONTROL, "max-age=86400") // 24 hours
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                        .body(resource);
            } else {
                log.warn("File not found or not readable: {}", filename);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            log.error("Error serving image: {}", filename, ex);
            return ResponseEntity.notFound().build();
        }
    }
}