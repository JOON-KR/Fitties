<template>
  <div>
    <header class="header-with-shadow">
      <div class="logo">
        <router-link to="/">
            <img :src="logoSrc" alt="logo">
        </router-link>
      </div>
      <nav>
        <ul>
          <li><router-link to="/calendar" class="menu-link"><strong class="menu-item"><span>CALENDAR</span></strong></router-link></li>
          <li><router-link to="/fitty" class="menu-link"><strong class="menu-item"><span>FITTY</span></strong></router-link></li>
          <template v-if="userStore.userData">
            <li><router-link to="/profile" class="menu-link"><strong class="menu-item"><span>INBODY</span></strong></router-link></li>
            <li><router-link to="/ranking" class="menu-link"><strong class="menu-item"><span>RANKING</span></strong></router-link></li>
            <li><router-link to="/myPage" class="menu-link"><strong class="menu-item"><span>MY PAGE</span></strong></router-link></li>
            <li class="menu-id">
              <img :src="getTierImageSrc(userStore.tierData)" alt="Tier Image" class="tier-image">
              <strong>{{ userStore.userData.name }}</strong></li>
            <li @click="logout"><strong class="menu-item"><span>LOGOUT</span></strong></li>
          </template>
          <template v-else>
            <li><router-link to="/login" class="menu-link"><strong class="menu-item"><span>LOGIN</span></strong></router-link></li>
            <li><router-link to="/regist" class="menu-link"><strong class="menu-item"><span>SIGN UP</span></strong></router-link></li>
          </template>
        </ul>
      </nav>
    </header>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/userStore";
import { useCalendarStore } from "@/stores/calendarStore";
import logo from '@/assets/LOGO.png';

const router = useRouter();
const userStore = useUserStore();
const logoSrc = logo;
const calendarStore = useCalendarStore();
const logout = function () {
  userStore.logoutUser();
  calendarStore.attendData = "";
};
const getTierImageSrc = (tier) => {

  return new URL(`/src/assets/${tier}.png`, import.meta.url).href;
};
const check = function () {
  console.log(userStore.userProfile);
  console.log(userStore.userData);
  console.log(userStore.userRank);
};
</script>

<style scoped>
.header-with-shadow {
  height: 100px; /* 고정된 높이 설정 */
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;
  background-color: #ffffff;
  box-shadow: 0 5px 100px rgba(38, 55, 108, 0.1);
  overflow: hidden; /* 헤더 영역을 벗어난 내용 숨기기 */
}

.logo img {
  margin-left: 50px;
  height: 70px;
  width: auto;
}

.tier-image {
  width: 20px;
  height: 16px;
  margin-right: 5px;
}

nav ul {
  display: flex;
  list-style: none;
  margin: 0;
  padding: 0;
  align-items: center;
}

nav ul li {
  margin: 0 1px;
}

.menu-id {
  font-size: 17px;
  text-decoration: none;
  color: rgb(0, 0, 0);
  white-space: nowrap; /* 텍스트 줄바꿈 방지 */
  overflow: hidden; /* 넘치는 텍스트 숨기기 */
  text-overflow: ellipsis; /* 말줄임표(...) 표시 */
  max-width: 150px; /* 최대 너비 설정, 필요에 따라 조정 */
}

.menu-item,
.menu-item *,
.menu-item:after,
.menu-item:before,
.menu-item:after,
.menu-item:before {
  border: 0 solid;
  box-sizing: border-box;
}

.menu-item {
  -webkit-tap-highlight-color: transparent;
  -webkit-appearance: button;
  background-color: #000;
  background-image: none;
  color: #fff;
  cursor: pointer;
  font-family: ui-sans-serif, system-ui, -apple-system, BlinkMacSystemFont,
    Segoe UI, Roboto, Helvetica Neue, Arial, Noto Sans, sans-serif,
    Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol, Noto Color Emoji;
  font-size: 100%;
  line-height: 4;
  margin: 0;
  -webkit-mask-image: -webkit-radial-gradient(#000, #fff);
  padding: 0;
}

.menu-item:disabled {
  cursor: default;
}

.menu-item:-moz-focusring {
  outline: auto;
}

.menu-item svg {
  display: block;
  vertical-align: middle;
}

.menu-item [hidden] {
  display: none;
}

.menu-item {
  box-sizing: border-box;
  display: block;
  font-weight: 900;
  -webkit-mask-image: none;
  padding: 2rem 5rem;
  perspective: 800px;
  position: relative;
  text-transform: uppercase;
  transform-style: preserve-3d;
}

.menu-item span {
  background: #fff;
  color: #000;
  display: grid;
  inset: 0;
  place-items: center;
  position: absolute;
  transform: rotateX(0deg);
  transform-origin: top center;
  transition: 0.2s;
}

.menu-item:hover span {
  transform: rotateX(35deg);
}

.menu-item:after,
.menu-item:before {
  background: #ddd;
  content: "";
  height: 100%;
  left: 0;
  position: absolute;
  top: 0;
  transform: rotateX(0deg);
  width: 100%;
  z-index: -1;
}

.menu-item:after {
  background: #183889;
  width: 0;
}

.menu-item:hover:after {
  -webkit-animation: progress-bar 1.2s;
  animation: progress-bar 1.2s;
}

@-webkit-keyframes progress-bar {
  0% {
    opacity: 1;
    width: 0;
  }

  10% {
    opacity: 1;
    width: 15%;
  }

  25% {
    opacity: 1;
    width: 25%;
  }

  40% {
    opacity: 1;
    width: 35%;
  }

  55% {
    opacity: 1;
    width: 75%;
  }

  60% {
    opacity: 1;
    width: 100%;
  }

  to {
    opacity: 0;
    width: 100%;
  }
}

@keyframes progress-bar {
  0% {
    opacity: 1;
    width: 0;
  }

  10% {
    opacity: 1;
    width: 15%;
  }

  25% {
    opacity: 1;
    width: 25%;
  }

  40% {
    opacity: 1;
    width: 35%;
  }

  55% {
    opacity: 1;
    width: 75%;
  }

  60% {
    opacity: 1;
    width: 100%;
  }

  to {
    opacity: 0;
    width: 100%;
  }
}

.menu-link {
  text-decoration: none;
  color: black;
}
</style>
