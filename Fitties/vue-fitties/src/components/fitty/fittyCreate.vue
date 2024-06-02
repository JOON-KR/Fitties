<template>
  <div class="party-container">
    <form @submit.prevent="createParty" class="form">
      <h2><strong>Create Fitty</strong></h2>
      <br>
      <div class="flex-column">
        <label for="fittyName"><strong>Fitty Title</strong></label>
        <div class="inputForm">
          <input type="text" id="fittyName" v-model="fittyName" class="input" required>
        </div>
      </div>
      <div class="flex-column">
        <label for="goal"><strong>Object</strong></label>
        <div class="inputForm">
          <input type="number" id="goal" v-model.number="goal" min="1" max="100" class="input" required>
        </div>
      </div>
      <div>
        <input type="hidden" id="status" v-model="status" value="admin">
      </div>
      <div class="flex-row">
        <input type="checkbox" id="isOpen" v-model="isOpen" class="checkbox">
        <label for="isOpen">공개 여부</label>
      </div>
      <br>
      <button type="submit" class="button-submit">생 성</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useUserStore } from "@/stores/userStore";
import { useFittyStore } from "@/stores/fittyStore";
import { useRouter } from "vue-router";
import axios from 'axios';
const userRank = useUserStore().userRank;
const router = useRouter();
const fittyName = ref('');
const goal = ref('');
const status = ref('admin');
const userId = ref(userRank.userId);
const isOpen = ref(true); // 기본값을 true로 설정
const answer = ref("");
const fittyStore = useFittyStore();

const createParty = async () => {
  // isOpen 값이 true인 경우 1로, false인 경우 0으로 설
    const response = await axios.get(`http://localhost:8080/gpt/valid/${fittyName.value}`);
    answer.value = response.data.choices[0].message.content;
    console.log('서버로부터의 응답:',answer.value);
    if (answer.value === "false") {
      alert("부적절한 FITTY 이름입니다");
      return;
    }
 
    const newFitty = {
      fittyName: fittyName.value,
      goal: goal.value,
      status: 'admin',
      userId: userId.value,
      isOpen: isOpen.value ? 1 : 0,
    };
    console.log(newFitty);
    fittyStore.addFitty(newFitty);
    router.push({name:"fittyList"});
  };
</script>

<style scoped>
.party-container {
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

.checkbox {
  margin-right: 10px;
}

.flex-row {
  display: flex;
  align-items: center;
  /* justify-content: space-between; */
  margin-bottom: 0px;
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
