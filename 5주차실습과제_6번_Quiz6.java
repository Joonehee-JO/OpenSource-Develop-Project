/*****************************
* 실습 6번

* 작성자 :  2017038076 조준희

* 작성일 : 2021-10-01

*********************************/
class ColorPoint {	//ColorPoint 클래스
	int x,y;
	String color = null;
	
	ColorPoint(){	//생성자
		x=0;
		y=0;
		color = "BLACK";
	}
	
	ColorPoint(int x, int y){	//인자를 받는 생성자
		this.x = x;
		this.y = y;
	}
	
	void setXY(int x, int y) {	//x,y를 받아서 각 인스턴스 변수에 저장
		this.x = x;
		this.y = y;
	}
	
	void SetColor(String str) {	//str를 받아서 color에 저장
		this.color = str;
	}
	
	  public String toString() {	//문자열출력
	      String str = this.color+"색의 ("+this.x+","+this.y+") 점";
	      return str;
	   }
}

public class Quiz6 {
	public static void main(String[] args) {
		ColorPoint zeroPoint = new ColorPoint();
		System.out.println(zeroPoint.toString()+"입니다.");
		
		ColorPoint cp = new ColorPoint(10,10);
		cp.setXY(5,5);
		cp.SetColor("RED");
		System.out.println(cp.toString()+"입니다.");
	}
}
