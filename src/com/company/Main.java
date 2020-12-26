package com.company;

import dev.jabo.kree.*;
import dev.jabo.kree.Component;
import dev.jabo.kree.Window;
import dev.jabo.kree.components.BoxCollider;
import dev.jabo.kree.components.MeshRenderer;
import dev.jabo.kree.components.PlayerMovement;
import dev.jabo.kree.ui.Text;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Game game = new Game(new Window("Ex", 600, 400));

        SceneManager.setScene(new MyScene(game));
        game.start();
    }
}


class MyScene extends Scene {


    public MyScene(Game game) {
        super(game);
    }

    @Override
    public void Initialize() {


    }

    @Override
    public void Update() {

        if (Input.isKeyPressed(KeyEvent.VK_A)) {
            SceneManager.setScene(new TwoScene(game));
        }

    }

    @Override
    public void Render(Graphics graphics) {
        BufferedImage background = ImageLoader.loadImage("/cat.jpg");
        graphics.drawImage(background, 0, 0, null);


    }
}


class TwoScene extends Scene {
    GameObject go;
    GameObject two;
    private Text t;

    public TwoScene(Game game) {
        super(game);
        t = new Text(this, "Hello", new Vector2(100, 100), 50);
        t.setColor(Color.BLACK);
    }

    @Override
    public void Initialize() {
        go = new GameObject(this, "Obj");
        go.addComponent(new MeshRenderer());
        go.addComponent(new BoxCollider());
        go.getTransform().setScale(new Vector2(20, 20));
        go.getTransform().setPosition(new Vector2(10, 10));
        go.addComponent(new PlayerMovement(2f, PlayerMovement.WASD));
        go.addComponent(new MyComp());
        two = new GameObject(this, "two");

        two.addComponent(new MeshRenderer());
        two.getTransform().setScale(new Vector2(20, 20));
        two.addComponent(new BoxCollider());
        two.getTransform().setPosition(new Vector2(150, 200));

    }

    @Override
    public void Update() {
        BoxCollider bc = (BoxCollider) go.getComponent("Box Collider");
        BoxCollider cb = (BoxCollider) two.getComponent("Box Collider");
        if (bc.collidingWith(cb) && Input.isKeyPressed(KeyEvent.VK_Q)) {
            t.setColor(Color.RED);
        }
    }

    @Override
    public void Render(Graphics graphics) {

        Font myFont = null;
        try {
            myFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File("C:\\Users\\1\\IdeaProjects\\kreetest\\Assets\\20219.ttf"));
            myFont = myFont.deriveFont(Font.BOLD, 24f);


        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        t.setFont(myFont);


    }
}


class MyComp extends Component {

    @Override
    public void Update() {
        Vector2 xy = gameObject.getTransform().getPosition();
        if (xy.getX() >= 600) {
            xy.setX(600);
        }

    }

    @Override
    public void Render(Graphics graphics) {

    }
}