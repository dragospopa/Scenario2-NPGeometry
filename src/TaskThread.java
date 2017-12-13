import com.sun.javafx.tk.Toolkit;

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
        solver.solve(order);
        listener.notifyJobDone(extractResult(), order);
    }

    public String extractResult(){
        return solver.extractResult();
    }
}
