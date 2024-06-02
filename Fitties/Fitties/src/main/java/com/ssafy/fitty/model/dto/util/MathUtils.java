package com.ssafy.fitty.model.dto.util;

import java.util.*;

import com.ssafy.fitty.model.dto.user.UserProfile;


public class MathUtils {

	

	 // 데이터 정규화: Z-Score 정규화
    public static double[] zScoreNormalization(double[] data) {
        double mean = calculateMean(data);
        double stdDev = calculateStdDev(data, mean);
        
        double[] normalizedData = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            normalizedData[i] = (data[i] - mean) / stdDev;
        }
        
        return normalizedData;
    }

    // 데이터 정규화: Min-Max 스케일링
    public static double[] minMaxScaling(double[] data) {
        double min = findMin(data);
        double max = findMax(data);
        
        double[] normalizedData = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            normalizedData[i] = (data[i] - min) / (max - min);
        }
        
        return normalizedData;
    }

    // 피어슨 상관계수 계산
    public static double pearsonCal(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("두 배열의 길이가 일치하지 않습니다.");
        }

        double meanX = calculateMean(x);
        double meanY = calculateMean(y);
        
        double numerator = 0.0, denominatorX = 0.0, denominatorY = 0.0;
        for (int i = 0; i < x.length; i++) {
            numerator += (x[i] - meanX) * (y[i] - meanY);
            denominatorX += Math.pow(x[i] - meanX, 2);
            denominatorY += Math.pow(y[i] - meanY, 2);
        }

        double denominator = Math.sqrt(denominatorX * denominatorY);

        if (denominator == 0) {
            return 0; // 분모가 0이면 상관관계가 없다고 가정
        }

        return numerator / denominator;
    }

    // 평균 계산
    private static double calculateMean(double[] data) {
        double sum = 0.0;
        for (double value : data) {
            sum += value;
        }
        return sum / data.length;
    }

    // 표준 편차 계산
    private static double calculateStdDev(double[] data, double mean) {
        double sumSqDiff = 0.0;
        for (double value : data) {
            sumSqDiff += Math.pow(value - mean, 2);
        }
        return Math.sqrt(sumSqDiff / data.length);
    }

    // 최소값 찾기
    private static double findMin(double[] data) {
        double min = data[0];
        for (double value : data) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    // 최대값 찾기
    private static double findMax(double[] data) {
        double max = data[0];
        for (double value : data) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
    
    //두 데이터의 유클리드거리 반환
    public static double euclideanDistance(double[] point1, double[] point2) {

        double sum = 0.0;
        for (int i = 0; i < point1.length; i++) {
            sum += Math.pow(point2[i] - point1[i], 2);
        }
        return Math.sqrt(sum);
    }
    
    //유사사용자 찾기
    public List<UserProfile> calculateSimilar(List<UserProfile> list, UserProfile user) {
    	
        // 사용자의 데이터 추출
        double[] userValues = { user.getMuscle(), user.getFat(), user.getBmi(), user.getFatPer(),user.getWeight()};
        List<double[]>pearson = new ArrayList<double[]>();
        List<double[]>euclide = new ArrayList<double[]>();
        //피어슨 상관계수를 구해서 pearson에 { 유저번호 , 상관계수값} 으로 add
        for (int i=0;i<list.size();i++) {
        	UserProfile profile = list.get(i);
        	if(!user.getGender().equals(profile.getGender())||user.getUserId().equals(profile.getUserId()))continue;
            double[] profileValues = { profile.getMuscle(), profile.getFat(), profile.getBmi(), profile.getFatPer(),profile.getWeight()};
        //    System.out.println(Arrays.toString(profileValues));
            double[] normalizedUser1Data = zScoreNormalization(userValues);
            double[] normalizedUser2Data = zScoreNormalization(profileValues);
            double correlation = pearsonCal(normalizedUser1Data, normalizedUser2Data);
            double [] tmp ={i, correlation};
            pearson.add(tmp);
        }
        //피어슨 상관계수 유사도로 정렬(내림차)
        Collections.sort(pearson, (o1, o2) -> Double.compare(o2[1], o1[1]));
        
        //상위 10% 거르기
        int tenPer = list.size()/10;
        
        //유클리드 거리 계산
        for(int i=0;i<tenPer;i++) {
        	UserProfile profile = list.get((int)pearson.get(i)[0]);
        	double[] profileValues = { profile.getMuscle(), profile.getFat(), profile.getBmi(), profile.getFatPer(),profile.getWeight()};
        	double dif = euclideanDistance(profileValues, userValues);
        	double [] tmp ={(int)pearson.get(i)[0], dif};
        	euclide.add(tmp);
        }
        //유클리드 거리 유사도로 정렬(오름차)
        Collections.sort(euclide, (o1, o2) -> Double.compare(o1[1], o2[1]));
    	
        //5명 반환 또는 사이즈만큼 반환
        List<UserProfile> result = new ArrayList<UserProfile>();
        for(int i=0;i<5 && i<euclide.size();i++) {
        	int num = (int)euclide.get(i)[0];
        	result.add(list.get(num));
        }
		return result;
	}
    
    
//    public static void main(String[] args) {
//
//    	//test
//    	List<UserProfile> list = RandomDataGenerator.ProfileGenerator();
//    	UserProfile user = new UserProfile("10000", "male", "img", "reg", 30, 15, 20, 20, 70);
//    	list.add(user);
//        // 사용자의 데이터 추출
//        double[] userValues = { user.getMuscle(), user.getFat(), user.getBmi(), user.getFatPer(),user.getWeight()};
//        List<double[]>Pearson = new ArrayList<>();
//        System.out.println("사용자와 다른 사용자 간의 피어슨 상관계수:");
//        for (UserProfile profile : list) {
//            double[] profileValues = { profile.getMuscle(), profile.getFat(), profile.getBmi(), profile.getFatPer(),profile.getWeight()};
//            System.out.println(Arrays.toString(profileValues));
//            double[] normalizedUser1Data = zScoreNormalization(userValues);
//            double[] normalizedUser2Data = zScoreNormalization(profileValues);
//            double correlation = pearsonCal(normalizedUser1Data, normalizedUser2Data);
//            double [] tmp ={Double.parseDouble(profile.getUserId()), correlation};
//            Pearson.add(tmp);
//            //{1,2,3,4,5 } {3,4,5,6,7}
//            System.out.println("사용자 " + user.getUser_id() + profile.toString());
//            System.out.println("사용자 " + user.getUser_id() + "과(와) 사용자 " + profile.getUser_id() + " 간의 상관계수: " + correlation);
//        }
//        Collections.sort(Pearson, (o1, o2) -> Double.compare(o2[1], o1[1]));
//        List<UserProfile>result = new ArrayList<>();
//        for (int i=0;i<1000;i++) {
//        	int id = (int)Pearson.get(i)[0];
//        	result.add(list.get(id));
//        	System.out.println(list.get(id).toString());
//        	System.out.println((int)Pearson.get(i)[0]+" "+Pearson.get(i)[1]);
//        }
//        List<int[]>res = new ArrayList<>();
//        for (UserProfile profile : result) {
//            double[] profileValues = { profile.getMuscle(), profile.getFat(), profile.getBmi(), profile.getFatPer(),profile.getWeight()};
//            double dif = euclideanDistance(profileValues, userValues);
//            System.out.println(profile.getUser_id());
//            int tmp[] = {Integer.parseInt(profile.getUserId()), (int)dif};
//            res.add(tmp);
//        }
//        Collections.sort(res,(o1,o2)->o1[1]-o2[1]);
//            // 결과 출력
//        for(int i=0;i<10;i++) {
//        	System.out.println("가장 유사한 유저: "+list.get(res.get(i)[0]).getUserId());
//        	System.out.println("data: "+list.get(res.get(i)[0]).toString());
//        }
//    }
}
