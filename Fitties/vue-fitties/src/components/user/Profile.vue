<template>
  <div class="back">
    <div class="profile-container">
      <div class="create-profile-form">
        <CreateProfile />
      </div>

      <div class="profile-list">
        <h1 class="title-animated">프로필 목록</h1>
        <div v-if="userProfiles.length === 0" class="profile-info">
          <h2>프로필이 없습니다.</h2>
        </div>
        <div v-else class="profile-info">
          <div
            v-for="(profile, index) in userProfiles"
            :key="index"
            class="profile-card"
          >
            <h5>
              <strong>프로필 {{ index + 1 }}</strong>
            </h5>

            <div class="card-img" style="text-align: center">
              <div v-if="profile.image">
                <img :src="`src/assets/${profile.image}`" alt="이미지" />
              </div>
            </div>

            <div>
              <div>
                <strong><label>등록일</label></strong>
                {{ profile.regDate }}
              </div>
              <div>
                <strong><label>골격근량 (kg)</label></strong>
                {{ profile.muscle.toFixed(2) }}
              </div>
              <div>
                <strong><label>체지방량 (kg)</label></strong>
                {{ profile.fat.toFixed(2) }}
              </div>
              <div>
                <strong><label>BMI</label></strong>
                {{ profile.bmi.toFixed(2) }}
              </div>
              <div>
                <strong><label>체지방률 (%)</label></strong>
                {{ profile.fatPer.toFixed(2) }}
              </div>
              <div>
                <strong><label>체중 (kg)</label></strong>
                {{ profile.weight.toFixed(2) }}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div :style="backgroundStyle" class="backGG"></div>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/userStore";
import { computed } from "vue";
import CreateProfile from "@/components/user/CreateProfile.vue";
import back from "@/assets/back-logo.png";
import user from "@/assets/user.png";

const userSrc = user;
const backSrc = back;

const userStore = useUserStore();
userStore.getUserAllProfiles();
const userProfiles = computed(() => userStore.userAllProfiles);

const backgroundStyle = computed(() => ({
  backgroundImage: `url(${backSrc})`,
  backgroundSize: "cover",
  backgroundPosition: "center",
  opacity: 0.5, // 불투명하게 설정
  position: "absolute",
  top: 0,
  left: 0,
  width: "100%",
  height: "115%",
  zIndex: -1, // 가장 뒤로 이동
}));
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
  margin-top: 30px;
  font-size: 2.5rem;
  font-weight: bold;
  color: rgb(13, 30, 80);
  text-align: center;
  animation: fadeIn 1s ease-out;
  margin-right: 30px;
}

.backGG {
  /* background-image: linear-gradient(to bottom, #ffffff, #5875be); */
  /* background-color: #9aaad3; */
  /* opacity: 0.8; */
}

.card-img img {
  width: 100px;
  height: 100px;
  border-radius: 100px;
}

.profile-container {
  margin: 20px auto;
  width: 80%;
  max-width: 1000px; /* 조정된 최대 너비 */
  display: flex; /* 프로필 목록과 폼을 가로로 배치 */
  justify-content: space-between; /* 폼과 목록을 가로로 정렬하고 사이의 간격을 최대화 */
}

.profile-list {
  flex: 1; /* 남은 공간을 모두 차지하도록 설정 */
}

.profile-info {
  padding-left: 50px;
  padding-right: 50px;
  padding-bottom: 50px;
  padding-top: 20px;
  max-height: 750px; /* 최대 높이를 설정 */
  overflow-y: auto; /* 수직 스크롤을 추가 */
}

.profile-info h2 {
  margin-top: 100px;
  text-align: center;
}

.create-profile-form {
  width: 45%; /* 폼의 너비를 조정 */
}

.profile-card {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 20px;
  margin-bottom: 20px;
  border-radius: 8px;
  background-image: linear-gradient(to bottom, #ffffff, #f1f3fd);
  box-shadow: 0 4px 50px rgba(0, 0, 0, 0.2);
  transition: transform 0.3s;
  width: 80%;
  opacity: 1;
}

.profile-card:hover {
  transform: translateY(-5px);
}

.profile-card h3 {
  margin-bottom: 10px;
}

.profile-card div {
  margin-bottom: 5px;
}

.profile-card strong {
  display: inline-block;
  width: 100px;
}

.profile-card label {
  font-weight: bold;
}
.profile-image {
  max-width: 100%;
  height: auto;
  border-radius: 5px;
  margin-bottom: 10px;
}
</style>
