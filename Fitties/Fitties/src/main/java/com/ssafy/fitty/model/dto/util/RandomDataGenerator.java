package com.ssafy.fitty.model.dto.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ssafy.fitty.model.dto.calendar.CalendarDetail;
import com.ssafy.fitty.model.dto.user.User;
import com.ssafy.fitty.model.dto.user.UserProfile;
import com.ssafy.fitty.model.dto.user.UserRank;

public class RandomDataGenerator {
    private static final Random random = new Random();
   
    
    // 근육량(muscle)과 체지방량(fat) 생성
    public static double[] generateMuscleFat(String gender) {
        double muscle;
        double fat;
        double muscleStart;
        double fatStart;
        if (gender.equalsIgnoreCase("male")) {
            muscleStart = 20; // 남성 근육량 시작값 (kg)
            fatStart = 5; // 남성 체지방량 시작값 (kg)
        } else { // 여성인 경우
            muscleStart = 15; // 여성 근육량 시작값 (kg)
            fatStart = 5; // 여성 체지방량 시작값 (kg)
        }
        
        // 근육량과 체지방량에 대한 통합 백분율 생성 (0% ~ 100%)
        double totalPercentage = random.nextDouble();
        
        // 백분율에 따라 최종 값 생성 (근육량과 체지방량 각각의 최대값을 초과하지 않도록 조정)
        double totalWeight = 20;
        muscle = muscleStart + totalPercentage * totalWeight;
        fat = fatStart + totalPercentage * totalWeight;
        
        // 근육량에 대한 추가적인 랜덤한 퍼센트 적용 (-20% ~ 20%)
        double muscleVariation = random.nextDouble() * 0.4 - 0.2; // -0.2에서 0.2 사이의 값
        muscle *= (1 + muscleVariation);
        
        // 체지방량에 대한 추가적인 랜덤한 퍼센트 적용 (-20% ~ 20%)
        double fatVariation = random.nextDouble() * 0.4 - 0.2; // -0.2에서 0.2 사이의 값
        fat *= (1 + fatVariation);
        
        return new double[]{muscle, fat};
    }

    // 체지방율(fat_per) 생성
    public static double generateFatPer(double weight, double fat) {
        double fatPer = fat / weight * 100; // 체지방율 계산
        return fatPer;
    }

    // 체질량지수(BMI) 생성
    public static double generateBMI(double muscle, double fat, double height) {
        double weight = generateWeight(muscle, fat);
        double bmi = weight / (height * height);
        return bmi;
    }

    // 몸무게(weight) 생성
    public static double generateWeight(double muscle, double fat) {
        // 근육량과 체지방량에 대한 총합
        double totalWeight = muscle + fat;
        
        // 근육량과 체지방량의 총합에 -30%부터 30% 사이의 랜덤한 값을 더함
        double variationPercentage = random.nextDouble() * 0.4 - 0.2; // -0.3에서 0.3 사이의 값
        double adjustedTotalWeight = totalWeight * (1 + variationPercentage);
        double result = adjustedTotalWeight+(totalWeight/2);
        if(result <45) {
        	result = result/10 + 45;
        }
        return result;
    }
    public static double generateHeight(double weight, String gender) {
        double minHeight;
        if (gender.equalsIgnoreCase("male")) {
            minHeight = 155; // 남성 최소 키 (cm)
        } else {
            minHeight = 150; // 여성 최소 키 (cm)
        }
        
        // 몸무게를 기준으로 키 조정
        double heightVariationPercentage = random.nextDouble() * 0.3 + 0.1; // 0.1에서 0.4 사이의 값
        double adjustedHeight = weight * heightVariationPercentage + minHeight;
        
        return adjustedHeight / 100; // m 단위로 변환하여 반환
    }
//   랜덤 유저데이터 생성
    public static List<User> userGenerator() {
    	List<User>result=new ArrayList<User>();
    	for(int i=1;i<=2000;i++) {
    		User user =new User();
    		user.setId("ssafy_"+i);
    		user.setPassword("password"+i);
    		user.setEmail("ssafy"+i+"@ssafy.com");
    		user.setName("김싸피"+i);
    		user.setNickname("싸피생입니다"+i);
    		result.add(user);
    	}
    	return result;
    }
   //랜덤 랭크데이터 생성
    public static List<UserRank> RankGenerator(){
    	List<UserRank>result=new ArrayList<UserRank>();
    	for(int i=1;i<=2000;i++) {
    		UserRank userRank =new UserRank("ssafy_"+i,0 , 0, 0, 0);
    		userRank.setLevel((int) (Math.random() * 50) + 1);
    		userRank.setExp((int) (Math.random() * 2000) + 1);
    		userRank.setStreak((int) (Math.random() * 366));
    		userRank.setTier((int) (Math.random() * 5001) );
    		result.add(userRank);
    	}
    	return result;
    }
   //랜덤 프로필데이터 생성
    public static List<UserProfile> ProfileGenerator(){
    	List <UserProfile>result = new ArrayList<UserProfile>();
    	for(int i=1;i<=2000;i++) {
    		UserProfile userProfile = new UserProfile();
    		String gender = "male";
    		if(i>1000) {
    			gender = "female";
    		}
	    	double[] muscleFat = generateMuscleFat(gender);
	        double muscle = muscleFat[0];
	        double fat = muscleFat[1];
	        double weight = generateWeight(muscle, fat)+10;
	        double height = generateHeight(weight, gender);
	        double bmi = generateBMI(muscle, fat, height);
	        double fatPer = generateFatPer(weight, fat);
	        if(gender.equals("female"))weight-=20;
	        userProfile.setBmi(bmi);
	        userProfile.setFat(fat);
	        userProfile.setFatPer(fatPer);
	        userProfile.setGender(gender);
	        userProfile.setMuscle(muscle);
	        userProfile.setUserId("ssafy_"+i);
	        userProfile.setWeight(weight);
	        result.add(userProfile);
    	}
    	return result;
    }
    public static List<CalendarDetail> detailGenerator(String userId){
        List<CalendarDetail> result = new ArrayList<CalendarDetail>();
        String part[] = {"upper1", "upper2", "lower1", "lower2"};
   
        long nowDate = System.currentTimeMillis();
        Random random = new Random();
        for(int i=0; i<10; i++) {
            // date 값은 MySQL timestamp 값에 들어갈 수 있도록 하고 nowdate 기준으로 for문 반복할 때마다 
            // +1일부터 +10일까지 차례대로 가도록 함
            java.sql.Timestamp timestamp = new java.sql.Timestamp(nowDate + (i * 24 * 60 * 60 * 1000));
            String startTime = timestamp.toString();
            CalendarDetail cd = new CalendarDetail(userId, startTime, part[random.nextInt(part.length)]);
            // random endTime 설정 (startTime 기준 2시간 이내)
            java.sql.Timestamp endTimeTimestamp = new java.sql.Timestamp(timestamp.getTime() + (random.nextInt(2 * 60 * 60 * 1000))); // 2 hours in milliseconds
            String endTime = endTimeTimestamp.toString();
            cd.setEndTime(endTime);
            result.add(cd);
        }

        
        return result; // 생성된 일정 세부 사항 리스트 반환
    }

    
//    public static void main(String[] args) {
//   
//        userGenerator();
//        // 생성된 데이터 출력
//    
//    }
}
