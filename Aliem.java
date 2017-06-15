import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
/**
 * Abstract class Aliem - write a description of the class here
 * 
 * @author: 
 * Date: 
 */
public abstract class Aliem extends Circle
{
    private int velocidadEnX;
    private int velocidadEnY; 
    
    public Aliem (){
        velocidadEnX = 1;
        velocidadEnY = 0;
    }
    public void mover(int anchoDeLaEscena){
        //Desplazamos los aliems
        setTranslateX(getTranslateX() + velocidadEnX);
        setTranslateY(getTranslateY() + velocidadEnY);
        // Controlamos si los aliems tocan a ziquierda o derecha
        if (getBoundsInParent().getMinX() <= 0 ||
        getBoundsInParent().getMaxX() >= anchoDeLaEscena) {
            velocidadEnX *=-1;
            setCenterY(getCenterY()+20);
        }

    }
    public  void disparar(){
        D_Aliem a = new D_Aliem((int)getCenterX(),(int)getCenterY());
    }
}
