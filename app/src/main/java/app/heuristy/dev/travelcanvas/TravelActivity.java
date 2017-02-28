package app.heuristy.dev.travelcanvas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class TravelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        Intent intent = getIntent();
        Travel travel = (Travel)intent.getSerializableExtra("travel_image_src");

        ImageView cover = (ImageView)findViewById(R.id.travel_image);
        cover.setImageResource(travel.getThumbnail());
    }
}
