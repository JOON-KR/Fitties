<template>
  <div class="register-container">
    <form class="form">
      <h1 class="title-animated">회원 정보 수정</h1>
      
      <div class="flex-column">
        <label for="name"><strong>이름</strong></label>
        <div class="inputForm">
          <input
            type="text"
            id="name"
            v-model="user.name"
            class="input"
            placeholder="이름을 입력하세요"
          />
        </div>
      </div>
      <div class="flex-column">
        <label for="nickname"><strong>닉네임</strong></label>
        <div class="inputForm">
          <input
            type="text"
            id="nickname"
            v-model="user.nickname"
            class="input"
            placeholder="닉네임을 입력하세요"
          />
        </div>
      </div>
      <div class="flex-column">
        <label for="email"><strong>이메일</strong></label>
        <div class="inputForm">
          <input
            type="email"
            id="email"
            v-model="user.email"
            class="input"
            placeholder="이메일을 입력하세요"
          />
        </div>
      </div>
      <div class="flex-column">
        <label for="password"><strong>PW</strong></label>
        <div class="inputForm">
          <input
            type="password"
            id="password"
            v-model="password"
            class="input"
            placeholder="비밀번호를 입력하세요"
          />
        </div>
      </div>
      <div class="button-container">
        <button @click.prevent="edit" class="button-submit">수정하기</button>
        <button @click.prevent="cancel" class="button-cancel">취소</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/userStore";
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const store = useUserStore();

// 스토어에서 직접 데이터 가져오기
const user = {
  name: store.userData.name,
  nickname: store.userData.nickname,
  email: store.userData.email,
};

const password = ref(""); // 비밀번호

const edit = function () {
  const newUser = {
    password: password.value,
    name: user.name,
    nickname: user.nickname,
    email: user.email,
  };
  console.log(newUser);
  store.editUser(newUser);
  console.log(store.userData);
  router.push({ name: "main" });
};

const cancel = function () {
  router.push({ name: "main" });
};
</script>

<style scoped>
@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(-20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.title-animated {
  margin-top: 0px;
  font-size: 2rem;
  font-weight: bold;
  color: rgb(13, 30, 80);
  text-align: center;
  margin-bottom: 20px;
  animation: fadeIn 1s ease-out;
}

.register-container {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100vh;
  background-color: #ffffff;
  background-image: linear-gradient(to bottom, #ffffff, #99a7cb);
  background-size: contain;
  background-position: right;
}

.form {
  width: 560px;
  padding: 36px 48px;
  border-radius: 8px;
  background-color: white;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.flex-column {
  display: flex;
  flex-direction: column;
  margin-bottom: 16px;
}

.inputForm {
  display: flex;
  align-items: center;
  background-color: #f1f1f1;
  border: 1px solid #d1d5db;
  border-radius: 4px;
  padding: 0 12px;
  margin-top: 5px;
}

.input {
  border: none;
  outline: none;
  background: none;
  padding: 10px;
  flex-grow: 1;
}

.button-container {
  display: flex;
  justify-content: space-between;
}

.button-submit,
.button-cancel {
  width: 48%;
  padding: 12px;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
  margin-top: 10px;
}

.button-submit {
  background-color: #0c2c85;
  color: white;
}

.button-submit:hover {
  background-color: #6173af;
}

.button-cancel {
  background-color: #d1d5db;
  color: #333;
}

.button-cancel:hover {
  background-color: #a1a5af;
}
</style>
