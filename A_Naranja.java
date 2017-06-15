import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
public class A_Naranja extends Aliem
{
    
    public A_Naranja(int valorX)
    {
        setFill(Color.ORANGE);  
        setRadius(14); 
        setCenterX(valorX);
        setCenterY(145);
        puntos=80;
        imagenAliem=new Image("Imagenes/aliem5.png");
        setFill( new ImagePattern(imagenAliem));
    }
}
