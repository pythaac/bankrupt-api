package com.bankrupt.bankruptapi.controller;

import com.bankrupt.bankruptapi.dto.PdfFileDto;
import com.bankrupt.bankruptapi.service.HwpLibService;
import com.bankrupt.bankruptapi.service.PdfboxService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/file")
public class FileController {
    private final PdfboxService pdfboxService;
    private final HwpLibService hwpLibService;

    @GetMapping(value = "/pdf")
    public ResponseEntity<ByteArrayResource> getPdf(@RequestParam String file, @RequestParam String fileName) {

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header("Content-Disposition", "inline")
                .body(pdfboxService.getPdfBytesByScourtUrl(
                        file, fileName
                ));
    }

    @GetMapping(value = "/hwp")
    public ByteArrayResource getHwp(@RequestParam String file, @RequestParam String fileName) {
        return hwpLibService.getHwpBytesByScourtUrl(file, fileName);
    }
}
