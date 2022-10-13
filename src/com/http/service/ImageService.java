package com.http.service;

import com.http.util.PropertiesUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageService {

    @Getter
    private static final ImageService INSTANCE = new ImageService();

    private final String basePath = PropertiesUtil.get("image.base.url");

    @SneakyThrows
    public void upload(String imagePath, InputStream imageContent) {
        Path imageFullPath = Path.of(basePath, imagePath);
        try (imageContent) {
            Files.createDirectories(imageFullPath.getParent()); // Создание всех директорий, если их нету
            Files.write(imageFullPath, imageContent.readAllBytes(), CREATE, TRUNCATE_EXISTING); // Опции говорят - создать если его нету, и не падать, если существует файл с таким именем
        }
    }

    @SneakyThrows
    public Optional<InputStream> get(String imagePath) {
        
        Path imageFullPath = Path.of(basePath, imagePath);
        return Files.exists(imageFullPath) ? Optional.of(Files.newInputStream(imageFullPath)) : Optional.empty();
    }
}
