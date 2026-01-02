package com.ocr.project.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sourceforge.tess4j.Tesseract;

@Configuration
public class OCRConfig {

    @Bean
    public Tesseract tesseract() {
        Tesseract tess = new Tesseract();
        tess.setLanguage("eng");
        tess.setDatapath("src/main/resources/tessdata");
        return tess;
    }
}
