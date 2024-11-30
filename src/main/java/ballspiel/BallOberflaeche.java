package ballspiel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Eine Oberfläche, in der viele bunte Bälle hüpfen
 *
 */
public class BallOberflaeche extends VBox {
	private final Text uhrzeit;
	private final Pane spielflaeche;
	
	/**
	 * erstellt die Oberfläche
	 * @param controller Objekt, in dem die Ereignisse verarbeitet werden
	 */
	public BallOberflaeche(BallSpielerei controller)
	{
		this.setSpacing(10);
		HBox oben = new HBox(5);
		oben.setAlignment(Pos.CENTER);
		Label beschriftung = new Label("Uhrzeit: ");
		uhrzeit = new Text("00:00:00");
		oben.getChildren().add(beschriftung);
		oben.getChildren().add(uhrzeit);
		this.getChildren().add(oben);
		
		spielflaeche = new Pane();
		spielflaeche.setPrefHeight(300);
		spielflaeche.setPrefWidth(500);
		spielflaeche.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE,
				CornerRadii.EMPTY, Insets.EMPTY)));
		this.getChildren().add(spielflaeche);

		HBox unten = new HBox(5);
		unten.setAlignment(Pos.CENTER);
		Button starten = new Button("Ball starten");
		starten.setOnAction(e -> controller.neuerBall());
		unten.getChildren().add(starten);
		Farbtopf[] farben = controller.getFarben();
		Button[] farbbuttons = new Button[farben.length];
		for(int i = 0; i < farbbuttons.length; i++)
		{
			Farbtopf topf = farben[i];
			farbbuttons[i] = new Button(topf.getFarbe().toString());
			farbbuttons[i].setTextFill(topf.getFarbe());
			farbbuttons[i].setOnAction(e -> controller.auffuellen(topf));
			unten.getChildren().add(farbbuttons[i]);
		}
		Button leeren = new Button("alle entfernen");
		leeren.setOnAction(e -> controller.alleBeenden());
		unten.getChildren().add(leeren);
		this.getChildren().add(unten);
	}
	
	/**
	 * Breite der Hüpffläche
	 * @return Breite der Hüpffläche
	 */
	public double getVerfuegbareBreite() {
		return spielflaeche.getWidth();
	}
	
	/**
	 * Höhe der Hüpffläche
	 * @return Höhe der Hüpffläche
	 */
	public double getVerfuegbareHoehe()
	{
		return spielflaeche.getHeight();
	}
	
	/**
	 * fügt einn Ball in die Hüpffläche ein
	 * @param ball einzufügender Ball
	 */
	public void ballEintragen(Ball ball) {
		spielflaeche.getChildren().add(ball);
	}

	public void setTime(String time) {
		uhrzeit.setText(time);
	}
}
