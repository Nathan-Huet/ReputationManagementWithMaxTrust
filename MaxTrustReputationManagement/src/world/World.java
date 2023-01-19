package world;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import agent.Agent;
import agent.GoodAgent;
import agent.ThreatAgent;
import factory.GoodAgentFactory;
import factory.ThreatAFactory;
import model_Tropical.TropicalAtom;
import model_Tropical.TropicalMatrix;

public class World {
    protected double convergence;
    protected ArrayList<Agent> agents;
    protected ArrayList<GoodAgent> goodAgents;
    protected ArrayList<ThreatAgent> threatAgents;

    public World(int numberOfGoodAgent, double convergence){
        this.convergence = convergence;
        int numberOfAgent = numberOfGoodAgent;
        GoodAgentFactory factoryGood = new GoodAgentFactory();
        agents = new ArrayList<>();
        goodAgents = new ArrayList<>();

        for (int i = 0; i < numberOfGoodAgent; i++) {
            Agent agent = factoryGood.getAgent(numberOfAgent);
            goodAgents.add((GoodAgent) agent);
            agents.add(agent);
        }
    }

    public World(int numberOfGoodAgent, int numberOfAgentTheatA , double convergence) {
        this.convergence = convergence;
        int numberOfAgent = numberOfGoodAgent + numberOfAgentTheatA;

        GoodAgentFactory factoryGood = new GoodAgentFactory();
        agents = new ArrayList<>();
        goodAgents = new ArrayList<>();

        Agent agent;
        for (int i = 0; i < numberOfGoodAgent; i++) {
            agent = factoryGood.getAgent(numberOfAgent);
            goodAgents.add((GoodAgent) agent);
            agents.add(agent);
        }

        ThreatAFactory factoryThreatA = new ThreatAFactory();
        threatAgents = new ArrayList<>();        
        for (int i = 0; i < numberOfAgentTheatA; i++) {
            agent = factoryThreatA.getAgent(numberOfAgent);
            threatAgents.add((ThreatAgent) agent);
            agents.add(agent);
        }
    }

    /**
     * Retourne le nombre d'agents appartenant au monde
     * @return le nombre d'agents appartenant au monde
     */
    public int getNumberOfAgent() {
        return agents.size();
    }

    /**
     * Retourne les agents appartenant au monde
     * @return liste des agents 
     */
    public ArrayList<Agent> getAgents() {
        return agents;
    }

    /**
     * Retourne les agents bons appartenant au monde
     * @return liste des agents bons 
     */
    public ArrayList<GoodAgent> getGoodAgents() {
        return goodAgents;
    }

    /**
     * Retourne les agents malveillants appartenant au monde
     * @return liste des agents malveillants
     */
    public ArrayList<ThreatAgent> getThreatAgents() {
        return threatAgents;
    }

    @Override
    public String toString() {
        return "Number of Agents : " + getNumberOfAgent() + ", Good Agents : " + goodAgents + ", Threat Agents : " + threatAgents;
    }

    /**
     * Retourne le nombre total d'interactions réussies avec des bons agents comme émetteur de la demande d'interaction
     * @return Le nombre total d'interactions réussies avec des bons agents comme émetteur de la demande d'interaction
     */
    public int numberOfSuccessfulInteractionsGoodAgents() {
        int result = 0;
        for (GoodAgent goodAgent : goodAgents) {
            int[] numberOfSuccess = goodAgent.getNumberOfSuccessfulInteractions();
            for (int i = 0; i < numberOfSuccess.length; i++) {
                result += numberOfSuccess[i];
            }
        }
        return result;
    }

    /**
     * Retourne le nombre total d'interactions non réussies avec des bons agents comme émetteur de la demande d'interaction
     * @return Le nombre total d'interactions non réussies avec des bons agents comme émetteur de la demande d'interaction
     */
    public int numberOfUnsuccessfulInteractionsGoodAgents() {
        int result = 0;
        for (GoodAgent goodAgent : goodAgents) {
            int[] numberOfFailure = goodAgent.getNumberOfUnsuccessfulInteractions();
            for (int i = 0; i < numberOfFailure.length; i++) {
                result += numberOfFailure[i];
            }
        }
        return result;
    }

    /**
     * Rassemble le vecteur de confiance de tous les Agents dans une Matrice Tropicale
     * @return la matrice tropicale résultante
     */
    public TropicalMatrix gatherTropicalVector(){
        TropicalAtom[][] tropicalMatrix = new TropicalAtom[agents.size()][];
        for (int i = 0; i < agents.size(); i++) {
            tropicalMatrix[i] = agents.get(i).getTrustVector();
        }
        return new TropicalMatrix(getNumberOfAgent(), convergence, tropicalMatrix);
    }


    /**
     * Appellera les fonctions pour calculer la matrice de confiance
     * @return
     */
    public TropicalMatrix computeTropicalMatrix() {
        return gatherTropicalVector();
    }

    public void runOneSimulationCycle(int numberOfQueryCycles) {
        for (int i = 0; i < numberOfQueryCycles; i++) {
            System.out.println("Query cycle n°" + (i+1));
            runOneQueryCycle();
        }
    }

    //TODO
    public void runOneQueryCycle() {
        Random random = new Random();
        LinkedList<Agent> agentsListening = new LinkedList<>();
        LinkedList<Agent> agentsIssuingQuery = new LinkedList<>();
        for (Agent agent : agents) {
            boolean isListening = random.nextBoolean();
            boolean isIssuingQuery = random.nextBoolean();
            if (isListening) {
                agentsListening.add(agent);
            }
            if (isIssuingQuery) {
                agentsIssuingQuery.add(agent);
            }
        }    
        //System.out.println("agentsListening : " + agentsListening);
        //System.out.println("agentsIssuingQuery : " + agentsIssuingQuery);
        int numberOfAgentQueryWithoutResponse = 0;
        LinkedList<Agent> toRemove = new LinkedList<>();
        for (Agent agent : agentsIssuingQuery) {
            if (agentsListening.contains(agent)) {
                if (agentsListening.size() > 1) {
                    Agent peer;
                    do {
                        int positionOfAgent = random.nextInt(agentsListening.size());
                        peer = agentsListening.get(positionOfAgent);    
                    } while (agent.equals(peer));
                    agentsListening.remove(peer);
                    agent.interactsWith(peer);
                    toRemove.add(agent);
                }else{
                    numberOfAgentQueryWithoutResponse++;
                    System.out.println("Query without response : " + numberOfAgentQueryWithoutResponse);
                    toRemove.add(agent);
                }
            }
        }
        //System.out.println("PASS 1");

        for (Agent agent : toRemove) {
            agentsIssuingQuery.remove(agent);
        }

        for (Agent agent : agentsIssuingQuery) {
            if (agentsListening.size() > 0) {
                Agent peer;
                do {
                    int positionOfAgent = random.nextInt(agentsListening.size());
                    peer = agentsListening.get(positionOfAgent);    
                } while (agent.equals(peer));
                agentsListening.remove(peer);
                agent.interactsWith(peer);
            }
            else{
                numberOfAgentQueryWithoutResponse++;
                System.out.println("Query without response : " + numberOfAgentQueryWithoutResponse);
            }
        }
    }

}
