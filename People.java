package fanal;

public class People {
	String id;
	String name;
}

class Employee extends People{//�����ȣ�� 0���� ���̵�� �̸� ����
	int num=0;
	void set_data(String id, String name) {
		this.id = id;
		this.name = name;
	}
}

class manager extends People{//�����ڹ�ȣ�� 1�� ���� ���̵�� �̸� ����
	int num=1;
	void set_data(String id, String name) {
		this.id = id;
		this.name = name;
	}
}
