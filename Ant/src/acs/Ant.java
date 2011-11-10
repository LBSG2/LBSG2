package acs;





public class Ant {

	private ACS antColony;
	
	int[] currentTour;
	int currentTourLength;
	double[] prob;
	
	
	boolean[] visited;
	
	int currentCity, nextCity;
	
	public Ant(ACS colony){
		this.antColony = colony;
	}

	public void run() {
		currentTour = new int[antColony.cityCount+1];
		currentTourLength=0;
		prob  = new double[antColony.cityCount];
		visited = new boolean[antColony.cityCount];
		
		for (int i = 0; i < antColony.cityCount; i++) {
			visited[i]=false;
		}
		
		//Zufällige Startstadt
		currentCity = currentTour[0]=currentTour[antColony.cityCount]=antColony.random.nextInt(antColony.cityCount);
		visited[currentCity]=true;
		
		
		
		for (int i = 1; i < antColony.cityCount; i++) { //noch n-1 mal von der startstadt aus bewegen
			
			//Übergangswahrscheinlichkeiten berechnen
				for (int j = 0; j < antColony.cityCount; j++) {
					prob[j] = visited[j] ? 0 : antColony.delta[currentCity][j]* antColony.eta[currentCity][j];
				}
			
			
			//nächste stadt auswählen
			double maxProb;
			double sumProb;
			double q = antColony.random.nextDouble();
			
			nextCity = 0;
			
			if(q<=antColony.q0){
				maxProb = 0;
				for (int j = 0; j < antColony.cityCount; j++) {
					if(prob[j]>maxProb){
						maxProb=prob[j];
						nextCity = j;
					}
						
				}
			}else{
				sumProb=0;
				for (int j = 0; j < antColony.cityCount; j++) {
					sumProb += prob[j];
				}
				
				double rand = antColony.random.nextDouble() * sumProb;
				
				maxProb = 0;
				for (int j = 0; j < antColony.cityCount; j++) {
					maxProb += prob[j];
					if(maxProb >= rand){
						nextCity = j;
						break;
					}
					
				}
				
				
			}

			//local update
			antColony.delta[currentCity][nextCity] = antColony.delta[nextCity][currentCity] =
					(1-antColony.gamma)* antColony.delta[currentCity][nextCity] + antColony.gamma * antColony.tau0;
			currentTour[i]=currentCity=nextCity;
			visited[currentCity]=true;
			
			

			}
		currentTourLength = antColony.computeLength(currentTour);
		if (currentTourLength<antColony.bestTourLength) {
			antColony.bestTour = currentTour;
			antColony.bestTourLength = currentTourLength;
			//System.out.println(bestLength);
			
		}
	}
}
