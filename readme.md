# Teoria śladów w eliminacji Gaussa



## Opis

Projekt wykonany w języku scala demonstrujący wykorzystanie teorii śladów we współbieżnej eliminacji Gaussa. Użytkownik może rozwiązać układ równań podany jako macierz, poznać postać normalną Foaty (FNF) dla podanego problemu oraz wygenerować graf zależności Diekerta dla tasków.



## Technologie

* Scala
* Graphiz



## Przykład działania

1. Dane wejściowe: w pierwszym wierszu podany jest rozmiar macierzy, kolejne wiersze poza ostatnim to rozważana macierz, ostatni wiersz to wektor wynikowy.

   ```
   3
   2.0 1.0 3.0
   4.0 3.0 8.0
   6.0 5.0 16.0
   6.0 15.0 27.0
   ```

2. Wynik działania programu:

   Uzyskujemy macierz trójkątną dolną oraz macierz wynikową. Dodatkowo otrzymujemy graf zależności Diekerta.

   ```
   Output matrix before solving equations: 
   [2.0, 1.0, 3.0 | 6.0]
   [0.0, 1.0, 2.0 | 3.0]
   [0.0, 0.0, 3.0 | 3.0]
   
   Output (after solving equations):
   [1.0, 0.0, 0.0 | 1.0]
   [0.0, 1.0, 0.0 | 1.0]
   [0.0, 0.0, 1.0 | 1.0]
   ```

   

![dot2](graphs_svg_files/dot2.svg)