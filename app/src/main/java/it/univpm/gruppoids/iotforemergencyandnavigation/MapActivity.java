package it.univpm.gruppoids.iotforemergencyandnavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import it.univpm.gruppoids.iotforemergencyandnavigation.interfaces.TouchImageView;

public class MapActivity extends AppCompatActivity {

    private ImageView mapImage;
    private Button insertPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        final Intent inputIntent = getIntent();
        if (inputIntent != null) {
            final int floorExtra = inputIntent.getIntExtra("floor", 0);
            final String floorString = Integer.toString(floorExtra);
            mapImage = (ImageView) findViewById(R.id.mapFloor);

            if (!floorString.equals(0)) {
                getSupportActionBar().setTitle(getResources().getString(R.string.floor_pos) + " " + floorString);
            }
            switch (floorString) {
                case "145":
                    mapImage.setImageResource(R.drawable.q145);
                    break;
                case "150":
                    mapImage.setImageResource(R.drawable.q150);
                    break;
                case "155":
                    mapImage.setImageResource(R.drawable.q155);
                    break;
            }
        }

        mapImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    insertPos = (Button) findViewById(R.id.insert_pos);
                    insertPos.setVisibility(View.VISIBLE);
                    String text = "You click at x = " + event.getX() + " and y = " + event.getY();
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        insertPos.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.firstPage:
                Intent intent = new Intent(this, InitPositionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                MapActivity.this.finish();
                break;
            case android.R.id.home: // Id relativo alla freccia in alto a sinistra, per tornare alla schermata precedente
                NavUtils.navigateUpFromSameTask(this);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void goMod(View view) {
        Intent intent = new Intent(this, ModActivity.class);
        startActivity(intent);
    }
}