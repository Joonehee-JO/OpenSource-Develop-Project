/*****************************

* 프로그램명: 학생관리 프로그램

* 작성자 :  2017038076 조준희

* 작성일 : 2021-09-24

*프로그램 설명 : 키보드로부터 학생 수를 입력받은 후 그 수만큼 이름,학번,성적등의 학생정보를
			입력받고 출력,조회 등의 메뉴 기능들을 수행하는 프로그램입니다.

*********************************/
import java.util.InputMismatchException;	//예외처리 위하여 임포트
import java.util.Scanner;	//스캐너객체사용위하여 임포트

class Student{	//학생클래스
	String Id;	//학생 인스턴스 변수들
	String Name;
	double KorScore;
	double EngScore;
	double MathScore;
	double ClangScore;
	double TotalScore;
	double AvgScore;
	String Grade;
	
	Student(){}	//생성자
	
	static int index = 0;	
	
	void CalcScore(){	//총점수와 평균점수를 계산해주는 메소드
		this.TotalScore = this.KorScore + this.EngScore + this.MathScore + this.ClangScore;
		this.AvgScore = this.TotalScore / (double)4;
		CalcGrade();
	}
	
	void CalcGrade() {	//학점을 매겨주는 메소드
		if (this.AvgScore >= 95.0) {
			this.Grade = "A+";
		}
		else if (AvgScore < 95.0 &&  AvgScore >= 90.0) {
			this.Grade = "A";
		}
		else if (AvgScore >= 85.0) {
			this.Grade = "B+";
		}
		else if (AvgScore < 85.0 &&  AvgScore >= 80.0) {
			this.Grade = "B";
		}
		else if (AvgScore >= 75.0) {
			this.Grade = "C+";
		}
		else if (AvgScore < 75.0 &&  AvgScore >= 70.0) {
			this.Grade = "C";
		}
		else {
			this.Grade = "F";
		}
	}

	@Override
	public String toString() {
		return "[Id=" + Id + ", Name=" + Name + ", KorScore=" + KorScore + ", EngScore=" + EngScore
				+ ", MathScore=" + MathScore + ", ClangScore=" + ClangScore + ", TotalScore=" + TotalScore
				+ ", AvgScore=" + AvgScore + ", Grade=" + Grade + "]";
	}
	
}
public class SungJuk {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); //Scanner 객체 생성
		
		System.out.println("몇 명의 학생정보를 입력하십니까");	//입력받을 학생 수
		int numPeople = scanner.nextInt();
		
		Student[] students = new Student[numPeople];	//입력받은 수만큼 객체를 저장할 배열리스트
		Student.index = numPeople;
		
		for(int j=0; j<students.length; j++) {	//배열에 인스턴스 생성
			students[j] = new Student();
		}
		
		while(true) {
			menu();
			int userChoice = scanner.nextInt();
			
			switch (userChoice) {	//스위치문 사용하여 유저가 입력한 넘버에 맞는 기능 사용하기
				case 1 :
					userInput(students);
					break;
				case 2 :
					calcScore(students);
					System.out.println("학생성적을 전부 계산하였습니다.");
					break;
				case 3 :
					for(int i=0; i<Student.index; i++)
						System.out.println(students[i].toString());
					break;
				case 4 : 
					System.out.println("이름으로 검색하고 싶으시면 1을, 학번으로 검색하고 싶으시면 2를 입력해주세요");
					int n = scanner.nextInt();
					if(n==1) {
						System.out.println("찾고싶으신 학생의 이름을 입력해주세요");
						String name = scanner.next();
						searchName(name, students);
						
					}
					if(n==2) {
						System.out.println("찾고싶으신 학생의 학번을 입력해주세요");
						String id = scanner.next();
						searchID(id, students);
					}
					break;
				case 5 : 
					System.out.println("삭제하실 학생의 학번을 입력해주세요");
					String ID = scanner.next();
					deleate(ID, students);
					break;
				case 0 : 
					break;
				default :
					System.out.println("제대로 입력해주세요");
					break;
			}
			if(userChoice == 0)
				break;
		}
		scanner.close();
	}
	
	static void menu() {	//메뉴출력
		System.out.println("1. 성적입력,  2. 성적계산,  3. 전체출력, 4. 조회(4-1. 이름, 4-2. 학번) , 5. 삭제(학번), 0. 종료)");
	}
	
	static void userInput(Student[] students) {	//학생정보를 입력받는 메소드
		Scanner scanner = new Scanner(System.in);
		for(int i=0; i<Student.index; i++) {	
			System.out.println((i+1)+"번째 학생의 정보를 입력하시오.");
			System.out.println("학번 : ");
			students[i].Id = scanner.next();
			System.out.println("이름 : ");
			students[i].Name = scanner.next();
			System.out.println("국어 : ");
			try {	//예외처리를 통하여 데이터가 올바르게 입력되는지 확인
				students[i].KorScore = scanner.nextDouble();
			}
			catch (InputMismatchException e) {
				System.out.println("숫자를 제대로 입력하세요!");
				scanner.next();
				i--;
				continue;
			}
			System.out.println("수학 : ");
			try {	//예외처리를 통하여 데이터가 올바르게 입력되는지 확인
				students[i].MathScore = scanner.nextDouble();
				
			}
			catch (InputMismatchException e) {
				System.out.println("숫자를 제대로 입력하세요!");
				scanner.next();
				i--;
				continue;
			}
			System.out.println("영어 : ");
			try {	//예외처리를 통하여 데이터가 올바르게 입력되는지 확인
				students[i].EngScore = scanner.nextDouble();
			}
			catch (InputMismatchException e) {
				System.out.println("숫자를 제대로 입력하세요!");
				scanner.next();
				i--;
				continue;
			}
			System.out.println("C언어 : ");
			try {	//예외처리를 통하여 데이터가 올바르게 입력되는지 확인
				students[i].ClangScore = scanner.nextDouble();
			}
			catch (InputMismatchException e) {
				System.out.println("숫자를 제대로 입력하세요!");
				scanner.next();
				i--;
				continue;
			}
		}
	}
	
	static void calcScore(Student[] students) {	//학생성적계산하는 메소드
		for(int i=0; i<Student.index; i++)
			students[i].CalcScore();
	}
	
	static void searchName(String name, Student[] students) {	//학생이름을 입력받아 해당이름을 가진 학생이 있으면 그 학생의 정보를 출력해주는 메소드
		for(int i=0; i<Student.index; i++) {
			if(name.equals(students[i].Name))
				System.out.println("찾으시는 학생의 정보 ::: "+students[i].toString());
		}
	}
	
	static void searchID(String Id, Student[] students) {	//학생학번을 입력받아 해당학번을 가진 학생이 있으면 그 정보를 출력해주는 메소드
		for(int i=0; i<Student.index; i++) {
			if(Id.equals(students[i].Id))
				System.out.println("찾으시는 학생의 정보 ::: "+students[i].toString());
		}
	}
	
	static void deleate(String ID, Student[] students) {	//학생학번을 입력받아 해당학번의 정보를 지워주는 메소드
		for(int i=0; i<Student.index; i++)
			if(ID.equals(students[i].Id)) {
				Student.index--;
				Student[] students1 = new Student[Student.index-1];
				for(int j=0; j<students1.length-1; j++)	
					students1[j] = new Student();
				for(int index = 0; index<i; index++)
					students1[index] = students[index];
				for(int j = i; j < Student.index - 1; j++){
                    students1[j] = students[j+1];
                }
				for(int k=0; k<students1.length; k++) {
					students[i] = students1[k];
				}
			}
	}
}
