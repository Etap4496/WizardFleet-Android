package edu.utsa.cs3443.uxb422_lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import edu.utsa.cs3443.uxb422_lab4.model.Fleet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load the fleet of airships from the CSV file once at start
        Fleet fleet = Fleet.getInstance();
        fleet.loadAirships(this);
        fleet.loadWizards(this); // Load wizards as well

        // Click listeners for each airship button
        setupButtonClickListener(R.id.buttonWindrider, "DR-512");
        setupButtonClickListener(R.id.buttonStormchaser, "ST-743");
        setupButtonClickListener(R.id.buttonSkybreaker, "SK-301");
        setupButtonClickListener(R.id.buttonThunderhawk, "TH-104");
    }

    private void setupButtonClickListener(int buttonId, String registry) {
        findViewById(buttonId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AirshipActivity.class);
                intent.putExtra("AIRSHIP_REGISTRY", registry);
                startActivity(intent);
            }
        });
    }
}