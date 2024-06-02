<template>
  <div :style="backgroundStyle" class="background"></div>
  <template v-if="show">
    <div class="container">
      <section class="banner">
        <div class="banner-group">
          <div class="banner1">
            <template v-if="isCalendar">
              <MainCalendar />
            </template>
          </div>
          <div class="banner2">
            <template v-if="isProfile">
              <MainProfile />
            </template>
          </div>
          <div class="banner3-4">
            <template v-if="isFitty">
              <MainFitty />
            </template>
          </div>
          <div class="banner4">
            <template v-if="isRank">
              <MainRank />
            </template>
          </div>
        </div>
      </section>
    </div>
  </template>
  <template v-else>
    <MainThumbNail />
  </template>
</template>

<script setup>
import { computed, onMounted, ref } from "vue";
import { useUserStore } from "@/stores/userStore";
import { useFittyStore } from "@/stores/fittyStore";
import { useCalendarStore } from "@/stores/calendarStore";
import { useRouter } from "vue-router";

import MainCalendar from "@/components/main/MainCalendar.vue";
import MainProfile from "@/components/main/MainProfile.vue";
import MainFitty from "@/components/main/MainFitty.vue";
import MainRank from "@/components/main/MainRank.vue";
import MainThumbNail from "@/components/main/MainThumbNail.vue";

const fittyStore = useFittyStore();
const router = useRouter();
const store = useUserStore();
const calendarStore = useCalendarStore();

const show = ref(false);

function checkToken() {
  const user = JSON.parse(localStorage.getItem("user"));
  const token = user ? user.token : null;
  show.value = !!token;
}

checkToken();
setInterval(checkToken, 500);

onMounted(async () => {
  if (show.value) {
    await store.getRankList();
    await calendarStore.getDetailList();
    await calendarStore.getSchedule();
    await fittyStore.getFittyList();
    await store.getUserData();
    await store.getUserRank();
    await store.getUserProfile();
    await store.getRankList();
  }
});

const isRank = computed(() => store.userRank !== null);
const isProfile = computed(() => store.userProfile !== null);
const isCalendar = computed(() => calendarStore.schedule !== null);
const isFitty = computed(() => fittyStore.fittyList !== null);

import backLogo from "@/assets/back-logo.png";
const backgroundStyle = computed(() => ({
  backgroundImage: `url(${backLogo})`,
  backgroundSize: "cover",
  backgroundPosition: "center",
  opacity: 0.5,
  position: "absolute",
  top: 0,
  left: 0,
  width: "100%",
  height: "100%",
  zIndex: -1,
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
  margin-top: 25px;
  font-size: 2.5rem;
  font-weight: bold;
  color: rgb(13, 30, 80);
  text-align: center;
  animation: fadeIn 1s ease-out;
}

.background {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
  opacity: 0.5;
}

.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100%;
  padding: 20px;
  border-radius: 777px;
  background-color: white;
  margin-bottom: 6rem;
}

.banner {
  display: flex;
  grid-template-columns: 1fr;
  gap: 30px;
  width: 100%;
  position: relative;
}

.banner-group {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 30px;
  width: 100%;
  flex-wrap: wrap;
  margin-top: 37px;
}

.banner1,
.banner2 {
  /* padding: 20px; */
  border-radius: 8px;

  /* display: flex; */
  /* justify-content: center;
  align-items: center; */
  height: 350px;
}

.banner1 {
  width: 900px;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1), 0px 1px 3px rgba(0, 0, 0, 0.08);
}

.banner2 {
  width: 350px;
  background-color: none;
  display: table-column;
}

.banner3-4,
.banner4 {
  display: grid;
  justify-content: space-evenly; /* 아이템 간의 간격을 최대화하여 화면 하단 공간을 다 차지하도록 설정 */
  width: 100%;
}

.banner-item {
  flex: 1; /* 아이템이 동일한 너비를 가지도록 설정 */
}

.full-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.quit {
  width: 500px;
  height: 300px;
  font-size: 0.8rem;
  margin-top: 20px;
}
</style>
