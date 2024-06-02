package com.ssafy.fitty.model.dto.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatGPT {


    
    public String gptService(String word,int part) throws Exception{
        //part == 1 -> 루틴추천
    	//part == 2 -> 유효성검사
    	if(part==1) {
    		word = "\'"+word+"\'"+" 운동 루틴을 추천해줘 현재 시간을 고려해서 유동적으로. 8개정도, 답변형식은 무조건 이렇게만 하고 사족을 붙이면 안돼. 답변은 6줄로 이루어져있고 각 줄은[운동이름 : n세트 n회 반복 ] 이야 각 운동을 - 라는 구분자로 구분할거니까 줄바꿈 대신 - 를 사용해줘";
    	}else if(part==2){
    		word = "\'"+word+"\'"+" 이것은 운동 그룹의 이름인데, 비속어나 사회적으로 보았을 때 저속한 내용이 포함되어 있다면 답변으로 'false'를 반환하고 그게 아니라면 'true'를 반환해줘 그 외 말은 하지마";
    	}else if(part==3){
    		word = "\'"+word+"\'"+" 이것은 닉네임인데, 비속어나 사회적으로 보았을 때 저속한 내용이 포함되어 있다면 답변으로 'false'를 반환하고 그게 아니라면 'true'를 반환해줘 그 외 말은 하지마 ";
    	}else {
    		word = "\'"+word+"\'"+" 운동 그룹의 이름을 8글자 이내로, 순우리말 한글로 1개만 랜덤으로 정해서 추천해줘 ,답변에 이름을 제외한 사족을 붙이지마.";
        	
    	}
    	URL url = new URL(GPT_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        String message = "{\"role\":\"user\",\"content\":\"" + word + "\"}";
        
        String data = "{\"model\":\"gpt-3.5-turbo\",\"temperature\":0.7,\"messages\":[" + message + "]}";
        //웹에서 주고받는거?
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()))) {
            bw.write(data);
            bw.flush();
        }
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder sb = new StringBuilder();
            String line = null;
            
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            
            connection.disconnect();
            return sb.toString();
        	} 
        }
//    public static void main(String[] args) throws Exception{
//    	ChatGPT gpt =new ChatGPT();
//		System.out.println(gpt.gptService("이거되네?",1));
//	}
}
