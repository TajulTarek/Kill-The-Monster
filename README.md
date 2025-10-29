# Kill-The-Monster

An arcade-style JavaFX game for Windows built with FXML views and controller classes. Choose between single-player "Punch", single-player "Laser Gun", or competitive two-player "Laser Gun" modes and try to kill as many monsters as you can.

## Features
- Single-player Punch mode: click monsters to reduce their health; game ends if a monster reaches the ground.
- Single-player Laser Gun mode: move a laser cannon with keyboard and stop monsters before they land; you lose health when a monster touches the ground.
- Two-player Laser Gun mode: two cannons controlled on the same keyboard; select a battle timer and the higher kill count wins.
- Simple score and health tracking overlays.
- Fully JavaFX-based UI using FXML and CSS.

## Game Modes
1. Punch (Single Player)
   - Click on monsters to reduce their health to zero.
   - If any monster reaches the ground, the game ends and your results are shown.

2. Laser Gun (Single Player)
   - Move the laser cannon and align its barrel with monsters to damage them.
   - If a monster reaches the ground, your health decreases by the monster's remaining health percentage.
   - The game ends when your health reaches 0; your total kill count is displayed.

3. Laser Gun (Two Players)
   - Two cannons: left player vs right player sharing the keyboard.
   - Choose a battle time; when the timer ends, the higher kill count wins (draws possible).

## Controls
- Home/Navigation
  - Home -> Play -> Choose Mode
  - Rules and Restart buttons are available inside modes.

- Punch Mode
  - Mouse: click on monsters to punch (reduce their health).

- Laser Gun (Single Player)
  - A: move cannon left
  - D: move cannon right

- Laser Gun (Two Players)
  - Left player: A (left), D (right)
  - Right player: J (left), L (right)

## Screens and FXML
- Home: `src/application/Home.fxml` (controller `HomeController`)
- Choose Mode: `src/application/chooseMode.fxml` (controller `chooseController`)
- Punch Mode: `src/application/punch.fxml` (controller `punchController`)
- Laser Gun (Single): `src/application/lasergunMode.fxml` (controller `lasergunModeController`)
- Laser Gun (Two Players): `src/application/lasergunMode2.fxml` (controller `lasergunModeController2`)
- Rules: `src/application/punchrules.fxml`, `src/application/laserrules.fxml`, `src/application/laserrules2.fxml`

## Project Structure
```
.
├─ src/
│  ├─ application/
│  │  ├─ *.java              # Controllers and Main
│  │  ├─ *.fxml              # Views
│  │  ├─ *.css               # Stylesheets
│  │  └─ pics/               # Images and audio assets
│  ├─ levels/                # Level images
│  └─ module-info.java       # Java module declarations
├─ bin/                      # Compiled classes (if present)
└─ build.fxbuild             # e(fx)clipse/IDE build metadata
```

## Requirements
- JDK 17 or newer (works well with LTS JDKs)
- JavaFX SDK (tested with JavaFX 19 as indicated by FXML `xmlns`)

If you run from the command line, you must add JavaFX to the module path. When running from an IDE (IntelliJ IDEA, Eclipse with e(fx)clipse, or VS Code with JavaFX configured), set up the JavaFX SDK in project settings.

### Java Module Configuration
`src/module-info.java` declares:
```
module Kill_The_Monstar {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;

    opens application to javafx.graphics, javafx.fxml;
}
```

## Build and Run

### Run from IDE
1. Install the JavaFX SDK and configure it in your IDE's project SDK/libs.
2. Ensure VM options include the JavaFX modules and module path (see below).
3. Run the `application.Main` class.

### Run from Command Line (Windows)
Assuming:
- `JAVA_HOME` points to your JDK
- `PATH_TO_FX` points to the JavaFX SDK `lib` folder, for example: `C:\javafx-sdk-19\lib`

Compile:
```
javac --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml,javafx.web,javafx.graphics,javafx.base -d bin src/module-info.java src/application/*.java
```

Run:
```
java --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml,javafx.web,javafx.graphics,javafx.base -cp bin application.Main
```

Note: If your classpath uses the `src` layout directly in your environment, point `-cp` accordingly, but using `bin` (compiled output) is recommended.

## Assets and Styling
- Images, sprites, and audio: `src/application/pics/`
- Global styles: `src/application/application.css`, `src/application/level1.css`, `src/application/levels.css`, `src/application/choosemode.css`

