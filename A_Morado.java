import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
/**
 * Write a description of class A_Negro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class A_Morado extends Aliem
{
    
    public A_Morado(int valorX)
    {
        setFill(Color.PURPLE);  
        setRadius(16);
        setCenterX(valorX);
        setCenterY(110);
        puntos=160;
        imagenAliem=new Image("Imagenes/aliem3.png");
        setFill( new ImagePattern(imagenAliem));
    }

}
