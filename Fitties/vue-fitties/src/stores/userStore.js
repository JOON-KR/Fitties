import { defineStore } from "pinia";
import { ref } from "vue";
import axios from "axios";
import router from "@/router";

const REST_BASE_API = `http://localhost:8080`;
const REST_USER_API = `http://localhost:8080/user`;

export const useUserStore = defineStore("user", () => {
  const userData = ref("");
  const token = ref("");
  const userRank = ref("");
  const userProfile = ref("");
  const userAllProfiles = ref([]);
  const similarProfiles = ref([]);
  const rankList = ref([]);
  const tierData = ref("");
  // Axios 인스턴스 생성
  const login = ref(false);
  const axiosInstance = axios.create({
    baseURL: REST_BASE_API,
  });

  // Axios 인터셉터 추가
  axiosInstance.interceptors.request.use(config => {
    if (token.value) {
      config.headers.Authorization = `Bearer ${token.value}`;
    }
    return config;
  });

  // 회원가입
  const registUser = async function (user) {
    try {
      console.log(user);
      await axios.post(`${REST_USER_API}/regist`, user);
      router.push({ name: "login" });
    } catch (error) {
      alert("회원가입에 실패했습니다.");
    }
  };

  // 로그인 및 토큰 생성
  const loginUser = async function (id, password) {
    try {
      token.value = "";
      const user = {
        id: id,
        password: password,
      };
      const response = await axios.post(`${REST_USER_API}/login`, user);
      token.value = response.data.accessToken;
      await getUserData();
      await getUserRank();
      await getUserProfile();
      await getRankList();
      tierData.value = getTierGrade(userRank.tier);
    } catch (error) {
      alert("로그인에 실패했습니다.");
    }
  };
  const getTierGrade = (tier) => {
    if (tier >= 0 && tier < 500) return '아이언';
    else if (tier >= 500 && tier < 1000) return '브론즈';
    else if (tier >= 1000 && tier < 1500) return '실버';
    else if (tier >= 1500 && tier < 2000) return '골드';
    else if (tier >= 2000 && tier < 2500) return '플래티넘';
    else if (tier >= 2500 && tier < 3000) return '에매랄드';
    else if (tier >= 3000 && tier < 3500) return '다이아';
    else if (tier >= 3500 && tier < 4000) return '마스터';
    else if (tier >= 4000 && tier < 4500) return '그랜드마스터';
    else return '챌린저';
  };
  // 사용자 데이터 가져오기
  const getUserData = async function () {
    try {
      const response = await axiosInstance.get("/user");
      userData.value = response.data;
    } catch (error) {
      console.error("사용자 데이터를 가져오는 중 오류 발생:", error);
    }
  };

  // 랭크 정보 가져오기
  const getUserRank = async function () {
    try {
      const response = await axiosInstance.get(`/rank`);
      userRank.value = response.data;
    } catch (error) {
      console.error("랭크 정보를 가져오는 중 오류 발생:", error);
    }
  };

  const getRankList = async function () {
    try {
      const response = await axiosInstance.get(`/rank/all`);
      rankList.value = response.data;
    } catch (error) {
      // console.error("랭크 정보를 가져오는 중 오류 발생:", error);
    }
  };
  const getUserRanks = async function (userId) {
    try {
      console.log(userId);
      const response = await axiosInstance.get(`/rank/${userId}`);
      return response.data;
    } catch (error) {
      console.error("랭크 정보를 가져오는 중 오류 발생:", error);
    }
  };
  // 프로필 정보 가져오기
  const getUserProfile = async function () {
    const user = 'me';
    try {
      const response = await axiosInstance.get(`/profile/user/${user}`);
      userProfile.value = response.data;
    } catch (error) {
      console.error("프로필 정보를 가져오는 중 오류 발생:", error);
    }
  };

  // 모든 프로필 정보 가져오기
  const getUserAllProfiles = async function () {
    try {
      const response = await axiosInstance.get(`/profile/userAll`);
      userAllProfiles.value = response.data;
    } catch (error) {
      console.error("모든 프로필 정보를 가져오는 중 오류 발생:", error);
    }
  };

  // 프로필 등록
  const addUserProfile = async function (profile) {
    try {
      console.log(profile);
      const userProfile = {
        image: profile.image,
        muscle: profile.muscle,
        fatPer: profile.fatPer,
        bmi: profile.bmi,
        fat: profile.fat,
        weight: profile.weight,
        userId: profile.userId,
        gender: profile.gender,
      };
      const response = await axiosInstance.post(`/profile`, userProfile);
      userAllProfiles.value.push(response.data);
      await getUserAllProfiles();
      await getUserProfile();
    } catch (error) {
      console.error("프로필 정보를 등록하는 중 오류 발생:", error);
    }
  };

  
  // 로그아웃
  const logoutUser = function () {
    userData.value = null;
    token.value = "";
    userRank.value = null;
    userProfile.value = null;
    userAllProfiles.value = [];
    similarProfiles.value = [];
    localStorage.setItem("attendData","");
    localStorage.setItem("fittyNameData","");
    tierData.value = null;
    localStorage.clear();
    router.push({ name: "login" });
  };
  const quitUser =  async function () {
    try {
      await axiosInstance.delete(`user`);
      logoutUser();
    } catch (error) {
      console.error("회원 탈퇴 중 오류 발생:", error);
    }
  };
  // 회원 정보 수정
  const editUser = async function (user) {
    try {
      await axiosInstance.put(`user/update`, user);
      await getUserData();
    } catch (error) {
      console.error("회원 정보를 수정하는 중 오류 발생:", error);
    }
  };
  const getSimilar = async function (){
    try{
    
      
      const newProfile = {
        userId:userProfile.value.userId,
        gender:userProfile.value.gender,
        muscle:userProfile.value.muscle,
        fat:userProfile.value.fat,
        bmi:userProfile.value.bmi,
        fatPer:userProfile.value.fatPer,
        weight:userProfile.value.weight,
      }
      console.log(newProfile.gender);
      const response = await axios.post(`${REST_BASE_API}/profile/similar`,newProfile);
      similarProfiles.value = response.data;
    } catch(error) {
      console.error("파티 정보를 집어넣다가 오류 발생:", error);
    }
  };
  return {
    registUser,
    loginUser,
    logoutUser,
    getUserRanks,
    getUserRank,
    getUserData,
    userData,
    editUser,
    userRank,
    userProfile,
    token,
    userAllProfiles,
    addUserProfile,
    getUserProfile,
    getUserAllProfiles,
    getSimilar,
    similarProfiles,
    getRankList,
    rankList,
    quitUser,
    tierData,
    getTierGrade,
  };
}, { persist: true });
