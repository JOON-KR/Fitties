<template>
  <div>
    <div v-if="isProfile">
      <section class="banner">
        <div class="banner-group">
          <div class="banner1">
            <div class="profile1">
              <div class="wow">
                <div class="info-item">
                  <label for=""><strong>티어</strong></label>
                  <span>{{ getTierGrade(userRank.tier) }}</span>
                </div>
                <div class="info-item">
                  <label for=""><strong>경험치 </strong></label>
                  <span> {{ userRank.exp }}</span>
                </div>
                <div class="info-item">
                  <label for=""><strong>레벨</strong></label>
                  <span> Lv. {{ userRank.level }}</span>
                </div>
                <div class="info-item">
                  <label for=""><strong>스트릭</strong></label>
                  <span> {{ userRank.streak }} 일</span>
                </div>
              </div>
              <div class="wow2">
                <img :src="`src/assets/${profile.image}`" alt="이미지" />
                <div class="nickname">
                  <strong> {{ userData.nickname }}</strong>
                </div>
              </div>
            </div>
          </div>
          <br />
          <div class="banner2">
            <div class="info-item2">
              <strong>근육량</strong>
              {{ profile.muscle ? profile.muscle.toFixed(2) : "0.00" }}
            </div>
            <div class="info-item2">
              <strong>체지방량</strong>
              {{ profile.fat ? profile.fat.toFixed(2) : "0.00" }}
            </div>
            <div class="info-item2">
              <strong>BMI</strong>
              {{ profile.bmi ? profile.bmi.toFixed(2) : "0.00" }}
            </div>
            <div class="info-item2">
              <strong>체지방률</strong>
              {{ profile.fatPer ? profile.fatPer.toFixed(2) : "0.00" }}
            </div>
            <div class="info-item2">
              <strong>체중</strong>
              {{ profile.weight ? profile.weight.toFixed(2) : "0.00" }}
            </div>
            <router-link to="/profile" class="button-container">
              <button class="button-sort"><strong>MORE</strong></button>
            </router-link>
          </div>
        </div>
      </section>
    </div>
    <p v-else>Loading...</p>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/userStore";
import { computed, onMounted } from "vue";
import { useRouter } from "vue-router";

const store = useUserStore();
const router = useRouter();

const isProfile = computed(() => store.userProfile !== null);

onMounted(async () => {
  await store.getUserData();
  await store.getUserRank();
  await store.getUserProfile();
  await store.getRankList();
});

const profile = computed(() => store.userProfile || {});
const userData = computed(() => store.userData || {});
const userRank = computed(() => store.userRank || {});

const getTierGrade = (tier) => {
  if (tier >= 0 && tier < 500) return "아이언";
  else if (tier >= 500 && tier < 1000) return "브론즈";
  else if (tier >= 1000 && tier < 1500) return "실버";
  else if (tier >= 1500 && tier < 2000) return "골드";
  else if (tier >= 2000 && tier < 2500) return "플래티넘";
  else if (tier >= 2500 && tier < 3000) return "에매랄드";
  else if (tier >= 3000 && tier < 3500) return "다이아";
  else if (tier >= 3500 && tier < 4000) return "마스터";
  else if (tier >= 4000 && tier < 4500) return "그랜드마스터";
  else return "챌린저";
};
</script>

<style scoped>
.banner1 {
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1), 0px 1px 3px rgba(0, 0, 0, 0.08);
  background-color: #081d57;
  color: white;
  border-radius: 10px;
  margin-bottom: 8.2px;
  width: 350px;
  height: 168px; /* 원하는 높이로 설정 */
  display: flex; /* 부모 요소를 수평으로 정렬 */
  justify-content: space-between; /* 자식 요소 사이의 간격을 최대화하여 나란히 배치 */
  align-items: center; /* 수직 정렬을 위해 자식 요소를 가운데로 정렬 */
}

.profile1 {
  display: flex; /* 자식 요소를 수평으로 배치 */
  flex-direction: row; /* 자식 요소를 수평으로 정렬 */
}

.banner2 {
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1), 0px 1px 3px rgba(0, 0, 0, 0.08);
  border-radius: 10px;
  height: 150px;
  width: 350px;
  background-color: rgb(241, 241, 212);
  color: #081d57;
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 3px; /* 간격을 줄임 */
  padding: 3px; /* 패딩을 줄임 */
  box-sizing: border-box;
}

.wow {
  /* 각각의 자식 요소에 대한 스타일 */
  flex: 1; /* 동일한 너비를 가지도록 설정 */
  padding: 20px; /* 내부 여백 조정 */
  width: 230px;
}

.wow .info-item {
  display: flex;
  justify-content: space-between; /* 레이블과 콘텐츠 사이의 간격을 최대화 */
  margin-bottom: 3px; /* 항목 간의 여백 추가 */
}

.wow2 {
  /* 각각의 자식 요소에 대한 스타일 */
  flex: 1; /* 동일한 너비를 가지도록 설정 */
  padding: 10px; /* 내부 여백 조정 */
  display: flex; /* 자식 요소를 수평으로 배치 */
  flex-direction: column; /* 자식 요소를 수직으로 정렬 */
  align-items: center; /* 수직 정렬을 위해 자식 요소를 가운데로 정렬 */
  padding-top: 20px;
}

.wow2 img {
  /* 이미지에 대한 스타일 */
  width: 100px; /* 이미지 너비 조정 */
  height: 100px; /* 이미지 높이 자동 조정 */
  border-radius: 50%;
}

.nickname {
  /* 닉네임에 대한 스타일 */
  margin-top: 10px; /* 위쪽 여백 추가 */
}

.banner2 .info-item2 {
  display: flex;
  justify-content: space-between;
  padding: 7px; /* 패딩을 줄임 */
  border-radius: 5px;
  font-size: 0.9em; /* 폰트 크기 줄임 */
}

.banner2 .info-item2 strong {
  margin-right: 0px;
}

.button-container {
  grid-column: span 2;
  display: flex;
  justify-content: flex-end;
}

.button-sort {
  padding: 5px 20px; /* 패딩을 줄임 */
  color: #0c2c85;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 0.8rem;
  background-color: transparent;
}
</style>
