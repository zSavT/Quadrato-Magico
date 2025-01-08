import aima.search.framework.GoalTest;
import aima.search.framework.HeuristicFunction;
import aima.search.framework.StepCostFunction;

public class SquareState implements GoalTest, StepCostFunction, HeuristicFunction, Cloneable {

    static public final String su = "Su";
    static public final String giu = "Giù";
    static public final String sinistra = "Sinistra";
    static public final String destra = "Destra";
    static public final String ne = "Nord Est";
    static public final String se = "Sud Est";
    static public final String so = "Sud Ovest";
    static public final String no = "Nord Ovest";

    public enum actions {SU, GIU, SINISTRA, DESTRA, NE, SE, SO, NO}

    private int dim;
    private int[][] stato;
    private int preX;
    private int preY;
    private int dimCellePopolate;

    SquareState(){}

    public void setDim(int dim) {
        this.dim = dim;
    }

    public void setStato(int[][] stato) {
        this.stato = stato;
    }

    public void setPreX(int preX) {
        this.preX = preX;
    }

    public void setPreY(int preY) {
        this.preY = preY;
    }

    public void setDimCellePopolate(int dimCellePopolate) {
        this.dimCellePopolate = dimCellePopolate;
    }

    public int getDim() {
        return dim;
    }

    public int[][] getStato() {
        return stato;
    }

    public int getValoreStato(int x, int y) {
        return stato[x][y];
    }

    public int getPreX() {
        return preX;
    }

    public int getPreY() {
        return preY;
    }

    public int getDimCellePopolate() {
        return dimCellePopolate;
    }

    SquareState(int dim) {
        this.dim = dim;
        this.stato = new int[dim][dim];
        for (int i = 0; i < dim; i++){
            for(int j = 0; j < dim; j++)
                this.stato[i][j] = 0;
        }
        this.stato[0][0] = 1;
        dimCellePopolate = 1;
        this.preX = 0;
        this.preY = 0;
    }

    private boolean isCellaVuota(int x,int y)
    {
        return this.getValoreStato(x,y) == 0;
    }

    public boolean mossaValida(SquareState.actions azione){
        if(azione == actions.SU)
        {
            return this.preY >= 3 && isCellaVuota(this.preX, this.preY - 3);
        }

        if(azione == actions.GIU){
            return this.preY + 3 < this.dim && isCellaVuota(this.preX, this.preY + 3);
        }

        if(azione == actions.SINISTRA)
        {
            return this.preX >= 3 && isCellaVuota(this.preX - 3, this.preY);
        }

        if(azione == actions.DESTRA){
            return this.preX + 3 < this.dim && isCellaVuota(this.preX + 3, this.preY);
        }

        //in alto a sinistra
        if(azione == actions.NO)
        {
            return this.preY - 2 >= 0 && this.preX - 2 >= 0 && isCellaVuota(this.preX - 2, this.preY - 2);
        }

        //in basso a sinistra
        if(azione == actions.SO)
        {
            return this.preY + 2 < this.dim && this.preX - 2 >= 0 && isCellaVuota(this.preX - 2, this.preY + 2);
        }

        //in alto a destra
        if(azione == actions.NE)
        {
            return this.preY - 2 >= 0 && this.preX + 2 < this.dim && isCellaVuota(this.preX + 2, this.preY - 2);
        }

        //in basso a destra
        if(azione == actions.SE)
        {
            return this.preY + 2 < this.dim && this.preX + 2 < this.dim && isCellaVuota(this.preX + 2, this.preY + 2);
        }
        return false;
    }

    public SquareState movimento(SquareState.actions azione) {
        try {
            SquareState stato = (SquareState) this.clone();

            if (azione == actions.SU) {
                stato.stato[stato.preX][stato.preY - 3] = stato.stato[stato.preX][stato.preY] + 1;
                stato.preY = stato.preY - 3;
            }

            if (azione == actions.GIU) {
                stato.stato[stato.preX][stato.preY + 3] = stato.stato[stato.preX][stato.preY] + 1;
                stato.preY = stato.preY + 3;
            }

            if (azione == actions.SINISTRA) {
                stato.stato[stato.preX - 3][stato.preY] = stato.stato[stato.preX][stato.preY] + 1;
                stato.preX = stato.preX - 3;
            }

            if(azione == actions.DESTRA) {
                stato.stato[stato.preX + 3][stato.preY] = stato.stato[stato.preX][stato.preY] + 1;
                stato.preX = stato.preX + 3;
            }

            if(azione == actions.NO) {
                stato.stato[stato.preX - 2][stato.preY - 2] = stato.stato[stato.preX][stato.preY] + 1;
                stato.preY = stato.preY - 2;
                stato.preX = stato.preX - 2;
            }

            if(azione == actions.NE) {
                stato.stato[stato.preX + 2][stato.preY - 2] = stato.stato[stato.preX][stato.preY] + 1;
                stato.preY = stato.preY - 2;
                stato.preX = stato.preX + 2;
            }

            if(azione == actions.SO) {
                stato.stato[stato.preX - 2][stato.preY + 2] = stato.stato[stato.preX][stato.preY] + 1;
                stato.preY = stato.preY + 2;
                stato.preX = stato.preX - 2;
            }

            if(azione == actions.SE) {
                stato.stato[stato.preX + 2][stato.preY + 2] = stato.stato[stato.preX][stato.preY] + 1;
                stato.preY = stato.preY + 2;
                stato.preX = stato.preX + 2;
            }
            return stato;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isGoalState(Object o) {
        if(o instanceof SquareState stato) {
            for (int i = 0; i < stato.dim; i++)
                for (int j = 0; j < stato.dim; j++)
                    if (stato.stato[i][j] == 0)
                        return false;
            System.out.println("Stato finale raggiunto:\n" + stato);
            return true;
        }
        else
            return false;
    }

    @Override
    public double getHeuristicValue(Object stato) {
        if(stato instanceof SquareState tempStato){
            return tempStato.dim*tempStato.dim - tempStato.dimCellePopolate;
        }
        return Double.NaN;
    }

    @Override
    public Double calculateStepCost(Object o, Object o1, String s) {
        return 1.0;
    }

    public String toString() {
        String out = "[\n";
        for (int i=0; i<this.dim; i++) {
            for (int j=0; j<this.dim; j++) {
                out = out + " " + this.stato[i][j] + ",";
            }
            out = out + "\n";
        }
        out = out + "]";
        return out;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        SquareState temp = (SquareState) super.clone();
        temp.stato = new int[this.dim][this.dim];
        for (int i=0; i<this.dim; i++)
            System.arraycopy(this.stato[i], 0, temp.stato[i], 0, this.dim);
        return temp;
    }
}