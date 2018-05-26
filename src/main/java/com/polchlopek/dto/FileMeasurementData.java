package com.polchlopek.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;


public class FileMeasurementData {

    private CommonsMultipartFile file;

    public CommonsMultipartFile getFile() {
        return file;
    }

    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }

    public FileMeasurementData() {
    }
}
