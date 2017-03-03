package app.heuristy.dev.travelcanvas.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Heuristy10 on 2017-03-03.
 */

public class VSChildDrgView extends VSChildView implements View.OnTouchListener{

    float pX, pY;
    float eX, eY;

    public VSChildDrgView(Context context){
        super(context);
        init();
    }

    public VSChildDrgView(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }

    public VSChildDrgView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    void init(){
        this.setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch(event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                pX = getVscX();
                pY = getVscY();

                eX = event.getRawX();
                eY = event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                setVscX(Math.max(0, Math.min(pX + (event.getRawX() - eX) / getViewScale(), getVsView().getWorldWidthMax() - getVscWidth())));
                setVscY(Math.max(0, Math.min(pY + (event.getRawY() - eY) / getViewScale(), getVsView().getWorldHeightMax() - getVscHeight())));
                break;

            case MotionEvent.ACTION_UP:
                break;

            default:
                return false;
        }
        return true;
    }
}
