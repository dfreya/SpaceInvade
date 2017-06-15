import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.shape.Shape;
import java.util.Iterator;

import javafx.scene.shape.Line;
import javafx.scene.paint.*;

import java.util.ArrayList;
import javafx.scene.control.Label;
/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game extends Application

{
    // se da el maño a la pnatalal de juego.
    private static final int ANCHO_ESCENA = 500;
    private static final int ALTO_ESCENA = 500;

    // Parameros para los bloques
    public ArrayList<BloqueCobertura> ladrillos;
    private static final int NUMERO_BC = 4;
    private static final int DISTANCIA_X = 100;

    //Parameros para los Aliemes
    private ArrayList<Aliem> lineasAliem;
    private static final int NUMERO_ALIEMS = 10;
    private static final int DISTANCIA_ALIEMS_X = 45;

    //para la nave
    private ArrayList<D_Nave> disparoPlayer;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage escenario)
    {
        // se crea la escena y el contenedor .
        Group g = new Group();
        Scene escena = new Scene(g, ANCHO_ESCENA,ALTO_ESCENA);

        //se crea la nave del jugador
        Nave player= new Nave(ANCHO_ESCENA); 
        g.getChildren().add(player);

        //se crean 4 coberturas
        ladrillos = new ArrayList<>();
        int posicionX=80;
        for(int cont=0;cont<NUMERO_BC;cont++){            
            BloqueCobertura bloque = new BloqueCobertura(posicionX); 
            g.getChildren().add(bloque);
            posicionX = posicionX + DISTANCIA_X;
            ladrillos.add(bloque);
        }

        lineasAliem = new ArrayList<>();
        //Se crean los A_rojos
        int posicionAlienRX=50;
        for(int cont=0;cont<NUMERO_ALIEMS;cont++){            
            A_Rojo lineaR = new A_Rojo(posicionAlienRX); 
            g.getChildren().add(lineaR);
            posicionAlienRX = posicionAlienRX + DISTANCIA_ALIEMS_X;
            lineasAliem.add(lineaR);
        }
        //Se crean los A_AZULES
        int posicionAlienAX=50;
        for(int cont=0;cont<NUMERO_ALIEMS;cont++){            
            A_Azul lineaA = new A_Azul(posicionAlienAX); 
            g.getChildren().add(lineaA);
            posicionAlienAX = posicionAlienAX + DISTANCIA_ALIEMS_X;
            lineasAliem.add(lineaA);
        }
        //Se crean los A_verdes
        int posicionAlienVX=50;
        for(int cont=0;cont<NUMERO_ALIEMS;cont++){            
            A_Verde lineaV = new A_Verde(posicionAlienVX); 
            g.getChildren().add(lineaV);
            posicionAlienVX = posicionAlienVX + DISTANCIA_ALIEMS_X;
            lineasAliem.add(lineaV);
        }
        //Se crean los A_NARANJA
        int posicionAlienNX=50;
        for(int cont=0;cont<NUMERO_ALIEMS;cont++){            
            A_Naranja lineaN = new A_Naranja(posicionAlienNX); 
            g.getChildren().add(lineaN);
            posicionAlienNX = posicionAlienNX + DISTANCIA_ALIEMS_X;
            lineasAliem.add(lineaN);
        }
        //Se crean los A_MORADA
        int posicionAlienMX=50;
        for(int cont=0;cont<NUMERO_ALIEMS;cont++){            
            A_Morado lineaM = new A_Morado(posicionAlienMX); 
            g.getChildren().add(lineaM);
            posicionAlienMX = posicionAlienMX + DISTANCIA_ALIEMS_X;
            lineasAliem.add(lineaM);
        }

        // se crea la linea la cual si llegan loS aliens pierdes.
        Line lineaLost=new Line(0,370,ANCHO_ESCENA,370); 
        lineaLost.setStroke(Color.BLUE);
        g.getChildren().add(lineaLost);     

        // se crean las lineas de fondo de pantalla, que sirven para control de disparos.
        //lineaDisparo1.setVisible(false);
        Line lineaDisparo1=new Line(0,70,ANCHO_ESCENA,70); 
        lineaDisparo1.setStroke(Color.GREEN);

        g.getChildren().add(lineaDisparo1); 
        Line lineaDisparo2=new Line(0,470,ANCHO_ESCENA,470); 
        lineaDisparo2.setStroke(Color.GREEN);
        g.getChildren().add(lineaDisparo2); 

        Timeline timeline = new Timeline();
        KeyFrame keyframe = new KeyFrame(Duration.seconds(0.01), event -> {
                    //desplazar Aliems
                    for(int contAliem=0;contAliem<lineasAliem.size();contAliem++){
                        lineasAliem.get(contAliem).mover(ANCHO_ESCENA);
                        //si un alien llega hasta una posicion el jugador pierde
                        Shape aliemVsBarra = Shape.intersect(lineaLost,lineasAliem.get(contAliem));
                        if(aliemVsBarra.getBoundsInParent().getWidth() != -1){
                            Label mensajeGameOver = new Label("Game over");
                            mensajeGameOver.setTranslateX(escena.getWidth() / 2);
                            mensajeGameOver.setTranslateY(escena.getHeight() / 2);
                            g.getChildren().add(mensajeGameOver);
                            timeline.stop();
                        }
                    }

                    // desplazar nave
                    player.mover();

                    //mostrar disparos
                    for(int contDisparo=0;contDisparo<disparoPlayer.size();contDisparo++){
                        disparoPlayer.get(contDisparo).moverDisparo();

                    }

                    //Comprobar choques de disparos
                    //Disparo chocan contra tope de pantalla.
                    boolean choquePantallas=false;
                    int contBala=0;
                    while (!choquePantallas && !disparoPlayer.isEmpty() && contBala<disparoPlayer.size()){
                        Shape choqueTop = Shape.intersect(lineaDisparo1,disparoPlayer.get(contBala));
                        Shape choqueBott = Shape.intersect(lineaDisparo2,disparoPlayer.get(contBala));
                        if(choqueTop.getBoundsInParent().getWidth() != -1 
                        || choqueBott.getBoundsInParent().getWidth() != -1){
                            choquePantallas = true;
                            disparoPlayer.get(contBala).setVisible(false);
                            disparoPlayer.remove(contBala);                            
                            contBala--;
                        }
                        contBala++;
                    }

                    //Disparo chocan contra Aliems.
                    boolean choqueAliems=false;
                    Iterator<D_Nave> iteradorBala=disparoPlayer.iterator();
                    while (!choqueAliems && iteradorBala.hasNext()){
                        Disparo disparoAliado= iteradorBala.next();
                        Iterator<Aliem> iteradorAliem=lineasAliem.iterator();
                        while(iteradorAliem.hasNext()){
                            Aliem posibleAliem=iteradorAliem.next();
                            Shape balaVsAliem = Shape.intersect(disparoAliado,posibleAliem);
                            if(balaVsAliem.getBoundsInParent().getWidth() !=-1){
                                choqueAliems=true;
                                posibleAliem.setVisible(false);
                                disparoAliado.setVisible(false);
                                iteradorAliem.remove();
                                iteradorBala.remove(); 
                            }                           
                        }
                        //Disparo JUgador COntra el Bloque
                        Iterator<BloqueCobertura> iteradorBloque=ladrillos.iterator();
                        while(iteradorBloque.hasNext()){
                            BloqueCobertura posibleBloque=iteradorBloque.next();
                            Shape balaVsBloque = Shape.intersect(disparoAliado,posibleBloque);
                            if(balaVsBloque.getBoundsInParent().getWidth() !=-1){
                                disparoAliado.setVisible(false);
                                iteradorBala.remove();
                                posibleBloque.setVida(posibleBloque.getVida()-1);
                                if(posibleBloque.getVida()==0){
                                    posibleBloque.setVisible(false);
                                    iteradorBloque.remove();
                                }
                            }
                        }
                    }

                    

                });
        disparoPlayer = new ArrayList<>();
        escena.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.RIGHT) {
                    player.cambiarDireccionALaDerecha();
                }
                else if (event.getCode() == KeyCode.LEFT) {
                    player.moverIz();
                }
                if (event.getCode() == KeyCode.SPACE) {
                    D_Nave bala= player.disparar();
                    // al disparar creo un objeto disapra que es devuelto por el metodo disparar de la nave.
                    disparoPlayer.add(bala);
                    g.getChildren().add(bala);
                }

            });
        escena.setOnKeyReleased(event ->{
                if(event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT){
                    player.parar();
                }
            });

        timeline.getKeyFrames().add(keyframe);

        escenario.setScene(escena);
        escenario.show();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();     
    }

}
