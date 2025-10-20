# Reflektioner över kapitel 2-11 i boken Clean Code (Robert C. Martin)

## Kapitel 2
Som i modulen har jag försökt vara väldigt noga med namn på metoder och variabler. Mina metoder i AppController följer samma standard och är tydliga (tycker jag), tex är alla metoder som svarar på knapptryck inledda med ”on”. Jag har tagit mig tid och försökt se till så att namnen avslöjar vad som händer utan att man faktiskt behöver läsa koden. Alla namnen går att både läsa och uttala utan problem, och är lätta att söka efter.  

![Konceptuellt](/docs/academic_images/kapitel2.png)

## Kapitel 3
Mina funktioner är överlag små och fokuserade, har ett syfte (som speglas i namnet), och har inga oväntade bieffekter. Jag har försökt vara noga med att hålla ungefär samma abstraktionsnivå inom samma metod, men är lite osäker på hur väl jag lyckats med det. Särskilt i UI-klassen blir det lite blandat emellanåt, men allt är ändå tydligt och läsligt (och kort) så jag känner mig okej med det. Jag har även försökt att undvika repetition i möjligaste mån, och tror att jag lyckats ganska väl med det, hjälpmetoder finns och används för det mesta på flera ställen.  

![Hög nivå](/docs/academic_images/kapitel3_1.png) ![Lite blandat](/docs/academic_images/kapitel3_2.png)

## Kapitel 4
Koden innehåller oerhört få kommentarer, men det känns som att det är precis lagom ändå. 
Eftersom appen inte direkt innehåller några publika metoder annat än main (App.java) så har jag varit väldigt sparsam med Javadoc och istället generellt valt att låta metodnamn och returvärden tala för sig. Några förtydligande finns:  
![Information om filnamn](/docs/academic_images/kapitel4_1.png) ![Förtydligande över returvärden](/docs/academic_images/kapitel4_2.png)

## Kapitel 5
När det gäller var man placerar funktioner som kallar på varandra är jag lite tudelad. Ibland vill jag ha privata metoder alldeles under de metoder som använder dem, och ibland föredrar jag att samla ihop alla privata metoder i slutet av filen (jag gillar att dela upp saker i kategorier). I appen har jag valt att lägga dem nära där de anropas första gången.
Avskyr långa rader, så försöker oftast att dela upp så det blir lättare att läsa all kod utan att behöva flytta ögonen för mycket i sidled.  

![Placering av privata metoder](/docs/academic_images/kapitel5.png)

## Kapitel 6
Intressant kapitel och ögonöppnande gällande getters/setters, för tidigare har jag fått höra att såna ska man nästan alltid ha, men givetvis är det ofantligt dumt om de inte Verkligen behövs. Självklart bryter det inkapsling liksom, så det tar jag verkligen med mig framåt.
Jag hade från början stoppat in modulens kategorier direkt i min AppController (UI-klassen), för att kunna visa dem i gränssnittet, men efter lite funderande så ändrade jag så att de istället skickas in till den via adaptern och behöll därmed lite lägre coupling.
Jag såg även till att skicka in bland annat Adaptern till UI-klassen via App.java istället för att skapa själv, tror det har samma effekt på coupling? Det var tanken iaf! (mer tankar om detta under kapitel 11)  

![Här sätts adaptern](/docs/academic_images/kapitel6.png)

## Kapitel 7
Jag kontrollerar null-värden endast i de metoder som tar emot värden utifrån, där jag inte kan styra att inkommande inte får vara null på annat sätt. Eftersom jag velat utgå ifrån att jag INTE var skapare av modulen när jag byggde appen, så tog jag det säkra före det osäkra trots att jag vet att modulen aldrig returnerar null någonstans, hade det varit en annan skapare av modulen vet man ju aldrig om man inte lusläser all dens kod.  

![En null-kontroll](/docs/academic_images/kapitel7.png)

## Kapitel 8
Ser fram emot testkursen lite mer nu, för att kunna skriva tester för att lära sig tredjepartskod låter ju hur klokt som helst, även om det tar lite tid (som man säkert hade lagt på misstag pga inte testat ändå). 
Läser jag kapitlet rätt så ingår väl wrappers egentligen i kategorin ”Boundaries”, för vad är en wrapper om inte just det – en gräns mellan programmet jag skriver och en yttre part jag använder mig av. Om jag tolkat rätt så har jag ju implementerat just det i min app genom min BudgetTrackerAdapter-klass, som sköter all logik mellan UI-klassen och modulen.  

![Metod som kommunicerar mellan klasserna](/docs/academic_images/kapitel8.png)

## Kapitel 9
Jag har inte ännu funnit den stora glädjen i testande, framförallt för att jag inte gillar viss typ av repetition (så manuella är dötrista), och jag inte riktigt haft tid att sätta mig in i ett bra testramverk än. Det lilla jag nosat på automatiska tester har jag ändå gillat, så jag ser fram emot kursen som kommer då jag faktiskt gärna vill prova på TDD.
Men i detta projekt har jag hållit det simplast möjligt (mycket pga tidsbrist också), och testerna är korta, tydliga, och väl namngivna.  

![Testkod](/docs/academic_images/kapitel9.png) 

## Kapitel 10
Som med mina metoder har jag försökt hålla klasser väl namngivna, begränsade, och korta. Något jag däremot inte har gjort, ser jag när jag skriver detta, är att begränsa antalet fält i åtminstone UI-klassen. Jag har radat upp i princip alla element först i klassen, oavsett hur många gånger de sen används nere i koden, vilket ibland bara är en enda. Ska ändras vid tillfälle, på ett eller annat sätt (utan att tappa för mycket cohesion)!  

![Lite väl många fält](/docs/academic_images/kapitel10.png)

## Kapitel 11
Det visar sig när jag läser detta kapitel att jag redan har använt mig av det de kallar dependecy injection i min app, då App.java skickar in den instans av adaptern som AppController sen använder. Det är dock fortfarande hög coupling på klasserna eftersom AppController har BudgetTrackerAdapter som fälttyp, men det är åtminstone lite förberett för att lättare kunna byta ut adaptern-typen mot tex ett interface längre fram om jag skulle vilja det.
Tidigare kurser har lagt en väldigt bra grund för att lära oss att bygga bra system tycker jag, Mats ffa har nött in ”separation of concerns” hos oss och för mig är det något jag strävar efter så mycket jag kan (kategorisering – yey!).  

![App.java](/docs/academic_images/kapitel11.png)


### Notes from the author :)
Ca 270 rader kod innan refactoring.  
Några av mina egna tankar/förändringar under refactoring:
-	hade transactioncategories i controllern, ändrade så det skickas in eller hämtas från app.java eller adaptern.
-	onSaveLogToFile() i controllern är lite lång, men jag vet inte hur jag skulle kunna förkorta den snyggt utan att det blir onödigt futtiga hjälpmetoder.
-	bygg ut adaptern/wrappern med fel-loggning i framtiden
