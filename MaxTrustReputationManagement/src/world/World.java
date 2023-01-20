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
    public int realNumberOfSuccessfulInteractionsGoodAgents() {
        int result = 0;
        for (GoodAgent goodAgent : goodAgents) {
            int[] numberOfSuccess = goodAgent.getRealNumberOfSuccessfulInteractions();
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
    public int realNumberOfUnsuccessfulInteractionsGoodAgents() {
        int result = 0;
        for (GoodAgent goodAgent : goodAgents) {
            int[] numberOfFailure = goodAgent.getRealNumberOfUnsuccessfulInteractions();
            for (int i = 0; i < numberOfFailure.length; i++) {
                result += numberOfFailure[i];
            }
        }
        return result;
    }


    /**
     * Retourne le nombre total d'interactions réussies 
     * @return Le nombre total d'interactions réussies 
     */
    public int realNumberOfSuccessfulInteractions() {
        int result = 0;
        for (Agent agent : agents) {
            int[] numberOfSuccess = agent.getRealNumberOfSuccessfulInteractions();
            for (int i = 0; i < numberOfSuccess.length; i++) {
                result += numberOfSuccess[i];
            }
        }
        return result;
    }

    /**
     * Retourne le nombre total d'interactions non réussies
     * @return Le nombre total d'interactions non réussies 
     */
    public int realNumberOfUnsuccessfulInteractions() {
        int result = 0;
        for (Agent agent : agents) {
            int[] numberOfFailure = agent.getRealNumberOfUnsuccessfulInteractions();
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
        boolean[][] agentsListeningAtStep = new boolean[numberOfQueryCycles][agents.size()];
        boolean[][] agentsIssuingQueryAtStep = new boolean[numberOfQueryCycles][agents.size()];
        for (int i = 0; i < numberOfQueryCycles; i++) {
            for (int j = 0; j < agents.size(); j++) {
                agentsIssuingQueryAtStep[i][j] = false;
                agentsListeningAtStep[i][j] = false;
            }
        }

        Random random = new Random();
        for (int i = 0; i < agents.size(); i++) {
            int numberOfStepListening = random.nextInt(numberOfQueryCycles);
            int numberOfStepIssuingQuery = random.nextInt(numberOfQueryCycles/2);
            //System.out.println("A" + i + ", Listening :" +numberOfStepListening);
            //System.out.println("A" + i + ", Issuing :" + numberOfStepIssuingQuery);
            while (numberOfStepListening > 0) {
                int positionOfStep = 0;
                do {
                    positionOfStep = random.nextInt(numberOfQueryCycles);    
                } while (agentsListeningAtStep[positionOfStep][i]);
                agentsListeningAtStep[positionOfStep][i] = true;
                numberOfStepListening--;
            }

            while (numberOfStepIssuingQuery > 0) {
                int positionOfStep = 0;
                do {
                    positionOfStep = random.nextInt(numberOfQueryCycles);    
                } while (agentsIssuingQueryAtStep[positionOfStep][i]);
                agentsIssuingQueryAtStep[positionOfStep][i] = true;
                numberOfStepIssuingQuery--;
            }
        }
        /*System.out.println("Listening");
        Application.printBooleanMatrix(agentsListeningAtStep);
        System.out.println("Issuing");
        Application.printBooleanMatrix(agentsIssuingQueryAtStep);
        */
        for (int i = 0; i < numberOfQueryCycles; i++) {
            //System.out.println("Query cycle n°" + (i+1));
            runOneQueryCycle(agentsIssuingQueryAtStep[i], agentsListeningAtStep[i]);
        }
    }

    public void runOneQueryCycle(boolean[] agentsIssuingQueryAtStep, boolean[] agentsListeningAtStep) {
        LinkedList<Agent> agentsListening = new LinkedList<>();
        LinkedList<Agent> agentsIssuingQuery = new LinkedList<>();
        for (int i = 0; i < agents.size(); i++) {
            if (agentsIssuingQueryAtStep[i]) {
                agentsIssuingQuery.add(agents.get(i));
            }

            if (agentsListeningAtStep[i]) {
                agentsListening.add(agents.get(i));
            }
        }
        Random random = new Random();
        int numberOfAgentQueryWithoutResponse = 0;
        LinkedList<Agent> toRemove = new LinkedList<>();
        for (Agent agent : agentsIssuingQuery) {
            if (agentsListening.contains(agent)) {
                if (agentsListening.size() > 1) {
                    Agent peer;
                    //Refaire la sélection en se basant sur la matrice de confiance 
                    do {
                        int positionOfAgent = random.nextInt(agentsListening.size());
                        peer = agentsListening.get(positionOfAgent);    
                    } while (agent.equals(peer));
                    agentsListening.remove(peer);
                    agent.interactsWith(peer);
                    toRemove.add(agent);
                }else{
                    numberOfAgentQueryWithoutResponse++;
                    //System.out.println("Query without response : " + numberOfAgentQueryWithoutResponse);
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
                //Refaire la sélection en se basant sur la matrice de confiance 
                do {
                    int positionOfAgent = random.nextInt(agentsListening.size());
                    peer = agentsListening.get(positionOfAgent);    
                } while (agent.equals(peer));
                agentsListening.remove(peer);
                agent.interactsWith(peer);
            }
            else{
                numberOfAgentQueryWithoutResponse++;
                //System.out.println("Query without response : " + numberOfAgentQueryWithoutResponse);
            }
        }
    }

}
