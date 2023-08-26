package it.geymonat.testLTA.controller;

import it.geymonat.testLTA.model.Auto;
import it.geymonat.testLTA.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class UploadCsvController {

    @Autowired
    private CsvService csvService;
    @PostMapping("/upload")
    public List<Auto> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        return csvService.loadCsv(file);
    }
}
