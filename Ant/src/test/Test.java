package test;

import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;

import util.Point;
import util.TspFileReader;
import acs.ACS;

public class Test {
	
	
	public Point[] points;
	static final Random random = new Random();
    JFrame F=new JFrame();
	final int COORD_MAX = 80;
	public int[] bestTour;
	ACS acs;
	public Test(){
		

	}
	
	public void init(boolean random,int n){

		if(random){
			points = new Point[n];
			for (int i = 0; i < points.length; i++) {
				points[i]= getPoint(COORD_MAX);
			}
			return;
		}else{
			
		
			TspFileReader fR = new TspFileReader();
			points = fR.readFile();
		}
	}
	public void run(){

		for (int i = 0; i < 1; i++) {
			
		
			acs = new ACS(buildMatrix(points));
		

			bestTour = acs.getBestTour();
			//System.out.print(getDistance(bestTour));
		}
	}
	

	public void draw(){
		
        F.setTitle("Test");
        F.setSize(300,300);
        F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        F.add(new TestCanvas(this));
        F.setVisible(true);
	}
	
	public static int[][] buildMatrix(Point[] points){
		int[][] temp = new int[points.length][points.length];
		
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points.length; j++) {
				Point pi = points[i];
				Point pj = points[j];
				temp[i][j]=temp[j][i]=(int)Math.sqrt((Math.pow((pi.x-pj.x),2)+Math.pow((pi.y-pj.y),2)));
				//System.out.print("  " + temp[i][j]+"  ,");
			}
			//System.out.println("");
		}
		
		return temp;
	}
	
	public static Point getPoint(int max){
		
		return new Point(random.nextDouble()*max,random.nextDouble()*max);
	}

}
