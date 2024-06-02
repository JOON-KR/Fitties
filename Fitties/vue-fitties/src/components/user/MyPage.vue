<template>
  <div class="my-page-container">
    <form class="form">
      <h1 class="title-animated">마이 페이지</h1>
      <div v-if="userData && userRank" class="user-info">
        <div class="info-row">
          <strong><label for="">이름</label></strong>
          <span>{{ userData.name }}</span>
        </div>
        <div class="info-row">
          <strong><label for="">사용자 ID</label></strong>
          <span>{{ userRank.userId }}</span>
        </div>
        <div class="info-row">
          <strong><label for="">이메일</label></strong>
          <span>{{ userData.email }}</span>
        </div>
        <div class="info-row">
          <strong><label for="">닉네임</label></strong>
          <span>{{ userData.nickname }}</span>
        </div>
        <div class="info-row">
          <strong><label for="">티어</label></strong>
          <span>{{ userRank.tier }}</span>
        </div>
        <div class="info-row">
          <strong><label for="">경험치</label></strong>
          <span>{{ userRank.exp }}</span>
        </div>
        <div class="info-row">
          <strong><label for="">레벨</label></strong>
          <span>{{ userRank.level }}</span>
        </div>
        <div class="info-row">
          <strong><label for="">스트릭</label></strong>
          <span>{{ userRank.streak }}</span>
        </div>
      </div>
      <router-link to="/edit">
        <button class="button-edit">회원 정보 수정</button>
      </router-link>
      <button @click="confirmQuit" class="button-quit">회원 탈퇴</button>
    </form>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/userStore";
import { computed } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const store = useUserStore();

const userProfile = computed(() => store.userProfile);
const userData = computed(() => store.userData);
const userRank = computed(() => store.userRank);

const confirmQuit = () => {
  if (confirm("정말로 탈퇴하시겠습니까?")) {
    store.quitUser();
    router.push("/login");
  } else {
    // "아니오"를 선택한 경우
    // 아무런 작업 없음
  }
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

.my-page-container {
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

.user-info {
  margin-top: 20px;
}

.info-row {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.info-row strong {
  width: 120px; /* 모든 라벨의 너비를 동일하게 설정 */
}

.info-row span {
  flex-grow: 1;
}

.button-edit,
.button-quit {
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

.button-edit:hover,
.button-quit:hover {
  background-color: #6173af;
}
</style>
