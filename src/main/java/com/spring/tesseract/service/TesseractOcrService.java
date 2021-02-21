package com.spring.tesseract.service;

import com.spring.tesseract.model.response.ImageTextDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TesseractOcrService {

    ImageTextDto extractTextFromImage(MultipartFile file) throws IOException;

}
