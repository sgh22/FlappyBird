# Flappy Bird í JavaFX

Þetta er einföld útgáfa af Flappy Bird leiknum, forrituð í Java með JavaFX.
Leikmaður stýrir fugli með því að ýta á spacebar og þarf að fljúga á milli 
hindrana án þess að rekast á þær.

Leikurinn byggir á einföldum leikreglum: fuglinn flýgur upp þegar ýtt er á 
spacebar og fellur að sjálfu sér niður. Leikmaður þarf að forðast að klessa 
á hindranir eða jörðina. Ef það gerist kemur Game Over skjár og leikmaður 
getur annað hvort byrjað upp á nýtt eða farið aftur í menu (aðalvalmynd).

## Eiginleikar

- Fuglinn hoppar með því að ýta á bilslána
- Hindranir færast frá hægri til vinstri
- Stig eru reiknuð þegar fuglinn fer framhjá pípu
- Game Over skjár birtist þegar leik lýkur
- Möguleiki á að byrja leikinn aftur eða fara í menu

## Uppsetning og keyrsla

1. Opnaðu verkefnið í IntelliJ IDEA eða öðrum IDE með JavaFX stuðningi
2. Keyrðu skrána FlappyBirdApplication.java
3. Leikurinn opnast og byrjar í menu

## Maven uppsetning og keyrsla

1. Opnaðu terminal eða skipanalínu í verkefnismöppunni
2. Keyrðu eftirfarandi til að sækja dependency og byggja verkefnið:

   mvn clean install

3. Til að keyra leikinn með JavaFX Maven plugin:

   mvn javafx:run

Þú þarft að vera með Maven uppsett í tölvunni.

## Möppuskipan

src/\
     hi/\
      flappybird/\
    BirdMovement.java\
    CollisionHandler.java\
    FlappyBirdApplication.java\
    GameSceneController.java\
    MainMenuController.java\
    ObstaclesHandler.java\
    game-scene.fxml\
    main-menu.fxml

## Skráalýsing

FlappyBirdApplication.java – Byrjar leikinn og opnar menu\
GameSceneController.java – Heldur utan um leik, fugl og stig\
MainMenuController.java – Stýrir menu og PLAY hnappi\
ObstaclesHandler.java – Býr til og hreyfir hindranir\
BirdMovement.java – Heldur utan um hreyfingu fuglsins\
CollisionHandler.java – Athugar hvort fugl rekst á hindrun\
game-scene.fxml – FXML útlit fyrir leikinn\
main-menu.fxml – FXML útlit fyrir menu

## Kröfur

- OpenJDK 23
- JavaFX með Maven

## Höfundar

Nafn: Stefanía Guðrún Harðardóttir og Brynja Kristín Bertelsdóttir\
Skóli: Háskóli Íslands\
Áfangi: Viðmótsforritun vor 2025 lokaverkefni

## Staða verkefnis

Verkefnið er tilbúið sem demo. Leikurinn virkar, stig eru talin rétt og viðmótið birtir valkosti við leikslok. Í framtíðinni væri hægt að bæta við PNG mynd fyrir fuglinn, hljóðum, þema í main menu, high score og fleira.

