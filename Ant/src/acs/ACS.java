package acs;

import java.util.Random;

public class ACS {
	
	public int cityCount;
	public int distances[][];
	double eta[][];
	double delta[][];
	int bestTour[];
	int bestTourLength = Integer.MAX_VALUE;
	


	//ACS-Params
	static final int antCount = 2;
	static final double itCount = 20000;
	static final double beta = 2;
	static final double gamma = 0.1;
	static final double q0 = 0.9;
	static final double Q = 1.0;
	public double tau0;
	
	private Ant[] ants;
	static final Random random = new Random();
	

	public ACS(int[][] distance){
		
		this.distances = distance;
		cityCount = distances.length;
		bestTour = new int[cityCount];

		

		init();
		run();
		
	}


	
	private void init(){
		
		//calculate tau0 and set bestTour and bestTourLength to the one obtained by nn-search
		tau0 = 1.0 / (cityCount * nearestNeighborSearch());
			
		this.delta = new double[cityCount][cityCount];
		this.eta = new double[cityCount][cityCount];
		
		//create ants
		this.ants = new Ant[antCount];
		for (int i = 0; i < antCount; i++) {
			ants[i]=new Ant(this);
		}
		
		
		//initialize delta and eta matrix
		for (int i = 0; i < cityCount; i++) {
			for (int j = 0; j < cityCount; j++) {
				delta[i][j]=tau0;	
				eta[i][j]= Math.pow(distances[i][j],-beta);	
			}			
		}
		

	}

	
	private void run(){
		for (int t = 0; t < itCount; t++) {
			for (int k = 0; k < antCount; k++) {
				ants[k].run();
				
			}
			
			//global update
			for (int i = 0; i < cityCount; i++) {
				delta[bestTour[i]][bestTour[i+1]]=
						delta[bestTour[i+1]][bestTour[i]]=
						(1-gamma)* delta[bestTour[i]][bestTour[i+1]] +
						gamma * (Q/bestTourLength);
				
			}
		}
	}

	private double nearestNeighborSearch() {
		
		boolean[] visited = new boolean[cityCount];
		int nnTour[] = new int[cityCount+1];
		
		//Start und Endstadt
		nnTour[0]=nnTour[cityCount]=0;
		visited[0]=true;
		
		//nn-Suche
		for (int i = 1; i < cityCount; i++) {
			int nearest = 0;
			for (int j = 0; j < cityCount; j++) {
				if(!visited[j] &&
					(nearest == 0 ||
					distances[nnTour[i-1]][j] < distances[i-1][nearest])){
					
					nearest = j;
				}
			}
			nnTour[i] = nearest;
			visited[nearest]=true;
		}
		bestTour = nnTour;
		bestTourLength = computeLength(nnTour);
		
		return bestTourLength;
	}
	
	public int computeLength(int tour[]){
		int length = 0;
		for (int i = 0; i < cityCount; i++) {
			length += distances[tour[i]][tour[i+1]];
			
	
		}
		
		return length;
	}
	
	public int[] getBestTour(){
		return bestTour;
	}
	
	public int getBestTourLength(){
		return bestTourLength;
	}
}
