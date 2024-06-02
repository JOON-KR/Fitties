import { createRouter, createWebHistory } from "vue-router";
import Test from "@/views/Test.vue";

import FittyView from "@/views/FittyView.vue";
import FittyList from "@/components/fitty/fittyList.vue";
import FittyCreate from "@/components/fitty/fittyCreate.vue";
import MainView from "@/views/MainView.vue";
import Login from "@/components/user/Login.vue";
import Regist from "@/components/user/Regist.vue";
import Detail from "@/components/Calendar/Detail.vue";
import Edit from "@/components/user/Edit.vue";
import MyPage from "@/components/user/MyPage.vue";
import Profile from "@/components/user/Profile.vue";
import CreateProfile from "@/components/user/CreateProfile.vue";
import Calendar from "@/views/Calendar.vue";
import Schedule from "@/components/Calendar/Schedule.vue";
import CreateSchedule from "@/components/Calendar/CreateSchedule.vue";
import FittyStatus from "@/components/fitty/fittyStatus.vue";
import FittyMatching from "@/components/fitty/fittyMatching.vue";
import RankingBoard from "@/components/Ranking/Board.vue";
import Ranking from "@/views/Ranking.vue";
const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path:"/test",
      name:"test",
      component: Test,
    },
    {
      path: "/",
      name: "main",
      component: MainView,
    },
    {
      path: "/login",
      name: "login",
      component: Login,
    },
    {
      path: "/regist",
      name: "regist",
      component: Regist,
    },
    {
      path: "/edit",
      name: "edit",
      component: Edit,
    },
    {
      path: "/mypage",
      name: "mypage",
      component: MyPage,
    },
    {
      path: "/profile",
      name: "profile",
      component: Profile,
      children: [
        {
          path: "create",
          name: "createProfile",
          component: CreateProfile,
        },
      ],
    },
    {
      path: "/fitty",
      name: "fitty",
      component: FittyView,
      children: [
        {
          path: "",
          name: "fittyList",
          component: FittyList,
        },
        {
          path: "create",
          name: "fittyCreate",
          component: FittyCreate,
        },
        {
          path: "status",
          name: "fittyStatus",
          component: FittyStatus,
        },
        {
          path: "matching",
          name: "fittyMatching",
          component: FittyMatching,
        }
      ],
    },
    {
      path: "/ranking",
      name: "ranking",
      component: Ranking,
      children: [
        {
          path: "board",
          name: "board",
          component: RankingBoard,
        }
      ],
    },

    {
      path: "/calendar",
      name: "calendar",
      component: Calendar,
      children: [
        {
          path: "schedule",
          name: "schedule",
          component: Schedule,
          children: [
            {
              path: "create",
              name: "createSchedule",
              component: CreateSchedule,
            },
          ],
        },
        {
          path: "detail",
          name: "detail",
          component: Detail,
        },
      ],
    },
  ],
});

export default router;
