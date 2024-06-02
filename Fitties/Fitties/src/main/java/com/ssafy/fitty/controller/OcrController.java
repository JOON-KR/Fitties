package com.ssafy.fitty.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ocr")
@CrossOrigin(value="*")
public class OcrController {

    @PostMapping("/{imagePath}")
    public ResponseEntity<?> performOCR(@PathVariable String imagePath) {
       
        
            System.out.println("이미지경로"+imagePath);
            // 로컬 파일 경로 설정
            String localImagePath = "C:\\MnJ\\Fitties\\vue-fitties\\src\\assets\\";
            localImagePath+=imagePath;

            try {
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setUseCaches(false);
                con.setDoInput(true);
                con.setDoOutput(true);
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                con.setRequestProperty("X-OCR-SECRET", secretKey);

                JSONObject json = new JSONObject();
                json.put("version", "V2");
                json.put("requestId", UUID.randomUUID().toString());
                json.put("timestamp", System.currentTimeMillis());
                JSONObject image = new JSONObject();
                image.put("format", "jpg");

                // 로컬 파일을 읽어 바이트 배열로 변환
                FileInputStream inputStream = new FileInputStream(localImagePath);
                byte[] buffer = new byte[inputStream.available()];
                inputStream.read(buffer);
                inputStream.close();

                // 바이트 배열을 Base64로 인코딩하여 JSON에 추가
                String encodedImage = Base64.getEncoder().encodeToString(buffer);
                image.put("data", encodedImage);
                image.put("name", "demo");

                JSONArray images = new JSONArray();
                images.add(image);
                json.put("images", images);
                String postParams = json.toString();

                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(postParams);
                wr.flush();
                wr.close();

                int responseCode = con.getResponseCode();
                BufferedReader br;
                if (responseCode == 200) {
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                
                
                JSONParser parser = new JSONParser();
                // JSON 문자열을 JSONObject로 파싱
                JSONObject jsonResponse = (JSONObject) parser.parse(response.toString());

                // images 배열 추출
                JSONArray imagesArray = (JSONArray) jsonResponse.get("images");

                // 반환할 결과 리스트 생성
                List<String> resultList = new ArrayList<>();

                // images 배열 내부의 객체들을 순회하며 필요한 정보 추출
                for (Object imageObj : imagesArray) {
                    JSONObject image1 = (JSONObject) imageObj;
                    JSONArray fieldsArray = (JSONArray) image1.get("fields");

                    // 필드 배열 내부의 객체들을 순회하며 필요한 정보 추출
                    for (Object fieldObj : fieldsArray) {
                        JSONObject field = (JSONObject) fieldObj;

                        // 필요한 정보만을 추출하여 결과 리스트에 추가
                        String inferText = (String) field.get("inferText");
                        resultList.add(inferText);
                    }
                }
                System.out.println(resultList.toString());
            // 클라이언트에 OCR 응답 반환
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error occurred: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
       
    }
}

