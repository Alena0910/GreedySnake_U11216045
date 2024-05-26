public class FruitCounter {

    private static int counter;

    public FruitCounter(){
        counter = 0;
    }
    public int getCounter(){
        return counter;
    }
    public void addCounter(){
        counter++;
    }
    public void resetCounter(){
        counter = 0;
    }
}
