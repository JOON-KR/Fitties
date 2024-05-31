
# SpringBoot+Vue.js-Project-SSAFIT
운동 스케쥴 관리 및 루틴을 추천, 사용자간 운동 경쟁이 활성화된 사이트


## 🖥️ 프로젝트 소개
AI(Chat-Gpt)를 활용한 운동스케줄 관리 및 운동루틴 정보 제공
Inbody유사도 분석 알고리즘을 바탕으로한 사용자간 그룹매칭
유저간 운동 성취도 랭킹 및 리워드로 경쟁 활성화
<br>

## 🕰️ 개발 기간
* 24.05.09일 - 22.05.23일

### 🧑‍🤝‍🧑 맴버구성
 - 팀장  : 윤여준 - 알고리즘 로직, 외부 라이브러리 및 API, 로그인 보안, 프론트 연결 관리
 - 팀원  : 성민기 - REST-API, DB연결 관리, 프론트 퍼블리싱, 프론트 디테일  

### ⚙️ 개발 환경
- `Java 17`
- `JDK 17.0.1`
- **IDE** : STS 4, eclipse
- **Framework** : Springboot, Spring-Security
- **Database** : My SQL
- **ORM** : Mybatis
- **api** : Clova-OCR, Chat-Gpt
- 
## 📌 주요 기능

#### 로그인
- JWT 인증
- Spring-Security
- HS256 / Base64 / Bcrypt

#### 회원가입
- AI 유효성체크 -건전한 닉네임체크

### 마이 페이지
- 회원 정보 조회 / 수정 / 삭제

<img src="https://github.com/JOON-KR/SSAFY-11th-1st-Semester-Final-Project/assets/171137407/4da47f16-f353-47be-8617-ffa11ed3254a" width="1000" height="600" alt="FITTIES(MyPage)">


#### 프로필 등록
- Clova OCR api - 인바디 이미지에서 텍스트를 추출하여 자동입력
- 타임라인
  
<img src="https://github.com/JOON-KR/SSAFY-11th-1st-Semester-Final-Project/assets/171137407/be2e5084-011b-45f5-a12d-317ba5b589a9" width="1000" height="600" alt="FITTIES(인바디)">


#### 메인 페이지 - 
- 로그인 전 / 후 페이지처리
- 반응형 달성률
- 랜덤 파티리스트

 < 로그인 전 >
 ![image](https://github.com/JOON-KR/SSAFY-11th-1st-Semester-Final-Project/assets/171137407/16d3efe3-7b9a-4dce-9adc-28f0e40eca01)

 < 로그인 후 >
<img src="https://github.com/JOON-KR/SSAFY-11th-1st-Semester-Final-Project/assets/171137407/2aa431ca-e16d-4ad2-8f0a-fe0edf004759" width="1000" height="600" alt="FITTIES(Login후)">


#### Fitty 페이지
- 파티 신청 / 수락 / 강퇴 / 탈퇴 / 검색 / 생성시 공개 - 비공개
- 알고리즘 기반 매칭시스템 - 피어슨 상관계수, 유클리드 거리유사도 분석 
- Open AI 유효성 체크 - 건전한 Fitty명 체크
- 스케쥴 자동 생성
- 유저 프로필 확인

<img src="https://github.com/JOON-KR/SSAFY-11th-1st-Semester-Final-Project/assets/171137407/312b23c0-677f-4d6d-a9ad-7934f20a38b0" width="1000" height="600" alt="FITTIES(FITTY)">



#### 랭킹 페이지
- 데이터 자동 순환
- 검색
  
<img src="https://github.com/JOON-KR/SSAFY-11th-1st-Semester-Final-Project/assets/171137407/81c2520a-89f4-4d6d-9dfa-f281bd5f281d" width="1000" height="600" alt="FITTIES(Ranking)">


### 스케쥴 페이지
- 스케쥴 생성 / 삭제
- 카운팅 정렬기반 매일 운동 부위 추천
- 출석 체크
- 운동 기록 타임라인
- AI 운동 루틴 추천
- 파티원 달성률 조회
  
<img src="https://github.com/JOON-KR/SSAFY-11th-1st-Semester-Final-Project/assets/171137407/eb1a072d-aca3-4071-a867-12ec2445f08b" width="1000" height="600" alt="FITTIES(Calendar)">


## 성과
- 1등!
<img src="https://github.com/JOON-KR/SSAFY-11th-1st-Semester-Final-Project/assets/171137407/97e05bc5-d7a1-4d13-9bd4-cc92bc1b9708" width="400" height="600" alt="Image Description">

