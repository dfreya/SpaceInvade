import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
/**
 * Write a description of class Disparo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Disparo extends Circle
{
    protected int velocidadEnY;
    public Disparo(int valorX,int valorY)
    {
        setFill(Color.BLACK);  
        setRadius(5); 
        setCenterX(valorX);
        setCenterY(valorY);
        
    }

    public void moverDisparo(){
        setTranslateY(getTranslateY() + velocidadEnY);
    }
    
}
