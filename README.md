# Pong

1. Repository herunterladen (clonen) und auf euren Rechnern zum Laufen bringen.
2. Eine Klasse 'Ball' erstellen, die 'GameObject' implementiert, nach dem Muster von 'Paddle'.
3. Der Ball soll nicht nur eine Position haben, sondern auch einen Richtungsvektor (Point2D oder double X und double Y). Der Vektor wird auf die Position aufaddiert bei jedem Update.
4. Wenn die Y-Koordinate des Balls <= 0 ist, dann spiegelt ihr den Richtungsvektor oben an der Kante.
Wenn die Y-Koordinate des Balls >= maxX ist, dann … unten an der Kante.
Spiegelung eines Richtungsvektors geschieht, indem man eine Koordinate *-1 nimmt. Welche müsst ihr selber rausfinden. :)
5. Wenn ihr das Ganze jetzt noch für X macht, dann sollte der Ball mal unendlich in eurem Spielfeld umherspringen.
6. Jetzt könnt ihr noch einen Stand-Zähler machen (würde vorschlagen 2 Zahlen oben am Rand rechts und links).
Die Zähler könnt ihr erhöhen, wenn der Ball die X-Grenze des jeweiligen Spielers überschreitet.
7. Dann solltet ihr noch, wenn das passiert, den Ball resetten (von der Mitte aus mit einem neuen, zufälligen, Richtungsvektor starten.
8. Das könnt ihr dann auch noch am Anfang des Spieles machen, damit der Ball nicht immer den selben Startvektor hat am Beginn des Spieles (Random-Klasse… https://stackoverflow.com/a/12458415). Als Seed könnt ihr `System.currentTimeInMillis()` nehmen.
9. Die Paddles sollten jetzt noch kollidieren mit dem Ball (wenn Ball die X-Koordinate des Paddles erreicht hat, checken, ob die Y-Koordinate des Balles zwischen denen des Paddles liegt und ev. spiegeln).
10. Euer Pong ist jetzt fertig.
Jetzt könnt ihr noch Zusatzaufgaben machen… Sucht euch was aus und baut das, wenn ihr glaubt, dass es eine gute Idee ist und das Spiel interessanter macht :)

## Ideen
* Ball wechselt zufällig die Geschwindigkeit
* Ball wechselt die Farbe
* Wenn Ball Blau und er wird vom Paddle getroffen, dann 'spawned' er einen zweiten Ball, der mit zufälligem Vektor startet, wo der Ball getroffen wurde.
* Wenn ein Ball das Spielfeld verlässt, dann den Ball rausnehmen.
* Wenn der letzte Ball das Spielfeld verlässt, dann gibt es erst einen Punkt.
* Hindernisse, die zufällig am Spielfeld auftauchen.
* ...

Zufällige Elemente sollten selten auftreten; Wahrscheinlichkeiten sorgsam wählen.
Viel Spaß.
