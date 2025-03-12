package net.project.pms_backend.service.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OCRService {

    private ImageAnnotatorClient client;

    @PostConstruct
    public void init() throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:/Users/User/Downloads/boxly-451314-a33dcd606c56.json"));
        ImageAnnotatorSettings settings = ImageAnnotatorSettings.newBuilder().setCredentialsProvider(() -> credentials).build();
        client = ImageAnnotatorClient.create(settings);
    }

    @PreDestroy
    public void destroy() throws Exception {
        client.close();
    }


    public String extractTextAndBarcodeFromImage(MultipartFile file) throws IOException {
        ByteString byteString = ByteString.copyFrom(file.getBytes());
        Image img = Image.newBuilder().setContent(byteString).build();

        List<AnnotateImageRequest> requests = new ArrayList<>();
        requests.add(AnnotateImageRequest.newBuilder()
                .addFeatures(Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION))
                .addFeatures(Feature.newBuilder().setType(Feature.Type.DOCUMENT_TEXT_DETECTION))
                .addFeatures(Feature.newBuilder().setType(Feature.Type.LOGO_DETECTION))
                .setImage(img)
                .build());

        try {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            StringBuilder result = new StringBuilder();
            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    throw new IOException("Error: " + res.getError().getMessage());
                }

                // Extract text
                result.append(res.getTextAnnotationsList().get(0).getDescription()).append("\n");

                // Extract barcode (assuming it's detected as a logo)
                for (EntityAnnotation annotation : res.getLogoAnnotationsList()) {
                    if (annotation.getDescription().toLowerCase().contains("barcode")) {
                        result.append("BARCODE: ").append(annotation.getDescription());
                        break;
                    }
                }
            }
            return result.toString();
        } catch (Exception e) {
            throw new IOException("Error during image analysis: " + e.getMessage(), e);
        }
    }
}