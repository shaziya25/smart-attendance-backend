package com.shaziya.attendance_system.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.shaziya.attendance_system.entity.Lecture;
import com.shaziya.attendance_system.repository.LectureRepository;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class QRCodeService {

    private final LectureRepository lectureRepository;

    public QRCodeService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public byte[] generateLectureQRCode(Long lectureId)
            throws WriterException, IOException {

        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() ->
                        new RuntimeException("Lecture not found"));

        // This is the data that goes inside the QR code.
        String qrData =
                "http://192.168.0.109:5173/scan/" + lectureId;

        QRCodeWriter writer = new QRCodeWriter();

        BitMatrix matrix = writer.encode(
                qrData,
                BarcodeFormat.QR_CODE,
                300,
                300
        );

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        MatrixToImageWriter.writeToStream(
                matrix,
                "PNG",
                stream
        );

        return stream.toByteArray();
    }
}