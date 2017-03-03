package app.heuristy.dev.travelcanvas.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heuristy10 on 2017-03-03.
 * VSView : VisualScheduleView
 */

public class VSView extends RelativeLayout implements View.OnTouchListener{

    private final static int seekScaleMax = 3;
    private final static int viewDragMargin = 300;

    private SeekBar scaleBar;

    private List<VSChildView> viewList;

    private float worldX;
    private float worldY;
    private float worldWidth;
    private float worldHeight;
    private float worldWidthMax;
    private float worldHeightMax;

    private float touchCenterX;
    private float touchCenterY;

    private boolean isSetup = false;
    
    private ScaleGestureDetector scaleGestureDetector;

    public VSView(Context context){
        super(context);
        init();
    }

    public VSView(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }

    public VSView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(!isSetup){
            setup();
            isSetup = true;
        }
    }

    void init(){
        // set Touch Listener
        this.setOnTouchListener(this);

        // set Detector
        scaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
    }

    void setup(){
        // get X, Y, Width, Height
        worldX = 0;
        worldY = 0;
        worldWidth = getWidth();
        worldHeight = getHeight();

        // collect Child Views
        viewList = new ArrayList<>();
        for(int i=0;i<getChildCount();i++){
            View v = getChildAt(i);
            if(v instanceof VSChildView){
                viewList.add((VSChildView)v);
            }
        }

        // adjust Child Views
        for(VSChildView v : viewList){
            adjustVscView(v);
        }
    }

    public void setSeekBar(SeekBar seekBar){
        scaleBar = seekBar;

        scaleBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float scale = (float)1/((float)(seekScaleMax-1)*progress/100 + 1);
                float pX = worldX;
                float pY = worldY;
                if(fromUser){ // progressbar를 직접 터치하여 조절
                    // 화면 중심이 기준
                    pX = getWorldCenterX();
                    pY = getWorldCenterY();
                }else{ // 핀치줌 의한 확대축소
                    // 핀치줌의 중심이 기준
                    pX = worldX + touchCenterX / scale;
                    pY = worldY + touchCenterY / scale;
                }
                // pX, pY를 중심으로 확대 적용
                float dW = worldWidth - getWidth() / scale;
                float dH = worldHeight - getHeight() / scale;
                worldX = worldX + (pX - worldX) * dW / worldWidth;
                worldY = worldY + (pY - worldY) * dH / worldHeight;

                // 화면 영역 제한
                worldX = Math.max(-viewDragMargin, Math.min(worldX, worldWidthMax - worldWidth + viewDragMargin));
                worldY = Math.max(-viewDragMargin, Math.min(worldY, worldHeightMax - worldHeight + viewDragMargin));

                // scale에 맞게 View조정
                worldWidth = (float)getWidth() / scale;
                worldHeight = (float)getHeight() / scale;

                adjustVscViewAll();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void notifyVscChanged(VSChildView vscView){
        adjustVscView(vscView);
    }

    private void adjustVscViewAll(){
        for(VSChildView v : viewList){
            adjustVscView(v);
        }
    }

    private void adjustVscView(VSChildView vsChildView){

        if("back".equals(vsChildView.getTag())){ // background
            // 전체크기를 커버하지 못하지만 시각적 효과로 극복한다.
            if(-viewDragMargin <= worldX && worldX <= 0)
                vsChildView.setX((vsChildView.getVscX() - worldX)*getScale());
            else if(worldWidthMax - worldWidth <= worldX && worldX <= worldWidthMax - worldWidth + viewDragMargin)
                vsChildView.setX((worldWidthMax - worldWidth - worldX)*getScale());
            else
                vsChildView.setX(0);

            if(-viewDragMargin <= worldY && worldY <= 0)
                vsChildView.setY((vsChildView.getVscY() - worldY)*getScale());
            else if(worldHeightMax - worldHeight <= worldY && worldY <= worldHeightMax - worldHeight + viewDragMargin)
                vsChildView.setY((worldHeightMax - worldHeight - worldY)*getScale());
            else
                vsChildView.setY(0);

        }else{ // else object
            // set X, Y
            vsChildView.setX((vsChildView.getVscX() - worldX)*getScale());
            vsChildView.setY((vsChildView.getVscY() - worldY)*getScale());

            // set Width, Height
            ViewGroup.LayoutParams params = vsChildView.getLayoutParams();
            params.width = (int)(vsChildView.getVscWidth() * getScale());
            params.height = (int)(vsChildView.getVscHeight() * getScale());
            vsChildView.setLayoutParams(params);
        }
    }


    boolean activation = true;


    private float eX, eY;
    private float wpX, wpY;


    @Override
    public boolean onTouch(View view, MotionEvent event) {


        // 터치 중심을 찾는다.
        float accX = 0;
        float accY = 0;
        int pointerCount = event.getPointerCount();
        for(int i=0;i<pointerCount;i++){
            accX += event.getX(i);
            accY += event.getY(i);
        }
        touchCenterX = accX / pointerCount;
        touchCenterY = accY / pointerCount;

        // 멀티터치에 의한 크기변화
        scaleGestureDetector.onTouchEvent(event);

        switch(event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                eX = event.getRawX();
                eY = event.getRawY();
                wpX = worldX;
                wpY = worldY;
                activation = true;
                break;

            case MotionEvent.ACTION_MOVE:

                if(pointerCount > 1)
                    activation = false; // 멀티터치에 의한 교란을 막음.

                if(activation){
                    worldX = wpX - (event.getRawX() - eX) / getScale();
                    worldY = wpY - (event.getRawY() - eY) / getScale();

                    // 화면 영역 제한
                    float resizedWorldX = Math.max(-viewDragMargin, Math.min(worldX, worldWidthMax - worldWidth + viewDragMargin));
                    float resizedWorldY = Math.max(-viewDragMargin, Math.min(worldY, worldHeightMax - worldHeight + viewDragMargin));

                    worldX = resizedWorldX;
                    worldY = resizedWorldY;

                    adjustVscViewAll();
                }
                break;

            case MotionEvent.ACTION_UP:
                break;

            default:
                return false;
        }

        return true;
    }

    public float getWorldX(){
        return worldX;
    }

    public float getWorldY(){
        return worldY;
    }

    public float getWorldWidth(){
        return worldWidth;
    }

    public float getWorldHeight(){
        return worldHeight;
    }

    public float getWorldWidthMax() {
        return worldWidthMax;
    }

    public void setWorldWidthMax(float worldWidthMax) {
        this.worldWidthMax = worldWidthMax;
    }

    public float getWorldHeightMax() {
        return worldHeightMax;
    }

    public void setWorldHeightMax(float worldHeightMax) {
        this.worldHeightMax = worldHeightMax;
    }

    public float getWorldCenterX(){
        return worldX + worldWidth/2;
    }

    public float getWorldCenterY(){
        return worldY + worldHeight/2;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleBar.setProgress((int)Math.max(0, Math.min(scaleBar.getProgress() - (float)Math.log(detector.getScaleFactor()) * 25, 100)));
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return super.onScaleBegin(detector);
        }
    }

    public float getScale(){
        return (float)1/((float)(seekScaleMax-1)*scaleBar.getProgress()/100 + 1);
    }
}
