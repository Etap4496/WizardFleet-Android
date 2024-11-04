package edu.utsa.cs3443.uxb422_lab4.model;

import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Imported Fleet class from Lab 2 with slight modifications

public class Fleet {

    // Singleton instance
    private static Fleet instance;

    // List of airships and a map for wizards
    private List<Airship> airships;
    private HashMap<String, List<Wizard>> wizardMap; // Key: airship registry, Value: List of Wizards

    // Private constructor to prevent instantiation from other classes
    private Fleet() {
        airships = new ArrayList<>();
        wizardMap = new HashMap<>();
    }

    // Method to get the singleton instance of Fleet
    public static Fleet getInstance() {
        if (instance == null) {
            instance = new Fleet();
        }
        return instance;
    }

    // Non-static method to access the airships list
    public List<Airship> getAirships() {
        return airships;
    }

    // Method to load airships from a CSV file in the assets folder
    public void loadAirships(Context context) {
        try {
            // Access fleet.csv in the assets folder
            InputStream is = context.getAssets().open("fleet.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming CSV format: Name,Registry,Class
                String[] tokens = line.split(",");
                if (tokens.length >= 3) {
                    String name = tokens[0].trim();
                    String registry = tokens[1].trim();
                    String airshipClass = tokens[2].trim();
                    Airship airship = new Airship(name, registry);
                    airship.setAirshipClass(airshipClass); // Set the airship class
                    airships.add(airship);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to load wizards from a CSV file in the assets folder
    public void loadWizards(Context context) {
        try {
            // Access wizards.csv in the assets folder
            InputStream is = context.getAssets().open("wizards.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming CSV format: Name, Role, Rank, Species, Assignment
                String[] tokens = line.split(",");
                if (tokens.length >= 5) {
                    String name = tokens[0].trim();
                    String role = tokens[1].trim();
                    String rank = tokens[2].trim();
                    String species = tokens[3].trim();
                    String assignment = tokens[4].trim();

                    Wizard wizard = new Wizard(name, role, rank, species, assignment);

                    // Get the airship registry based on assignment
                    List<Wizard> assignedWizards = wizardMap.getOrDefault(assignment, new ArrayList<>());
                    assignedWizards.add(wizard);
                    wizardMap.put(assignment, assignedWizards);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to get an Airship by its registry
    public static Airship getAirshipByRegistry(String registry) {
        for (Airship airship : getInstance().getAirships()) {
            if (airship.getRegistry().equals(registry)) {
                // Populate wizards for the airship
                List<Wizard> wizards = getInstance().wizardMap.get(registry);
                if (wizards != null) {
                    for (Wizard wizard : wizards) {
                        airship.addWizard(wizard);
                    }
                }
                return airship;
            }
        }
        return null; // or handle the case where airship is not found
    }

    // Method to add an airship to the list
    public void addAirship(Airship airship) {
        airships.add(airship);
    }
}