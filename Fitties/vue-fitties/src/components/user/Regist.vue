<template>
  <div class="login-container">
    <form class="form">
      <img :src="logoSrc" alt="logo" class="logoimg" />
      <div class="flex-column">
        <label for="id"><strong>ID</strong></label>
        <div class="inputForm">
          <img :src="userSrc" alt="user" />
          <input type="text" id="id" v-model="id" class="input" placeholder="Enter your ID" />
        </div>
      </div>
      <div class="flex-column">
        <label for="password"><strong>Password</strong></label>
        <div class="inputForm">
          <img :src="lockSrc" alt="lock" />
          <input type="password" id="password" v-model="password" class="input" placeholder="Enter your Password" />
        </div>
      </div>
      <div class="flex-column">
        <label for="name"><strong>Name</strong></label>
        <div class="inputForm">
          <input type="text" id="name" v-model="name" class="input" placeholder="Enter your name" />
        </div>
      </div>
      <div class="flex-column">
        <label for="nickname"><strong>Nickname</strong></label>
        <div class="inputForm">
          <input type="text" id="nickname" v-model="nickname" class="input" placeholder="Enter your nickname" />
        </div>
      </div>
      <div class="flex-column">
        <label for="email"><strong>E-mail</strong></label>
        <div class="inputForm">
          <input type="email" id="email" v-model="email" class="input" placeholder="Enter your email" />
        </div>
      </div>
      <button @click.prevent="handleRegister" class="button-submit">회원가입</button>
      <p class="p">이미 계정이 있으신가요? 
        <span class="span">
          <router-link to="/login" class="menu-link">
            로그인
          </router-link>
        </span>
      </p>
    </form>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";
import { useUserStore } from "@/stores/userStore";
import LOGO from '@/assets/LOGO.png';
import user from '@/assets/user.png';
import lock from '@/assets/lock.png';

const logoSrc = LOGO;
const userSrc = user;
const lockSrc = lock;
const router = useRouter();
const store = useUserStore();

const id = ref("");
const password = ref("");
const name = ref("");
const nickname = ref("");
const email = ref("");
const answer = ref(false);

const handleRegister = async () => {
  if (!validateInputs()) return;

  try {
    const response = await axios.get(`http://localhost:8080/gpt/valid2/${nickname.value}`);
    answer.value = response.data.choices[0].message.content;
    console.log('서버로부터의 응답:', answer.value);

    if (answer.value === "false") {
      alert("부적절한 닉네임 입니다");
      return;
    }

    const newUser = {
      id: id.value,
      password: password.value,
      name: name.value,
      nickname: nickname.value,
      email: email.value
    };

    store.registUser(newUser);
    alert("회원가입이 완료되었습니다!");
    router.push("/login");
  } catch (error) {
    console.error("회원가입 중 오류 발생:", error);
    alert("회원가입 중 오류가 발생했습니다. 다시 시도해주세요.");
  }
};

const validateInputs = () => {
  if (!id.value || !password.value || !name.value || !nickname.value || !email.value) {
    alert("모든 항목을 입력하세요.");
    return false;
  }

  if (id.value.length > 16 || password.value.length > 16 || name.value.length > 16 || nickname.value.length > 16) {
    alert("입력 항목은 최대 16자까지 입력 가능합니다.");
    return false;
  }

  if (email.value.length > 50) {
    alert("이메일은 최대 50자까지 입력 가능합니다.");
    return false;
  }

  // 이메일 유효성 검사 로직 추가

  return true;
};
</script>

<style scoped>
.login-container {
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

.logoimg {
  width: 120px;
  margin-bottom: 20px;
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

.inputForm img {
  margin-left: 4px;
  height: 1em;
  width: auto;
}

.button-submit {
  width: 100%;
  padding: 12px;
  border: none;
  border-radius: 4px;
  background-color: #0c2c85;
  color: white;
  font-size: 16px;
  cursor: pointer;
  margin-bottom: 20px;
  margin-top: 10px;
}

.button-submit:hover {
  background-color: #6173af;
}

.p {
  text-align: center;
  margin-bottom: 16px;
}

.span {
  color: #1d4ed8;
  cursor: pointer;
}
</style>
