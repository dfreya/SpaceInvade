import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
public class A_Verde extends Aliem
{
    
    public A_Verde(int valorX)
    {
        setFill(Color.GREEN);  
        setRadius(14); 
        setCenterX(valorX);
        setCenterY(180);
        puntos=40;
        imagenAliem=new Image("Imagenes/aliem4.png");
        setFill( new ImagePattern(imagenAliem));
    }

}
