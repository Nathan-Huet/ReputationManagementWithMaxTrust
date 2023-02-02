package world;

import agent.Agent;
import agent.IdCreator;

public class SimulationLogger {
    private static SimulationLogger simulationLogger;
    private int numberOfAgents;
    private int[][] numberOfSuccessfulInteractions;
	private int[][] numberOfUnsuccessfulInteractions;
	private int[][] realNumberOfUnsuccessfulInteractions;
	private int[][] realNumberOfSuccessfulInteractions;
    private int numberOfQueryCycles;
    private int numberOfSimulationCycles;
    private int numberOfAgentQueryWithoutResponse;

    private SimulationLogger() {}

    /**
	 * initialise si besoin et retourne l'instance de SimulationLogger
	 * @return l'instance du Singleton SimulationLogger
	 */
	public static SimulationLogger getSimulationLogger() {
		if (simulationLogger == null) {
			simulationLogger = new SimulationLogger();
			simulationLogger.numberOfAgents = IdCreator.getIdCreator().getNumberOfAgent();
            simulationLogger.numberOfQueryCycles = 0;
            simulationLogger.numberOfSimulationCycles = 0;
            simulationLogger.numberOfAgentQueryWithoutResponse = 0;
            int number = simulationLogger.numberOfAgents;
            simulationLogger.numberOfSuccessfulInteractions = new int[number][];
		    simulationLogger.numberOfUnsuccessfulInteractions = new int[number][];
		    simulationLogger.realNumberOfSuccessfulInteractions = new int[number][];
		    simulationLogger.realNumberOfUnsuccessfulInteractions = new int[number][];
            
            for (int i = 0; i < number; i++) {
                simulationLogger.numberOfSuccessfulInteractions[i] = new int[number];
                simulationLogger.numberOfUnsuccessfulInteractions[i] = new int[number];
                simulationLogger.realNumberOfSuccessfulInteractions[i] = new int[number];
                simulationLogger.realNumberOfUnsuccessfulInteractions[i] = new int[number];
                for (int j = 0; j < number; j++) {
                    simulationLogger.numberOfSuccessfulInteractions[i][j] = 0;
                    simulationLogger.numberOfUnsuccessfulInteractions[i][j] = 0;
                    simulationLogger.realNumberOfSuccessfulInteractions[i][j] = 0;
                    simulationLogger.realNumberOfUnsuccessfulInteractions[i][j] = 0;
                }
            }

		}
		return simulationLogger;
	}

    /**
     * Permet à un agent d'évaluer réellement son interaction avec un autre
     * @param thisAgent l'évaluateur
     * @param other l'évalué
     * @param interactionResult l'interaction
     */
    public void realEvaluation(Agent thisAgent, Agent other, boolean interactionResult) {
        if (interactionResult) {
			realNumberOfSuccessfulInteractions[thisAgent.getId()][other.getId()] ++;
		}else {
			realNumberOfUnsuccessfulInteractions[thisAgent.getId()][other.getId()] ++;
		} 
    }

    public void evaluation(Agent thisAgent, Agent other, boolean result) {
        if (result) {
			numberOfSuccessfulInteractions[thisAgent.getId()][other.getId()] ++;
		}else {
			numberOfUnsuccessfulInteractions[thisAgent.getId()][other.getId()] ++;
		}	
    }

    public int[] getNumberOfSuccessfulInteractions(int id) {
        return numberOfSuccessfulInteractions[id];
    }

    public int[] getNumberOfUnsuccessfulInteractions(int id) {
        return numberOfUnsuccessfulInteractions[id];
    }

    public int[] getRealNumberOfSuccessfulInteractions(int id) {
        return realNumberOfSuccessfulInteractions[id];
    }

    public int[] getRealNumberOfUnsuccessfulInteractions(int id) {
        return realNumberOfUnsuccessfulInteractions[id];
    }

    public void newQueryCycle() {
        numberOfQueryCycles++;
    }

    public int getQueryCycle() {
        return numberOfQueryCycles;
    }

    public void resetQueryCycles() {
        numberOfQueryCycles = 0;
    }

    public void newSimulationCycle() {
        numberOfSimulationCycles++;
    }

    public int getSimulationCycle() {
        return numberOfSimulationCycles;
    }

    public void resetSimulationCycles() {
        numberOfSimulationCycles = 0;
    }

    public void newAgentQueryWithoutResponse() {
        numberOfAgentQueryWithoutResponse++;
    }

    public int getNumberOfAgentQueryWithoutResponse() {
        return numberOfAgentQueryWithoutResponse;
    }
}
