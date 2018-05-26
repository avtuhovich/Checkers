package sample;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Controller {
    @FXML
    GridPane deck;

    int[][] desk = new int[][]{
            {-1, 0, -1, 0, -1, 0, -1, 0},
            {0, -1, 0, -1, 0, -1, 0, -1},
            {-1, 0, -1, 0, -1, 0, -1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1}
    };

    int fst = 12;
    int scd = 12;


    int player = 1;

    Circle actual = null;

    Circle genChip(boolean color){
        Circle circle = new Circle();
        circle.setRadius(20);
        circle.setFill(color ? Color.BLACK : Color.RED);
        circle.setStroke(Color.BLACK);
        circle.setOnMouseClicked(event -> {
            if (color && player != 1 || !color && player != -1) return;
            actual = (Circle) event.getSource();
        });
        return circle;
    }



    @FXML
    private void initialize(){
        deck.setGridLinesVisible(true);
        for(int i = 1; i < 9; i++){
            for(int j = 0; j < 8; j++){
                if(i%2==1 && j%2!=1 || i%2!=1 && j%2==1) {
                    StackPane pane = new StackPane();
                    pane.setOnMouseClicked(this::deckClick);
                    if (j < 3 || j > 4) {
                        Shape circ = j < 3 ? genChip(false) : genChip(true);
                        pane.getChildren().add(circ);
                        pane.setOnMouseClicked(null);

                    }
                    deck.add(pane,i,j);
                }

            }
        }
    }

    private void deckClick(MouseEvent mouseEvent) {
        Node cl = mouseEvent.getPickResult().getIntersectedNode();
        if(cl != null){
            int x = GridPane.getColumnIndex(actual.getParent());
            int y = GridPane.getRowIndex(actual.getParent());
            int cx = GridPane.getColumnIndex(cl);
            int cy = GridPane.getRowIndex(cl);
            if(Math.abs(x-cx) == 1 && desk[cx][cy]==0){
                deck.getChildren().remove(actual.getParent());
                StackPane pane = new StackPane();
                pane.setOnMouseClicked(this::deckClick);
                deck.add(pane,x,y);
                pane = new StackPane();
                pane.getChildren().add(actual);
                deck.getChildren().remove(getStackByIndex(cx,cy));
                deck.add(pane,cx,cy);
                desk[cx][cy] = player;
                desk[x][y] = 0;
                player *= -1;
                actual = null;
            }
            if(Math.abs(x-cx) == 2){
                int delx = (cx-x) / 2;
                int dely = (cy-y) / 2;
                if(desk[x+delx][y+dely]== player * -1) {
                    deck.getChildren().remove(getStackByIndex(x + delx, y + dely));
                    if(player == 1) scd--;
                    else fst++;
                    deck.getChildren().remove(actual.getParent());
                    StackPane pane = new StackPane();
                    pane.setOnMouseClicked(this::deckClick);
                    deck.add(pane,x + delx, y + dely);
                    pane = new StackPane();
                    pane.setOnMouseClicked(this::deckClick);
                    deck.add(pane,x,y);
                    pane = new StackPane();
                    pane.getChildren().add(actual);
                    deck.add(pane,cx,cy);
                    player *= -1;
                    actual = null;
                }
            }
        }
    }

    private Node getStackByIndex(int x, int y){
        for(int i = 0; i < deck.getChildren().size(); i++){
            if (deck.getChildren().get(i) instanceof Label || deck.getChildren().get(i) instanceof Group) continue;
            StackPane pane = (StackPane)deck.getChildren().get(i);
            if(GridPane.getColumnIndex(pane) == x &&
                    GridPane.getRowIndex(pane) == y)
                return ((StackPane) deck.getChildren().get(i));
        }
        return null;
    }
}
