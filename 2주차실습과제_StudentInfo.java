/*****************************

* 프로그램명: 학생정보입력 프로그램

* 작성자 :  2017038076 조준희

* 작성일 : 2021-09-10

*프로그램 설명 : 키보드로부터 학번, 이름, 국어, 영어, 수학, C언어 점수를 입력받아 총점, 평균, 학점을 출력하는 프로그램입니다.

*********************************/
import java.util.Scanner;

public class StudentInfo {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("학생의 학번을 입력하세요 : ");
		String StudentId = scanner.next();
		System.out.println("학생의 이름을 입력하세요 : ");
		String StudentName = scanner.next();
		System.out.println("학생의 국어점수를 입력하세요 : ");
		double KoreanScore = scanner.nextDouble();
		System.out.println("학생의 영어점수를 입력하세요 : ");
		double EnglishScore = scanner.nextDouble();
		System.out.println("학생의 수학점수를 입력하세요 : ");
		double MathScore = scanner.nextDouble();
		System.out.println("학생의 C언어점수를 입력하세요 : ");
		double ClangScore = scanner.nextDouble();
		
		double TotalScore = KoreanScore + EnglishScore + MathScore + ClangScore;
		double AvgScore = TotalScore/4.0;
		
		System.out.println("학번은 " + StudentId + "이고 이름은 " + StudentName + "인 학생의 ");
		System.out.println("총점은 : " + TotalScore);
		System.out.println("평균점수는 : " + AvgScore);
		
		if (AvgScore >= 95.0) {
			System.out.println("학점은 A+입니다.");
		}
		else if (AvgScore < 95.0 &&  AvgScore >= 90.0) {
			System.out.println("학점은 A입니다.");
		}
		else if (AvgScore >= 85.0) {
			System.out.println("학점은 B+입니다.");
		}
		else if (AvgScore < 85.0 &&  AvgScore >= 80.0) {
			System.out.println("학점은 B입니다.");
		}
		else if (AvgScore >= 75.0) {
			System.out.println("학점은 C+입니다.");
		}
		else if (AvgScore < 75.0 &&  AvgScore >= 70.0) {
			System.out.println("학점은 C입니다.");
		}
		else {
			System.out.println("학점은 F입니다.");
		}
		
		scanner.close();
	}
}
