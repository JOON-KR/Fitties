<template>
  <div class="login-container">
    <form class="form">
      <img :src="logoSrc" alt="logo" class="logoimg">
      <div class="flex-column">
        <label for="id"><strong>ID</strong></label>
        <div class="inputForm">
          <img :src="userSrc" alt="user">
          <input type="text" id="id" v-model="id" class="input" placeholder="Enter your ID" />
        </div>
      </div>
      <div class="flex-column">
        <label for="password"><strong>Password</strong></label>
        <div class="inputForm">
          <img :src="lockSrc" alt="lcok">
          <input type="password" id="password" v-model="password" class="input" placeholder="Enter your Password" />
          <!-- <img :src="eyeSrc" alt="eye"> -->
        </div>
      </div>
      <div class="flex-row">
        <div>
          <input type="checkbox" class="checkbox"/>
          <label>Remember me</label>
        </div>
        <!-- <span class="span">Forgot password?</span> -->
      </div>
      <button @click="login" class="button-submit">Sign In</button>
      <p class="p">Don't have an account? 
        <span class="span">
          <router-link to="/regist" class="menu-link">
            Sign Up
          </router-link>
        </span>
      </p>
      
    </form>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/userStore";
import { ref } from "vue";
import { useRouter } from "vue-router";
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

const login = function () {
  store.loginUser(id.value, password.value);
 
  router.push({name:"main"})
  
};
</script>

<style scoped>
.login-container {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100vh;
  /* background-color: #0e234d; */
  background-color: #ffffff;
  /* background-image: url('/src/assets/back-logo.png'); 이미지 경로를 지정합니다. */
  background-image: linear-gradient(to bottom, #ffffff, #99a7cb);
  background-size: contain; /* 배경 이미지를 컨테이너에 맞게 조절합니다. */
  background-position: right; /* 배경 이미지를 가운데 정렬합니다. */
}

.logoimg{
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

.checkbox {
  margin-right: 10px;
}

.flex-row {
  display: flex;
  align-items: center;
  justify-content: space-between;
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
.line::before,
.line::after {
  content: "";
  display: inline-block;
  width: 45%;
  height: 1px;
  background-color: #d1d5db;
  vertical-align: middle;
  margin: 0 10px;
}
.flex-row .btn {
  display: flex;
  align-items: center;
  background-color: #4285f4;
  color: white;
  border: none;
  border-radius: 4px;
  padding: 10px 16px;
  cursor: pointer;
}
.flex-row .btn svg {
  margin-right: 8px;
}
.flex-row .btn.google {
  background-color: #4285f4;
}
.flex-row .btn.google:hover {
  background-color: #357ae8;
}
</style>
