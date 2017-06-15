import javafx.scene.paint.Color;

public class A_Rojo extends Aliem
{
    
    public A_Rojo(int valorX){
        setFill(Color.RED);  
        setRadius(12); 
        setCenterX(valorX);
        setCenterY(250);
        puntos=10;
    }    
}
