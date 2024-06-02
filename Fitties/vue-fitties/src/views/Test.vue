<template>
    <div>
      <h1>파일 업로드</h1>
      <div>
        <label for="image"><strong>이미지</strong></label>
        <div>
          <input
            type="file"
            ref="fileInput"
            accept="image/*"
            @change="handleFileChange"
            required
          />
        </div>
      </div>
      <button @click="uploadFile">확인</button>
    </div>
  </template>
  
  <script setup>
  import axios from 'axios';
  import { ref } from 'vue';
  
  const fileInput = ref(null);
  const selectedFile = ref(null);
  
  function handleFileChange(event) {
    // 파일이 선택되면 해당 파일을 selectedFile에 저장
    selectedFile.value = event.target.files[0];
  }
  
  async function uploadFile() {
    try {
      if (!selectedFile.value) {
        alert("파일을 선택하세요.");
        return;
      }
  
      // 파일 업로드
      const formData = new FormData();
      formData.append("file", selectedFile.value);
  
      const response = await axios.post(
        "http://localhost:8080/upload/load",
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );
  
      // 서버에서 반환된 파일 경로를 이미지 데이터로 설정
      const imagePath = response.data; // 서버에서 반환하는 경로 키에 맞게 변경
  
      // 이미지 데이터를 OCR 서버로 전송
      const response2 = await axios.post(
        `http://localhost:8080/ocr/${imagePath}`,
        {},
        {
          headers: {
            "Content-Type": "application/json",
            "X-OCR-SECRET": "UWVSTEV0WlV5Y09GaGVNYnJCZVdubU5GdGhicVVDWko="
          },
        }
      );
      console.log(response2);
  
      // 폼 초기화
      fileInput.value.value = null;
      selectedFile.value = null;
    } catch (error) {
      alert("파일의 용량이 너무 큽니다.");
      console.error("파일 업로드 중 오류 발생:", error);
    }
  }
  </script>
  