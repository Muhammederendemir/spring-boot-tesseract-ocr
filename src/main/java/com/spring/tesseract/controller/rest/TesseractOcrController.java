package com.spring.tesseract.controller.rest;

import com.spring.tesseract.model.response.ImageTextDto;
import com.spring.tesseract.service.TesseractOcrService;
import com.spring.tesseract.validator.ValidFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "Tesseract Ocr", description = "Retrieve and process images")
@RestController
@Validated
@RequestMapping("/versions/1")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TesseractOcrController {

    final TesseractOcrService imageProcessService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ImageTextDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)

    })
    @Operation(summary = "Extract text from Image")
    @PostMapping(value = "/images/extract", consumes = {"multipart/form-data"})
    @ResponseStatus(value = HttpStatus.OK)
    public ImageTextDto extractTextFromImage(@ValidFile @RequestPart("file") MultipartFile file) throws IOException {
        return imageProcessService.extractTextFromImage(file);
    }

}
