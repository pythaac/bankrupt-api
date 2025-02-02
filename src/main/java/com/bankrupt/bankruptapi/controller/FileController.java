package com.bankrupt.bankruptapi.controller;

import com.bankrupt.bankruptapi.dto.PdfFileDto;
import com.bankrupt.bankruptapi.service.PdfboxService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/file")
public class FileController {
    private final PdfboxService pdfboxService;

    @GetMapping(value = "/pdf")
    public ResponseEntity<ByteArrayResource> getPdf(@RequestBody PdfFileDto pdfFileDto) {

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header("Content-Disposition", "inline")
                .body(pdfboxService.getPdfBytesByScourtUrl(
                        pdfFileDto.getFile(), pdfFileDto.getFileName()
                ));
    }
}
