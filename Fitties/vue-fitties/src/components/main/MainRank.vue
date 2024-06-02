<template>
  <div class="ranking-card">
    <div class="title-container">
      <h2 class="title">랭킹보드</h2>
      <div class="sorting-buttons">
        <router-link to="/ranking">
          <button class="button-sort"><strong>MORE</strong></button>
        </router-link>
        <!-- <button @click="sortRankList('level')" class="button-sort">Level</button>
        <button @click="sortRankList('streak')" class="button-sort">Streak</button> -->
      </div>
    </div>
    <div v-if="rankList.length > 0">
      <table class="ranking-table">
        <thead>
          <tr>
            <th>Rank</th>
            <th>User ID</th>
            <th>Rating</th>
            <th>티어</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in displayedRankList" :key="item.userId">
            <td>{{ index + 1 }}</td>
            <td>{{ item.userId }}</td>
            <td>{{ item.tier }}</td>
            <td>{{ getTierGrade(item.tier) }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-else>
      <h5>랭킹 데이터를 불러오는 중입니다...</h5>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import { useUserStore } from "@/stores/userStore";

const userStore = useUserStore();
const rankList = ref([]);

onMounted(async () => {
  await userStore.getRankList();
  rankList.value = [...userStore.rankList];
});

const displayedRankList = computed(() => {
  const sortedList = rankList.value.slice().sort((a, b) => b.tier - a.tier);
  const topUser = sortedList[0];
  const remainingUsers = sortedList.slice(1, 5);
  return [topUser, ...remainingUsers];
});

const sortRankList = (attribute) => {
  if (attribute === "tier") {
    rankList.value.sort((a, b) => b[attribute] - a[attribute]);
  } else {
    rankList.value.sort((a, b) => b[attribute] - a[attribute]);
  }
};

const getTierGrade = (tier) => {
  // 티어 등급 반환 함수 유지
};

// 3초마다 랜덤으로 순위가 변경되도록 타이머 설정
setInterval(() => {
  sortRankList("tier");
}, 3000);
</script>

<style scoped>
.ranking-card {
  width: 340px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 8px;
  overflow-y: auto;
  background-color: white;
}

.title-container {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.title {
  font-size: 1.2rem;
  font-weight: bold;
  color: #333;
  margin-bottom: 10px;
}

.sorting-buttons {
  display: flex;
}

.button-sort {
  padding: 5px 20px; /* 패딩을 줄임 */
  color: #0c2c85;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 0.8rem;
  background-color: transparent;
  margin-bottom: 4px;
}

.button-sort:hover {
  background-color: #6173af;
}

.ranking-table {
  width: 100%;
  border-collapse: collapse;
}

.ranking-table th,
.ranking-table td {
  padding: 5px;
  border-bottom: 1px solid #ccc;
  text-align: center;
  font-size: 0.9rem;
}

.pagination {
  display: flex;
  justify-content: center;
  margin-top: 10px;
}
</style>
