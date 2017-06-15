import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.animation.Animation.Status;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.shape.Shape;
import java.util.Iterator;

import javafx.scene.shape.Line;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import javafx.scene.control.Label;
import java.util.Random;

public class Game extends Application
{
    // se da el maño a la pnatalal de juego.
    private static final int ANCHO_ESCENA = 500;
    private static final int ALTO_ESCENA = 500;
    //CAbecera
    private int puntuacion;

    // Parameros para los bloques
    public ArrayList<BloqueCobertura> ladrillos;
    private static final int NUMERO_BC = 4;
    private static final int DISTANCIA_X = 100;

    //Parameros para los Aliemes
    private ArrayList<Aliem> lineasAliem;
    private static final int NUMERO_ALIEMS = 10;
    private static final int DISTANCIA_ALIEMS_X = 45;
    private ArrayList<D_Aliem> disparoAliem;
    private int contFramesParaDisparo;

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
        //creo cabecera
        Label mensajePuntos = new Label("PUNTOS : 0");
        mensajePuntos.setTranslateX(10);
        mensajePuntos.setTranslateY(30);
        g.getChildren().add(mensajePuntos);
        
        Label mensajeVida = new Label("VIDA : 3");
        mensajeVida.setTranslateX(400);
        mensajeVida.setTranslateY(30);
        g.getChildren().add(mensajeVida);

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
        lineaLost.setVisible(false);

        // se crean las lineas de fondo de pantalla, que sirven para control de disparos.
        //lineaDisparo1.setVisible(false);
        Line lineaDisparo1=new Line(0,70,ANCHO_ESCENA,70); 
        lineaDisparo1.setStroke(Color.GREEN);
        g.getChildren().add(lineaDisparo1); 

        Line lineaDisparoBot=new Line(0,470,ANCHO_ESCENA,470); 
        lineaDisparoBot.setStroke(Color.GREEN);
        g.getChildren().add(lineaDisparoBot);
        lineaDisparoBot.setVisible(false);

        //contador de frame
        Random rnd =new Random();

        //arraylist de disparos Aliems
        disparoAliem = new ArrayList<>();

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

                    //disparo Aliems
                    if(contFramesParaDisparo==30){
                        int aliemQueDispara=rnd.nextInt(lineasAliem.size());
                        D_Aliem bala = lineasAliem.get(aliemQueDispara).disparar();
                        disparoAliem.add(bala);
                        g.getChildren().add(bala);
                        contFramesParaDisparo=0;
                    }
                    contFramesParaDisparo++;
                    //mostrar disparos Aliems
                    for(int iDisparoAliem=0;iDisparoAliem<disparoAliem.size();iDisparoAliem++){
                        disparoAliem.get(iDisparoAliem).moverDisparo();
                    }
                    // desplazar nave
                    player.mover();

                    //mostrar disparos jugador
                    for(int contDisparo=0;contDisparo<disparoPlayer.size();contDisparo++){
                        disparoPlayer.get(contDisparo).moverDisparo();
                    }

                    //Comprobar choques de disparos
                    //Disparo chocan contra tope de pantalla.
                    boolean choquePantallas=false;
                    int contBalaPlayer=0;

                    while (!choquePantallas && !disparoPlayer.isEmpty() && contBalaPlayer<disparoPlayer.size()){
                        Shape choqueTop = Shape.intersect(lineaDisparo1,disparoPlayer.get(contBalaPlayer));

                        if(choqueTop.getBoundsInParent().getWidth() != -1)
                        {
                            choquePantallas = true;
                            disparoPlayer.get(contBalaPlayer).setVisible(false);
                            disparoPlayer.remove(contBalaPlayer);
                            contBalaPlayer--;
                        }
                        contBalaPlayer++;
                    }
                    choquePantallas=false;
                    int contBalaAliem=0;
                    while(!choquePantallas && !disparoAliem.isEmpty() && contBalaAliem<disparoAliem.size()){
                        Shape choqueBott = Shape.intersect(lineaDisparoBot,disparoAliem.get(contBalaAliem));
                        if( choqueBott.getBoundsInParent().getWidth() != -1){
                            disparoAliem.get(contBalaAliem).setVisible(false);
                            disparoAliem.remove(contBalaAliem);
                            contBalaPlayer--;
                        }
                        contBalaAliem++;
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
                                puntuacion=puntuacion+posibleAliem.getPuntos();
                                mensajePuntos.setText("PUNTOS : " + String.valueOf(puntuacion));
                            }                           
                        }
                        //Disparo Jugador Contra el Bloque
                        Iterator<BloqueCobertura> iteradorBloque=ladrillos.iterator();
                        while(iteradorBloque.hasNext()){
                            BloqueCobertura posibleBloque=iteradorBloque.next();
                            Shape balaJugadorVsBloque = Shape.intersect(disparoAliado,posibleBloque);
                            if(balaJugadorVsBloque.getBoundsInParent().getWidth() !=-1){
                                disparoAliado.setVisible(false);
                                iteradorBala.remove();
                                posibleBloque.setVida(posibleBloque.getVida()-1);
                                if(posibleBloque.getVida()==0){
                                    posibleBloque.setVisible(false);
                                    iteradorBloque.remove();
                                }
                            }
                            
                            //PREGUNTAR
                            //PREGUNTAR
                            //PREGUNTAR
                            //PREGUNTAR
                            //PREGUNTAR
                            //PREGUNTAR
                            
                            mensajeVida.setText("VIDA : " + String.valueOf(player.getVida()));
                        }
                    }
                    ////Disparo Aliem Contra el Bloque
                    Iterator<D_Aliem> iteradorBalaAliem=disparoAliem.iterator();
                    while (iteradorBalaAliem.hasNext()){
                        Disparo disparoAliem= iteradorBalaAliem.next();
                        Iterator<BloqueCobertura> iteradorBloque=ladrillos.iterator();
                        while(iteradorBloque.hasNext()){
                            BloqueCobertura posibleBloque=iteradorBloque.next();
                            Shape balaAliemVsBloque = Shape.intersect(disparoAliem,posibleBloque);
                            if(balaAliemVsBloque.getBoundsInParent().getWidth() !=-1){
                                disparoAliem.setVisible(false);
                                iteradorBalaAliem.remove();
                                posibleBloque.setVida(posibleBloque.getVida()-1);
                                if(posibleBloque.getVida()==0){
                                    posibleBloque.setVisible(false);
                                    iteradorBloque.remove();
                                }
                            }
                        }
                        Shape balaAliemVsNave = Shape.intersect(disparoAliem,player);
                        if(balaAliemVsNave.getBoundsInParent().getWidth() !=-1){
                            player.setVida(player.getVida()-1);
                            disparoAliem.setVisible(false);
                            iteradorBalaAliem.remove();
                            if(player.getVida()==0){
                                Label mensajeGameOver = new Label("Game over");
                                mensajeGameOver.setTranslateX(escena.getWidth() / 2);
                                mensajeGameOver.setTranslateY(escena.getHeight() / 2);
                                g.getChildren().add(mensajeGameOver);
                                timeline.stop();
                            }
                        }
                    }

                    if (puntuacion==3100){
                        Label mensajeWin = new Label("YOU WIN");
                        mensajeWin.setTranslateX(escena.getWidth() / 2);
                        mensajeWin.setTranslateY(escena.getHeight() / 2);
                        g.getChildren().add(mensajeWin);
                        timeline.stop();
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
                if (event.getCode() == KeyCode.P) {
                    Label mensajePausa = new Label("PAUSE");
                    if (timeline.getStatus()==Status.RUNNING){
                        mensajePausa.setTranslateX(100);
                        mensajePausa.setTranslateY(30);
                        g.getChildren().add(mensajePausa);
                        timeline.stop();
                    }
                    else{
                        timeline.play();
                        mensajePausa.setText("");
                    }

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
