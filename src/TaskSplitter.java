import java.util.ArrayList;

/**
 * Created by tomaszczernuszenko on 13/12/2017.
 */
public class TaskSplitter implements JobDoneListener{
    IlyaFileReader ilyaFileReader;
    ArrayList<String> results;
    int counter;

    public void getInput(){
        ilyaFileReader = new IlyaFileReader();
        ilyaFileReader.readFile();
        counter = 0;
        results = new ArrayList<>();
        for (int i = 0; i < ilyaFileReader.rooms.size(); i++) {
            results.add("");
        }
    }

    public void splitTasks(){
        ArrayList<TaskThread> threads = new ArrayList<>();
        for (int i = 0; i < ilyaFileReader.rooms.size(); i++) {
            threads.add(new TaskThread(new CaseSolver(ilyaFileReader.rooms.get(i),ilyaFileReader.decorations.get(i)), i+1, this));
            threads.get(i).run();
        }
    }

    @Override
    public void notifyJobDone(String s, int order) {
        results.set(order-1, s);
        counter++;
        System.out.println(order);
        if(counter == ilyaFileReader.rooms.size()){
            OutputHandler handler = new OutputHandler();
            try {
                handler.generateOutputFile(results);
            } catch (Exception e){
                System.out.println("Houston we have a problem;");
                System.out.println(e.toString());
            }
        }
    }
}
