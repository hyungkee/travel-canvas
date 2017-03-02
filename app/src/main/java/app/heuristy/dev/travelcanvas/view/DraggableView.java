package app.heuristy.dev.travelcanvas.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by Heuristy10 on 2017-03-03.
 */

public class DraggableView extends RelativeLayout implements View.OnTouchListener{
    Context mContext;
    float dX, dY;
    int lastAction;

    public DraggableView(Context context){
        super(context);
        init(context);
    }

    public DraggableView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }

    public DraggableView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    void init(Context context){
        mContext = context;
        this.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch(event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                dX = getX() - event.getRawX();
                dY = getY() - event.getRawY();
                lastAction = MotionEvent.ACTION_DOWN;
                break;

            case MotionEvent.ACTION_MOVE:
                setX(event.getRawX() + dX);
                setY(event.getRawY() + dY);
                lastAction = MotionEvent.ACTION_MOVE;
                break;

            case MotionEvent.ACTION_UP:
                if(lastAction == MotionEvent.ACTION_DOWN)
                    Toast.makeText(mContext, "Clicked!", Toast.LENGTH_SHORT).show();
                break;

            default:
                return false;
        }
        return true;
    }
}
