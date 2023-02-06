package main;

import agent.Agent;
import world.World;

public class TestWorld {
    public static void main(String[] args) {
        double convergence = 0.1;
        World world = new World(3,7, 4, convergence);

        int numberOfSimulationCycles = 30;
        int numberOfQueryCycles = 50;
        int terminalTime = 5;
        
        world.runOneExperiment(numberOfSimulationCycles,numberOfQueryCycles,terminalTime);
        System.out.println("1: " + world.realNumberOfSuccessfulInteractionsGoodAgents());
        System.out.println("2: " + world.realNumberOfUnsuccessfulInteractionsGoodAgents());

    }

    public void launch1(World world) {
        Agent a0 = world.getAgents().get(0);
        Agent a1 = world.getAgents().get(1);
        Agent a2 = world.getAgents().get(2);
        Agent a3 = world.getAgents().get(3);
        Agent a4 = world.getAgents().get(4);

		
		for (int i = 0; i < 50; i++) {
			a0.interactsWith(a1);
			a0.interactsWith(a2);
            a0.interactsWith(a3);
            a0.interactsWith(a4);
			a1.interactsWith(a0);
			a1.interactsWith(a2);
            a1.interactsWith(a3);
            a1.interactsWith(a4);
			a2.interactsWith(a0);
			a2.interactsWith(a1);
            a2.interactsWith(a3);
			a2.interactsWith(a4);
            a3.interactsWith(a0);
			a3.interactsWith(a1);
            a3.interactsWith(a2);
			a3.interactsWith(a4);
            a4.interactsWith(a0);
			a4.interactsWith(a1);
            a4.interactsWith(a2);
			a4.interactsWith(a3);
		}
		
		System.out.println(a0);
		System.out.println(a1);
		System.out.println(a2);
        System.out.println(a3);
		System.out.println(a4);
		

        System.out.println(world.realNumberOfSuccessfulInteractionsGoodAgents());
        System.out.println(world.realNumberOfUnsuccessfulInteractionsGoodAgents());

        System.out.println(world);

        Application.printTrustMatrix(world.gatherTropicalVector().getTrustMatrix());
	}

    
}
