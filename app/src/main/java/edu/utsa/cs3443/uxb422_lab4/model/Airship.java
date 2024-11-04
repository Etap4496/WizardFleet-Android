package edu.utsa.cs3443.uxb422_lab4.model;

import java.util.ArrayList;


//Imported Airship class from Lab 2 with slight modifications of methods

public class Airship {
    private String name;
    private String registry;
    private String airshipClass;
    private ArrayList<Wizard> wizards;

    public Airship(String name, String registry) {
        this.name = name;
        this.registry = registry;
        this.airshipClass = airshipClass;
        wizards = new ArrayList<>();
    }

    public int getNumberOfWizards() {
        return wizards.size();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getRegistry() {
        return registry;
    }

    public ArrayList<Wizard> getWizards() {
        return wizards;
    }

    public void addWizard(Wizard wizard) {
        this.wizards.add(wizard);
    }

    public void setAirshipClass(String airshipClass) {
        this.airshipClass = airshipClass;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s, %s. Registry: %s\n", name, airshipClass, registry));
        sb.append(String.format("%d wizards assigned.\n", getNumberOfWizards()));
        for (Wizard wizard : wizards) {
            sb.append(wizard.toString()).append("\n");
        }
        return sb.toString();
    }
}