
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
            this.solver = new CaseSolver(this.solver.room, this.solver.decorations);
            solver.solve(order);
            attempts++;
        } while (solver.getCoverage() < 30 && attempts < 20);
        listener.notifyJobDone(extractResult(), order);
    }

    public String extractResult(){
        System.out.println(solver.getCoverage());
        return solver.extractResult();
    }
}
