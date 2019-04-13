package runners;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SpaceInvaders extends Application {
	AnimationTimer timer;
	GridPane gPane = new GridPane();
	Pane pane = new Pane();
	Scene scene = new Scene(pane, 600, 650);
	List<ImageView> Enemies = new ArrayList<ImageView>();
	List<Circle> enemiesShoot = new ArrayList<Circle>();
	List<Rectangle> artilleryShoot = new ArrayList<Rectangle>();
	ImageView Player;
	Text Score;
	Text Lives;
	int scoreNum = 0;
	int livesNum = 3;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {

			}
		};
		Background bg = new Background(new BackgroundFill(Color.BLACK, null, null));
		firstScene(primaryStage);
		Player = newPlayer();
		playerMove(Player);
		TextEdit();
		music();
		gPane.add(Lives, 0, 0);
		gPane.add(Score, 0, 1);
		pane.getChildren().add(gPane);
		pane.setBackground(bg);
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
			if (Enemies.isEmpty()) {
				EnemiesShoot();
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

		EnemiesMove();

		// Win();
		// GameOver();
		primaryStage.setTitle("Space Invaders App");
	}

	private void EnemiesShoot() {
		// TODO Auto-generated method stub

	}

	private void playerShoot(double d) {
		// TODO Auto-generated method stub

	}

	private void EnemiesMove() {
		// TODO Auto-generated method stub

	}

	private void firstScene(Stage primaryStage) {
		Image i = new Image("file:///C:/Users/עידן/workspace/SpaceInvaders/src/main/java/runners/SpaceInvaders.jpg");
		Button play = new Button("Play");
		Button howToPlay = new Button("How To Play");
		Button exit = new Button("Exit");
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(250));
		BackgroundImage bg = new BackgroundImage(i, null, null, null, null);
		vBox.setBackground(new Background(bg));
		vBox.getChildren().addAll(play, howToPlay, exit);
		Scene firstScene = new Scene(vBox, 600, 650);
		primaryStage.setScene(firstScene);
		primaryStage.show();
		primaryStage.setResizable(false);
		play.setOnAction(e -> {
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);

		});
		howToPlay.setOnAction(e -> {
			Pane pane = new Pane();
			Button back = new Button("Back To Menu");
			back.setLayoutY(600.0);
			back.setLayoutX(250.0);
			Image image = new Image(
					"file:///C:/Users/עידן/workspace/SpaceInvaders/src/main/java/runners/HowToPlay.PNG");
			BackgroundImage bg1 = new BackgroundImage(image, null, null, null, null);
			pane.setBackground(new Background(bg1));
			pane.getChildren().add(back);
			Scene textScene = new Scene(pane, 600, 650);
			back.setOnAction(event -> {
				primaryStage.setScene(firstScene);
				primaryStage.show();
				primaryStage.setResizable(false);
			});
			primaryStage.setScene(textScene);
			primaryStage.show();
			primaryStage.setResizable(false);
		});
		exit.setOnAction(e -> {
			primaryStage.close();
		});
	}

	private void GameOver() {
		livesNum = 0;
		if (livesNum == 0) {
			Text gameOver = new Text("GAME OVER !");
			gameOver.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
			gameOver.setFill(Color.RED);
			gameOver.setStroke(Color.BLACK);
			gameOver.setStrokeWidth(3);
			gameOver.setX(90);
			gameOver.setY(300);
			MediaPlayer mi = new MediaPlayer(new Media("file:///C:/Users/עידן/Downloads/Music/GameOver.mp3"));
			mi.setAutoPlay(true);
			pane.getChildren().add(gameOver);
			// timer.stop();
		}
	}

	private void Win() {
		if (Enemies.isEmpty()) {
			Text win = new Text("YOU WIN !!");
			win.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
			win.setX(90);
			win.setY(300);
			win.setFill(Color.YELLOW);
			win.setStrokeWidth(3);
			win.setStroke(Color.GOLD);
			MediaPlayer mp = new MediaPlayer(new Media("file:///C:/Users/עידן/Downloads/Music/YouWin.mp3"));
			mp.setAutoPlay(true);
			pane.getChildren().add(win);
			// timer.stop();
		}
	}

	private void playerMove(ImageView player2) {
		scene.setOnKeyPressed(e -> {

			if (e.getCode() == KeyCode.LEFT) {
				player2.setLayoutX(player2.getLayoutX() - 50);
			}
			if (e.getCode() == KeyCode.RIGHT) {
				player2.setLayoutX(player2.getLayoutX() + 50);
			}
		});

	}

	private void music() {
		Text text = new Text("Sound:");
		RadioButton start = new RadioButton("ON");
		RadioButton stop = new RadioButton("OFF");
		Media media = new Media("file:///C:/Users/עידן/Desktop/GameSound.mp3");
		MediaPlayer mPlayer = new MediaPlayer(media);
		start.setOnAction(e -> {
			mPlayer.play();
		});
		stop.setOnAction(e -> {
			mPlayer.stop();
		});
		text.setFill(Color.BLUE);
		Font value = new Font(STYLESHEET_MODENA, 15.0);
		text.setFont(value);
		gPane.add(text, 7, 0);
		gPane.add(start, 7, 1);
		gPane.add(stop, 7, 2);
		gPane.setHgap(60.0);
		mPlayer.setVolume(0.5);

	}

	private void TextEdit() {
		Score = new Text("Score : " + scoreNum);
		Font value = new Font(STYLESHEET_MODENA, 20);
		Score.setFont(value);
		Score.setFill(Color.RED);
		Lives = new Text("Lives : " + livesNum);
		Font value2 = new Font(STYLESHEET_MODENA, 20);
		Lives.setFont(value2);
		Lives.setFill(Color.RED);

	}

	private ImageView newPlayer() {
		ImageView newPlayer;
		newPlayer = new ImageView(
				new Image("file:///C:/Users/עידן/workspace/SpaceInvaders/src/main/java/runners/6Bignkbc8.png"));
		newPlayer.setLayoutX(250.0);
		newPlayer.setLayoutY(600.0);
		newPlayer.setFitWidth(50.0);
		newPlayer.setFitHeight(50.0);
		pane.getChildren().add(newPlayer);
		return newPlayer;
	}

}
