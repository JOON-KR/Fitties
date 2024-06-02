<template>
  <div class="schedule-container">
    <h2 class="title"><strong>목표 정보</strong></h2>
    <label class="switch">
      <input class="cb" type="checkbox" @change="toggleVisibility" :checked="isVisible" />
      <span class="toggle">
        <span class="left">off</span>
        <span class="right">on</span>
      </span>
    </label>
    <template v-if="schedule">
      <div class="schedule-content">
        <div class="schedule-info">
          <vue-cal
            :events="events"
            :time="isVisible"
            default-view="month"
            class="calendar"
            @event-render="eventRender"
          />
          <p><strong>목표:</strong> {{ schedule.goal }}</p>
          <p><strong>현재:</strong> {{ schedule.current }}</p>
          <p>
            <strong>달성률:</strong>
            <span :style="percentageStyle">{{ percentage }}%</span>
          </p>
          <button @click="deleteSchedule" class="delete-button">
            스케줄 포기하기
          </button>
        </div>
        <div class="details-board">
          <Detail />
        </div>
      </div>
    </template>
    <template v-else>
      <div>
        <CreateSchedule />
      </div>
    </template>
  </div>
  <div class="fitty-list-container">
    <h3 class="subtitle"><strong>My Fitty List</strong></h3>
    <div class="fitty-list">
      <div class="banner1">
        <div
          v-for="(fitty, index) in fittyList"
          :key="fitty.userId"
          class="fitty-item"
        >
          <div class="banner2-2">
            <div
              class="donut"
              :class="getDonutStyleClass(index)"
              :style="{ '--percent': ((fitty.current / fitty.goal) * 100) + '%' }"
              :data-percent="((fitty.current / fitty.goal) * 100).toFixed(2)"
              ref="donutElement"
            ></div>
          </div>
          <br />
          <p><strong>달성률</strong> {{ (fitty.current / fitty.goal * 100).toFixed(2) }}%</p>
        </div>
      </div>
    </div>
  </div>
</template>


<script setup>
import { ref, onMounted, computed, watch } from "vue";
import { useCalendarStore } from "@/stores/calendarStore";
import { useUserStore } from "@/stores/userStore";
import { useFittyStore } from "@/stores/fittyStore";
import CreateSchedule from "@/components/Calendar/CreateSchedule.vue";
import VueCal from "vue-cal";
import Detail from "@/components/Calendar/Detail.vue";
import "vue-cal/dist/vuecal.css";

const fittyStore = useFittyStore();
const isVisible = ref(false);
const achievedDates = ref([]);
const toggleVisibility = () => {
  isVisible.value = !isVisible.value;
};

const store = useCalendarStore();
const userStore = useUserStore();
const schedule = ref(null);

const fittyList = computed(() => fittyStore.myFitty);

onMounted(async () => {
  await store.getSchedule();
  schedule.value = store.schedule;
  await fittyStore.myFittyList();
});

const percentage = computed(() => {
  if (schedule.value && schedule.value.goal) {
    return ((schedule.value.current / schedule.value.goal) * 100).toFixed(2);
  }
  return 0;
});

const percentageStyle = computed(() => {
  const percent = parseFloat(percentage.value);
  if (percent >= 75) return { color: "green" };
  if (percent >= 50) return { color: "orange" };
  return { color: "red" };
});

const events = computed(() => {
  if (schedule.value) {
    return [
      {
        start: new Date(schedule.value.startDate),
        end: new Date(schedule.value.endDate),
        title: "스케쥴 목표",
        content: `목표: ${schedule.value.goal}, 현재: ${schedule.value.current}`,
      },
    ];
  }
  return [];
});

function calculateAchievedDates() {
  achievedDates.value = [];
  if (schedule.value && schedule.value.current >= schedule.value.goal) {
    const startDate = new Date(schedule.value.startDate);
    const endDate = new Date(schedule.value.endDate);
    const currentDate = new Date(startDate);
    while (currentDate <= endDate) {
      achievedDates.value.push(currentDate.toISOString().split('T')[0]);
      currentDate.setDate(currentDate.getDate() + 1);
    }
  }

  finishSchedule();
}

function finishSchedule() {
  calculateAchievedDates();

  const eventsEl = document.querySelectorAll('.vuecal__day-event');

  eventsEl.forEach(eventEl => {
    const eventDate = eventEl.dataset.date;

    if (achievedDates.value.includes(eventDate)) {
      eventEl.style.backgroundColor = '#3498db';
      eventEl.style.color = 'white';
    }
  });
}

function eventRender({ event, el }) {
  if (achievedDates.value.includes(event.start.toISOString().split('T')[0])) {
    el.classList.add('achieved');
  }
}

function deleteSchedule() {
  store.deleteSchedule();
  schedule.value = null;
}

watch(
  () => store.schedule,
  (newValue) => {
    schedule.value = newValue;
  }
);

const getDonutStyleClass = (index) => {
  const classes = ['donutStyle1', 'donutStyle2', 'donutStyle3', 'donutStyle4', 'donutStyle5'];
  return classes[index % classes.length];
};
</script>


<style scoped>
/* The switch - the box around the slider */
.switch {
  font-size: 17px;
  position: relative;
  display: inline-block;
  width: 5em;
  height: 2.5em;
  user-select: none;
  margin-bottom: 20px; /* 추가: 버튼과 아래 컨텐츠 사이의 여백 */
}

/* Hide default HTML checkbox */
.switch .cb {
  opacity: 0;
  width: 0;
  height: 0;
}

/* The slider */
.toggle {
  position: absolute;
  cursor: pointer;
  margin-left: 10px;
  width: 80%;
  height: 80%;
  background-color: #373737;
  border-radius: 0.1em;
  transition: 0.4s;
  text-transform: uppercase;
  font-weight: 700;
  overflow: hidden;
  box-shadow: -0.3em 0 0 0 #373737, -0.3em 0.3em 0 0 #373737,
    0.3em 0 0 0 #373737, 0.3em 0.3em 0 0 #373737, 0 0.3em 0 0 #373737;
}

.toggle > .left {
  position: absolute;
  display: flex;
  width: 50%;
  height: 88%;
  background-color: #f3f3f3;
  color: #373737;
  left: 0;
  bottom: 0;
  align-items: center;
  justify-content: center;
  transform-origin: right;
  transform: rotateX(10deg);
  transform-style: preserve-3d;
  transition: all 150ms;
}

.left::before {
  position: absolute;
  content: "";
  width: 100%;
  height: 100%;
  background-color: rgb(206, 206, 206);
  transform-origin: center left;
  transform: rotateY(90deg);
}

.left::after {
  position: absolute;
  content: "";
  width: 100%;
  height: 100%;
  background-color: rgb(112, 112, 112);
  transform-origin: center bottom;
  transform: rotateX(90deg);
}

.toggle > .right {
  position: absolute;
  display: flex;
  width: 50%;
  height: 88%;
  background-color: #f3f3f3;
  color: rgb(206, 206, 206);
  right: 1px;
  bottom: 0;
  align-items: center;
  justify-content: center;
  transform-origin: left;
  transform: rotateX(10deg) rotateY(-45deg);
  transform-style: preserve-3d;
  transition: all 150ms;
}

.right::before {
  position: absolute;
  content: "";
  width: 100%;
  height: 100%;
  background-color: rgb(206, 206, 206);
  transform-origin: center right;
  transform: rotateY(-90deg);
}

.right::after {
  position: absolute;
  content: "";
  width: 100%;
  height: 100%;
  background-color: rgb(112, 112, 112);
  transform-origin: center bottom;
  transform: rotateX(90deg);
}

.switch input:checked + .toggle > .left {
  transform: rotateX(10deg) rotateY(45deg);
  color: rgb(206, 206, 206);
}

.switch input:checked + .toggle > .right {
  transform: rotateX(10deg) rotateY(0deg);
  color: #487bdb;
}

/* 기존 스타일 유지 */
.schedule-container {
  max-width: 1200px; /* 조정된 최대 너비 */
  margin: 0 auto;
  padding: 20px;
  background: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.title {
  text-align: center;
  font-size: 2rem;
  color: #333;
  margin-bottom: 20px;
}

.schedule-content {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.schedule-info {
  background: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.details-board {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.schedule-info p {
  font-size: 1.2rem;
  margin: 10px 0;
}

.schedule-info strong {
  color: #555;
}

.delete-button {
  display: block;
  width: 100%;
  padding: 10px;
  border: none;
  background: #0035a0;
  color: white;
  font-size: 1rem;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 20px;
}

.delete-button:hover {
  background: #dadeed;
}

.calendar {
  margin-top: 20px;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

/* VueCal Custom Styles */
.vuecal {
  font-family: "Arial", sans-serif;
  border-radius: 8px;
}

div.vuecal__title-bar {
  background-color: #3498db; /* 타이틀 바 배경색 */
}

.vuecal__cell {
  border: 1px solid #e0e0e0; /* 셀 테두리 */
}

.vuecal__cell--head {
  background-color: #f7f7f7; /* 헤더 셀 배경색 */
}

.vuecal__event {
  background-color: #3498db; /* 이벤트 배경색 */
  color: white; /* 이벤트 텍스트 색상 */
  border-radius: 4px; /* 이벤트 테두리 반경 */
  padding: 2px 5px; /* 이벤트 내부 여백 */
}

.vuecal__event.achieved {
  background-color: #4caf50; /* 달성한 날짜의 배경색 */
  border-color: #4caf50; /* 달성한 날짜의 테두리 색상 */
}

.vuecal__event--start {
  border-top-left-radius: 4px; /* 이벤트 시작 부분의 테두리 반경 */
  border-top-right-radius: 4px;
}

.vuecal__event--end {
  border-bottom-left-radius: 4px; /* 이벤트 종료 부분의 테두리 반경 */
  border-bottom-right-radius: 4px;
}

.vuecal__event-title {
  font-weight: bold; /* 이벤트 제목의 글꼴 굵기 */
}



.banner1 {
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  display: flex;
  flex-direction: row; /* Change from column to row */
  justify-content: center;
  align-items: center;
  width: 100%; /* Adjust width to fit content */
  height: auto; /* Adjust height to fit content */
  gap:100px;
  margin-bottom: 50px;
}
.subtitle {
  text-align: center; 
  margin-top: 30px; /* 위쪽 간격 */
  margin-bottom: 50px; /* 아래쪽 간격 */
}


.banner2-2 {
  display: flex;
  justify-content: center;
  align-items: center;
}
.donut {
  width: calc(100% - 20px);
  padding-bottom: calc(100% - 20px);
  margin: 0 auto;
  border-radius: 50%;
  position: relative;
  text-align: center;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.3), 0px 1px 3px rgba(0, 0, 0, 0.08);
}

.donut::before {
  content: attr(data-percent) "%";
  color: #0d155c;
  width: 70%;
  height: 70%;
  background: #ffffff;
  border-radius: 50%;
  position: absolute;
  left: 15%;
  top: 15%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 2.35vw;
  font-weight: bold;
  box-shadow: 0px 0px 6px rgba(0, 0, 0, 0.3), 0px 1px 3px rgba(0, 0, 0, 0.08);
}

.donutStyle1 {
  background: conic-gradient(#4F98FF 0% var(--percent), #DEDEDE var(--percent) 100%);
}

.donutStyle2 {
  background: conic-gradient(#FF4F4F 0% var(--percent), #DEDEDE var(--percent) 100%);
}

.donutStyle3 {
  background: conic-gradient(#4FFF4F 0% var(--percent), #DEDEDE var(--percent) 100%);
}

.donutStyle4 {
  background: conic-gradient(#FF4FFF 0% var(--percent), #DEDEDE var(--percent) 100%);
}

.donutStyle5 {
  background: conic-gradient(#FFFF4F 0% var(--percent), #DEDEDE var(--percent) 100%);
}



</style>
