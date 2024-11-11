package com.bankrupt.bankruptapi.service;

import lombok.AllArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;

import java.net.URL;

import static com.bankrupt.bankruptapi.Constant.SCOURT_DONWLOAD_URL;

@Service
@AllArgsConstructor
public class PdfboxService {
    private final String downloadUrl = SCOURT_DONWLOAD_URL + "?path=011";

    public void test(String file, String fileName) {

        try {
            URL url = new URL(downloadUrl + "&file=" + file + "&downFile=" + fileName);
            PDDocument.load()
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
