import java.security.SecureRandom;

public class RandomMission {

    private static String missionString = "";
    public static int[] mission = new int[5];

    public static void randomMission(){
        SecureRandom sr = new SecureRandom();
        boolean notZero = false;
        for(int i = 0 ; i < 5 ; i++){
            mission[i] = sr.nextInt(10);
            if(mission[i] != 0) notZero = true;
        }
        if(!notZero) mission[0] = 1;
    }

    public static String getMissionString(){
        missionString = "Mission: red=" + mission[0] + " orange=" + mission[1] + " yellow=" + mission[2] + " green=" + mission[3] + " blue=" + mission[4];
        return missionString;
    }

    public static int[] getMission(){
        return mission;
    }
}
