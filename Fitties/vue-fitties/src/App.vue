<script setup>
import { RouterLink, RouterView } from "vue-router";
import HeaderView from "@/components/common/HeaderView.vue";
import MainView from "@/views/MainView.vue";
import FooterView from "@/components/common/FooterView.vue";
</script>

<template>
  <HeaderView />
  <!-- <MainView /> -->
  <RouterView />

  <FooterView />
</template>

<style scoped></style>
