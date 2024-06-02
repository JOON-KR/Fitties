<template>
  <div class="main" :class="{ scrolled: isScrolled }">
    <!-- 비주얼 영역 -->
    <section class="section visual">
      <div class="video-wrap">
        <div class="vsc-controller"></div>
        <video muted="" autoplay="" loop="" playsinline="">
          <source src="/src/assets/fitty.mp4" type="video/mp4" />
        </video>
        <div class="text-overlay">
          <div class="motion-logo">
            <div class="title" :style="{ fontSize: titleSize, letterSpacing: letterSpacing, fontWeight: '900' }">
              <strong>FITTIES</strong>
            </div>
          </div>
          <div class="text-wrap inner">
            <h2 class="title ft-poppins fc-white">
              Better Health 
              With FITTIES
            </h2>
            <p class="text fc-white">
              함께라면 할 수 있습니다 <br />
              <h6>ⓒ FITTIES</h6>
            </p>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<style scoped>
/* 스타일 영역 */
/* 비디오와 텍스트 오버레이를 포함한 스타일 */
.video-wrap {
  position: relative;
  width: 100%;
  height: 100vh;
  overflow: hidden;
}

.video-wrap video {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.text-overlay {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
  color: white; /* 텍스트 색상을 흰색으로 설정 */
  transition: font-size 0.5s ease; /* 텍스트 크기 변환 트랜지션 */
}

.title {
  font-weight: bold;
}

.text-wrap {
  margin-top: 20px;
  opacity: 0; /* 초기에 숨김 */
  transition: opacity 0.5s ease; /* 텍스트 나타내기 트랜지션 */
}

.scrolled .text-overlay {
  font-size: 24px; /* 스크롤 내릴 때 텍스트 크기 변경 */
}

.scrolled .text-wrap {
  opacity: 1; /* 스크롤 내릴 때 텍스트 나타내기 */
}

/* 추가: 수평 스크롤 없애기 */
body, html {
  overflow-x: hidden;
}
</style>

<script setup>
import { ref, computed, onMounted, onUnmounted } from "vue";

const isScrolled = ref(false);

const handleScroll = () => {
  isScrolled.value = window.scrollY > 0;
};

onMounted(() => {
  window.addEventListener("scroll", handleScroll);
});

onUnmounted(() => {
  window.removeEventListener("scroll", handleScroll);
});

// 전체 화면의 가로 너비를 구함
const screenWidth = computed(() => window.innerWidth);
// 텍스트 크기를 동적으로 설정하기 위한 비율 계산
const titleSize = computed(() => screenWidth.value / 8 + "px");
// 자간을 동적으로 설정하기 위한 비율 계산
const letterSpacing = computed(() => screenWidth.value / 80 + "px");
</script>
