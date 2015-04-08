package a11ProxyChainFlyweight;

import java.math.*;

public abstract class Chain {
	Chain nextLink;

	public void setNext(Chain link) {
		nextLink = link;
	}
	
	abstract protected void handleNum(int num);
}

class PrimeHandler extends Chain {
	protected void handleNum(int num){
		if (isPrime(num)){
			System.out.println("num: " + num + " handled in PrimeHandler");
			// TODO always send across the network to PP
			// PP returns some function of the number
			// PH reports the result in a pop-up window, saying it was obtained remotely
		}
		else nextLink.handleNum(num);
	}
	
	private boolean isPrime(int num){
		BigInteger bi = new BigInteger(Integer.toString(num));
		return bi.isProbablePrime(1);
	}
}

class OddHandler extends Chain {
	protected void handleNum(int num){
		if(num % 2 != 0){
			System.out.println("num: " + num + " handled in OddHandler");
			// TODO "sometimes" send the number across the network to OP
			// OP performs a non-deterministic function
			// OP sends the result back to OH
			// OH reports result in pop-up window with a note saying it was obtained from cache or remotely
		}
		else nextLink.handleNum(num);
	}
}

class EvenHandler extends Chain {
	protected void handleNum(int num){
		System.out.println("num: " + num + " handled in EvenHandler");
		EHServer srvr = new EHServer();
		int result = srvr.mashNum(num);
		// TODO EH puts the result in a pop-up window, and that it was computed locally
		System.out.println("Result computed locally: " + result);
	}
}
