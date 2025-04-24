import java.io.*;
import java.net.*;
import java.util.*;
import redis.clients.jedis.Jedis;

public class tinyURL {

	static final String AB = "0123456789abcdefghijklmnopqrstuvwxyz";
	static Random rnd = new Random();

	public static String randomString( int len ) {
		StringBuilder sb = new StringBuilder( len );
		for( int i = 0; i < len; i++ ) 
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
			return sb.toString();
	}

	public static void main (String [] args) throws Exception {

		String replyFromUser;
		Jedis jedis = new Jedis();

		// read from the input
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); 
		
		while (true){
			System.out.println("(I)nsert a long URL | (Q)uery a short URL | (S)tatistics | e(X)it");
			replyFromUser = inFromUser.readLine(); //read the reply
			if (replyFromUser.equals("I")) {
				// to be done
			} else if (replyFromUser.equals("Q")) {
				// to be done
			} else if (replyFromUser.equals("S")) {
				// to be done
			} else if (replyFromUser.equals("X")) {
				System.out.println("Goodbye");
				System.exit(1);
			} else {
				System.out.println(replyFromUser + "is not a valid choice, retry");
			}
		}
	}
}
