package main;

import java.util.LinkedList;

import agent.Agent;
import agent.MaliciousCollectiveAgent;
import factory.AgentFactory;
import factory.GoodAgentFactory;
import factory.ThreatAFactory;
import factory.ThreatBFactory;
import factory.ThreatCFactory;
import factory.ThreatDFactory;

public class TestFactory {

	public static void main(String[] args) {
		TestFactory tf = new TestFactory();
		//tf.launch1(3);
		//tf.launch2(3);
		//tf.launch3(3);
		//tf.launch4(3);
		tf.launch5(3);
	}

	public void launch1(int numberOfAgents) {
		AgentFactory factoryGood = new GoodAgentFactory();
		
		Agent a0 = factoryGood.getAgent(numberOfAgents);
		Agent a1 = factoryGood.getAgent(numberOfAgents);
		Agent a2 = factoryGood.getAgent(numberOfAgents);
		
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
	
	public void launch2(int numberOfAgents) {
		AgentFactory factoryGood = new GoodAgentFactory();
		AgentFactory factoryThreatA = new ThreatAFactory();
		
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
	
	public void launch3(int numberOfAgents) {
		AgentFactory factoryGood = new GoodAgentFactory();
		ThreatBFactory factoryThreatB = new ThreatBFactory();
		
		Agent a0 = factoryGood.getAgent(numberOfAgents);
		LinkedList<MaliciousCollectiveAgent> collectif = factoryThreatB.getCollective(numberOfAgents, 2);
		Agent a1 = collectif.get(0);
		Agent a2 = collectif.get(1);
		
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
	
	public void launch4(int numberOfAgents) {
		AgentFactory factoryGood = new GoodAgentFactory();
		ThreatCFactory factoryThreatC = new ThreatCFactory(0.5);
		
		Agent a0 = factoryGood.getAgent(numberOfAgents);
		LinkedList<MaliciousCollectiveAgent> collectif = factoryThreatC.getCollective(numberOfAgents, 2);
		Agent a1 = collectif.get(0);
		Agent a2 = collectif.get(1);
		
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
	
	public void launch5(int numberOfAgents) {
		AgentFactory factoryGood = new GoodAgentFactory();
		ThreatBFactory factoryThreatB = new ThreatBFactory();
		
		
		Agent a0 = factoryGood.getAgent(numberOfAgents);		
		LinkedList<MaliciousCollectiveAgent> collectif = factoryThreatB.getCollective(numberOfAgents, 1);
		MaliciousCollectiveAgent a1 = collectif.get(0);
		LinkedList<MaliciousCollectiveAgent> mechant = new LinkedList<>();
		mechant.add(a1);
		
		ThreatDFactory factoryThreatD = new ThreatDFactory(mechant);
		LinkedList<MaliciousCollectiveAgent> collectifD = factoryThreatD.getCollective(numberOfAgents, 1);
		MaliciousCollectiveAgent a2 = collectifD.get(0);
		
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
