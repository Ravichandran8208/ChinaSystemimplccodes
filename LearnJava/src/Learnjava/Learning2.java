package Learnjava;

public class Learning2 {

	public static void main(String[] args) {
		String str="welcome",ntstr=" ";
		char ch;
		StringBuilder obj= new StringBuilder("welcome");
		StringBuffer obj1=new StringBuffer("welcome");
		System.out.println(obj.reverse());
		System.out.println(obj1.reverse());
		for(int i=0;i<str.length();i++) {
			ch=str.charAt(i);
			ntstr=ch+ntstr;
			

   }
		
		
		System.out.print(ntstr);	
		
	}
}

