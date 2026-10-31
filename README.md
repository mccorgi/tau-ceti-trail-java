# tau-ceti-trail-java

#Tau Ceti Trail (Phase 1)

Author Sung Park (tpo25)

Text-based space version of Oregon Trail. Phase 1 runs in the terminal on a IDE. Phase 2 will add GUI.

##How to run

###From Terminal

Mac/Linus
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out app.Main

Windows(Powershell)
mkdir out
dir /s /b src\*.java > sources.txt
javac -d out @sources.txt
java -cp out app.Main

###From IDE
Go to Main.java in app and run the file.

##Features

3 difficulties: Expedition(Easy), Settler(Medium), Mercenary(Hard)

Each turn you rest or travel

Resting can heal injured and sick

Injured crew members will die in 3 turns if not healed

Travel advances along the route(10 steps)

Random events during travel(illness, breakdown, asteroid field, pirates)

Rest can heal crew memebers

5 main locations and trade is available at these locations

Win by reaching Tau Ceti with at least one crew member alive

Lose if everyone dies or a critical resource reaches zero

##Packages

app - running the game

model - classes used to run the game, presets, route

model.crew - classes for crew

model.resources - classes for resources/cargo

model.events - classes for random events


