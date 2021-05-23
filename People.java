package fanal;

public class People {
	String id;
	String name;
}

class Employee extends People{//사원번호는 0고정 아이디랑 이름 저장
	int num=0;
	void set_data(String id, String name) {
		this.id = id;
		this.name = name;
	}
}

class manager extends People{//관리자번호는 1로 고정 아이디랑 이름 저장
	int num=1;
	void set_data(String id, String name) {
		this.id = id;
		this.name = name;
	}
}
