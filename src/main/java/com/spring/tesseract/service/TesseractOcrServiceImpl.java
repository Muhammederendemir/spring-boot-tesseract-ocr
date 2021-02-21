package com.spring.tesseract.service;

import com.spring.tesseract.model.response.ImageTextDto;
import com.spring.tesseract.utils.TesseractOcrUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TesseractOcrServiceImpl implements TesseractOcrService {

    final Tesseract tesseract;

    @Override
    public ImageTextDto extractTextFromImage(MultipartFile file) throws IOException {

        try {

            String text = tesseract.doOCR(TesseractOcrUtil.createImageFromBytes(file.getBytes()));

            return ImageTextDto.builder()
                    .fileName(file.getOriginalFilename())
                    .text(text)
                    .build();

        } catch (TesseractException e) {
            log.error("Tesseract error: " + e.getMessage());
            return ImageTextDto.builder()
                    .fileName(file.getOriginalFilename())
                    .text("")
                    .build();
        }
    }
}
