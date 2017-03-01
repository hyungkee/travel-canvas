package app.heuristy.dev.travelcanvas;

import java.io.Serializable;

/**
 * Created by Heuristy10 on 2017-03-01.
 */
public class Travel implements Serializable{
    private String name, detail;
    private int thumbnail;
    private int id;

    public Travel(){
        this.id = -1; // for add card
    }

    public Travel(String name, String detail, int thumbnail, int id){
        this.name = name;
        this.detail = detail;
        this.thumbnail = thumbnail;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
