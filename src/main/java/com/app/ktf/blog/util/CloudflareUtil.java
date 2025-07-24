package com.app.ktf.blog.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Component
public class CloudflareUtil {

    @Value("${cloudflare.api.token}")
    private String apiToken;

    @Value("${cloudflare.upload.url}")
    private String uploadUrl;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public String uploadImage(MultipartFile imageFile) {
        try {
            // Prepare URL connection
            URL url = new URL(uploadUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + apiToken);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=---Boundary");

            // Prepare multipart/form-data payload
            String boundary = "---Boundary";
            String lineEnd = "\r\n";
            String twoHyphens = "--";

            StringBuilder sb = new StringBuilder();
            sb.append(twoHyphens).append(boundary).append(lineEnd);
            sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"")
                    .append(imageFile.getOriginalFilename()).append("\"").append(lineEnd);
            sb.append("Content-Type: ").append(imageFile.getContentType()).append(lineEnd);
            sb.append(lineEnd);

            byte[] headerBytes = sb.toString().getBytes();
            byte[] footerBytes = (lineEnd + twoHyphens + boundary + twoHyphens + lineEnd).getBytes();

            // Write payload
            conn.getOutputStream().write(headerBytes);
            conn.getOutputStream().write(imageFile.getBytes());
            conn.getOutputStream().write(footerBytes);
            conn.getOutputStream().flush();
            conn.getOutputStream().close();

            // Read response
            InputStream responseStream = conn.getInputStream();
            Scanner s = new Scanner(responseStream).useDelimiter("\\A");
            String response = s.hasNext() ? s.next() : "";

            // Parse JSON to extract the uploaded image URL
            JsonNode jsonNode = objectMapper.readTree(response);
            if (jsonNode.get("success").asBoolean()) {
                return jsonNode.get("result").get("variants").get(0).asText();
            } else {
                throw new RuntimeException("Cloudflare upload failed: " + response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to upload image to Cloudflare", e);
        }
    }
}
