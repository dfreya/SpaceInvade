import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
public class Nave extends Rectangle
{
    private static final int ANCHO_NAVE = 30;
    private static final int ALTO_NAVE = 25;
    private int velocidadX;
    private Image imagenNave;
    private int limiteDeX;
    private int vida;
    public Nave (int limiteEnX){
        setWidth(ANCHO_NAVE);
        setHeight(ALTO_NAVE);
        setTranslateX(225);
        setTranslateY(445);
        velocidadX=0;
        imagenNave=new Image("Imagenes/nave.png");
        setFill( new ImagePattern(imagenNave));
        limiteDeX=limiteEnX;
        vida=3;
    }
    public void moverIz() 
    {
        if (getBoundsInParent().getMinX() != 0) {
            velocidadX = -1;
        }
    }
     public void cambiarDireccionALaDerecha() 
    {
        if (getBoundsInParent().getMaxX() != limiteDeX) {
           velocidadX = 1;
        }
    }
    public void parar(){
        velocidadX=0;
    }
    public void mover()
    {
        setTranslateX(getTranslateX() + velocidadX);
        if (getBoundsInParent().getMinX() == 0 || getBoundsInParent().getMaxX() == limiteDeX) {
            velocidadX = 0;
        }
        
    }
    
     public D_Nave disparar(){
        D_Nave a = new D_Nave((int)getTranslateX()+ANCHO_NAVE/2,(int)getTranslateY()+ALTO_NAVE/2);
        return a;
    }
   
    public int getVida(){
        return vida;
    }
    public void setVida(int vidaActual){
        vida=vidaActual;
    }
}
