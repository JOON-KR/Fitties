import { defineStore } from "pinia";
import { isRuntimeOnly, ref } from "vue";
import axios from "axios";
import router from "@/router";

const REST_BASE_API = `http://localhost:8080`;
const REST_FITTY_API = `http://localhost:8080/fitty`;

export const useFittyStore = defineStore("fitty", () => {
  const token = ref();
  const fittyData = ref(null);
  const fittyList = ref([]);
  const guest = ref([]);
  const member = ref([]);
  const userToken = localStorage.getItem('user');
  const myFitty = ref([]);
if (userToken !== null) {
    token.value = JSON.parse(userToken).token;
} else {
    // localStorage에 'user' 키가 없거나 값이 null인 경우에 대한 처리
    // 예를 들어, 기본값을 설정하거나 에러를 처리할 수 있습니다.
}


    // 로컬 스토리지의 토큰을 감시하고 변경이 있을 때마다 스토어의 토큰을 업데이트
    // watchEffect(() => {
    //   token.value = localStorage.getItem("token");
    // });

  // Axios 인스턴스 생성
  const axiosInstance = axios.create({
    baseURL: REST_FITTY_API,
  });

  // Axios 인터셉터 추가
  axiosInstance.interceptors.request.use(config => {
    token.value = JSON.parse(localStorage.getItem('user')).token;
      config.headers.Authorization = `Bearer ${token.value}`;
    
    return config;
  });
  const createFitty = function (fitty) {
    console.log(fitty);
    axiosInstance({
      url: REST_FITTY_API,
      method: "POST",
      data: fitty,
    })
      .then(() => {
        router.push({ name: "fittyList" });
      })
      .catch((err) => {
        console.log(err);
      });
    };
    
    // 모든 Fitty 정보 가져오기 (name을 동적으로 받아옴)
    const getFittyList = async function () {
      try {
        const response = await axiosInstance.get(`/all`);
      fittyList.value = response.data;
    } catch (error) {
      console.error("모든 Fitty 정보를 가져오는 중 오류 발생:", error);
    }
  };
  
  // Fitty 정보 가져오기
  const getFittyData = async function () {
    try {
      const response = await axiosInstance.get();
      fittyData.value = response.data;
      console.log('응답 헤더:', response.headers);
      const user = JSON.parse(localStorage.getItem('user'));
      console.log(user.token);
      console.log(fittyData.value);
      localStorage.setItem('fittyNameData',response.data.fittyName);
    } catch (error) {
      console.error("FITTY 데이터를 가져오는 중 오류 발생:", error);
    }
  };
  
  // const myFitty = async function(){
  //   try{
  //     const response = await axiosInstance.get();
  //     fittyData.value = response.data;
  //   } catch(error){
  //     console.error("MY FITTY 데이터를 가져오는 중 오류 발생:", error);
  //   }
  // }

  // Fitty 정보 수정
  const modifyFitty = async function (fitty) {
    try {
      
      await axiosInstance.put(fitty.userId);
      await getGuest(fitty.fittyName);
      await getMember(fitty.fittyName);
    } catch (error) {
      console.error("Fitty 정보를 수정하는 중 오류 발생:", error);
    }
  };

  // Fitty에서 탈퇴
  const quitFitty = async function () {
    try {
      await axiosInstance.delete();
      console.log("Fitty quit successfully");
    } catch (error) {
      console.error("Fitty에서 탈퇴 중 오류 발생:", error);
    }
  };

  // 특정 이름의 Fitty 삭제
  const removeFitty = async function (fittyName) {
    try {
      await axiosInstance.delete(`/clear/${fittyName}`);
      await getFittyData();
      localStorage.setItem("fittyNameData","");
      console.log("Fitty delete successfully");
    } catch (error) {
      console.error("Fitty 삭제 중 오류 발생:", error);
    }
  };

  const addFitty = async function (fitty){
    try{
      const fitParty = {
        userId : fitty.userId,
        fittyName : fitty.fittyName,
        status : fitty.status,
        goal : fitty.goal,
        isOpen : fitty.isOpen,
      };
      const response = await axiosInstance.post(`/add`, fitParty);
      fittyList.value.push(response.data);
      await getFittyList();
    } catch (error){
      console.error("파티 정보를 등록하는 중 오류 발생:", error);
    }
  }

  
  const getGuest = async function(fittyName){
    try{
      const response = await axiosInstance.get(`/guest/${fittyName}`);
      guest.value = response.data;      
      
    } catch(error) {
      console.error("파티 게스트 가져오다가 오류 발생:", error);
    }
  }
  
  const getMember = async function(fittyName){
    try{
      console.log(fittyName);
      const response = await axiosInstance.get(`/member/${fittyName}`);
      member.value = response.data;      
    } catch(error) {
      console.error("파티 멤버 가져오다가 오류 발생:", error);
    }
  }

  const kickFittyMember = async function(fitty){
    try{
      const response = await axiosInstance.delete(`/${fitty.userId}`);
      await getMember((localStorage.getItem('fittyNameData'))); 
      localStorage.setItem('fittyNameData',"");
      await getFittyData();  
      
    } catch(error) {
      console.error("파티 멤버 가져오다가 오류 발생:", error);
    }
  }

  const applyFitty = async function(fitty){
      try{
        const fitParty = {
          userId : fitty.userId,
          fittyName : fitty.fittyName,
          status : fitty.status,
          goal : fitty.goal,
          isOpen : fitty.isOpen,
        };
        console.log(fitParty);
        const response = await axiosInstance.post(``, fitParty);
        fittyList.value.push(response.data);
        getFittyData();
        await getFittyList();
      } catch {
        alert('이미 등록된 Fitty가 있습니다');
        console.error("파티 정보를 등록하는 중 오류 발생:", error);
      }
    }
  
    const myFittyList = async function(){
      try{

        const response = await axiosInstance.get(`/my`);
        myFitty.value = response.data;      
      } catch(error) {
        console.error("파티 멤버 가져오다가 오류 발생:", error);
      }
    }
  return {
    fittyData,
    fittyList,
    createFitty,
    getFittyList,
    getFittyData,
    quitFitty,
    removeFitty,
    modifyFitty,
    addFitty,
    kickFittyMember,
    removeFitty,
    guest,
    member,
    getMember,
    getGuest,
    // putUser,
    token,
    applyFitty,
    myFitty,
    myFittyList,
  };
}, { persist: true });
