package edu.utsa.cs3443.uxb422_lab4;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import edu.utsa.cs3443.uxb422_lab4.model.Airship;
import edu.utsa.cs3443.uxb422_lab4.model.Fleet;
import edu.utsa.cs3443.uxb422_lab4.model.Wizard;

import java.util.List;

public class AirshipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airship);

        TextView airshipName = findViewById(R.id.airship_name);
        TextView airshipRegistry = findViewById(R.id.airship_registry);
        LinearLayout wizardList = findViewById(R.id.wizard_list);

        // Retrieve the registry ID from the intent
        String registry = getIntent().getStringExtra("AIRSHIP_REGISTRY");

        // Get the corresponding airship and populate data
        Airship airship = Fleet.getAirshipByRegistry(registry);
        if (airship != null) {
            airshipName.setText(airship.getName());
            airshipRegistry.setText(airship.getRegistry());

            // Populate the wizard list dynamically
            List<Wizard> wizards = airship.getWizards();
            for (Wizard wizard : wizards) {
                addWizardToLayout(wizard, wizardList);
            }
        }
    }

    private void addWizardToLayout(Wizard wizard, LinearLayout wizardList) {
        LinearLayout wizardItem = (LinearLayout) getLayoutInflater().inflate(R.layout.wizard_item, null);

        ImageView wizardImage = wizardItem.findViewById(R.id.wizardImageView);
        TextView wizardName = wizardItem.findViewById(R.id.wizardNameTextView);
        TextView wizardRank = wizardItem.findViewById(R.id.wizardRankTextView);

        wizardName.setText(wizard.getName());
        wizardRank.setText(wizard.getRank());

        // Load wizard image by resource name
        int imageResource = getResources().getIdentifier(wizard.getImageResourceName(), "drawable", getPackageName());
        if (imageResource != 0) {
            wizardImage.setImageResource(imageResource);
        }

        // Add the wizard layout to the wizard list
        wizardList.addView(wizardItem);
    }
}