<template>
  <div class="ranking-board-container">
    <h1 class="title-animated">랭킹보드</h1>
    <div v-if="rankList.length > 0">
      <div class="sorting-buttons">
        <button @click="sortRankList('tier')" class="button-sort">Tier</button>
        <button @click="sortRankList('level')" class="button-sort">Level</button>
        <button @click="sortRankList('streak')" class="button-sort">Streak</button>
      </div>
      <table class="ranking-table">
        <thead>
          <tr>
            <th>Rank</th>
            <th>User ID</th>
            <th>Rating</th>
            <th>티어</th>
            <th>Level</th>
            <th>Streak</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in pagedRankList" :key="item.userId">
            <td><strong>{{ item.rank }}</strong></td>
            <td><strong>{{ item.userId }}</strong></td>
            <td><strong>{{ item.tier }}</strong></td>
            <td>
              <img :src="getTierImageSrc(item.tier)" alt="Tier Image" class="tier-image">
            </td>
            <td><strong>Lv.{{ item.level }}</strong></td>
            <td><strong>{{ item.streak }}일</strong></td>
          </tr>
        </tbody>
      </table>
      <div class="pagination">
        <button @click="prevPage" :disabled="currentPage === 1" class="button-page">이전</button>
        <span v-for="page in paginatedPages" :key="page" class="page-number" :class="{ active: page === currentPage }" @click="goToPage(page)">
          {{ page }}
        </span>
        <button @click="nextPage" :disabled="currentPage === totalPages" class="button-page">다음</button>
      </div>
    </div>
    <div v-else>
      <h5>랭킹 데이터를 불러오는 중입니다...</h5>
    </div>
    <div :style="backgroundStyle" class="backGG"></div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { useUserStore } from '@/stores/userStore';
import back from "@/assets/back-logo.png";

const backSrc = back;

const userStore = useUserStore();
const rankList = ref([]);

onMounted(async () => {
  await userStore.getRankList();
  rankList.value = [...userStore.rankList];
  sortRankList('tier');
});

const pageSize = 10;
const currentPage = ref(1);
const pagedRankList = computed(() => {
  const startIndex = (currentPage.value - 1) * pageSize;
  const endIndex = currentPage.value * pageSize;
  const slicedRankList = rankList.value.slice(startIndex, endIndex);
  return slicedRankList.map((item, index) => ({
    ...item,
    rank: startIndex + index + 1
  }));
});

const nextPage = () => {
  if (currentPage.value * pageSize < rankList.value.length) {
    currentPage.value++;
  }
};

const prevPage = () => {
  if (currentPage.value > 1) {
    currentPage.value--;
  }
};

const sortRankList = (attribute) => {
  rankList.value.sort((a, b) => {
    return b[attribute] - a[attribute];
  });
};

const getTierGrade = (tier) => {
  if (tier >= 0 && tier < 1000) return '아이언';
  else if (tier >= 500 && tier < 1500) return '브론즈';
  else if (tier >= 1000 && tier < 2000) return '실버';
  else if (tier >= 1500 && tier < 2500) return '골드';
  else if (tier >= 2000 && tier < 3000) return '플래티넘';
  else if (tier >= 3000 && tier < 3500) return '다이아';
  else if (tier >= 3500 && tier < 4000) return '마스터';
  else if (tier >= 4000 && tier < 4500) return '그랜드마스터';
  else return '챌린저';
};

const getTierImageSrc = (tier) => {
  const tierGrade = getTierGrade(tier);
  return new URL(`/src/assets/${tierGrade}.png`, import.meta.url).href;
};

const totalPages = computed(() => Math.ceil(rankList.value.length / pageSize));

const paginatedPages = computed(() => {
  const pages = [];
  const totalPageCount = totalPages.value;
  const currentPageValue = currentPage.value;

  const startPage = Math.max(1, currentPageValue - 2);
  const endPage = Math.min(totalPageCount, currentPageValue + 2);

  for (let i = startPage; i <= endPage; i++) {
    pages.push(i);
  }

  return pages;
});

const goToPage = (page) => {
  if (page >= 1 && page <= totalPages.value) {
    currentPage.value = page;
  }
};


const backgroundStyle = computed(() => ({
  backgroundImage: `url(${backSrc})`,
  backgroundSize: "cover",
  backgroundPosition: "center",
  opacity: 0.5,
  position: "absolute",
  top: 0,
  left: 0,
  width: "100%",
  height: "115%",
  zIndex: -1,
}));
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
  font-size: 2.5rem;
  font-weight: bold;
  color: rgb(13, 30, 80);
  text-align: center;
  animation: fadeIn 1s ease-out;
  margin-bottom: 30px;
}

.ranking-board-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  min-height: 100vh;
  padding: 20px;
  
  /* background-color: #ffffff; */
  /* background-image: linear-gradient(to bottom, #ffffff, #99a7cb); */
}

.sorting-buttons {
  display: flex;
  justify-content: center;
  margin-bottom: 30px;
}

.button-sort {
  padding: 10px 20px;
  background-color: #0c2c85;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-right: 10px;
}

.button-sort:hover {
  background-color: #6173af;
}

.ranking-table {
  width: 100%;
  max-width: 1300px;
  border-collapse: collapse;
  margin-bottom: 50px;
  
}

.ranking-table th, .ranking-table td {
  padding: 10px;
  border: 0px solid #9b6868;
  text-align: center;
}

.ranking-table th {
  background-color: #071981;
  font-weight: bold;
  width: 600px;
  color: white;
}

.ranking-table tr:nth-child(even) {
  background-color: #f3f6fa;
  opacity: 0.95;
}

.ranking-table tr:hover {
  background-image: linear-gradient(to right, #bbbbc0, #061850);
  color: white;
  opacity: 0.8;
}

.tier-image{
  width:44px;
  height: 36px;
}

.pagination {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.button-page {
  padding: 10px 20px;
  background-color: #0c2c85;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin: 0 10px;
}

.button-page[disabled] {
  background-color: #a1a5af;
  cursor: not-allowed;
}

.button-page:not([disabled]):hover {
  background-color: #6173af;
}

.page-number {
  padding: 10px 15px;
  margin: 0 5px;
  background-color: #fff;
  border: 1px solid #0c2c85;
  border-radius: 4px;
  cursor: pointer;
}

.page-number.active {
  background-color: #0c2c85;
  color: white;
  border-color: #0c2c85;
}

.page-number:hover {
  background-color: #6173af;
  color: white;
}

</style>
