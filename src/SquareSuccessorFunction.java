import aima.search.framework.SuccessorFunction;
import aima.search.framework.Successor;
import java.util.ArrayList;
import java.util.List;

public class SquareSuccessorFunction implements SuccessorFunction {
    @Override
    public List<Successor> getSuccessors(Object o) {
        List<Successor> successori = new ArrayList<>();
        if(o instanceof SquareState)
        {
            SquareState stato = (SquareState) o;

            if (stato.mossaValida(SquareState.actions.SU))
                successori.add(new Successor( SquareState.su, stato.movimento(SquareState.actions.SU)));

            if (stato.mossaValida(SquareState.actions.GIU))
                successori.add(new Successor( SquareState.giu, stato.movimento(SquareState.actions.GIU)));

            if (stato.mossaValida(SquareState.actions.SINISTRA))
                successori.add(new Successor( SquareState.sinistra, stato.movimento(SquareState.actions.SINISTRA)));

            if (stato.mossaValida(SquareState.actions.DESTRA))
                successori.add(new Successor( SquareState.destra, stato.movimento(SquareState.actions.DESTRA)));

            if (stato.mossaValida(SquareState.actions.NE))
                successori.add(new Successor( SquareState.ne, stato.movimento(SquareState.actions.NE)));

            if (stato.mossaValida(SquareState.actions.SE))
                successori.add(new Successor( SquareState.se, stato.movimento(SquareState.actions.SE)));
            if (stato.mossaValida(SquareState.actions.SO))
                successori.add(new Successor( SquareState.so, stato.movimento(SquareState.actions.SO)));

            if (stato.mossaValida(SquareState.actions.NO))
                successori.add(new Successor( SquareState.no, stato.movimento(SquareState.actions.NO)));
        }
        return successori;
    }
}

