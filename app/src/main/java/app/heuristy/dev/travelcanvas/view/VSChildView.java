package app.heuristy.dev.travelcanvas.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Heuristy10 on 2017-03-03.
 */

public class VSChildView extends View implements VSChild{

    private VSView vsView;
    private float vscX, vscY;
    private float vscWidth, vscHeight;

    public VSChildView(Context context){
        super(context);
        init();
    }

    public VSChildView(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }

    public VSChildView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        vsView = null;
        vscX = 0;
        vscY = 0;
        vscWidth = 300;
        vscHeight = 300;
    }

    public float getViewScale(){
        if(vsView == null)
            return 1;
        else
            return vsView.getScale();
    }



    @Override
    public void setVscX(float vscX) {
        this.vscX = vscX;
        if(vsView!=null)    vsView.notifyVscChanged(this);
    }

    @Override
    public float getVscX() {
        return vscX;
    }

    @Override
    public void setVscY(float vscY) {
        this.vscY = vscY;
        if(vsView!=null)    vsView.notifyVscChanged(this);

    }

    @Override
    public float getVscY() {
        return vscY;
    }

    @Override
    public void setVscWidth(float vscWidth) {
        this.vscWidth = vscWidth;
        if(vsView!=null)    vsView.notifyVscChanged(this);
    }

    @Override
    public float getVscWidth() {
        return vscWidth;
    }

    @Override
    public void setVscHeight(float vscHeight) {
        this.vscHeight = vscHeight;
        if(vsView!=null)    vsView.notifyVscChanged(this);
    }

    @Override
    public float getVscHeight() {
        return vscHeight;
    }

    @Override
    public void regVsView(VSView vsView){
        this.vsView = vsView;
    }

    @Override
    public VSView getVsView(){
        return vsView;
    }



}
