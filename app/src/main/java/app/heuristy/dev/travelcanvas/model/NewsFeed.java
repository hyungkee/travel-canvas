package app.heuristy.dev.travelcanvas.model;

/**
 * Created by Heuristy10 on 2017-03-02.
 */

public class NewsFeed {
    public String title;
    public String contents;

    public NewsFeed(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
