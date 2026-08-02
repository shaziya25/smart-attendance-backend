package com.shaziya.attendance_system.controller;

import com.google.zxing.WriterException;
import com.shaziya.attendance_system.service.QRCodeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/lectures")
public class QRCodeController {

    private final QRCodeService qrCodeService;

    public QRCodeController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @GetMapping(value = "/{lectureId}/qr", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateLectureQR(
            @PathVariable Long lectureId
    ) throws WriterException, IOException {

        byte[] qr = qrCodeService.generateLectureQRCode(lectureId);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(qr);
    }
}