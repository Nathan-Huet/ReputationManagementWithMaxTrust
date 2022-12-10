package main;

import java.util.Arrays;

import agent.Agent;
import agent.IdCreator;
import factory.AgentFactory;
import factory.GoodAgentFactory;
import factory.ThreatAFactory;

public class TestFactory {

	public static void main(String[] args) {
		AgentFactory factoryGood = new GoodAgentFactory();
		AgentFactory factoryThreatA = new ThreatAFactory();
		IdCreator idCreator = IdCreator.getIdCreator();
		int numberOfAgents = 3;
		
		Agent a0 = factoryGood.getAgent(numberOfAgents);
		Agent a1 = factoryThreatA.getAgent(numberOfAgents);
		Agent a2 = factoryThreatA.getAgent(numberOfAgents);
		
		for (int i = 0; i < 50; i++) {
			a0.interactsWith(a1);
			a0.interactsWith(a2);
			a1.interactsWith(a0);
			a1.interactsWith(a2);
			a2.interactsWith(a0);
			a2.interactsWith(a1);
		}
		
		System.out.println(a0);
		System.out.println(a1);
		System.out.println(a2);
		
	}

}
