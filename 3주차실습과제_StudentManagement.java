/*****************************

* 프로그램명: 학생관리 프로그램

* 작성자 :  2017038076 조준희

* 작성일 : 2021-09-17

*프로그램 설명 : 키보드로부터 학번, 이름, 국어,영어,수학,C언어 점수를 입력받아 
			총점,평균,학점을 계산하여 입력한 학생정보를 출력해주는 프로그램입니다.

*********************************/
import java.util.InputMismatchException;
import java.util.Scanner; //Scanner 클래스 호출

public class StudentManagenet {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); //Scanner 객체 생성
		
		int n=0;
		
		System.out.println("몇 명의 학생정보를 입력하시겠습니까 : ");
		while(n==0){ //예외처리를 통하여 데이터가 올바르게 입력되는지 확인
			try {
				n = scanner.nextInt();
			}
			catch (InputMismatchException e) {
				System.out.println("숫자를 제대로 입력하세요!");
				scanner.next();
			}
		}
		
		//학생데이터를 받을 각 배열 선언
		String IdArr[] = new String[n];
		String NameArr[] = new String[n];
		double KorArr[] = new double[n];
		double EngArr[] = new double[n];
		double MathArr[] = new double[n];
		double ClangArr[] = new double[n];
		double TotalArr[] = new double[n];
		String GradeArr[] = new String[n];
		
		System.out.println("=================================");
		System.out.println("\t성 적 입 력 처리\t");
		System.out.println("=================================");
		
		
		for(int i=0; i<n; i++) {	//입력할 학생의 수만큼 학생데이터를 입력받음
			System.out.println((i+1)+"번째 학생의 성적읍 입력하시오.");
			System.out.println("학번 : ");
			String StudentId = scanner.next();
			IdArr[i] = StudentId;
			System.out.println("이름 : ");
			String StudentName = scanner.next();
			NameArr[i] = StudentName;
			System.out.println("국어 : ");
			try {	//예외처리를 통하여 데이터가 올바르게 입력되는지 확인
				double KoreanScore = scanner.nextDouble();
				KorArr[i] = KoreanScore;
			}
			catch (InputMismatchException e) {
				System.out.println("숫자를 제대로 입력하세요!");
				scanner.next();
				i--;
				continue;
			}
			System.out.println("영어 : ");
			try {
				double EnglishScore = scanner.nextDouble();
				EngArr[i] = EnglishScore;
			}
			catch (InputMismatchException e) {
				System.out.println("숫자를 제대로 입력하세요!");
				scanner.next();
				i--;
				continue;
			}	
			System.out.println("수학 : ");
			try {
				double MathScore = scanner.nextDouble();
				MathArr[i] = MathScore;
			}
			catch (InputMismatchException e) {
				System.out.println("숫자를 제대로 입력하세요!");
				scanner.next();
				i--;
				continue;
			}
			System.out.println("C언어 : ");
			try {
				double ClangScore = scanner.nextDouble();
				ClangArr[i] = ClangScore;
			}
			catch (InputMismatchException e) {
				System.out.println("숫자를 제대로 입력하세요!");
				scanner.next();
				i--;
				continue;
			}		
			System.out.println("");
		}
		
		
		for(int i=0; i<n; i++) {  //학생의 총점과 평균, 학점계산
			TotalArr[i] = KorArr[i]+EngArr[i]+MathArr[i]+ClangArr[i];
			double AvgScore = TotalArr[i]/(double)4;
			
			if (AvgScore >= 95.0) {
				GradeArr[i] = "A+";
			}
			else if (AvgScore < 95.0 &&  AvgScore >= 90.0) {
				GradeArr[i] = "A";
			}
			else if (AvgScore >= 85.0) {
				GradeArr[i] = "B+";
			}
			else if (AvgScore < 85.0 &&  AvgScore >= 80.0) {
				GradeArr[i] = "B";
			}
			else if (AvgScore >= 75.0) {
				GradeArr[i] = "C+";
			}
			else if (AvgScore < 75.0 &&  AvgScore >= 70.0) {
				GradeArr[i] = "C";
			}
			else {
				GradeArr[i] = "F";
			}
		}
		
		System.out.println("\t\t\t\t\t성적 관리 프로그램");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("학번\t\t이름\t국어\t영어\t수학\tC언어\t총점\t평균\t학점");
		System.out.println("-----------------------------------------------------------------------------");
		for(int i=0; i<n; i++) //최종적으로 입력한 학생데이터를 정리하여 출력
		{
			System.out.println(IdArr[i]+"\t"+NameArr[i]+"\t"+KorArr[i]
					+"\t"+MathArr[i]+"\t"+EngArr[i]+"\t"+ClangArr[i]
							+"\t"+TotalArr[i]+"\t"+(TotalArr[i]/(double)4)+"\t"+GradeArr[i]);
		}
		
		scanner.close();
	}
}
