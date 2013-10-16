 import java.util.Map;
 import java.util.HashMap;
 import java.util.ArrayList;
 class RomanNumeralException extends Exception{
	public RomanNumeralException(String message){
		super(message);
	}
}

public class GRomans{

	public static final String[] romans= {"M", "D", "C", "L", "X", "V", "I"};
	public static  ArrayList<String> romans= new ArrayList<String>(){
		
		{
			add("M");
			add("D");
			add("C");
			add("L");
			add("X");
			add("V");
			add("I");
		}

	};
	public static final int[]  arabics= {1000, 500, 100, 50, 10, 5, 1};

	public static int toArabic(String number){
		int[] numbers = new int[number.length()];
		number = number.toUpperCase();
		for (int i = 0; i < number.length(); i ++){
			numbers[i] = arabics[romans2.indexOf(""+number.charAt(i))];
			if (i>0 && numbers[i]>numbers[i-1]) numbers[i-1]*=-1;
		}

		int out = 0;
		for( int i : numbers)
			out+=i;

		return out;
	}

	public static String toRoman(int number) throws RomanNumeralException{
		
		if (number<0 || number>= 4000) 
			throw new RomanNumeralException("number out of range");
		
		String output = "";
		String[] out = {"", "", "", "", "", "", ""};
		
		int[] divs = new int[7];
		
		for (int i = 0; i< romans.length; i++){
			int div = number / arabics[i]; 
			divs[i] = div;
			number %= arabics[i];
			if (div!=0){
				if (div<=3){
					for (int p = 0; p<div; p++)
						out[i]+=romans[i];
				}else if(div == 4){
					if(divs[i-1]>0)
						out[i] = romans[i-2];
					else
						out[i] = romans[i-1];
					out[i-1] = romans[i];
				}else throw new RomanNumeralException("div more than 4");
			}
		}

		for (String o : out)
			 output+=o;
		return output;

	}

	public static void main(String[] args){
		
		int falseCount = 0;
		try{
			for(int i = 1; i<4000; i++ ){
				if (toArabic(toRoman(i)) != i){
					falseCount++;
					System.out.println("Error: " +i + "to roman is " + toRoman(i) + " but back to arabic its " + toArabic(toRoman(i)) );
				}

			}
			System.out.println(""+falseCount);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

	}
}