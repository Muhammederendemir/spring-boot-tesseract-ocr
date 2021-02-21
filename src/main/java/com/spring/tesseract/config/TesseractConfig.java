package com.spring.tesseract.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TesseractConfig {

    final Environment environment;

    @Bean
    public Tesseract tesseract() {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(environment.getProperty("tesseract.data.path"));
        tesseract.setLanguage(environment.getProperty("tesseract.data.language"));
        tesseract.setTessVariable("user_defined_dpi", environment.getProperty("tesseract.data.dpi"));
        return tesseract;
    }
}
