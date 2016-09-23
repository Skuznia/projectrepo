package regtest;

import java.util.Random;

public class Emailgeneratorv2 {
	public static void main(String[] args){
		Emailgeneratorv2 gen = new Emailgeneratorv2();
		System.out.println(gen.emailgen());
		
	}
	
	public String emailgen(){
		//I know there are more efficient ways for # to letters but I just wanted to make sure this worked asap
		//forgive me in advance for this array
		String[] letters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		Random randgen = new Random();
		//My basic logic is I generate a random length
		//then have a while loop generate random letters for the length of the email
		int length = randgen.nextInt(10) + 1;
		int i = 0;
		int letter = 0;
		String email = "";
		while(i < length){
			letter = randgen.nextInt(26);
			email = email + letters[letter];
			i++;
		}
		return email + "@mailinator.com";
	}
	

}
