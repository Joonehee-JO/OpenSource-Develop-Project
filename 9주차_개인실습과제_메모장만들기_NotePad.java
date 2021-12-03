/*****************************
* 메모장 만들기

* 작성자 :  2017038076 조준희

* 작성일 : 2021-11-03

* 기능 : 메모장의 새파일, 저장, 불러오기, 닫기 기능을 수행. 추가로 글씨색상 변경가능, 정보누를 시 학번,이름 출력

*********************************/
import javax.swing.*; //자바 스윙 임포트
import java.awt.*; //자바 awt 임포트
import java.awt.event.*;
import java.io.*; //파일리더 라이터쓰기 위해 임포트

public class NotePad extends JFrame {
	JTextArea text;
	Container pane;
	File f;
	JMenuBar nav_bar = new JMenuBar();
	JMenu file, info, edit;

	public NotePad() {
		super("OpenSourceNote"); // 부모클래스 생성자 호출
		pane = getContentPane(); // 컨텐츠 영역 선언
		pane.setLayout(new BorderLayout()); // JFrame 배치관리자 설정
		setJMenuBar(nav_bar); // 메뉴바 붙임

		file = new JMenu("파일"); // 파일 편집 정보 생성
		edit = new JMenu("편집");
		info = new JMenu("정보");

		text = new JTextArea(); // 노트 작성할 필드생성
		add(text);

		JMenuItem i_new = new JMenuItem("새파일"); // 각 항목별 이름 붙여주기
		JMenuItem i_open = new JMenuItem("불러오기");
		JMenuItem i_save = new JMenuItem("저장");
		JMenuItem i_close = new JMenuItem("닫기");
		JMenuItem i_color = new JMenuItem("색상");
		JMenuItem i_info = new JMenuItem("정보");

		file.add(i_new); // 파일에 항목들 붙이기
		file.add(i_open);
		file.add(i_save);
		file.add(i_close);
		edit.add(i_color);
		info.add(i_info); // 정보에 항목들 붙이기

		nav_bar.add(file); // 컨테이너에 포함시켜서 완성
		nav_bar.add(edit);
		nav_bar.add(info);

		// 메뉴에서 새파일 클릭했을때 이벤트 처리
		i_new.addActionListener(new ActionListener() { // 액션리스너 클래스 사용
			public void actionPerformed(ActionEvent e) {
				text.setText(""); // text내용을 모두 지운다
			}
		});

		// 메뉴에서 닫기 눌렀을 때 이벤트 처리
		i_close.addActionListener(new ActionListener() { // 액션리스너 클래스 사용
			public void actionPerformed(ActionEvent e) { 
				System.exit(0); // 프로그램 종료
			}
		});

		// 메뉴에서 정보를 클릭했을때 이벤트 처리
		i_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 정보를 누를시 보여지는 문구
				JOptionPane.showMessageDialog(pane, "2017038076_조준희");
			}
		});

		// 메뉴에서 저장을 클릭했을때 이벤트 처리
		i_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser(); // JFileChooser생성
				if (fc.showSaveDialog(NotePad.this) == JFileChooser.CANCEL_OPTION) // cancel 누르면 리턴으로 취소하기
					return;
				f = fc.getSelectedFile();	//getselectedfile메소드를 사용하여 레퍼런스변수에 파일가져와 저장
				fileSave(f); // 파일세이브메소드 사용
				setTitle(f.getName());
			}
		});

		// 메뉴에서 불러오기 클릭했을 때 이벤트 처리
		i_open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				if (fc.showOpenDialog(NotePad.this) == JFileChooser.CANCEL_OPTION)
					return;
				f = fc.getSelectedFile();
				setTitle(f.getName());
				fileRead(f); // 파일리드메소드 사용
			}
		});
		
		//메뉴에서 색상 클릭했을 때 이벤트 처리
		i_color.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JColorChooser cc = new JColorChooser();
				Color color = cc.showDialog(NotePad.this, "글자색", Color.orange);
				text.setForeground(color);
			}
		});
	}

	public static void main(String[] args) {
		NotePad note = new NotePad(); // 객체생성

		note.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x누르면 메모장 닫아줌

		note.setSize(600, 600); // 사이즈 지정
		note.setVisible(true); // 화면에 보이게 함
	}

	// 파일저장 메소드
	public void fileSave(File f) { // 파일객체받아옴
		try {
			PrintStream ps = new PrintStream(f); // 프린트스트림 객체생성
			ps.println(text.getText()); // println메소드 이용하여 텍스트내용을 문자로 출력
			ps.close(); // 다 사용후 클로스
		} catch (IOException e2) { // 오류발생시 노출
			System.out.println("파일저장 오류발생");
		}
	}

	// 파일불러오기 메소드
	private void fileRead(File f) {
		try {
			FileReader fr = new FileReader(f); // 파일리더객체생성
			StringWriter sw = new StringWriter(); // string라이터 객체생성
			while (true) { // 무한반복
				int ch = fr.read(); // 한글자씩 읽어옴
				if (ch == -1) // 더이상읽을 글자가 없어서 -1이 리턴됐다면
					break; // 루프빠져나옴
				sw.write(ch); // write메소드이용하여 글자출력
			}
			text.setText(sw.toString()); // 불러올내용을 텍스트에이리어에붙임
			sw.close(); // sw,fr 사용후 클로즈
			fr.close();
		} catch (FileNotFoundException e2) { // 오류발생 시 노출
			System.out.println("불러올 파일이 없습니다.");
		} catch (IOException e) {
			System.out.println("불러오기 오류발생");
		}
	}
}
