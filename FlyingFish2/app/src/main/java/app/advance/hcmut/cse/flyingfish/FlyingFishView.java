package app.advance.hcmut.cse.flyingfish;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class FlyingFishView extends View{
    private Bitmap[] fish = new Bitmap[2];
    private int fishX = 10;
    private int fishY;
    private int fishSpeed;

    private int canvasWidth, canvasHeight;

    private int yellowX[] = new int[2];
    private int yellowY[] = new int[2];
    private int yellowSpeed[] = new int[2];
    private Paint yellowPaint = new Paint();

    private int greenX[] = new int[2];
    private int greenY[] = new int[2];
    private int greenSpeed[] = new int[2];
    private Paint greenPaint = new Paint();

    private int redX[] = new int[2];
    private int redY[] = new int[2];
    private int redSpeed[] = new int[2];
    private Paint redPaint = new Paint();

    private int score, lifeCounterOfFish;

    private boolean touch = false;

    private Bitmap backgroundImage;

    private Paint scorePaint = new Paint();

    private Bitmap life[] = new Bitmap[2];

    public FlyingFishView(Context context) {
        super(context);

        fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(), R.drawable.fish2);

        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.background);

        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_grey);

        fishY = 550;
        score = 0;
        lifeCounterOfFish = 3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        canvas.drawBitmap(backgroundImage, 0, 0, null);

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);

        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);

        int minFishY = fish[0].getHeight();
        int maxFishY = canvasHeight - fish[0].getHeight() * 3;
        fishY = fishY + fishSpeed;

        if (fishY < minFishY){
            fishY = minFishY;
        }
        if (fishY > maxFishY){
            fishY = maxFishY;
        }

        fishSpeed = fishSpeed + 2;

        if (touch){
            canvas.drawBitmap(fish[1], fishX, fishY, null);
            touch = false;
        }
        else {
            canvas.drawBitmap(fish[0], fishX, fishY, null);
        }

        yellowSpeed[0] = 15;
        yellowX[0] = yellowX[0] - yellowSpeed[0];
        yellowSpeed[1] = 20;
        yellowX[1] = yellowX[1] - yellowSpeed[1];

        if (hitBallChecker(yellowX[0], yellowY[0]) || hitBallChecker(yellowX[1], yellowY[1])){
            if (hitBallChecker(yellowX[0], yellowY[0])){
                score = score + 1;
                yellowX[0] = -100;
            }
            else {
                score = score + 2;
                yellowX[1] = -100;
            }
        }

        if (yellowX[0] < 0){
            yellowX[0] = canvasWidth + 21;
            yellowY[0] = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(yellowX[0], yellowY[0], 25, yellowPaint);

        if (yellowX[1] < 0){
            yellowX[1] = canvasWidth + 21;
            yellowY[1] = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(yellowX[1], yellowY[1], 40, yellowPaint);

        greenSpeed[0] = 20;
        greenX[0] = greenX[0] - greenSpeed[0];
        greenSpeed[1] = 30;
        greenX[1] = greenX[1] - greenSpeed[1];

        if (hitBallChecker(greenX[0], greenY[0]) || hitBallChecker(greenX[1], greenY[1])){
            if (hitBallChecker(greenX[0], greenY[0])){
                score = score + 2;
                greenX[0] = -100;
            }
            else {
                score = score + 4;
                greenX[1] = -100;
            }
        }

        if (greenX[0] < 0){
            greenX[0] = canvasWidth + 21;
            greenY[0] = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(greenX[0], greenY[0], 25, greenPaint);

        if (greenX[1] < 0){
            greenX[1] = canvasWidth + 21;
            greenY[1] = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(greenX[1], greenY[1], 40, greenPaint);

        redSpeed[0] = 20;
        redX[0] = redX[0] - redSpeed[0];
        redSpeed[1] = 15;
        redX[1] = redX[1] - redSpeed[1];

        if (hitBallChecker(redX[0], redY[0]) || hitBallChecker(redX[1], redY[1])){
            if (hitBallChecker(redX[1], redY[1])) {
                redX[1] = -100;
                lifeCounterOfFish--;
            }
            else {
                redX[0] = -100;
                lifeCounterOfFish--;
            }

            if (lifeCounterOfFish == 0){
                Toast.makeText(getContext(), "Game over", Toast.LENGTH_SHORT).show();

                Intent gameOverIntent = new Intent(getContext(), GameOverActivity.class);
                gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameOverIntent.putExtra("score", score);
                getContext().startActivity(gameOverIntent);
            }
        }

        if (redX[0] < 0){
            redX[0] = canvasWidth + 21;
            redY[0] = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(redX[0], redY[0], 25, redPaint);

        if (redX[1] < 0){
            redX[1] = canvasWidth + 21;
            redY[1] = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(redX[1], redY[1], 40, redPaint);

        for (int i = 0; i < 3; i++){
            int x = (int) (580 + life[0].getWidth() * 1.5 * i);
            int y = 30;

            if (i < lifeCounterOfFish){
                canvas.drawBitmap(life[0], x, y, null);
            }
            else {
                canvas.drawBitmap(life[1], x, y, null);
            }
        }

        canvas.drawText("Score : " + score, 20, 60, scorePaint);

    }

    public boolean hitBallChecker(int x, int y){
        if (fishX < x && x < (fishX + fish[0].getWidth()) && fishY < y && y < (fishY + fish[0].getHeight())){
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            touch = true;

            fishSpeed = -22;
        }
        return true;
    }
}
