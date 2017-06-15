import javafx.scene.paint.Color;

/**
 * Write a description of class A_Rojo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class A_Rojo extends Aliem
{
    public A_Rojo(int valorX){
        setFill(Color.RED);  
        setRadius(12); 
        setCenterX(valorX);
        setCenterY(250);
    }
    
    public void mover(){}
    
}
