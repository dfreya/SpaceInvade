import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
public class A_Azul extends Aliem
{
    
    public A_Azul(int valorX){
        setFill(Color.BLUE);  
        setRadius(12); 
        setCenterX(valorX);
        setCenterY(215);
        puntos=20;
        imagenAliem=new Image("Imagenes/aliem2.png");
        setFill( new ImagePattern(imagenAliem));
    }
    
}
