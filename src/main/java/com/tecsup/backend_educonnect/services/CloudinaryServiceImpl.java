package com.tecsup.backend_educonnect.services;



import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl() {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("cloud_name", "db8ofruh9");  // Reemplaza con tu cloud_name
        valuesMap.put("api_key", "619972925578632");    // Reemplaza con tu api_key
        valuesMap.put("api_secret", "mRa4CSmnwMsIkXl8dhhgm58uOIc");  // Reemplaza con tu api_secret

        // Intentamos capturar cualquier error en la inicialización de Cloudinary
        try {
            cloudinary = new Cloudinary(valuesMap);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing Cloudinary: " + e.getMessage(), e);
        }
    }

    @PostConstruct
    public void init() {
        // Verifica si la inicialización fue exitosa
        System.out.println("Cloudinary initialized: " + cloudinary);
    }

    @Override
    public Map upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        if (!Files.deleteIfExists(file.toPath())) {
            throw new IOException("Failed to delete temporary file: " + file.getAbsolutePath());
        }
        return result;
    }

    public Map delete(String id) throws IOException {
        return cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
