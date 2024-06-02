<template>
  <h1 class="title-animated">"FITTY와 함께"</h1>
  <p class="cont">
    <strong>Fitty와 함께라면 목표를 달성할 수 있습니다!</strong>
  </p>
  <div class="container">
    <div :style="backgroundStyle" class="background"></div>
    <section class="banner">
      <template v-if="fittyData">
        <div class="banner-group">
          <div class="banner1">
            <div>
              <h2><strong>My Fitty</strong></h2>
              <p class="fit">
                <label for=""><strong>피티명 </strong></label>
                {{ fittyData.fittyName }}
              </p>
              <p class="fit">
                <label for=""><strong>피티 목표 </strong></label>
                {{ fittyData.goal }}
              </p>
              <p class="fit">
                <label for=""><strong>피티 권한 </strong></label>
                {{ fittyData.status }}
              </p>
            </div>
            <template v-if="fittyData.status === '방장'">
              <button @click="removeFitty(fittyData.fittyName)">
                DELETE FITTY
              </button>
            </template>
          </div>
          <template v-if="fittyData.status === '방장'">
            <div class="banner1-2">
              <table>
                <tr>
                  <th>멤버 이름</th>
                  <th>멤버 역할</th>
                </tr>
                <tr v-for="member in fittyMembers" :key="member.userId">
                  <td>{{ member.fittyName }}</td>
                  <td>{{ member.status }}</td>
                  <td>
                    <button
                      @click="
                        kickMember({
                          userId: member.userId,
                          fittyName: member.fittyName,
                        })
                      "
                    >
                      강퇴
                    </button>
                  </td>
                </tr>
              </table>
            </div>
          </template>
        </div>
        <div class="banner2">
          <img :src="teamSrc" alt="" class="full-img" />
        </div>
        <div class="banner3">
          <img :src="exerScr" alt="" class="full-img" />
        </div>
        <template v-if="fittyData.status === '방장'">
          <div class="banner4">
            <h2><strong>대기중인 수락</strong></h2>
            <div v-for="guest in fittyGuests" :key="guest.userId" class="card">
              <div class="card-content">
                <p>{{ guest.status }}</p>
                <div class="button">
                  <button
                    @click="
                      handleButtonClick({
                        userId: guest.userId,
                        fittyName: guest.fittyName,
                      })
                    "
                  >
                    수락
                  </button>
                </div>
              </div>
            </div>
          </div>
        </template>
        <template v-else>
          <button class="quit" @click="quitMember({ userId: 'me' })">
            탈퇴하기
          </button>
        </template>
      </template>
      <template v-else>
        <div class="elseData"></div>
      </template>
    </section>
  </div>
</template>

<script setup>
import { useFittyStore } from "@/stores/fittyStore";
import { onMounted, computed } from "vue";
import { useRouter } from "vue-router";
import Team from "@/assets/team.jpg";
import Exer from "@/assets/exercise.jpg";
import backLogo from "@/assets/back-logo.png";

const teamSrc = Team;
const exerScr = Exer;
const backSrc = backLogo;

const router = useRouter();
const store = useFittyStore();

onMounted(() => {
  store.getFittyData();
  store.getMember(localStorage.getItem("fittyNameData"));
  store.getGuest(localStorage.getItem("fittyNameData"));
});

const fittyData = computed(() => store.fittyData);
const fittyMembers = computed(() => store.member);
const fittyGuests = computed(() => store.guest);

const backgroundStyle = computed(() => ({
  backgroundImage: `url(${backSrc})`,
  backgroundSize: "cover",
  backgroundPosition: "center",
  opacity: 0.5, // 불투명하게 설정
  position: "absolute",
  top: 0,
  left: 0,
  width: "100%",
  height: "123%",
  zIndex: -1, // 가장 뒤로 이동
}));

const handleButtonClick = (fitty) => {
  store.modifyFitty(fitty);
};

const removeFitty = (fittyName) => {
  store.removeFitty(fittyName);
  router.push({ name: "fittyList" });
};

const kickMember = (fitty) => {
  store.kickFittyMember(fitty);
};

const quitMember = (fitty) => {
  store.kickFittyMember(fitty);
  store.getFittyData();
  router.push({ name: "fittyList" });
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
  margin-top: 25px;
  font-size: 2.5rem;
  font-weight: bold;
  color: rgb(13, 30, 80);
  text-align: center;
  animation: fadeIn 1s ease-out;
}

.cont {
  text-align: center;
}

.container {
  display: grid;
  justify-content: center;
  align-items: center;
  height: 100%;
  padding: 20px;
  margin-bottom: 50rem;
  /* background-image는 JavaScript에서 설정하므로 여기서는 제거 */
}

.background {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1; /* 가장 뒤로 이동 */
  opacity: 0.5; /* 불투명하게 설정 */
}

.banner {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 30px;
  width: 100%;
  position: relative; /* 배너를 상대적으로 배치 */
}

.banner-group {
  display: flex;
  justify-content: space-between;
  gap: 20px;
}

.banner-group,
.banner2,
.banner3,
.banner4 {
  position: relative;
  overflow: hidden;
  border-radius: 8px;
  width: 500px;
  height: 300px;
  background-color: white;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1), 0px 1px 3px rgba(0, 0, 0, 0.08);
}

.banner1 {
  width: 200px;
}

.banner1 div {
  margin-left: 20px;
  margin-right: 20px;
}

.banner1 h2 {
  margin-left: 20px;
  margin-top: 20px;
  margin-bottom: 20px;
}

.banner1 .fit {
  box-shadow: 0px 4px 6px rgba(41, 37, 37, 0.1), 0px 1px 3px rgb(255, 255, 255);
  margin-left: 20px;
}

.banner1 p label {
  display: inline-block; /* 또는 block */
  width: 80px; /* 원하는 너비로 설정 */
  margin-right: 0px; /* label과 텍스트 사이의 간격 */
}

.banner1 button {
  margin-top: 10px;
  margin-left: 50px;
}

.banner3,
.banner4 {
  margin-top: 20px;
}

.banner1-2 {
  overflow-y: auto;
}

.banner1-2 table {
  margin-top: 20px;
  align-items: center;
  /* margin-right: px; */
}

.banner4 {
  overflow-y: auto; /* 세로 스크롤을 활성화 */
}

.banner4 h2 {
  margin-left: 30px;
  margin-top: 20px;
  margin-bottom: 20px;
}

.banner4 p {
  text-align: center;
}

.card {
  width: 200px;
  height: 40px;
  background-image: linear-gradient(
    to bottom,
    rgb(255, 255, 255),
    rgb(255, 255, 255)
  );
  border-radius: 3px;
  color: rgb(5, 47, 131);
  cursor: pointer;
  display: inline-flex;
  flex-direction: column;
  justify-content: center;
  position: relative;
  margin-bottom: 10px;
  margin-right: 5px;
  margin-left: 30px;
  padding: 10px;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1), 0px 1px 3px rgba(0, 0, 0, 0.08);
}

.card-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.card p {
  margin: 0;
}

.full-img {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th,
td {
  padding: 8px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

button {
  margin-top: 0px;
  padding: 3px 8px;
  background-color: rgb(13, 30, 80);
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: rgb(34, 50, 100);
}

h2 {
  margin-bottom: 10px;
}

.quit {
  width: 500px; /* 원하는 너비로 설정 */
  height: 300px; /* 높이를 300px로 고정 */
  font-size: 0.8rem; /* 원하는 글자 크기로 설정 */
  margin-top: 20px;
}
</style>
