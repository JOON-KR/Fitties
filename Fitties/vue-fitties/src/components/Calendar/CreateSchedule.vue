<template>
  <div>
    <form @submit.prevent="createSchedule" class="form-container">
      <label for="endDate"><strong>종료일</strong></label>
      <input type="date" id="endDate" v-model="endDate" required />
      <label for="goal" class="goal"><strong>목표</strong></label>
      <input type="number" id="goal" v-model="goal" required />

      <div class="button-container">
        <button type="submit" class="button-form">생성하기</button>
        <button type="button" @click="cancel" class="button-form">취소</button>
      </div>
    </form>
    <br />
    <hr />
    <div class="img">
      <h4 class="with-fitty"><strong>Fitty와 함께하기</strong></h4>
      <!-- <img :src="raceSrc" alt="" /> -->
      <button @click="getFitty" class="button">같이하기</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useCalendarStore } from "@/stores/calendarStore";
import { useUserStore } from "@/stores/userStore";
import race from "@/assets/race.jpg";

const raceSrc = race;

const userStore = useUserStore();
const store = useCalendarStore();

const endDate = ref("");
const goal = ref(0);
const router = useRouter();

function createSchedule() {
  const scheduleData = {
    userId: userStore.userRank.userId,
    endDate: endDate.value,
    goal: goal.value,
  };
  store.registSchedule(scheduleData);
  router.push({ name: "schedule" });
}

function cancel() {
  // 취소 로직 구현
  // 취소 후 일정 페이지로 이동
  router.push({ name: "schedule" });
}

function getFitty() {
  // 일정 페이지로 이동
  router.push({ name: "fittyList" });
}
</script>

<style scoped>
/* 필요한 스타일링 추가 */
.form-container {
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-bottom: 20px;
}

/* 라벨 스타일링 */
label {
  font-weight: bold;
  font-size: 16px;
}

/* 입력 필드 스타일링 */
input {
  padding: 10px; /* 내부 여백 */
  font-size: 16px; /* 폰트 크기 */
  border: 2px solid #ccc; /* 테두리 스타일 */
  border-radius: 5px; /* 테두리 모서리 둥글게 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 그림자 */
}

/* 입력 필드 포커스 스타일링 */
input:focus {
  border-color: #0035a0; /* 포커스 시 테두리 색상 변경 */
}

.goal {
  margin-top: 20px;
}

.button-container {
  display: flex;
  justify-content: center;
}

.button-form {
  padding: 10px;
  font-size: 16px;
  width: 40%;
  background-color: #0035a0;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-left: 30px;
  margin-top: 40px;
}

.with-fitty {
  text-align: center;
  font-size: 2rem;
  color: #333;
  margin-bottom: 20px;
  margin-top: 6px;
}

.img {
  display: flex;
  text-align: center;
  justify-content: center;
}

img {
  width: 30%;
  height: 30vh;
  border-radius: 20px;
}

.button {
  padding: 10px;
  width: 100px;
  height: 50px;
  font-size: 16px;
  background-color: #0035a0;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-left: 40px;
}

.button:hover {
  background-color: #2980b9;
}
</style>
