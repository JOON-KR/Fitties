<template>
  <footer id="footer">
    <div class="under">
      <ul class="underlist">
        <li class="one"><a href="#">ABOUT</a></li>
        <li class="two"><a href="#">FITTY INFORMATION</a></li>
        <li class="three"><a href="#">WITH FITTIES</a></li>
      </ul>
    </div>
    <div class="right_under">
      <ul class="link">
        <li class="seven">
          <a>COPYRIGHTS 2018 @ FITTIES. CO. LTD. ALL RIGHTS RESERVED.</a>
        </li>
        <li class="eight">
          <a>주소: 대전광역시 유성구 덕명동 124번지(Lecture Bidg. 203)</a>
        </li>
        <li class="ten">
          <a
            >고객센터: 1588-1588 FAX: 063-7777-7777 이메일:
            SSAFY@fitties.co.kr</a
          >
        </li>
      </ul>
    </div>
  </footer>
</template>

<script setup>
// 이 곳에는 스크립트를 작성할 수 있습니다.
</script>

<style scoped>
/* 여기에 CSS를 넣어주세요. */
#footer {
  clear: both;
  width: 100%;
  height: auto; /* 높이를 자동으로 조정하여 내용에 따라 늘어나게 설정 */
  background-color: none;
  text-align: left;
  margin-bottom: 0px;
  text-decoration: none;
  font-family: Arial;
  border-top: 1px solid #ccc;
  /* margin-top: 20px; */
}

#footer .under {
  float: left;
  width: 50%;
  margin-top: 15px;
}

#footer .under .underlist {
  padding: 10px 0 0 40px; /* 위쪽 패딩을 줄여서 아래 내용과 같은 라인에 위치 */
  margin-left: 50%;
}

#footer .under .underlist li {
  list-style: none;
  margin-bottom: 2px; /* 각 항목 사이의 간격 조정 */
}

#footer .under .underlist li a {
  text-decoration: none;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  font-size: 14px;
  color: rgba(0, 0, 0, 0.68);
}

#footer .right_under {
  float: left;
  width: 35%;
  padding: 10px 0 0 40px; /* 위쪽 패딩을 줄여서 아래 내용과 같은 라인에 위치 */
  margin-top: 15px;
}

#footer .right_under li {
  list-style: none;
  margin-bottom: 2px; /* 각 항목 사이의 간격 조정 */
}

#footer .right_under li a {
  text-decoration: none;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  font-size: 14px;
  color: rgba(0, 0, 0, 0.68);
}

#footer .under .underlist a,
#footer .right_under li a {
  color: #999;
  font-size: 14px;
}

#footer .under .underlist a:hover,
#footer .right_under li a:hover {
  cursor: pointer;
  color: gainsboro;
}
</style>
