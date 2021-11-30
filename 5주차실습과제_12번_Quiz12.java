/*****************************
* 실습 12번

* 작성자 :  2017038076 조준희

* 작성일 : 2021-10-01

*********************************/
import java.util.Scanner;

abstract class Shape{	//추상클래스
	public Shape next;
	
	public Shape() {	//next null로초기화
		next = null;
	}
	
	public void setNext(Shape obj) {	//객체 주소 받아서 다음순서에 저장
		next = obj;
	}
	
	public Shape getNext() {	//다음순서를 넘겨줌
		return next;
	}
	
	public abstract void draw();
}

class Line extends Shape {
	public Line() {
		super();
	}
	
	public void draw() {	//추상메소드 오버라이딩
		System.out.println("Line");
	}
}

class Rect extends Shape {
	public Rect() {
		super();
	}
	
	public void draw() {
		System.out.println("Rect");
	}
}

class Circle extends Shape {
	public Circle() {
		super();
	}
	
	public void draw() {
		System.out.println("Circle");
	}
}

class GraphicEditor{	//그래픽에디터클래스
	Scanner scanner;
	public Shape first,last;	
	
	public GraphicEditor(){		
		first = null;
		last = null;
		scanner = new Scanner(System.in);
	}
	
	void run() {
		int userChoice;	//사용자가 선택한 번호
		System.out.println("그래픽 에디터 beauty를 실행합니다.");
		
		do {
			System.out.println("삽입(1), 삭제(2), 모두 보기(3), 종료(4)>>");
			userChoice = scanner.nextInt();
			
			switch (userChoice) {	//선택한 메뉴에 맞는 기능실행
			case 1 : 
				System.out.println("Line(1), Rect(2), Circle(3)>>");
				int num = scanner.nextInt();
				create(num);
				break;
			case 2 :
				System.out.println("삭제할 도형의 위치>>");
				int index = scanner.nextInt();
				delete(index);
				break;
			case 3 :
				print();
				break;
			case 4 :
				break;
			}
		}while(userChoice!=4);
		
		System.out.println("beauty을 종료합니다.");
	}
	
	void create(int num) {	
		Shape graphic; //레퍼런스변수 선언
		
		switch(num) {	//들어온 번호에 맞는 객체생성
		case 1:
			graphic = new Line();
			break;
		case 2 : 
			graphic = new Rect();
			break;
		case 3:
			graphic = new Circle();
			break;
		default:
			System.out.println("다시입력해주세요");
			return;
		}
		
		if(first==null) {	//아무것도 들어있지않았다면 첫 번째와 마지막 번째에 그래픽을 할당
			first=graphic;
			last=graphic;
		}
		else {
			last.setNext(graphic);	//첫번째가 아니면 마지막에 그래픽 할당 
			last=graphic;
		}
	}
	
	void delete(int index) {
		Shape cur = first;	//첫번째 객체
		Shape temp = first;	
		if(first==null) {	//객체수가 인덱스보다 작으면 삭제할 수 없음
			System.out.println("삭제할 수 없습니다.");
			return;
		}
		if(index==1) {	//첫 번째 객체를 삭제할 떄
			if(first==last) {	//객체가 한개밖에 없다면
				last=first=null;	//삭제
				return;
			}
			else {		//두 개 이상이라면
				first=first.getNext();	//첫번째를 삭제하고 그 뒤에를 첫번째로 저장
				return;
			}
		}
		else {	//인덱스가 존재하는 객체 수보다 큰 지 확인
			for(int i=1; i < index; i++) {	//첫 번째를 제외하고 반복문돌림
				temp = cur;		//현재 객체를 temp에 저장하고
				cur = cur.getNext();	//다음객체를 받는다.
				if(cur==null) {			//index가 객체수보다 크면 삭제불가능
					System.out.println("삭제할 수 없습니다.");
					return;
				}
			}
			if(cur==last) {	//인덱스가 마지막을 가리키면
				last = temp;	//last가 현재 객체를 받고
				last.setNext(null);	//마지막순서 삭제
			}
			else {			//끝이 아니라면 현재 객체 삭제
				temp.setNext(cur.getNext());	
			}
		}
	}
	
	void print() {	//생성된 모든 객체 출력
		Shape d = first;
		while(d!=null) {
			d.draw();
			d=d.getNext();
		}
	}
}

public class Quiz12 {
	public static void main(String[] args) {
		GraphicEditor ge = new GraphicEditor();
		ge.run();	//프로그램실행
	}
}
