package world;

import java.util.ArrayList;
import java.util.Random;

import agent.Agent;
import agent.GoodAgent;
import agent.ThreatAgent;
import factory.GoodAgentFactory;
import factory.ThreatAFactory;
import model_Tropical.TropicalAtom;
import model_Tropical.TropicalMatrix;

public class World {
    public static int numberOfQueryCycle;
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
        return gatherTropicalVector().maxTrust(initialTrustVector(), terminalTime);
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
     * Execute un cycle de simulation du système
     * @param numberOfQueryCycles nombre de cycle de requête par cycle de simulation
     * @param terminalTime le temps terminal que l'on se donne pour maxTrust
     */
    public void runOneSimulationCycle(int numberOfQueryCycles, int terminalTime) {
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

        for (GoodAgent trustedPeer : trustedPeers) {
            for (int i = 0; i < numberOfQueryCycles; i++) {
                agentsIssuingQueryAtStep[i][trustedPeer.getId()] = true;
                agentsListeningAtStep[i][trustedPeer.getId()] = true;
            }
        }
        /*System.out.println("Listening");
        Application.printBooleanMatrix(agentsListeningAtStep);
        System.out.println("Issuing");
        Application.printBooleanMatrix(agentsIssuingQueryAtStep);
        */
        for (int i = 0; i < numberOfQueryCycles; i++) {
            World.numberOfQueryCycle = i+1;
            System.out.println("Query cycle n°" + (i+1));
            TropicalAtom[] tropicalTrustVector = computeTropicalTrustVector(terminalTime);
            //System.out.println("COUCOU");
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
        //ArrayList<Integer> agentsListeningPosition = new ArrayList<>();
        ArrayList<Double> agentsListeningProbability = new ArrayList<>();
        double normalisation = 0;

        for (int i = 0; i < agents.size(); i++) {
            if (agentsIssuingQueryAtStep[i]) {
                agentsIssuingQuery.add(agents.get(i));
            }

            if (agentsListeningAtStep[i]) {
                agentsListening.add(agents.get(i));
                normalisation += tropicalTrustVector[i].getValue();
                //agentsListeningPosition.add(i);
                agentsListeningProbability.add(tropicalTrustVector[i].getValue());
            }
        }
        normalisation = 1/normalisation;
        for (int i = 0; i < agentsListeningProbability.size(); i++) {
            agentsListeningProbability.set(i, agentsListeningProbability.get(i) * normalisation);
        }


        Random random = new Random();
        int numberOfAgentQueryWithoutResponse = 0;
        //LinkedList<Agent> toRemove = new LinkedList<>();

        for (Agent agent : agentsIssuingQuery) {
            ArrayList<Agent> agentsListeningWork = new ArrayList<>();
            ArrayList<Double> agentsListeningProbabilityWork = new ArrayList<>();
            agentsListeningWork.addAll(agentsListening);
            agentsListeningProbabilityWork.addAll(agentsListeningProbability);
            if (agentsListening.contains(agent)) {
                int position = agentsListening.indexOf(agent);
                agentsListeningWork.remove(position);
                agentsListeningProbabilityWork.remove(position);
                normalisation = probaChosingUnknown;
                for (int index = 0; index < agentsListeningProbabilityWork.size(); index++) {
                    normalisation += agentsListeningProbabilityWork.get(index);
                }
                normalisation = 1/normalisation;
                for (int index = 0; index < agentsListeningProbabilityWork.size(); index++) {
                    agentsListeningProbability.set(index, agentsListeningProbability.get(index) * normalisation);
                }
            }

            double selection = random.nextDouble();
            double somme = probaChosingUnknown;
            Agent peer;
            boolean[] unknownAgents = agent.getUnknownAgents();
            ArrayList<Agent> unknownAgentsList = new ArrayList<>();
            for (Agent potentialPeer : agentsListeningWork) {
                if (unknownAgents[potentialPeer.getId()]) {
                    unknownAgentsList.add(potentialPeer);
                }
            }
            if (selection <= probaChosingUnknown && unknownAgentsList.size()>0) {
                peer = unknownAgentsList.get(random.nextInt(unknownAgentsList.size()));
                agent.interactsWith(peer);
            }else{
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
            
            if (agentsListeningProbabilityWork.size() == 0) {
                numberOfAgentQueryWithoutResponse++;
                System.out.println("Query without response in this cycle: " + numberOfAgentQueryWithoutResponse);
            }
            //tirer un double 
                //faire une somme de agentsListeningProbability etape par etape
                // garder l etape n-1 
                //si somme(n-1) < double < somme (n)
                //peer = n
                //remove agentsListeningPosition, agentsListeningProbability,agentsListening
                //to remove agentsIssuingQuery
        }


        /*for (Agent agent : agentsIssuingQuery) {
            if (agentsListening.contains(agent)) {*-///////=*$&²²&²
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
                toRemove.add(agent);
            }
            else{
                numberOfAgentQueryWithoutResponse++;
                //System.out.println("Query without response : " + numberOfAgentQueryWithoutResponse);
                toRemove.add(agent);
            }
        }*/
    }

}
