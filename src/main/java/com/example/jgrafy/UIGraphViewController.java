package com.example.jgrafy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class UIGraphViewController {

    @FXML
    private AnchorPane graphPane;
    @FXML
    private ScrollPane graphScroll;
    @FXML
    public void initialize(){
        graphPane.prefWidthProperty().bind(graphScroll.widthProperty());
        graphPane.prefHeightProperty().bind(graphScroll.heightProperty());
    }


    public void MinusClicked(ActionEvent actionEvent) {
        Graph graph = Main.getGraph();
        double vertexRadius;
        double scrollbarWidth = 20;
        double graphPaneWidth = graphPane.getWidth() - scrollbarWidth;
        double graphPaneHeight = graphPane.getHeight() - scrollbarWidth;
        if(graphPane.getWidth() / graphPane.getHeight() < graph.getNumOfColumns() / graph.getNumOfRows()){
            vertexRadius = graphPaneWidth / graph.getNumOfColumns() / 4;
        } else{
            vertexRadius = graphPaneHeight / graph.getNumOfRows() / 4;
        }
        for(int i = 0; i<graph.getNumOfRows(); i++){
            for(int n = 0; n< graph.getNumOfColumns(); n++){
                Circle vertexCircle = new Circle(vertexRadius);
                vertexCircle.setCenterX(graphPane.getLayoutX() + n * vertexRadius * 4 + vertexRadius);
                vertexCircle.setCenterY(graphPane.getLayoutY() + i * vertexRadius * 4 + vertexRadius);
                vertexCircle.setFill(Paint.valueOf("Black"));
                graphPane.getChildren().add(vertexCircle);
            }
        }
    }

    public void PlusClicked(ActionEvent actionEvent) {

    }
}
