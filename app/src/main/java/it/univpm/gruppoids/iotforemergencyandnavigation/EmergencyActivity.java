package it.univpm.gruppoids.iotforemergencyandnavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.Toast;

public class EmergencyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        final Button updateButton = (Button) findViewById(R.id.updatePos);
        if (updateButton != null) {
            updateButton.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Toast.makeText(getApplicationContext(), "Position is updated", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }
    }

    public void goInitPos(View view) {
        Intent intent = new Intent(this, InitPositionActivity.class);
        startActivity(intent);
        // TODO fare in modo che una volta terminata l'emergenza, l'utente non può più tornare nelle schermate precedenti tramite tasto back
        //finish();
    }

}