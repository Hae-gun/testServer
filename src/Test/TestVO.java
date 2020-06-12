package Test;

public class TestVO {
	private String name;
	private String pw;
	private int number;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "TestVO [name=" + name + ", pw=" + pw + ", number=" + number + "]";
	}
	
	
	
}
