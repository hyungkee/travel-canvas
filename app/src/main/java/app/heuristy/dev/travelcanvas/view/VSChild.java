package app.heuristy.dev.travelcanvas.view;

/**
 * Created by Heuristy10 on 2017-03-03.
 */

public interface VSChild {

    void setVscX(float vscX);
    float getVscX();

    void setVscY(float vscY);
    float getVscY();

    void setVscWidth(float vscWidth);
    float getVscWidth();

    void setVscHeight(float vscHeight);
    float getVscHeight();

    void regVsView(VSView vsView);
    VSView getVsView();
}
