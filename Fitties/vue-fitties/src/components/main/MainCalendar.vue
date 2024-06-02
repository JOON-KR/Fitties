<template>
  <div class="container">
    <section class="bannerTop">
      <div class="banner1">
        <div class="dayday">
          <strong>{{ getFormattedDate() }}</strong>
        </div>
        <div v-if="detail.startTime" class="timetime">
          <label for=""><strong>출석 시간:</strong> </label>
          {{ formatDate(attend, "HH:mm:ss") }}
        </div>
        <div v-else class="timetime2">
          <label for=""><strong>출석 시간:</strong></label>
          <div>미출석</div>
        </div>

        <div class="check">
          <button @click="recordStartTime" class="attendance">출석</button>
          <button @click="registDetail(detail)" class="attendance">종료</button>
        </div>
      </div>
      <div class="banner2">
        <div class="banner2-1">
          <div class="today">
            <h2><strong>Today</strong></h2>
            <br />
            <h3>
              <strong>{{ routine }} <br /></strong>
            </h3>
            <h5><strong>운동하기 좋은 날이에요</strong></h5>
          </div>
          <br />
          <li class="object">
            <strong><label for="">목표까지 남은 기간:</label></strong> D-{{
              daysToGoal
            }}
          </li>
          <li class="goal">
            <strong><label for="">달성률:</label></strong> {{ percentage }}%
          </li>
        </div>
        <div class="banner2-2">
          <div
            class="donut"
            :style="donutStyle"
            :data-percent="percentage"
            ref="donutElement"
          ></div>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref, computed, watch, onMounted } from "vue";
import { useCalendarStore } from "@/stores/calendarStore";
import { useUserStore } from "@/stores/userStore";
import tr from "vue-cal/dist/i18n/tr.cjs";

const store = useCalendarStore();
const userStore = useUserStore();
const answer = ref("");
const detailList = computed(() => store.detailList);
const routine = ref("");
const schedule = ref("");

const detail = ref({
  userId: null,
  startTime: null,
  fitPart: "",
});
const attend = computed(() => store.attendData);
onMounted(async () => {
  await userStore.getUserRank(); // userRank 데이터 가져오기
  await store.getDetailList();
  await store.getSchedule();

  detail.value.userId = userStore.userRank.userId; // userId 할당
  detail.value.startTime = store.attendData; // startTime 할당

  schedule.value = store.schedule;
  updateRoutine();
});

// 오늘
const getFormattedDate = () => {
  const daysOfWeek = [
    "일요일",
    "월요일",
    "화요일",
    "수요일",
    "목요일",
    "금요일",
    "토요일",
  ];
  const currentDate = new Date();

  const month = currentDate.getMonth() + 1; // 월은 0부터 시작하므로 +1
  const day = currentDate.getDate();
  const dayOfWeek = daysOfWeek[currentDate.getDay()];

  const formattedDate = `${month}월 ${day}일 ${dayOfWeek}`;
  return formattedDate;
};

// 목표까지 남은 기간 계산
const daysToGoal = computed(() => {
  if (schedule.value && schedule.value.endDate) {
    const today = new Date();
    const endDate = new Date(schedule.value.endDate);
    const timeDiff = endDate.getTime() - today.getTime();
    const daysDiff = Math.ceil(timeDiff / (1000 * 3600 * 24)); // milliseconds to days
    return daysDiff;
  }
  return null;
});

const percentage = computed(() => {
  if (schedule.value && schedule.value.goal) {
    return ((schedule.value.current / schedule.value.goal) * 100).toFixed(2);
  }
  return 0;
});

const fitParts = ["이두+등", "삼두+가슴", "어깨+하체", "하체+코어"];

// 날짜 형식 변환 함수
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

// 현재 시간을 포맷하여 반환하는 함수
const getCurrentTime = () => {
  return new Date().toISOString();
};
const attendData = ref(null);
// 출석 버튼 클릭 시 현재 시간을 startTime에 설정
const recordStartTime = () => {
  const currentTime = new Date();
  const clientTime = new Date(currentTime.getTime() + 9 * 60 * 60 * 1000);
  const formattedTime = clientTime.toISOString().slice(0, 19).replace("T", " ");

  detail.value.startTime = formattedTime;
  attendData.value = formattedTime;
  store.attendData = formattedTime;
  console.log(store.attendData);
};

// 등록 버튼 클릭 시 detail 객체를 store에 등록
const registDetail = async (detail) => {
  console.log(detail);
  try {
    detail.fitPart = routine.value;
    await store.registDetail(detail);
    detail.value = {
      userId: userStore.userRank.userId,
      startTime: null,
      fitPart: routine.value,
    };
    await store.getDetailList();
    await store.getSchedule();
    schedule.value = store.schedule;
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

// detailList가 업데이트될 때마다 fitPartArray 및 routine 값을 재계산
watch(detailList, (newDetailList) => {
  updateRoutine();
});

const donutStyle = computed(() => {
  return {
    background: `conic-gradient(#4F98FF 0% ${percentage.value}%, #DEDEDE ${percentage.value}% 100%)`,
    "--percent": `${percentage.value}%`,
  };
});
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-color: #ffffff; /* Optional: Add background color for better visibility */
}

.bannerTop {
  display: grid;
  grid-template-columns: 300px 1fr; /* Fixed width for banner1, rest for banner2 */
  grid-template-rows: 350px; /* Fixed height for both banners */
  /* gap: 10px; */
  width: 100%;
}

.banner1 {
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 300px;
  height: 350px;
}

.banner2 {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 30px;
  padding: 0px;
  background-color: white;
  border-radius: 8px;
  width: 100%;
  height: 100%;
}

.banner2-1 {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.banner2-2 {
  display: flex;
  justify-content: center;
  align-items: center;
}

.today h2 {
  font-weight: bold;
  background: #0c2c85;
  color: #ffffff;
  border-radius: 10px;
  text-align: center;
}

.today h3,
.today h5 {
  margin-left: 10px;
}

@keyframes fillDonut {
  0% {
    background: conic-gradient(#2a6ac5 0% 0%, #dedede 0% 0%);
  }
  to {
    background: conic-gradient(
      #4f98ff 0% var(--percent),
      #dedede var(--percent) 100%
    );
  }
}

.donut {
  width: calc(100% - 20px);
  padding-bottom: calc(100% - 20px);
  margin: 0 auto;
  border-radius: 50%;
  position: relative;
  text-align: center;
  /* background-color: #0d155c; */
  /* animation: fillDonut 2s ease-in-out forwards;  */
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.3), 0px 1px 3px rgba(0, 0, 0, 0.08);
}

.donut::before {
  color: #0d155c;
  box-shadow: 0px 0px 6px rgba(0, 0, 0, 0.3), 0px 1px 3px rgba(0, 0, 0, 0.08);
  width: 70%; /* 너비를 설정합니다 */
  height: 70%; /* 높이를 설정하여 정사각형을 만듭니다 */
  background: #ffffff;
  border-radius: 50%; /* 완전한 원을 만들기 위해 50%로 설정합니다 */
  position: absolute;
  left: 15%; /* 부모 요소의 가운데에 오도록 설정 */
  top: 15%; /* 부모 요소의 가운데에 오도록 설정 */
  display: flex; /* 가운데 정렬을 위해 flexbox 사용 */
  justify-content: center; /* 텍스트 수평 가운데 정렬 */
  align-items: center; /* 텍스트 수직 가운데 정렬 */
  content: attr(data-percent) "%";
  font-size: 2.35vw;
  font-weight: bold; /* 글씨를 두껍게 */
}

.object,
.goal {
  display: left;
  justify-content: flex-start;
}

.dayday {
  font-size: 52px;
  font-weight: bold;
}

.attendance {
  padding: 14px 20px; /* 패딩 크기 조정 */
  margin-left: 5px;
  background-color: #0c2c85;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1.4rem; /* 폰트 크기 조정 */
  /* font-weight: 570; */
}

.check {
  display: flex;
  justify-content: flex-start; /* 왼쪽 정렬 */
  align-items: left; /* 수직 가운데 정렬 */
  margin-top: 10px; /* 위쪽 여백 추가 */
  padding-right: 83px;
}

.timetime {
  font-size: 30px;
  display: flex;
  align-items: center; /* 수직 가운데 정렬 */
  margin-right: 30px; /* 오른쪽 여백 추가 */
}

.timetime2 {
  display: flex;
  align-items: center; /* 수직 가운데 정렬 */
  margin-right: 30px; /* 오른쪽 여백 추가 */
}

.timetime2 label {
  font-size: 30px;
  display: flex;
  align-items: center;
  margin-right: 10px;
}

.timetime2 div {
  font-size: 30px;
}

.timetime label strong {
  margin-right: 15px;
}
</style>
