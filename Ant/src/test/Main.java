package test;

import acs.*;
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Test t = new Test();
		t.init(true,50); //50 zufällige städte, bei false aus datei
		t.run();
		t.draw();


	}

}
