package a11ProxyChainFlyweight;

//Purpose.  Proxy design pattern
//1. Create a "wrapper" for a remote, or expensive, or sensitive target
//2. Encapsulate the complexity/overhead of the target in the wrapper
//3. The client deals with the wrapper
//4. The wrapper delegates to the target
//5. To support plug-compatibility of wrapper and target, create an interface

//import java.io.*;   
//import java.net.*;

//the server will have a proxy to talk to sockets
//it also implements the same interface as the proxy the client talks to

interface ServerFace {                  // 5. To support plug-compatibility 
	String handle(String message);       //
	long getStatus();                    //    between the wrapper and the
	int mashNum(int param);        //    target, create an interface 
}

/*
class ServerProxy implements ServerFace {
	EHServer srv = new EHServer();   // the target

	public String handle (String msg) {
		if (msg.equals("bot attack")){
			return "(proxy repels bot attack, not sent to server)"; 
		}
		return srv.handle(msg);    // wrapper delegates to the target
	}	

	public double mashNum (double num) {
		if (num < 0.0) return 0.0;   // no delegate to server
		return srv.mashNum(num);     // wrapper delegates to the target
	}

	public long getStatus () {
		return srv.getStatus();    // always delegate
	}

}
*/

public class EHServer implements ServerFace {  // it also implements the same interface
	// as the proxy the client talks to																
	private long status=0;

	public String handle (String msg) {
		status++;
		return "(server got "+msg+") " + msg.toUpperCase();
	}	
	public int mashNum (int num) {
		status++;
		if (num % 6 == 0)
			return -1;
		else
			return 2*num;
	}
	public long getStatus() {
		return status;
	}

}
