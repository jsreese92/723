package a11ProxyChainFlyweight;

//Purpose.  Flyweight design pattern
//
//1. Identify shareable state (intrinsic) and non-shareable state (extrinsic)
//2. Create a Factory that can return an existing object or a new object
//3. The client must use the Factory instead of "new" to request objects
//4. The client (or a third party) must compute the extrinsic state

import java.awt.*;
import java.awt.event.*;

class FlyweightFactory {
	private static java.util.Hashtable ht = new java.util.Hashtable();
	private static ButtonListener bl = new ButtonListener();
	public static Button makeButton( String num ) {
		if (ht.containsValue( num ))
			return (Button) ht.get( num );        // 2. Return an existing object
		Button btn = new Button( num );          // 1. Identify intrinsic state
		btn.addActionListener( bl );
		ht.put( num, btn );
		return btn;                              // 2. Return a new object
	}
	public static void report() {
		System.out.print( "size=" + ht.size() + "   " );
		for (java.util.Enumeration e = ht.keys(); e.hasMoreElements(); )
			System.out.print( e.nextElement() + " " );
		System.out.println();
	}  
}

class ButtonListener implements ActionListener {
	
	// TODO do this better, instead of making a new chain every time
	private Chain createChain(){
		Chain ph = new PrimeHandler();
		Chain oh = new OddHandler();
		Chain eh = new EvenHandler();

		ph.setNext(oh);
		oh.setNext(eh);
		return ph;
	}

	public void actionPerformed( ActionEvent e) {
		Chain chain = createChain();
		Button      btn  = (Button) e.getSource();
		Component[] btns = btn.getParent().getComponents();
		float stuff[] = new float[500];
		int i = 0;
		for ( ; i < btns.length; i++)
			if (btn == btns[i]) break;
		// 4. A third party must compute the extrinsic state (x and y)
		//    (the Button label is intrinsic state)
		System.out.println( "label-" + e.getActionCommand()
				+ "  x-" + i/FlyweightGUI.NUM   + "  y-" + i%FlyweightGUI.NUM );  // 1. Identify extrinsic state
		chain.handleNum(Integer.parseInt(e.getActionCommand()));
		
	}  
}

class FlyweightGUI {
	public static final int NUM = 15;
	// public static final int RAN = 10;
	

	public static void main( String[] args ) {
		Frame frame = new Frame( "Flyweight Demo" );
		frame.setLayout( new GridLayout( NUM, NUM ) );
		int startInt = 0;
		for (int i=0; i < NUM; i++)
			for (int j=0; j < NUM; j++){
				// 3. The client must use the Factory to request objects
				frame.add( FlyweightFactory.makeButton(Integer.toString(startInt)));
				startInt++;
			}

		// Integer.toString( (int)(Math.random()*RAN) ) ) );

		frame.pack();
		frame.setVisible( true );
		FlyweightFactory.report();
	}  
}

//size=25   24 23 22 21 20 19 18 17 16 9 15 8 14 13 7 12 6 5 11 10 4 3 2 1 0
//label-23  x-0  y-0
//label-7  x-0  y-1
//label-21  x-1  y-1
//label-21  x-4  y-6
//label-7  x-9  y-9
