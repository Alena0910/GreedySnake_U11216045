public class Score {
    
    private static int score;
    
    public Score(){
        score = 0;
    }
    
    public void addScore(){
        score++;
    }

    public void subScore(){
        score--;
    }
    
    public int getScore(){
        return score;
    }
    
    public void resetScore(){
        score = 0;
    }
}
