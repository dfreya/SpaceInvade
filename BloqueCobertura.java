import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
public class BloqueCobertura extends Rectangle
{
    private static final int ANCHO_BC = 40;
    private static final int ALTO_BC = 30;
    private int puntosVida;
    protected Image imagenBloque;

    public BloqueCobertura (int valorX){
        setWidth(ANCHO_BC);
        setHeight(ALTO_BC);
        setTranslateX(valorX);
        setTranslateY(395);
        puntosVida=10;
        imagenBloque=new Image("Imagenes/bloque.png");
        setFill( new ImagePattern(imagenBloque));
    }
    
    public int getVida(){
        return puntosVida;
    }
    public void setVida(int nuevaVida){
        puntosVida=nuevaVida;
    }
}

