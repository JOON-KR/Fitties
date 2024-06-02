<template :style="matchingContainerStyle">
  <div
    class="matching-container"
    ref="containerRef"
    @mousemove="handleMouseMove"
  >
    <h1 class="title-animated">나와 비슷한 FITTY를 만나보세요!</h1>
    <button @click="startMatching" :disabled="isLoading" class="button-submit">
      {{ matchingText }}
    </button>
    <!-- <button @click="startMatching" :disabled="isLoading">{{ matchedText }}</button> -->
    <!-- <button @click="startMatching" :disabled="isLoading" class="button-submit">매칭 하기?</button> -->
    <div v-if="isLoading"></div>
    <table v-else class="rank-table">
      <tbody>
        <tr
          v-for="(item, index) in RankList"
          :key="item.userId"
          class="rank-item"
          @mouseover="showData(item.userId)"
          @mouseleave="hideData"
        >
          <td>{{ item.userId }}</td>
          <td>{{ item.tier }}</td>
          <td>{{ item.level }}</td>
          <td>{{ item.streak }}</td>
        </tr>
      </tbody>
    </table>
    <!-- 추가 정보 화면 -->
    <article
      v-if="showAdditionalData"
      class="card additional-data"
      @mousemove="hideData"
    >
      <div class="card-img" style="text-align: center">
        <div class="card-imgs pv delete">
          <img :src="`../src/assets/${additionalData.image}`" alt="이미지" />
        </div>
      </div>

      <div class="project-info">
        <div class="flex">
          <br />
        </div>
        <table class="table">
          <tbody>
            <tr>
              <td
                style="
                  background-image: linear-gradient(to left, #bbbbc0, #061850);
                  color: white;
                "
              >
                Gender
              </td>
              <td>{{ additionalData.gender }}</td>
            </tr>
            <tr>
              <td
                style="
                  background-image: linear-gradient(to left, #bbbbc0, #061850);
                  color: white;
                "
              >
                Muscle
              </td>
              <td>{{ additionalData.muscle.toFixed(2) }}</td>
            </tr>
            <tr>
              <td
                style="
                  background-image: linear-gradient(to left, #bbbbc0, #061850);
                  color: white;
                "
              >
                Fat
              </td>
              <td>{{ additionalData.fat.toFixed(2) }}</td>
            </tr>
            <tr>
              <td
                style="
                  background-image: linear-gradient(to left, #bbbbc0, #061850);
                  color: white;
                "
              >
                BMI
              </td>
              <td>{{ additionalData.bmi.toFixed(2) }}</td>
            </tr>
            <tr>
              <td
                style="
                  background-image: linear-gradient(to left, #bbbbc0, #061850);
                  color: white;
                "
              >
                Fat Percentage (%)
              </td>
              <td>{{ additionalData.fatPer.toFixed(2) }}</td>
            </tr>
            <tr>
              <td
                style="
                  background-image: linear-gradient(to left, #bbbbc0, #061850);
                  color: white;
                "
              >
                Weight
              </td>
              <td>{{ additionalData.weight.toFixed(2) }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </article>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useUserStore } from "@/stores/userStore";
import { useCalendarStore } from "@/stores/calendarStore";
import { useFittyStore } from "@/stores/fittyStore";
import USER from "@/assets/user.png";
import axios from "axios";
import { useRouter } from "vue-router";
const userSrc = USER;
const calStore = useCalendarStore();
const matchingText = ref("매칭 시작하기");
const isLoading = ref(false);
const RankList = ref([]);
const store = useUserStore();
const showAdditionalData = ref(false);
const additionalData = ref(null);
const containerRef = ref(null);
const router = useRouter();
const fittyStore = useFittyStore();
const answer = ref("");
onMounted(() => {
  store.getSimilar();
});
const handleMouseMove = (event) => {
  const container = containerRef.value;
  const additionalDataElem = container.querySelector(".additional-data");

  if (!additionalDataElem) return;

  const containerRect = container.getBoundingClientRect();
  const mouseX = event.clientX;
  const mouseY = event.clientY;

  additionalDataElem.style.left = mouseX - containerRect.left - 720 + "px";
  additionalDataElem.style.top = mouseY - containerRect.top - 800 + "px";
};

const startMatching = async () => {
  if (matchingText.value === "Create FITTIES") {
    const today = new Date();
    const thirtyDaysLater = new Date(today);
    thirtyDaysLater.setDate(thirtyDaysLater.getDate() + 30);
    try {
      const response = await axios.get(`http://localhost:8080/gpt/fittyName/4`);
      answer.value = response.data.choices[0].message.content;

      console.log("서버로부터의 응답:", response.data);
    } catch (error) {
      console.error("일정 등록 중 오류 발생:", error);
    }
    const newSchedule = {
      userId: store.userRank.userId,
      endDate: thirtyDaysLater.toISOString().slice(0, 19).replace("T", " "),
      goal: 20,
    };
    const newMyFitty = {
      userId: store.userRank.userId,
      fittyName: answer.value,
      status: "member",
      is_open: 0,
      goal: 20,
    };
    calStore.registSchedule(newSchedule);
    fittyStore.addFitty(newMyFitty);
    for (const user of RankList.value) {
      console.log(user.userId);

      const newFitty = {
        userId: user.userId,
        fittyName: answer.value,
        status: "member",
        is_open: 0,
        goal: 20,
      };
      fittyStore.addFitty(newFitty);
    }

    matchingText.value = "Crete Success!!!";
    setTimeout(async () => {}, 3000);
    matchingText.value = "Go To Schedule!";
    return;
  }
  if (matchingText.value === "Go To Schedule!") {
    router.push({ name: "schedule" });
    return;
  }

  isLoading.value = true;
  matchingText.value = "매칭 중...";
  setTimeout(async () => {
    try {
      store.getSimilar();
      RankList.value = await Promise.all(
        store.similarProfiles.map(async (item) => {
          const userRank = await store.getUserRanks(item.userId);
          return { ...userRank, userId: item.userId };
        })
      );
    } catch (error) {
      console.error("데이터를 불러오는 중 오류가 발생했습니다:", error);
    } finally {
      isLoading.value = false;
      matchingText.value = "Create FITTIES";
    }
  }, 3000); // 3초 대기
};

const showData = async (userId) => {
  additionalData.value = await store.similarProfiles.find(
    (item) => item.userId === userId
  );
  showAdditionalData.value = true;
};

const hideData = () => {
  showAdditionalData.value = false;
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
  margin-top: 30px;
  font-size: 2.5rem;
  font-weight: bold;
  color: rgb(13, 30, 80);
  text-align: center;
  margin-bottom: 20px;
  animation: fadeIn 1s ease-out;
}

.matching-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  padding: 20px;
  margin-bottom: 27rem;
}

.rank-table {
  border-collapse: collapse;
  width: 70%;
  margin-right: 20px; /* 테이블과 .additional-data 사이의 간격을 설정합니다. */
  margin-top: 70px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  overflow: hidden;
}

/* 테이블 셀 스타일 */
td {
  border: 1px solid #ddd; /* 테두리 스타일 지정 */
  padding: 12px; /* 셀 안의 여백 조정 */
  text-align: center; /* 셀 안의 텍스트 정렬 방식 */
}

/* 마우스를 올리면 배경색 변경 */
.rank-item:hover {
  background-color: #ffffff;
  background-image: linear-gradient(to right, #bbbbc0, #061850);
  color: white;
  cursor: pointer;
  opacity: 0.85;
}

.button-submit {
  width: 200px;
  padding: 12px;
  border: none;
  border-radius: 4px;
  background-color: rgb(13, 30, 80);
  color: white;
  font-size: 16px;
  cursor: pointer;
  margin-top: 20px;
  transition: background-color 0.3s ease;
}

.button-submit:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
}

.button-submit:hover {
  background-color: #6173af;
}

.card {
  position: relative;
  width: 300px;
  max-height: 500px;
  border-radius: 8px;
  box-shadow: rgb(169, 171, 178) 0px 50px 100px -20px,
    rgba(0, 0, 0, 0.3) 0px 30px 60px -30px;
  /* background-color: #ffffff; */
}

.card .additional-data {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  width: 80%;
}

.card-imgs img {
  border-radius: 100%;
  width: 100px;
}

.project-title {
  font-weight: 500;
  font-size: 1.5em;
  color: black;
  text-align: center;
  /* background-color: #ffffff; */
}

.project-info {
  display: flex;
  flex-direction: column;
  margin-top: 10px;
}

/* 
.profile{
  display: table;
  width: 100%;
  flex-wrap: wrap;
} */

/* .profile p {
  display: table-cell;
  padding: 5px; 
} */

.table {
  margin-top: 10px;
  box-shadow: rgb(169, 171, 178) 2px 2px 25px;
}

.flex {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.lighter {
  font-size: 0.9em;
  color: black;
}

.tag {
  font-weight: lighter;
  color: grey;
}

.card-img {
  position: relative;
  top: -10px;
  height: 100px;
  display: flex;
  justify-content: center;
  /* background-color: #ffffff; */
}

.card-img div {
  width: 90%;
  height: 150px;
  object-fit: cover;
  border-radius: 8px;
  /* background-color: #ffffff; */
  box-shadow: rgb(169, 171, 178) 0.5px 0.5px 25px;
}

.card-imgs {
  transition: all 0.5s;
}

.card-img img {
  display: inline-flex;
  width: 7em;
}

/* .additional-data {
  margin-top: 20px;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #fff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  width: 20%;
 } */

/* 나머지 스타일은 여기에 있습니다. */
.additional-data {
  margin-top: 20px;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #ffffff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  width: 20%;
  position: relative;
  /* 부모 요소에 상대적으로 위치 */
  z-index: 1;
}

.additional-data h2 {
  margin-bottom: 10px;
}

.additional-data p {
  margin: 5px 0;
}
</style>
