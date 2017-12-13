
/**
 * Created by tomaszczernuszenko on 13/12/2017.
 */
public class TaskThread extends Thread {

    CaseSolver solver;
    int order;
    JobDoneListener listener;

    public TaskThread(CaseSolver solver, int order, JobDoneListener listener){
        this.solver = solver;
        this.order = order;
        this.listener = listener;
    }

    public void run() {
        int attempts=0;
        do {
            solver.solve(order);
            attempts++;
            this.solver = (solver.getCoverage()<30&&attempts<20) ? new CaseSolver(this.solver.room,this.solver.decorations):this.solver;
        } while (solver.getCoverage()<30&&attempts<20);
        listener.notifyJobDone(extractResult(), order);
    }

    public String extractResult(){
        System.out.println(solver.getCoverage());
        return solver.extractResult();
    }
}
