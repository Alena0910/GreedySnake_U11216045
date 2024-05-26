import java.security.SecureRandom;
import java.awt.Color;

public class RandomMode {

    private static int mode;
    private static int fruitColor;

    Color[] color = {
        new Color(193, 41, 46), // 紅色
        new Color(247, 184, 1), // 橘黃色
        new Color(241, 211, 2), // 黃色
        new Color(97, 226, 148), // 綠色
        new Color(87, 226, 229), // 藍色
        new Color(69, 66, 90), // 紫色
        new Color(236, 145, 216), // 粉紅色
        new Color(156, 175, 183), // 淺藍色
        new Color(241, 171, 134), // 淺橘色
        new Color(247, 219, 167), // 淺黃色
        new Color(238, 239, 168), // 淺綠色
        new Color(234, 99, 140), // 淺紅色
        new Color(99, 26, 134), // 深紫色
        new Color(196, 90, 179), // 深粉紅色
        new Color(244, 88, 102), // 深紅色
        new Color(91, 97, 138), // 深藍色
        new Color(6, 123, 194), // 深藍色
        new Color(132, 188, 218), // 淺藍色
    };

    public RandomMode(){
        mode = 0;
        fruitColor = 0;
    }

    public void setMode(){
        SecureRandom sr = new SecureRandom();
        mode = sr.nextInt(5);
        fruitColor = sr.nextInt(18);
    }

    public int getMode(){
        return mode;
    }

    public Color getFruitColor(){
        return color[fruitColor];
    }
}
