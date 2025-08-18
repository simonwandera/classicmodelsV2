package com.systech.systech.Dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@ConfigurationProperties(prefix = "file.storage")
@Component
@Data
public class FileStorageProperties {
    private String path = "./uploads";
    private List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png", "gif", "webp");
    private String maxFileSize = "5MB";
}