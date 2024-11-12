package com.bankrupt.bankruptapi.service;

import lombok.AllArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.net.URL;

import static com.bankrupt.bankruptapi.Constant.SCOURT_DONWLOAD_URL;

@Service
@AllArgsConstructor
public class PdfboxService {
    private final String downloadUrl = SCOURT_DONWLOAD_URL + "?path=011";

    public String getPdfTextByScourtUrl(String file, String fileName) {

        try {
            URL url = new URL(downloadUrl + "&file=" + file + "&downFile=" + fileName);

            try(PDDocument pdf = PDDocument.load(url.openStream())) {
                PDFTextStripper stripper = new PDFTextStripper();
                return stripper.getText(pdf);
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
