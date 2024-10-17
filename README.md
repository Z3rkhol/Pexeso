# Přehled

Pexeso je karetní hra založená na paměti, kde cílem je najít páry shodných karet tak, že je otočíte po dvou. Tato implementace Java umožňuje režimy pro jednoho hráče i pro dva hráče s podporou sítě 4x4 karet.

## Herní vlastnosti:
- Režim pro jednoho hráče: Hráč se pokusí najít všechny dvojice.
- Režim pro dva hráče: Hráči se střídají a vyhrává hráč s největším počtem párů.
- Velikost desky: mřížka 4x4.
- Symboly karet: Sada 8 jedinečných symbolů, náhodně zamíchaných a umístěných na mřížku.



## Začínáme

### Jak začít hru

Spusťte aplikaci.

Hra poskytne stručný úvod a instrukce.

Chcete-li začít, zadejte "start" a stiskněte Enter.

Vyberte si mezi režimem pro jednoho nebo pro dva hráče.

### Vstup uživatele

Zadejte souřadnice pomocí systému ve stylu šachovnice. Zadejte například „A1“ pro výběr levého horního rohu nebo „C3“ pro třetí řádek a třetí sloupec.

## Herní mechanika

### Inicializace desky

Hra využívá desku 4x4 reprezentovanou 2D polem. Každá buňka obsahuje buď symbol karty (po otočení) nebo * (označující odklopenou kartu).

### Symboly karet
Hra používá následující symboly:
: ₳, ■, ▲, ●, ₼, ⨝, Ω, Ξ

Tyto symboly se zamíchají a umístí na plán ve dvojicích.

## Hratelnost

### Režim pro jednoho hráče:

Hráč vybere dvě karty za kolo a snaží se je spojit.
Pokud se karty shodují, zůstanou viditelné. Pokud ne, jsou otočeny zpět.
Hra pokračuje, dokud nejsou nalezeny všechny dvojice.

### Režim pro dva hráče:

Hráči se střídají v tahu.

Pokud hráč najde odpovídající pár, získá bod a pokračuje ve svém tahu.
Pokud ne, přijde na řadu další hráč.

Hra končí, když jsou nalezeny všechny páry, a vyhrává hráč s největším počtem párů.

### Ověření vstupu
Vstup musí obsahovat písmeno (A-D) a číslo (1-4).

Hra zkontroluje, zda je vybraná karta již otočena, než umožní hráči pokračovat.

## Příklad toku hry:
    - Cílem hry je najít všechny dvojice stejných karet.
    - Karty budete postupně otáčet...
    - Pokud jste připraveni, napište 'start'.
    - Hráč vybere souřadnici (např. "A1") a karta na tomto místě se odhalí.
    - Hráč zvolí druhou souřadnici (např. "B2").
    - Pokud se obě karty shodují, zůstanou lícem nahoru; jinak se po krátké pauze otočí zpět.
    - Proces se opakuje, dokud nejsou nalezeny všechny páry, nebo v režimu pro dva hráče, dokud jeden hráč nemá více párů než druhý.

## Závěr
Pexeso je zábavné a jednoduché pexeso, které lze hrát sami nebo s kamarádem. Hra je skvělý způsob, jak zlepšit koncentraci a paměťové schopnosti. Šťastné párování!

