package ballspiel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Startet ein kleines Ballspiel als Übung für Threads
 *
 */
public class BallSpielerei extends Application {
	private BallOberflaeche view;
	private final Farbtopf[] farben = {new Farbtopf(Color.BLUE), new Farbtopf(Color.YELLOW), new Farbtopf(Color.RED)};

	private List<Thread> activeThreads = new ArrayList<>();

	private final Thread timeThread = new Thread(
			new CurrentTimeHandler((time) -> view.setTime(time), "HH:mm:ss")
	);

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Hüpfende Bälle");
		view = new BallOberflaeche(this);
		Scene scene = new Scene(view, 500, 400, false);		
	    primaryStage.setScene(scene);
	    primaryStage.setOnCloseRequest(event -> onCloseRequest());
	    primaryStage.show();

		timeThread.start();
	}

	private void onCloseRequest() {
		alleBeenden();
		timeThread.interrupt();
	}

	/**
	 * erzeugt einen neuen Ball und macht ihn in der Oberfläche sichtbar
	 */
	public void neuerBall()
	{
		Random r = new Random();
		int dx = r.nextInt(5) + 1;
		int dy = r.nextInt(5) + 1;
		int farbe = r.nextInt(farben.length);

		Ball ball = new Ball(view.getVerfuegbareBreite(), view.getVerfuegbareHoehe(), dx, dy, farben[farbe]);
		view.ballEintragen(ball);

		Thread animationThread = new Thread(() -> {
			int dauer = r.nextInt(500) + 1000;
			ball.huepfen(dauer);
		});
		activeThreads.add(animationThread);
		animationThread.start();
	}
	
	/**
	 * farben
	 * @return farben
	 */
	public Farbtopf[] getFarben() {
		return farben;
	}
	
	public void auffuellen(Farbtopf topf)
	{
		Random r = new Random();
		int menge = r.nextInt(5000) + 1000; 
		topf.fuellstandErhoehen(menge);
	}

	/**
	 * beendet das Hüpfen aller Bälle
	 */
	public void alleBeenden()
	{
		activeThreads.forEach(Thread::interrupt);
		activeThreads = new ArrayList<>();
	}
}
