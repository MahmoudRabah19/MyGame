package runners;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SpaceInvaders extends Application {
	Scene firstScene, howToPlayScene;

	AnimationTimer timer;
	GridPane gPane = new GridPane();
	Pane pane = new Pane();
	Scene scene = new Scene(pane, 600, 650);
	List<ImageView> Enemies = new ArrayList<ImageView>();
	List<Circle> enemiesShoot = new ArrayList<Circle>();
	List<Rectangle> artilleryShoot = new ArrayList<Rectangle>();
	Polygon Player;
	Text Score;
	Text Lives;
	int scoreNum = 0;
	int livesNum = 3;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

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
		//playerShoot();
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
			if (!Enemies.isEmpty()) {
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

	private void playerShoot() {
		Circle circle = new Circle(25);
		circle.setFill(Color.RED);
		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.W) {
				circle.setLayoutX(Player.getLayoutX());
				circle.setLayoutY(Player.getLayoutY() - 10);
			}

		});
	}

	private void EnemiesMove() {
		// TODO Auto-generated method stub

	}

	private void firstScene(Stage primaryStage) {
		Image i = new Image("BackGround.PNG");
		Button play = new Button("Play");
		Button howToPlay = new Button("How To Play");
		Button exit = new Button("Exit");
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(230));
		play.setMaxSize(100, 200);
		howToPlay.setMaxSize(100, 200);
		exit.setMaxSize(100, 200);
		BackgroundImage bg = new BackgroundImage(i, null, null, null, null);
		vBox.setBackground(new Background(bg));
		vBox.getChildren().addAll(play, howToPlay, exit);
		firstScene = new Scene(vBox, 600, 650);
		primaryStage.setScene(firstScene);
		primaryStage.show();
		primaryStage.setResizable(false);
		primaryStage.getIcons()
				.add(new Image("icon.jpg"));
		play.setOnAction(e -> {

			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.getIcons()
					.add(new Image("icon.jpg"));

		});
		howToPlay.setOnAction(e -> {
			Pane pane = new Pane();
			Button back = new Button("Back To Menu");
			back.setLayoutY(600.0);
			back.setLayoutX(250.0);
			Image image = new Image(
					"HowToPlay.PNG");
			BackgroundImage bg1 = new BackgroundImage(image, null, null, null, null);
			pane.setBackground(new Background(bg1));
			pane.getChildren().add(back);
			howToPlayScene = new Scene(pane, 600, 650);
			back.setOnAction(event -> {
				primaryStage.setScene(firstScene);
				primaryStage.show();
				primaryStage.setResizable(false);
				primaryStage.getIcons().add(
						new Image("icon.jpg"));
			});
			primaryStage.setScene(howToPlayScene);
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.getIcons()
					.add(new Image("icon.jpg"));
		});
		exit.setOnAction(e -> {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Exit");
			alert.setContentText("Are You Sure To Exit ?");
			
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				primaryStage.close();
			} else {
				alert.close();
			}
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
			
			
			 Media media = null;
				try {
					media = new Media(getClass().getResource("/music/GameSound.mp3").toURI().toString());
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    MediaPlayer mi = new MediaPlayer(media);
			mi.setAutoPlay(true);
			pane.getChildren().add(gameOver);
			timer.stop();
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
			
			 Media media = null;
				try {
					media = new Media(getClass().getResource("/music/GameSound.mp3").toURI().toString());
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			MediaPlayer mp = new MediaPlayer(media);
			mp.setAutoPlay(true);
			pane.getChildren().add(win);
			// timer.stop();
		}
	}

	private void playerMove(Polygon player) {
		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.A) {
				player.setLayoutX(player.getLayoutX() - 10);
				if (player.getLayoutX() - 10 < 0) {
					player.setLayoutX(10);
				}
			}
			if (e.getCode() == KeyCode.D) {
				player.setLayoutX(player.getLayoutX() + 10);
				if (player.getLayoutX() + 10 > 600) {
					player.setLayoutX(590);
				}
			}

		});
	}

	private void music() {
		Text text = new Text("Sound:");
		RadioButton start = new RadioButton("ON");
		RadioButton stop = new RadioButton("OFF");
	    Media media = null;
		try {
			media = new Media(getClass().getResource("/music/GameSound.mp3").toURI().toString());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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

	private Polygon newPlayer() {
		Polygon newPlayer = new Polygon();
		newPlayer.getPoints().addAll(new Double[] { 0.0, -25.0, -25.0, 25.0, 0.0, 15.0, 25.0, 25.0, 0.0, -25.0 });
		newPlayer.setFill(Color.BLUE);
		newPlayer.setLayoutX(300.0);
		newPlayer.setLayoutY(600.0);
		pane.getChildren().add(newPlayer);
		return newPlayer;
	}

}
