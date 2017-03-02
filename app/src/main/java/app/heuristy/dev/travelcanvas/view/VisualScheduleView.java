package app.heuristy.dev.travelcanvas.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.heuristy.dev.travelcanvas.R;

/**
 * Created by Heuristy10 on 2017-03-03.
 */

public class VisualScheduleView extends RelativeLayout implements View.OnTouchListener{

    private final static int seekScaleMax = 3;

    private SeekBar scaleBar;
    private Context mContext;

    private List<View> viewList;
    private List<ViewGroup.LayoutParams> paramList;

    private List<Float> XList, YList;

    private float dX, dY;
    private int lastAction;
    private float rev_worldX, rev_worldY;
    private float worldWidth, worldHeight;

    private ScaleGestureDetector scaleGestureDetector;

    public VisualScheduleView(Context context){
        super(context);
        init(context);
    }

    public VisualScheduleView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }

    public VisualScheduleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setup();
    }

    void init(Context context){
        mContext = context;

        rev_worldX = 0;
        rev_worldY = 0;

        viewList = new ArrayList<>();
        paramList = new ArrayList<>();
        XList = new ArrayList<>();
        YList = new ArrayList<>();

        this.setOnTouchListener(this);

        scaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
    }

    void setup(){
        ViewGroup.LayoutParams params = getLayoutParams();
        worldWidth = params.width;
        worldHeight = params.height;

        int childcount = getChildCount();

        for(int i=0;i<childcount;i++){
            View v = getChildAt(i);
            if(v != scaleBar){
                viewList.add(v);
                paramList.add(new ViewGroup.LayoutParams(v.getLayoutParams()));
            }
        }
    }

    public void setSeekBar(SeekBar seekBar){
        scaleBar = seekBar;

        scaleBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                final int size = viewList.size();
                for(int i=0;i<size;i++){
                    float scale = 1 + (float)(seekScaleMax-1)*progress/100;

                    View v = viewList.get(i);
                    ViewGroup.LayoutParams params = v.getLayoutParams();
                    params.width = (int)(paramList.get(i).width*scale);
                    params.height = (int)(paramList.get(i).height*scale);
                    v.setLayoutParams(params);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    boolean activation = true;

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        scaleGestureDetector.onTouchEvent(event);

        int pointerCount = event.getPointerCount();

        switch(event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                dX = rev_worldX - event.getRawX();
                dY = rev_worldY - event.getRawY();
                XList.clear();
                YList.clear();
                for(int i=0;i<viewList.size();i++){
                    XList.add(new Float(viewList.get(i).getX() - event.getX()));
                    YList.add(new Float(viewList.get(i).getY() - event.getY()));
                }
                lastAction = MotionEvent.ACTION_DOWN;
                activation = true;
                break;

            case MotionEvent.ACTION_MOVE:

                if(pointerCount > 1)
                    activation = false; // 멀티터치에 의한 교란을 막음.

                if(activation){
                    rev_worldX = event.getRawX() + dX;
                    rev_worldY = event.getRawY() + dY;
                    for(int i=0;i<viewList.size();i++){
                        viewList.get(i).setX(event.getX() + XList.get(i));
                        viewList.get(i).setY(event.getY() + YList.get(i));
                    }
                    lastAction = MotionEvent.ACTION_MOVE;
                }
                break;

            case MotionEvent.ACTION_UP:
//                if(lastAction == MotionEvent.ACTION_DOWN)
//                    Toast.makeText(mContext, "Clicked!", Toast.LENGTH_SHORT).show();
                break;

            default:
                return false;
        }
        return true;
    }



    public float getWorldX() {
        return -rev_worldX;
    }

    public float getWorldY() {
        return -rev_worldY;
    }

    public float getWorldWidth(){
        return worldWidth;
    }

    public float getWorldHeight(){
        return worldHeight;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleBar.setProgress((int)Math.max(0, Math.min(scaleBar.getProgress() + (float)(detector.getScaleFactor() - 1) * 100, 100)));
            return true;
        }
    }
}
