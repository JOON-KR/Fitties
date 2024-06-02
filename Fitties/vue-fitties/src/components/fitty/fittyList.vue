<template>
  <div class="container">
    <br />
    <h1 class="title-animated">"FITTY HOME"</h1>
    <br />
    <div class="header-container">
      <div class="button-container">
        <router-link class="button-link" :to="{ name: 'fittyStatus' }"
          >FITTY 가입현황</router-link
        >
        <router-link class="button-link" :to="{ name: 'fittyMatching' }"
          >FITTY 매칭</router-link
        >
        <router-link class="button-link" :to="{ name: 'fittyCreate' }"
          >FITTY 생성하기</router-link
        >
      </div>
      <div class="search-container">
        <div class="group">
          <input
            v-model="searchQuery"
            class="input"
            type="search"
            placeholder="FITTY 이름 또는 사용자 ID 검색"
          />
          <button @click="searchFitty">검색</button>
        </div>
      </div>
    </div>
    <hr />
    <div class="card-container">
      <div v-for="fitty in filteredFittyList" :key="fitty.userId" class="card">
        <p class="time-text">{{ fitty.status }} / 6</p>

        <p class="day-text">
          <strong>{{ fitty.fittyName }}</strong>
        </p>
        <div class="day-list">
          <label for=""><strong>Fitty Leader </strong></label>
          {{ fitty.userId }}
        </div>
        <div class="day-list">
          <label for=""><strong>Fitty Object </strong></label>
          {{ fitty.goal }}일
        </div>

        <!-- <button @click="applyForFitty(fitty)">가입신청</button> -->
        <div class="button" @click="applyForFitty(fitty)">
          <div class="svg-wrapper-1">
            <div class="svg-wrapper">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 24 24"
                width="24"
                height="24"
              >
                <path fill="none" d="M0 0h24v24H0z"></path>
                <path
                  fill="currentColor"
                  d="M1.946 9.315c-.522-.174-.527-.455.01-.634l19.087-6.362c.529-.176.832.12.684.638l-5.454 19.086c-.15.529-.455.547-.679.045L12 14l6-8-8 6-8.054-2.685z"
                ></path>
              </svg>
            </div>
          </div>
          <span>가입 신청</span>
        </div>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="1em"
          height="1em"
          viewBox="0 0 16 16"
          stroke-width="0"
          fill="currentColor"
          stroke="currentColor"
          class="moon"
        >
          <path
            d="M6 .278a.768.768 0 0 1 .08.858 7.208 7.208 0 0 0-.878 3.46c0 4.021 3.278 7.277 7.318 7.277.527 0 1.04-.055 1.533-.16a.787.787 0 0 1 .81.316.733.733 0 0 1-.031.893A8.349 8.349 0 0 1 8.344 16C3.734 16 0 12.286 0 7.71 0 4.266 2.114 1.312 5.124.06A.752.752 0 0 1 6 .278z"
          ></path>
          <path
            d="M10.794 3.148a.217.217 0 0 1 .412 0l.387 1.162c.173.518.579.924 1.097 1.097l1.162.387a.217.217 0 0 1 0 .412l-1.162.387a1.734 1.734 0 0 0-1.097 1.097l-.387 1.162a.217.217 0 0 1-.412 0l-.387-1.162A1.734 1.734 0 0 0 9.31 6.593l-1.162-.387a.217.217 0 0 1 0-.412l1.162-.387a1.734 1.734 0 0 0 1.097-1.097l.387-1.162zM13.863.099a.145.145 0 0 1 .274 0l.258.774c.115.346.386.617.732.732l.774.258a.145.145 0 0 1 0 .274l-.774.258a1.156 1.156 0 0 0-.732.732l-.258.774a.145.145 0 0 1-.274 0l-.258-.774a1.156 1.156 0 0 0-.732-.732l-.774-.258a.145.145 0 0 1 0-.274l.774-.258c.346-.115.617-.386.732-.732L13.863.1z"
          ></path>
        </svg>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref, computed } from "vue";
import { useRouter } from "vue-router";
import { useFittyStore } from "@/stores/fittyStore";
import { useUserStore } from "@/stores/userStore";
import LOGO from "@/assets/LOGO.png";

const logoSrc = LOGO;
const router = useRouter();
const store = useFittyStore();
const searchQuery = ref("");
const userStore = useUserStore();
// 데이터가 변경되면 자동으로 업데이트되도록 computed 사용
const fittyList = computed(() => store.fittyList);

// 검색어에 따라 필터링된 fittyList
const filteredFittyList = computed(() => {
  const query = searchQuery.value.toLowerCase();
  if (!query) {
    return fittyList.value;
  }
  return fittyList.value.filter(
    (fitty) =>
      fitty.fittyName.toLowerCase().includes(query) ||
      fitty.userId.toLowerCase().includes(query)
  );
});

// 가입 신청 함수
const applyForFitty = (fitty) => {
  if (!store.fittyNameData || store.fittyNameData.trim() === "") {
    // 가입 신청 함수 호출
    const newFitty = {
      userId: userStore.userRank.userId,
      fittyName: fitty.fittyName,
      status: "guest",
      goal: fitty.goal,
      isOpen: fitty.isOpen,
    };

    store.applyFitty(newFitty);
    // 가입신청 후 라우터 링크로 이동
    router.push({ name: "fittyStatus" });
  } else {
    // 이미 가입된 파티가 있을 경우 경고 메시지 표시
    alert("이미 가입된 파티가 있습니다");
    // 라우터 링크로 이동
    router.push({ name: "fittyStatus" });
  }
};

// 컴포넌트가 마운트될 때 데이터를 불러오기 위해 onMounted 사용
onMounted(() => {
  store.getFittyList();
  store.getFittyData();
});

// // container의 동적 스타일 설정
// const containerStyle = computed(() => ({
//   background: `
//     linear-gradient(to right, rgb(20, 30, 48), rgb(36, 59, 85)),
//     url(${LOGO})
//   `,
//   backgroundSize: 'cover',
//   backgroundPosition: 'center'
// }));
</script>

<style scoped>
.container {
  padding: 20px;
}

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
  font-size: 2.5rem;
  font-weight: bold;
  color: #0c2c85;
  text-align: left;
  margin-left: 13px;
  animation: fadeIn 1s ease-out;
}

.header-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.button-container {
  display: flex;
  margin-bottom: 0; /* header-container에서 이미 margin-bottom을 사용하므로 여기서는 필요 없음 */
}

.button-link {
  padding: 10px 20px;
  background-color: rgb(13, 30, 80);
  color: white;
  text-decoration: none;
  border-radius: 5px;
  margin-left: 15px;
}

.button {
  margin-left: 10px;
  margin-top: 10px;
  font-family: inherit;
  font-size: 15px;
  /* background: rgb(13, 30, 80); */
  background: rgb(148, 150, 184);
  color: white;
  padding: 0.4em 0.7em;
  padding-left: 0.1em;
  display: flex;
  align-items: center;
  border: none;
  border-radius: 16px;
  overflow: hidden;
  transition: all 0.2s;
  cursor: pointer;
  width: 250px;
  padding-right: 0.2em;
}

.button span {
  display: block;
  margin-left: 1em;
  transition: all 0.3s ease-in-out;
}

.button svg {
  margin-left: 5px;
  display: block;
  transform-origin: center center;
  transition: transform 0.3s ease-in-out;
}

.button:hover .svg-wrapper {
  animation: fly-1 0.6s ease-in-out infinite alternate;
}

.button:hover svg {
  transform: translateX(1.2em) rotate(45deg) scale(1.1);
}

.button:hover span {
  transform: translateX(4em);
}

.button:active {
  transform: scale(0.95);
}

@keyframes fly-1 {
  from {
    transform: translateY(0.1em);
  }

  to {
    transform: translateY(-0.1em);
  }
}

.search-container {
  display: flex;
  align-items: center;
}

.search-container input {
  padding: 10px;
  margin-right: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  width: 500px;
  height: 46px;
  text-align: center;
}

.search-container button {
  padding: 10px 20px;
  background-color: rgb(13, 30, 80);
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  width: 90px;
  height: 46px;
}

.group {
  display: flex;
  line-height: 28px;
  align-items: center;
  position: relative;
}

.input {
  width: 190%;
  height: 40px;
  line-height: 28px;
  padding: 0 1rem;
  padding-left: 2.5rem;
  border: 2px solid transparent;
  border-radius: 8px;
  outline: none;
  background-color: #f3f3f4;
  color: #0d0c22;
  transition: 0.3s ease;
}

.input::placeholder {
  color: #9e9ea7;
  text-align: center;
}

.input:focus,
.input:hover {
  outline: none;
  border-color: rgba(0, 48, 73, 0.4);
  background-color: #fff;
  box-shadow: 0 0 0 4px rgb(0 48 73 / 10%);
}

.icon {
  position: absolute;
  left: 1rem;
  fill: #9e9ea7;
  width: 1rem;
  height: 1rem;
}

.card-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 20px;
}

.card {
  width: 280px;
  height: 190px;
  background: linear-gradient(
    to bottom,
    rgb(255, 255, 255),
    rgb(243, 243, 250)
  );
  border-radius: 15px;
  box-shadow: rgb(225, 212, 212) 0.7px 0.9px 0.7px,
    rgb(166, 203, 255) -1px 0px 1px;
  color: rgb(5, 47, 131);
  cursor: pointer;
  display: flex;
  flex-direction: column;
  justify-content: center;
  position: relative;
  transition: all 0.3s ease;
}

.card:hover {
  box-shadow: rgba(159, 184, 247, 0.928) 5px 10px 20px,
    rgba(159, 184, 247, 0.928) -5px 0px 20px;
}

.time-text {
  font-size: 20px;
  margin-top: 0px;
  margin-left: 15px;
  font-weight: 600;
}

.time-sub-text {
  font-size: 15px;
  margin-left: 5px;
}

.day-text {
  font-size: 18px;
  margin-top: -10px;
  margin-left: 15px;
}

.day-list {
  font-size: 13px;
  margin-left: 15px;
}

.day-list label {
  display: inline-block; /* 또는 block */
  width: 80px; /* 원하는 너비로 설정 */
  margin-right: 0px; /* label과 텍스트 사이의 간격 */
}

.moon {
  font-size: 20px;
  position: absolute;
  right: 15px;
  top: 15px;
  transition: all 0.3s ease-in-out;
}

.card:hover > .moon {
  font-size: 23px;
}
</style>
