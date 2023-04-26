# Itinerary planning application for Pokemon group
<img src="Ressources/visuel.png">

## Introduction
***
"L'Association des Pokemonistes LIbres" is a group of Pokemon card collectors. This application allows the director of the association to deliver Pokemon cards to its members throughout France. This project was developed by me and my partner, Alexis ARAUJO, during our first year of the Computer Science Bachelor's Degree program.

## Features
***
- create a scenario by making a list of the different members to deliver, in .txt format.
- create an itinerary between different members in different cities, starting and ending at Vélizy (the group director's home).
- create the shortest possible itinerary.
- create all possible itineraries.
- view the members of the group.

## Installation
***
**1. Download** or **Clone** the repository on your local machine :
```bash
git clone https://github.com/SamirSubra/Itinerary-planning-application-for-Pokemon-group.git
```
**2.** **Install** JavaFX on your machine and **Configure** the libraries in your IDE, if necessary. 

**3.** **Run** the project by executing the main method in the _vue/Fenetre.java_ file.

**OR**

**Compile** with the command line :
```bash
javac --module-path "path\to\jdk\lib" --add-modules javafx.controls,javafx.fxml -cp src/main/java src/main/java/vue/Fenetre.java
```
Then **Run** the main method in the _vue/Fenetre.java_ file :
```bash
java --module-path "path\to\jdk\lib" --add-modules javafx.controls,javafx.fxml  Fenetre
```

## Usage
***
Select above the section you want (Itinéraires, Scenario or Membres). The scenario you create on the scenario section will be placed in Ressources folder. The scenario will be able to be used on the Itinéraires section.

## Visual
***
<div style="text-align: center;">

**Main page**
<img src="Ressources/main_page.png" alt="Main page" width="500px">
**Scenario page**
<img src="Ressources/scenario_page.png" alt="Scenario page" width="500px">
**Members page**
<img src="Ressources/members_page.png" alt="Members page" width="500px">
</div>