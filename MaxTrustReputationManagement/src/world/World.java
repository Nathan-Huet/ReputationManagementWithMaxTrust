package world;

import java.util.ArrayList;
import java.util.Random;

import agent.Agent;
import agent.GoodAgent;
import agent.ThreatAgent;
import factory.GoodAgentFactory;
import factory.ThreatAFactory;
import factory.ThreatBFactory;
import model_Tropical.TropicalAtom;
import model_Tropical.TropicalMatrix;

public class World {
   
    protected double probaChosingUnknown = 0.1;
    protected double convergence;
    protected ArrayList<Agent> agents;
    protected ArrayList<GoodAgent> trustedPeers;
    protected ArrayList<GoodAgent> goodAgents;
    protected ArrayList<ThreatAgent> threatAgents;

    public World(int numberOfTrustedPeers,int numberOfGoodAgent, double convergence){
        this.convergence = convergence;
        int numberOfAgent = numberOfTrustedPeers + numberOfGoodAgent;

        GoodAgentFactory factoryGood = new GoodAgentFactory();
        agents = new ArrayList<>();
        goodAgents = new ArrayList<>();
        trustedPeers = new ArrayList<>();

        Agent agent;
        for (int i = 0; i < numberOfTrustedPeers; i++) {
            agent = factoryGood.getAgent(numberOfAgent);
            trustedPeers.add((GoodAgent) agent);
            agents.add(agent);
        }
        for (int i = 0; i < numberOfGoodAgent; i++) {
            agent = factoryGood.getAgent(numberOfAgent);
            goodAgents.add((GoodAgent) agent);
            agents.add(agent);
        }
    }

    public World(int numberOfTrustedPeers,int numberOfGoodAgent, int numberOfAgentTheatA , double convergence) {
        this.convergence = convergence;
        int numberOfAgent = numberOfTrustedPeers + numberOfGoodAgent + numberOfAgentTheatA;

        GoodAgentFactory factoryGood = new GoodAgentFactory();
        agents = new ArrayList<>();
        goodAgents = new ArrayList<>();
        trustedPeers = new ArrayList<>();

        Agent agent;
        
        for (int i = 0; i < numberOfTrustedPeers; i++) {
            agent = factoryGood.getAgent(numberOfAgent);
            trustedPeers.add((GoodAgent) agent);
            agents.add(agent);
        }
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
     * calcule le vecteur de confiance globale du système
     * @param terminalTime le temps terminal que l'on se donne pour maxTrust
     * @return vecteur de probabilité (réputation pour chaque agent du système)
     */
    public TropicalAtom[] computeTropicalTrustVector(int terminalTime) {
        return gatherTropicalVector().computeMaxTrust(initialTrustVector(), terminalTime);
    }

    /**
     * retourne le vecteur de confiance initial 
     * @return vecteur de confiance initial
     */
    public TropicalAtom[] initialTrustVector() {
        TropicalAtom[] trustVector = new TropicalAtom[getNumberOfAgent()];
        double initialProba = 1.0/trustedPeers.size();
        for (int i = 0; i < trustVector.length; i++) {
            trustVector[i] = new TropicalAtom(0.0);
        }
        for (GoodAgent peer : trustedPeers) {
            trustVector[peer.getId()] = new TropicalAtom(initialProba);
        }
        return trustVector;
    }

    /**
     * Execute une experience 
     * @param numberOfSimulationCycles nombre de cycle de simulation par experience
     * @param numberOfQueryCycles nombre de cycle de requête par cycle de simulation
     * @param terminalTime le temps terminal que l'on se donne pour maxTrust
     */
    public void runOneExperiment(int numberOfSimulationCycles, int numberOfQueryCycles, int terminalTime) {
        SimulationLogger.getSimulationLogger().newSimulationCycle();
        System.out.println("Simulation cycle n°" + SimulationLogger.getSimulationLogger().getSimulationCycle());
        runOneSimulationCycle(numberOfQueryCycles, initialTrustVector());
        for (int i = 0; i < numberOfSimulationCycles - 1; i++) {
            SimulationLogger.getSimulationLogger().newSimulationCycle();
            System.out.println("Simulation cycle n°" + SimulationLogger.getSimulationLogger().getSimulationCycle());
            SimulationLogger.getSimulationLogger().resetQueryCycles();
            TropicalAtom[] tropicalTrustVector = computeTropicalTrustVector(terminalTime);
            runOneSimulationCycle(numberOfQueryCycles, tropicalTrustVector);
           
        }
        
    }

    /**
     * Execute un cycle de simulation du système
     * @param numberOfQueryCycles nombre de cycle de requête par cycle de simulation
     * @param terminalTime le temps terminal que l'on se donne pour maxTrust
     */
    public void runOneSimulationCycle(int numberOfQueryCycles, TropicalAtom[] tropicalTrustVector) {
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

        for (GoodAgent trustedPeer : trustedPeers) {
            for (int i = 0; i < numberOfQueryCycles; i++) {
                agentsIssuingQueryAtStep[i][trustedPeer.getId()] = true;
                agentsListeningAtStep[i][trustedPeer.getId()] = true;
            }
        }

        for (int i = 0; i < numberOfQueryCycles; i++) {
            SimulationLogger.getSimulationLogger().newQueryCycle();
            System.out.println("Query cycle n°" + SimulationLogger.getSimulationLogger().getQueryCycle());            
            runOneQueryCycle(agentsIssuingQueryAtStep[i], agentsListeningAtStep[i],tropicalTrustVector);
        }
    }

    /**
     * Execute un cycle de requête
     * @param agentsIssuingQueryAtStep vecteur booléen indiquant si un agent fait une requête sur ce cycle
     * @param agentsListeningAtStep vecteur booléen indiquant si un agent écoute les requêtes sur ce cycle
     * @param tropicalTrustVector vecteur de confiance global du système (réputation des agents)
     */
    public void runOneQueryCycle(boolean[] agentsIssuingQueryAtStep, boolean[] agentsListeningAtStep, TropicalAtom[] tropicalTrustVector) {
        ArrayList<Agent> agentsListening = new ArrayList<>();
        ArrayList<Agent> agentsIssuingQuery = new ArrayList<>();
        ArrayList<Double> agentsListeningProbability = new ArrayList<>();
        double normalisation = 0;

        for (int i = 0; i < agents.size(); i++) {
            if (agentsIssuingQueryAtStep[i]) {
                agentsIssuingQuery.add(agents.get(i));
            }

            if (agentsListeningAtStep[i]) {
                agentsListening.add(agents.get(i));
                normalisation += tropicalTrustVector[i].getValue();
                agentsListeningProbability.add(tropicalTrustVector[i].getValue());
            }
        }
        Random random = new Random();

        for (Agent agent : agentsIssuingQuery) {
            ArrayList<Agent> agentsListeningWork = new ArrayList<>();
            ArrayList<Double> agentsListeningProbabilityWork = new ArrayList<>();
            agentsListeningWork.addAll(agentsListening);
            agentsListeningProbabilityWork.addAll(agentsListeningProbability);
            if (agentsListening.contains(agent)) {
                int position = agentsListening.indexOf(agent);
                agentsListeningWork.remove(position);
                agentsListeningProbabilityWork.remove(position);
               
            }

            double selection = random.nextDouble();
            double somme = 0;
            Agent peer;
            boolean[] unknownAgents = agent.getUnknownAgents();
            ArrayList<Agent> unknownAgentsList = new ArrayList<>();
            ArrayList<Agent> knownAgentsList = new ArrayList<>();
            ArrayList<Agent> toRemove = new ArrayList<>();
            ArrayList<Integer> toRemoveProbability = new ArrayList<>();
            for (Agent potentialPeer : agentsListeningWork) {
                if (unknownAgents[potentialPeer.getId()]) {
                    unknownAgentsList.add(potentialPeer);
                    int position = agentsListeningWork.indexOf(potentialPeer);
                    toRemove.add(potentialPeer);
                    toRemoveProbability.add(position);
                }else{
                    knownAgentsList.add(potentialPeer);
                }
            }
            for (Agent remove : toRemove) {
                agentsListeningWork.remove(remove);
            }
            for (int j = toRemoveProbability.size() - 1; j >= 0; j--) {
                agentsListeningProbabilityWork.remove(toRemoveProbability.get(j).intValue());
            }

            if (selection <= probaChosingUnknown && unknownAgentsList.size()>0 || agentsListeningWork.size() < 1 && unknownAgentsList.size()>0) {
                peer = unknownAgentsList.get(random.nextInt(unknownAgentsList.size()));
                agent.interactsWith(peer);

            }else{
                if (agentsListeningProbabilityWork.size() == 0) {
                    SimulationLogger.getSimulationLogger().newAgentQueryWithoutResponse();
                    System.out.println("Query without response in this cycle: " + SimulationLogger.getSimulationLogger().getNumberOfAgentQueryWithoutResponse());
                    break;
                }

                normalisation = 0;
                for (int index = 0; index < agentsListeningProbabilityWork.size(); index++) {
                    normalisation += agentsListeningProbabilityWork.get(index);
                }
                normalisation = 1/normalisation;
                for (int index = 0; index < agentsListeningProbabilityWork.size(); index++) {
                    agentsListeningProbabilityWork.set(index, agentsListeningProbabilityWork.get(index) * normalisation);
                }

                for (int i = 0; i < agentsListeningProbabilityWork.size(); i++) {
                    double tmp = somme + agentsListeningProbabilityWork.get(i);
                    if (selection > somme && selection <= tmp) {
                        peer = agentsListeningWork.get(i);
                        agent.interactsWith(peer);
                        break;
                    }
                    somme = tmp;
                }

                
            }
            
            
           
        }


    }

}
