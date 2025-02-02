package com.bankrupt.bankruptapi.service;

import kr.dogfoot.hwplib.object.HWPFile;
import kr.dogfoot.hwplib.reader.HWPReader;
import kr.dogfoot.hwplib.tool.textextractor.TextExtractMethod;
import kr.dogfoot.hwplib.tool.textextractor.TextExtractor;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;

import static com.bankrupt.bankruptapi.Constant.SCOURT_DONWLOAD_URL;

@Service
@AllArgsConstructor
public class HwpLibService {
    private final String downloadUrl = SCOURT_DONWLOAD_URL + "?path=011";

    public String getHwpTextByScourtUrl(String file, String fileName) {

        if (file.endsWith(".hwp") || file.endsWith(".HWP")) {
            try {
                String url = downloadUrl + "&file=" + file + "&downFile=" + fileName;
                HWPFile hwpFile = HWPReader.fromURL(url);
                return TextExtractor.extract(hwpFile, TextExtractMethod.InsertControlTextBetweenParagraphText);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "";
    }

    public ByteArrayResource getHwpBytesByScourtUrl(String file, String fileName) {

        if (file.endsWith(".hwp") || file.endsWith(".HWP")) {
            try {
                URL url = new URL(downloadUrl + "&file=" + file + "&downFile=" + fileName);

                try (InputStream inputStream = url.openStream()) {
                    return new ByteArrayResource(inputStream.readAllBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
