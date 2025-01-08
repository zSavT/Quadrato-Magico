import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import aima.search.framework.*;
import aima.search.uninformed.*;
import aima.search.informed.*;


public class SquareDemo {


    public static void main(String[] args) {

        System.out.println("Scegli la tipologia di ricerca:\n\t1) Informata\n\t2) Non informata");
        Scanner scanner = new Scanner(System.in);
        int scelta = scanner.nextInt();
        try {
            SquareState stato = new SquareState(5);
            Problem problema = new Problem(stato, new SquareSuccessorFunction(), stato, stato, stato);
            Search ricerca;
            SearchAgent agente;
            if(scelta == 1)
            {
                ricercaInformata(stato, problema);
            } else if(scelta == 2)
            {
                ricercaNonInformata(stato, problema);
            }else {
                System.out.println("Input errato.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void ricercaNonInformata(SquareState stato, Problem problema) throws Exception {
        SearchAgent agente;
        Search ricerca;
        System.out.println(stato.toString());
        System.out.println("Depth First (without repetition checks):");
        ricerca = new DepthFirstSearch(new TreeSearch());
        agente = new SearchAgent(problema, ricerca);
        stampaAzioni(agente.getActions());
        stampaIstruzioni(agente.getInstrumentation());


        System.out.println("\nDepth First (Graph Search):");
        ricerca = new DepthFirstSearch(new GraphSearch());
        agente = new SearchAgent(problema, ricerca);
        stampaAzioni(agente.getActions());
        stampaIstruzioni(agente.getInstrumentation());


        System.out.println("\nBreadth First (Tree Search):");
        ricerca = new BreadthFirstSearch(new TreeSearch());
        agente = new SearchAgent(problema, ricerca);
        stampaAzioni(agente.getActions());
        stampaIstruzioni(agente.getInstrumentation());
        System.out.println(stato.toString());


        System.out.println("\nBreadth First (Graph Search):");
        ricerca = new BreadthFirstSearch(new GraphSearch());
        agente = new SearchAgent(problema, ricerca);
        stampaAzioni(agente.getActions());
        stampaIstruzioni(agente.getInstrumentation());


        System.out.println("\nDepthLimitedSearch:");
        ricerca = new DepthLimitedSearch(150);
        agente = new SearchAgent(problema, ricerca);
        stampaAzioni(agente.getActions());
        stampaIstruzioni(agente.getInstrumentation());
    }

    private static void ricercaInformata(SquareState stato, Problem problema) throws Exception {
        Search ricerca;
        SearchAgent agente;
        System.out.println(stato.toString());


        System.out.println("\nGreedy (Tree Search):");
        ricerca = new GreedyBestFirstSearch(new TreeSearch());
        agente = new SearchAgent(problema, ricerca);
        stampaAzioni(agente.getActions());
        stampaIstruzioni(agente.getInstrumentation());


        System.out.println("\nAStar (Tree Search):");
        ricerca = new AStarSearch(new TreeSearch());
        agente = new SearchAgent(problema, ricerca);
        stampaAzioni(agente.getActions());
        stampaIstruzioni(agente.getInstrumentation());


        System.out.println("\nAStar (Graph Search):");
        ricerca = new AStarSearch(new GraphSearch());
        agente = new SearchAgent(problema, ricerca);
        stampaAzioni(agente.getActions());
        stampaIstruzioni(agente.getInstrumentation());
    }


    private static void stampaIstruzioni(Properties properties) {
        System.out.println("Info ricerca : ");
        Iterator keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property.toString());
        }

    }

    private static void stampaAzioni(List actions) {
        for (int i = 0; i < actions.size(); i++) {
            String action = (String) actions.get(i);
            System.out.println("Stato (Iterazione : " + i +") : " + action);
        }
    }
}

