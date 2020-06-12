package Test;

import com.google.gson.Gson;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestVO vo = new TestVO();
		vo.setName("123");
		vo.setPw("123");
		
		
		Gson gson = new Gson();
		
		
		String jsonString = gson.toJson(vo);
		System.out.println("jsonString: " + jsonString);
		
		
		
		TestVO jsonObject = gson.fromJson(jsonString, TestVO.class);
		System.out.println("jsonObject: " + jsonObject);
		
	}

}
