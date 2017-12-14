import java.util.Random;

public class IlyaCoordinate {
    private double X;
    private double Y;

    public IlyaCoordinate(double x, double y) {
        this.X=x;
        this.Y=y;
    }

    public IlyaCoordinate(double minX, double maxX, double minY, double maxY){
        Random r = new Random();
        X = minX + (maxX - minX) * r.nextDouble();
        Y = minY + (maxY - minY) * r.nextDouble();
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public IlyaCoordinate clone(){
        return new IlyaCoordinate(this.X, this.Y);
    }

    @Override
    public String toString() {
        return "IlyaCoordinate{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }
}
