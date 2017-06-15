import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
public class A_Rojo extends Aliem
{
    
    public A_Rojo(int valorX){
        setFill(Color.RED);  
        setRadius(12); 
        setCenterX(valorX);
        setCenterY(250);
        puntos=10;
        imagenAliem=new Image("Imagenes/aliem1.png");
        setFill( new ImagePattern(imagenAliem));
    }    
}
