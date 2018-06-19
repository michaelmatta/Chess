import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Piece {

    private int x, y;
    private String id;
    private boolean color;
    public static int nextID = 1;
    private int myID;
    private boolean moved;
    private boolean pawnAttacked;

    public Piece(int x, int y, String id, boolean color) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.color = color;
        myID = nextID;
        nextID++;
    }

    public boolean equals(Piece other){
        return myID == other.myID;
    }

    public void move(int movetox, int movetoy, ArrayList<Piece> pieces) {
        int ogX = x;
        int ogY = y;
        pawnAttacked = true;

        if (id.equals("RookWhite") || id.equals("RookBlack")) { //rook movement
            if (movetoy == y) {//if moving straight up or down or left or right
                x = movetox;
                y = movetoy;
                if (movetox > ogX) {
                    for (int i = 0; i < Math.abs(ogX - movetox); i++) {
                        for (int j = 0; j < pieces.size(); j++) {
                            if (pieces.get(j).y == ogY && pieces.get(j).x == ogX + i && !equals(pieces.get(j))) {
                                System.out.println(pieces.get(j).id);
                                x = ogX;
                                y = ogY;
                            }
                        }
                    }
                }
                if (movetox < ogX){
                    for (int i = 0; i < Math.abs(ogX - movetox); i++) {
                        for (int j = 0; j < pieces.size(); j++) {
                            if (pieces.get(j).y == ogY && pieces.get(j).x == ogX - i && !equals(pieces.get(j))) {
                                System.out.println(pieces.get(j).id);
                                x = ogX;
                                y = ogY;
                            }
                        }
                    }
                }
            }
            else if (movetox == x){
                x = movetox;
                y = movetoy;
                if (movetoy > ogY) {
                    for (int i = 0; i < Math.abs(ogY - movetoy); i++) {
                        for (int j = 0; j < pieces.size(); j++) {
                            if (pieces.get(j).x == ogX && pieces.get(j).y == ogY + i && !equals(pieces.get(j))) {
                                System.out.println(pieces.get(j).id);
                                x = ogX;
                                y = ogY;
                            }
                        }
                    }
                }
                if (movetoy < ogY){
                    for (int i = 0; i < Math.abs(ogY - movetoy); i++) {
                        for (int j = 0; j < pieces.size(); j++) {
                            if (pieces.get(j).x == ogX && pieces.get(j).y == ogY - i && !equals(pieces.get(j))) {
                                System.out.println(pieces.get(j).id);
                                x = ogX;
                                y = ogY;
                            }
                        }
                    }
                }
            }
        }

        else if (id.equals("BishopWhite") || id.equals("BishopBlack")) { //bishop movement
            for (int h = -8; h < 8; h++) {
                if ((movetoy == y+h && movetox == x+h) || (movetoy == y-h && movetox == x+h)) {//if moving diagonal
                    x = movetox;
                    y = movetoy;
                    if (movetoy > ogY) {//going down blocking
                        if (movetox > ogX) {//going right
                            for (int i = 0; i < Math.abs(ogY - movetoy); i++) {
                                for (int j = 0; j < pieces.size(); j++) {
                                    if (pieces.get(j).x == ogX + i && pieces.get(j).y == ogY + i && !equals(pieces.get(j))) {
                                        System.out.println(pieces.get(j).id);
                                        x = ogX;
                                        y = ogY;
                                    }
                                }
                            }
                        }
                        if (movetox < ogX){//going left
                            for (int i = 0; i < Math.abs(ogY - movetoy); i++) {
                                for (int j = 0; j < pieces.size(); j++) {
                                    if (pieces.get(j).x == ogX - i && pieces.get(j).y == ogY + i && !equals(pieces.get(j))) {
                                        System.out.println(pieces.get(j).id);
                                        x = ogX;
                                        y = ogY;
                                    }
                                }
                            }
                        }
                    }
                    if (movetoy < ogY) {//going down blocking
                        if (movetox > ogX) {//going right
                            for (int i = 0; i < Math.abs(ogY - movetoy); i++) {
                                for (int j = 0; j < pieces.size(); j++) {
                                    if (pieces.get(j).x == ogX + i && pieces.get(j).y == ogY - i && !equals(pieces.get(j))) {
                                        System.out.println(pieces.get(j).id);
                                        x = ogX;
                                        y = ogY;
                                    }
                                }
                            }
                        }
                        if (movetox < ogX){//going left
                            for (int i = 0; i < Math.abs(ogY - movetoy); i++) {
                                for (int j = 0; j < pieces.size(); j++) {
                                    if (pieces.get(j).x == ogX - i && pieces.get(j).y == ogY - i && !equals(pieces.get(j))) {
                                        System.out.println(pieces.get(j).id);
                                        x = ogX;
                                        y = ogY;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if (id.equals("KnightWhite") || id.equals("KnightBlack")) { //rook movement
            if ((movetoy == y+2 && movetox == x+1)||(movetoy == y-2 && movetox == x+1)||(movetoy == y+2 && movetox == x-1)||(movetoy == y-2 && movetox == x-1)) {//L movement
                x = movetox;
                y = movetoy;
                }
            else if ((movetoy == y+1 && movetox == x+2)||(movetoy == y-1 && movetox == x+2)||(movetoy == y+1 && movetox == x-2)||(movetoy == y-1 && movetox == x-2)) {//L movement
                x = movetox;
                y = movetoy;
            }
        }
        else if (id.equals("PawnWhite")){ //pawn white movement
            if ((movetox == x && movetoy == y-1) ||(movetox == x && movetoy == y-2 && !moved)){
                x = movetox;
                y = movetoy;
                if (!moved) { //stops pawns from hopping 2 over pieces
                    moved = true;
                    for (int i = 0; i < pieces.size(); i++) {
                        if (pieces.get(i).y == movetoy + 1 && pieces.get(i).x == ogX && !equals(pieces.get(i))) {
                            x = ogX;
                            y = ogY;
                            moved = false;
                        }
                    }
                }
            }
            else if ((movetox == x+1 && movetoy == y-1)||(movetox == x-1 && movetoy == y-1)){
                for (int i = 0; i < pieces.size(); i++) { //Attacking black
                    if (!equals(pieces.get(i))) {
                        if (pieces.get(i).x == movetox && pieces.get(i).y == movetoy && !pieces.get(i).color) {
                            x = movetox;
                            y = movetoy;
                            pawnAttacked = false;
                        }
                    }
                }
            }
            if (y == 0){
                id = "QueenWhite";
            }
        }
        else if (id.equals("PawnBlack")){ //pawn black movement
            if ((movetox == x && movetoy == y+1) ||(movetox == x && movetoy == y+2 && !moved)){
                x = movetox;
                y = movetoy;
                if (!moved) { //stops pawns from hopping 2 over pieces
                    moved = true;
                    for (int i = 0; i < pieces.size(); i++) {
                        if (pieces.get(i).y == movetoy - 1 && pieces.get(i).x == ogX && !equals(pieces.get(i))) {
                            x = ogX;
                            y = ogY;
                            moved = false;

                        }
                    }
                }
            }
            else if ((movetox == x+1 && movetoy == y+1)||(movetox == x-1 && movetoy == y+1)) {
                for (int i = 0; i < pieces.size(); i++) { //Attacking white
                    if (!equals(pieces.get(i))) {
                        if (pieces.get(i).x == movetox && pieces.get(i).y == movetoy && pieces.get(i).color) {
                            x = movetox;
                            y = movetoy;
                            pawnAttacked = false;
                        }
                    }
                }
            }
            if (y == 7){ //turns into queen (need to change later because back rank pawns can attack straight
                id = "Latifa";
            }
        }
        else if (id.equals("QueenWhite") || id.equals("Latifa")){ //queen movement
            if (movetoy == y) {//if moving straight up or down or left or right
                x = movetox;
                y = movetoy;
                if (movetox > ogX) {
                    for (int i = 0; i < Math.abs(ogX - movetox); i++) {
                        for (int j = 0; j < pieces.size(); j++) {
                            if (pieces.get(j).y == ogY && pieces.get(j).x == ogX + i && !equals(pieces.get(j))) {
                                System.out.println(pieces.get(j).id);
                                x = ogX;
                                y = ogY;
                            }
                        }
                    }
                }
                if (movetox < ogX){
                    for (int i = 0; i < Math.abs(ogX - movetox); i++) {
                        for (int j = 0; j < pieces.size(); j++) {
                            if (pieces.get(j).y == ogY && pieces.get(j).x == ogX - i && !equals(pieces.get(j))) {
                                System.out.println(pieces.get(j).id);
                                x = ogX;
                                y = ogY;
                            }
                        }
                    }
                }
            }
            else if (movetox == x){
                x = movetox;
                y = movetoy;
                if (movetoy > ogY) {
                    for (int i = 0; i < Math.abs(ogY - movetoy); i++) {
                        for (int j = 0; j < pieces.size(); j++) {
                            if (pieces.get(j).x == ogX && pieces.get(j).y == ogY + i && !equals(pieces.get(j))) {
                                System.out.println(pieces.get(j).id);
                                x = ogX;
                                y = ogY;
                            }
                        }
                    }
                }
                if (movetoy < ogY){
                    for (int i = 0; i < Math.abs(ogY - movetoy); i++) {
                        for (int j = 0; j < pieces.size(); j++) {
                            if (pieces.get(j).x == ogX && pieces.get(j).y == ogY - i && !equals(pieces.get(j))) {
                                System.out.println(pieces.get(j).id);
                                x = ogX;
                                y = ogY;
                            }
                        }
                    }
                }
            }
            for (int h = -8; h < 8; h++) {
                if ((movetoy == y+h && movetox == x+h) || (movetoy == y-h && movetox == x+h)) {//if moving diagonal
                    x = movetox;
                    y = movetoy;
                    if (movetoy > ogY) {//going down blocking
                        if (movetox > ogX) {//going right
                            for (int i = 0; i < Math.abs(ogY - movetoy); i++) {
                                for (int j = 0; j < pieces.size(); j++) {
                                    if (pieces.get(j).x == ogX + i && pieces.get(j).y == ogY + i && !equals(pieces.get(j))) {
                                        System.out.println(pieces.get(j).id);
                                        x = ogX;
                                        y = ogY;
                                    }
                                }
                            }
                        }
                        if (movetox < ogX){//going left
                            for (int i = 0; i < Math.abs(ogY - movetoy); i++) {
                                for (int j = 0; j < pieces.size(); j++) {
                                    if (pieces.get(j).x == ogX - i && pieces.get(j).y == ogY + i && !equals(pieces.get(j))) {
                                        System.out.println(pieces.get(j).id);
                                        x = ogX;
                                        y = ogY;
                                    }
                                }
                            }
                        }
                    }
                    if (movetoy < ogY) {//going down blocking
                        if (movetox > ogX) {//going right
                            for (int i = 0; i < Math.abs(ogY - movetoy); i++) {
                                for (int j = 0; j < pieces.size(); j++) {
                                    if (pieces.get(j).x == ogX + i && pieces.get(j).y == ogY - i && !equals(pieces.get(j))) {
                                        System.out.println(pieces.get(j).id);
                                        x = ogX;
                                        y = ogY;
                                    }
                                }
                            }
                        }
                        if (movetox < ogX){//going left
                            for (int i = 0; i < Math.abs(ogY - movetoy); i++) {
                                for (int j = 0; j < pieces.size(); j++) {
                                    if (pieces.get(j).x == ogX - i && pieces.get(j).y == ogY - i && !equals(pieces.get(j))) {
                                        System.out.println(pieces.get(j).id);
                                        x = ogX;
                                        y = ogY;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if (id.equals("KingWhite") || id.equals("KingBlack")) { //king movement
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if ((movetoy == y + i && movetox == x + j)) {
                        x = movetox;
                        y = movetoy;
                        moved = true;
                    }
                }
            }
            if (color) {//if white and a king
                if (movetoy == ogY && movetox == ogX + 2 && !moved) { //castling king side
                    x = movetox;
                    y = movetoy;
                    for (int i = 0; i < pieces.size(); i++) {
                        if (pieces.get(i).id.equals("whiteRook")){

                        }
                        for (int j = 5; j < 7; j++) {
                            if (pieces.get(i).y == 7 && pieces.get(i).x == j && !equals(pieces.get(i))) {
                                System.out.println(pieces.get(i).id);
                                x = ogX;
                                y = ogY;
                            }
                        }
                    }
                } else if (movetoy == ogY && movetox == ogX - 2 && !moved) {

                }
            }
        }
        if (color){ //if white
            for (int i = 0; i < pieces.size(); i++) { //Attacking black
                if (!equals(pieces.get(i))){//moves back if white in that square
                    if (pieces.get(i).getX() == movetox && pieces.get(i).getY() == movetoy && pieces.get(i).isColor()) {
                        x = ogX;
                        y = ogY;
                        break;
                    }
                    else if (pawnAttacked && pieces.get(i).getX() == movetox && pieces.get(i).getY() == movetoy && id.equals("PawnWhite")){
                        x = ogX; //makes sure pawns cant attack straight
                        y = ogY;
                        break;
                    }
                }
                if (pieces.get(i).getX() == x && pieces.get(i).getY() == y && !pieces.get(i).isColor()) { //kills pieces
                    pieces.remove(i);
                    i--;
                }
            }
        }
        if (!color){ //if black
            for (int i = 0; i < pieces.size(); i++) { //Attacking white
                if (!equals(pieces.get(i))){//moves back if black in that square
                    if (pieces.get(i).getX() == movetox && pieces.get(i).getY() == movetoy && !pieces.get(i).isColor()) {
                        x = ogX;
                        y = ogY;
                        break;
                    }
                    else if (pawnAttacked && pieces.get(i).getX() == movetox && pieces.get(i).getY() == movetoy && id.equals("PawnBlack")){
                        x = ogX; //makes sure pawns cant attack straight
                        y = ogY;
                        break;
                    }
                }
                if (pieces.get(i).getX() == x && pieces.get(i).getY() == y && pieces.get(i).isColor() && pawnAttacked) { //kills pieces
                    pieces.remove(i);
                    i--;
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        try {
            BufferedImage pic = ImageIO.read(new File("res/" + id + ".png"));
            g2.drawImage(pic, x*Main.GRIDSIZE, y*Main.GRIDSIZE, 100, 100, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isColor() {
        return color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString() {
        return x + " " + y + " " + id;
    }


}
