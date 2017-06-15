import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
/**
 * Write a description of class BloqueCobertura here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BloqueCobertura extends Rectangle
{
    private static final int ANCHO_BC = 40;
    private static final int ALTO_BC = 30;
    private int puntosVida;

    public BloqueCobertura (int valorX){
        setWidth(ANCHO_BC);
        setHeight(ALTO_BC);
        setTranslateX(valorX);
        setTranslateY(395);
        puntosVida=10;
    }
    
    public int getVida(){
        return puntosVida;
    }
}

