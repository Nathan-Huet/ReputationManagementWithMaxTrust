package main;

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
		Agent a1 = factoryGood.getAgent(idCreator.getAgentId());
		Agent a2 = factoryThreatA.getAgent(idCreator.getAgentId());
		for (int i = 0; i < 50; i++) {
			System.out.println(a2.interactsWith(a1));
		}
		
	}

}
