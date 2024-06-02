import { defineStore } from "pinia";
import { ref } from "vue";
import axios from "axios";
import router from "@/router";


const REST_BASE_API = `http://localhost:8080`;
export const useCalendarStore = defineStore("calendar", () => {
  const schedule = ref("");
  const token = ref('');
  const detailList = ref();
  const attendData = ref();
  const userToken = localStorage.getItem('user');
if (userToken !== null) {
    token.value = JSON.parse(userToken).token;
} else {
    // localStorage에 'user' 키가 없거나 값이 null인 경우에 대한 처리
    // 예를 들어, 기본값을 설정하거나 에러를 처리할 수 있습니다.
}

  // Axios 인스턴스 생성
  const axiosInstance = axios.create({
    baseURL: REST_BASE_API,
  });

  // Axios 인터셉터 추가
  axiosInstance.interceptors.request.use(config => {
    const tmp = JSON.parse(localStorage.getItem('user')).token;
      config.headers.Authorization = `Bearer ${tmp}`;
    console.log(tmp);
    return config;
  });
  //목표 겟
  const getSchedule = async function () {
    try {
      const response = await axiosInstance.get("/schedule");
      schedule.value = response.data;
    } catch (error) {
      console.error("스케줄을 가져오는 중 오류 발생:", error);
    }
  };
  


  //목표생성
  const registSchedule = async function (schedule) {
    try {
        await axiosInstance({
            url: '/schedule',
            method: 'POST',
            data: schedule,
        })
        .then(async () => {
            await getSchedule();
        });
    } catch (error) {
        console.error('일정 생성 중 오류 발생:', error);
    }
}

const deleteSchedule = async function () {
    try {
        await axiosInstance.delete("/schedule")
        .then(async () => {
            await getSchedule();
            location.reload();
            router.push({ name: 'calendar' });
        });
    } catch (error) {
        console.error("일정 삭제 중 오류 발생:", error);
    }
};
const getDetailList = async function () {
  try {
      await axiosInstance.get("/calendarDetail")
      .then(async (response) => {
        console.log(response.data);
        detailList.value = response.data;
      });
  } catch (error) {
      console.error("캘린더 리스트 받아오는 중 오류 발생:", error);
  }
};
const registDetail = async function (detail) {
  try {
    console.log(detail);
    await axiosInstance({
      url: '/calendarDetail',
      method: 'POST',
      data: detail,
  })
      .then(async (response) => {
        console.log(response.data);
        getDetailList();
      });
    } catch (error) {
      console.error("캘린더등록 중 오류 발생:", error);
    }
  };

  return {
    schedule,
    getSchedule,
    registSchedule,
    deleteSchedule,
    getDetailList,
    detailList,
    registDetail,
    attendData
  };
}, { persist: true });
