
/**
 * Created by tomaszczernuszenko on 13/12/2017.
 */
public class TaskThread extends Thread {

    CaseSolver solver;
    CaseSolver currentMax;
    int order;
    JobDoneListener listener;

    public TaskThread(CaseSolver solver, int order, JobDoneListener listener){
        this.solver = solver;
        this.currentMax = solver;
        this.order = order;
        this.listener = listener;
    }

    public void run() {
        int attempts=0;
        while (attempts < 20) {
            solver.solve(order);
            currentMax = ((solver.getTotalCost()>currentMax.getTotalCost()&&(solver.getCoverage()>30))) ? solver:currentMax;
            this.solver = new CaseSolver(this.solver.room, this.solver.decorations);
            attempts++;
        }
        listener.notifyJobDone(extractResult(), order);
    }

    public String extractResult(){
        System.out.println(currentMax.getCoverage());
        return currentMax.extractResult();
    }
}
