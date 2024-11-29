package com.tecsup.backend_educonnect.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dtsasotcn",
                "api_key", "218475432247947",
                "api_secret", "N8bj7zGJrAkyb7JiM4q1zdOAHWU"));
        return cloudinary;
    }
}
