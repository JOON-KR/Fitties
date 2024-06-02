<template>
  <div class="today-record-container">
    <!-- 오늘 기록 박스 -->
    <div class="today-record-box">
      <h2 class="today-recommend"><strong>오늘의 추천운동</strong></h2>
      <h3 class="routine">"{{ routine }}"</h3>
      <hr />
      <h2 class="recommend-routine"><strong>추천 루틴</strong></h2>
      <transition name="gpt-transition">
        <div class="gpt" v-if="answer">
          <div
            v-for="(token, index) in tokens"
            :key="index"
            class="gpt-content"
          >
            {{ token }}
          </div>
        </div>
      </transition>
      <hr />
      <div class="flex-container">
        <div class="flex-item">
          <label
            ><h5><strong>출석 시간</strong></h5></label
          >
        </div>
        <div class="flex-item">
          <span v-if="detail.startTime">{{
            formatDate(detail.startTime, "HH:mm:ss")
          }}</span>
          <span v-else>출석하지 않음</span>
        </div>
      </div>
      <div class="flex-container">
        <div class="flex-item">
          <label
            ><h5><strong>운동 부위</strong></h5></label
          >
        </div>
        <div class="flex-item">
          <span>{{ detail.fitPart }}</span>
        </div>
      </div>
      <div>
        <button @click="recordStartTime" class="record-button">출석</button>
        <button @click="registDetail(detail)" class="record-button">
          종료
        </button>
      </div>
      <div class="fit-part-buttons">
        <button @click="setFitPart('이두+등')" class="fit-part-button">
          이두+등
        </button>
        <button @click="setFitPart('삼두+가슴')" class="fit-part-button">
          삼두+가슴
        </button>
        <button @click="setFitPart('어깨+하체')" class="fit-part-button">
          어깨+하체
        </button>
        <button @click="setFitPart('하체+코어')" class="fit-part-button">
          하체+코어
        </button>
      </div>
    </div>

    <!-- 게시판 부분 -->
    <div class="record-board">
      <h2><strong>운동 기록</strong></h2>
      <table class="record-table">
        <thead>
          <tr>
            <th>운동일</th>
            <th>시작시간</th>
            <th>종료시간</th>
            <th>운동 부위</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(record, index) in detailList" :key="index">
            <td>{{ formatDate(record.startTime, "MM-dd") }}</td>
            <td>{{ formatDate(record.startTime, "HH:mm") }}</td>
            <td>{{ formatDate(record.endTime, "HH:mm") }}</td>
            <td>{{ record.fitPart }}</td>
            <!-- 숨겨진 num 속성 -->
            <input type="hidden" :value="record.num" />
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, defineProps, defineEmits, watch } from "vue";
import { useCalendarStore } from "@/stores/calendarStore";
import { useUserStore } from "@/stores/userStore";
import axios from "axios";

const store = useCalendarStore();
const userStore = useUserStore();
const answer = ref("");
const detailList = computed(() => store.detailList);
const routine = ref("");
const detail = ref({
  userId: userStore.userRank.userId,
  startTime: store.attendData,
  fitPart: "",
});
const tokens = ref([]);
const props = defineProps({
  attendData: Date,
});

const fitParts = ["이두+등", "삼두+가슴", "어깨+하체", "하체+코어"];

const formatDate = (dateString, format) => {
  const date = new Date(dateString);
  const options = {};

  if (format.includes("MM-dd")) {
    options.month = "2-digit";
    options.day = "2-digit";
  }

  if (format.includes("HH:mm")) {
    options.hour = "2-digit";
    options.minute = "2-digit";
    options.hour12 = false;
  }

  return date.toLocaleString("ko-KR", options);
};

// 출석 버튼 클릭 시 현재 시간을 startTime에 설정
const recordStartTime = () => {
  const currentTime = new Date();

  const clientTime = new Date(currentTime.getTime() + 9 * 60 * 60 * 1000);
  const formattedTime = clientTime.toISOString().slice(0, 19).replace("T", " ");

  detail.value.startTime = formattedTime;
  store.attendData = formattedTime;
};

const setFitPart = async (part) => {
  detail.value.fitPart = part;
  try {
    const response = await axios.get(
      `http://localhost:8080/gpt/routine/${part}`
    );
    answer.value = response.data.choices[0].message.content;
    tokens.value = answer.value.split("-");
    console.log("서버로부터의 응답:", response.data);
  } catch (error) {
    console.error("일정 등록 중 오류 발생:", error);
  }
};

// 등록 버튼 클릭 시 detail 객체를 store에 등록

const registDetail = async () => {
  try {
    console.log(detail);
    detail.userId = userStore.userRank.userId;
    await store.registDetail(detail.value);
    detail.value = {
      userId: "",
      startTime: "",
      fitPart: "",
    };
    await store.getDetailList();
    await store.getSchedule();
  } catch (error) {
    console.error("일정 등록 중 오류 발생:", error);
  }
};

// 각 값의 등장 횟수를 세어서 가장 적게 등장한 값을 반환하는 함수
const getMinOccurrenceValue = (arr) => {
  if (!arr || arr.length === 0) {
    console.warn("fitPartArray가 비어 있습니다.");
    return null;
  }

  const counts = {
    "이두+등": 0,
    "삼두+가슴": 0,
    "어깨+하체": 0,
    "하체+코어": 0,
  };

  arr.forEach((value) => {
    if (counts[value] !== undefined) {
      counts[value]++;
    }
  });

  let minCount = Infinity;
  let minValue = null;
  for (const [key, value] of Object.entries(counts)) {
    if (value < minCount) {
      minCount = value;
      minValue = key;
    }
  }

  return minValue;
};

// fitPartArray 업데이트 후 routine 값을 재계산하는 함수
const updateRoutine = () => {
  const fitPartArray = detailList.value.map((detail) => detail.fitPart);
  routine.value = getMinOccurrenceValue(fitPartArray);
};

// 컴포넌트가 마운트될 때 detailList를 가져옴
onMounted(async () => {
  await store.getDetailList();
  updateRoutine(); // 초기 로드 시에도 routine 값을 계산
});
</script>

<style scoped>
.today-record-container {
  display: grid;
  grid-template-columns: 1fr 2fr; /* 컬럼 비율 조정 */
  gap: 20px;
}

.today-recommend,
.recommend-routine,
.routine {
  text-align: center;
}

.gpt {
  width: 100%;
  overflow: hidden; /* 필요에 따라 오버플로우 처리 */
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1), 0px 1px 3px rgba(0, 0, 0, 0.08);
  border-radius: 10px;
  transition: height 0.5s ease, opacity 0.5s ease;
  height: auto;
  opacity: 1;
}

.gpt-content {
  margin: 15px;
}

.today-record-box {
  background: #f9f9f9;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.today-record-box h2 {
  color: #333;
  margin-bottom: 10px;
}

.today-record-box button {
  margin: 5px;
  padding: 10px;
  font-size: 16px;
  background-color: #0035a0;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.today-record-box button:hover {
  background-color: #2980b9;
}

.record-board {
  background: #f9f9f9;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.record-table {
  width: 100%;
  border-collapse: collapse;
}

.record-table th,
.record-table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

.record-table th {
  background-color: #f2f2f2;
}

.fit-part-buttons {
  margin-top: 10px;
}

.fit-part-buttons button {
  margin: 2px;
  margin-left: 4.5px;
  padding: 5px;
  font-size: 16px;
  background-color: #0035a0;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.fit-part-button:hover {
  background-color: #0035a0;
}

.flex-container {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.flex-item {
  flex: 1;
}

.flex-item span {
  font-size: 18px;
  margin-bottom: 5px;
}

.flex-item:first-child {
  margin-right: 10px;
}

.gpt-transition-enter-active,
.gpt-transition-leave-active {
  transition: max-height 0.5s ease, opacity 0.5s ease;
}

.gpt-transition-enter-from,
.gpt-transition-leave-to {
  max-height: 0;
  opacity: 0;
}

.gpt-transition-enter-to,
.gpt-transition-leave-from {
  max-height: 500px; /* 필요에 따라 조정 */
  opacity: 1;
}
</style>
