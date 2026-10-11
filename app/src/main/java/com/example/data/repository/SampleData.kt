package com.example.data.repository

import com.example.data.model.*

object SampleData {

    // EXACTLY 12 KIEZE AS REQUESTED WITH REAL PHOTOGRAPHIC IMAGES
    val kieze = listOf(
        Kiez(
            id = "mitte",
            name = "Mitte",
            subtitle = "Historisch, Kulturell, Zentral",
            postalCodes = "10115, 10117, 10119, 10178, 10179",
            population = "ca. 102.000",
            specialty = "Brandenburger Tor, Museumsinsel, Fernsehturm",
            description = "Das pulsierende Herz Berlins: Wo die Geschichte an jeder Ecke spürbar ist. Zwischen der Museumsinsel, dem Alexanderplatz und den Hackeschen Höfen vereint Mitte Weltmetropolen-Flair mit exklusiven Galerien, Spitzen-Gastronomie und architektonischen Monumenten.",
            tags = listOf("Historisch", "Kultur", "Zentrum", "Monumente"),
            lat = 52.5200,
            lng = 13.4050,
            highlights = listOf("Fernsehturm", "Brandenburger Tor", "Museumsinsel", "Hackesche Höfe"),
            imageUrl = "https://images.unsplash.com/photo-1560969184-10fe8719e047?w=800&auto=format&fit=crop&q=80", // Brandenburger Tor
            impressionImages = listOf(
                "https://images.unsplash.com/photo-1560969184-10fe8719e047?w=600&auto=format&fit=crop&q=80",
                "https://images.unsplash.com/photo-1599946347371-68eb71b16afc?w=600&auto=format&fit=crop&q=80",
                "https://images.unsplash.com/photo-1546726747-421c6d69c929?w=600&auto=format&fit=crop&q=80"
            ),
            rentIndex = "21,50 €/m²",
            vibe = "Metropolisch & Monumental"
        ),
        Kiez(
            id = "kreuzberg",
            name = "Kreuzberg",
            subtitle = "Kreativ, International, Vielfältig",
            postalCodes = "10961, 10963, 10965, 10967, 10969, 10997, 10999",
            population = "ca. 155.000",
            specialty = "Streetart, Nightlife, Vielfalt, Multikulti",
            description = "Kreuzberg ist weltbekannt für seine kreative Szene, internationale Küche, Streetart und lebendige Kiez-Kultur. Hier treffen Tradition, Vielfalt und urbanes Leben aufeinander. Rund um die Bergmannstraße, das Paul-Lincke-Ufer und das Schlesische Tor pulsiert das Berliner Leben Tag und Nacht.",
            tags = listOf("Kreativ", "Vielfältig", "Szene", "Kulinarik"),
            lat = 52.4986,
            lng = 13.4069,
            highlights = listOf("Görlitzer Park", "Streetart Oberbaumbrücke", "Landwehrkanal", "Markthalle Neun", "Bergmannkiez"),
            imageUrl = "https://images.unsplash.com/photo-1587330979470-3595ac045ab0?w=800&auto=format&fit=crop&q=80", // Oberbaumbrücke Kreuzberg
            impressionImages = listOf(
                "https://images.unsplash.com/photo-1587330979470-3595ac045ab0?w=600&auto=format&fit=crop&q=80",
                "https://images.unsplash.com/photo-1513635269975-59663e0ac1ad?w=600&auto=format&fit=crop&q=80",
                "https://images.unsplash.com/photo-1509198397868-475647b2a1e5?w=600&auto=format&fit=crop&q=80",
                "https://images.unsplash.com/photo-1569336415962-a4bd9f69cd83?w=600&auto=format&fit=crop&q=80"
            ),
            rentIndex = "15,80 €/m²",
            vibe = "Alternativ & Bunt"
        ),
        Kiez(
            id = "neukoelln",
            name = "Neukölln",
            subtitle = "Bunt, Lebendig, Anders",
            postalCodes = "12043, 12045, 12047, 12049, 12051",
            population = "ca. 168.000",
            specialty = "Reuterkiez, Weserstraße, Maybachufer",
            description = "Neukölln hat sich vom traditionellen Arbeiterviertel zu einem der dynamischsten Hotspots Europas entwickelt. Cafés am Maybachufer, Künstlerateliers und kulinarische Entdeckungen aus aller Welt prägen das pulsierende Straßenbild.",
            tags = listOf("Bunt", "Urban", "Bars", "Weserstraße"),
            lat = 52.4812,
            lng = 13.4353,
            highlights = listOf("Tempelhofer Feld", "Maybachufer Markt", "Richardplatz", "Weserstraße Bars"),
            imageUrl = "https://images.unsplash.com/photo-1528728329032-2972f65dfb3f?w=800&auto=format&fit=crop&q=80", // Berlin Canal Maybachufer
            impressionImages = listOf(
                "https://images.unsplash.com/photo-1528728329032-2972f65dfb3f?w=600&auto=format&fit=crop&q=80",
                "https://images.unsplash.com/photo-1555396273-367ea4eb4db5?w=600&auto=format&fit=crop&q=80"
            ),
            rentIndex = "14,20 €/m²",
            vibe = "Dynamisch & Trendsetzend"
        ),
        Kiez(
            id = "friedrichshain",
            name = "Friedrichshain",
            subtitle = "Jung, Urban, Nachtleben",
            postalCodes = "10243, 10245, 10247, 10249",
            population = "ca. 138.000",
            specialty = "East Side Gallery, Simon-Dach-Straße, RAW",
            description = "Heimat der längsten Open-Air-Galerie der Welt (East Side Gallery) und legendärer Klubkultur rund um das RAW-Gelände. Auf der Simon-Dach-Straße reihen sich Straßencafés, Bars und internationale Restaurants aneinander.",
            tags = listOf("Jung", "Nachtleben", "RAW-Gelände", "East Side"),
            lat = 52.5159,
            lng = 13.4543,
            highlights = listOf("East Side Gallery", "RAW-Gelände", "Boxhagener Platz", "Simon-Dach-Straße"),
            imageUrl = "https://images.unsplash.com/photo-1569336415962-a4bd9f69cd83?w=800&auto=format&fit=crop&q=80", // East Side Gallery
            impressionImages = listOf(
                "https://images.unsplash.com/photo-1569336415962-a4bd9f69cd83?w=600&auto=format&fit=crop&q=80",
                "https://images.unsplash.com/photo-1541872703-74c5e44368f9?w=600&auto=format&fit=crop&q=80"
            ),
            rentIndex = "17,10 €/m²",
            vibe = "Clubkultur & Streetlife"
        ),
        Kiez(
            id = "prenzlauerberg",
            name = "Prenzlauer Berg",
            subtitle = "Familienfreundlich, Historisch",
            postalCodes = "10405, 10407, 10435, 10437, 10439",
            population = "ca. 165.000",
            specialty = "Kollwitzplatz, Mauerpark, Gründerzeit",
            description = "Prächtige Altbauten aus der Gründerzeit, idyllische Plätze wie der Kollwitzplatz und der berühmte Flohmarkt im Mauerpark machen diesen Kiez zu einem der lebenswertesten und charmantesten Viertel Berlins.",
            tags = listOf("Kollwitzplatz", "Mauerpark", "Café-Kultur", "Altbau"),
            lat = 52.5404,
            lng = 13.4150,
            highlights = listOf("Mauerpark & Flohmarkt", "Kulturbrauerei", "Kollwitzplatz", "Wasserturm"),
            imageUrl = "https://images.unsplash.com/photo-1572953109213-3be62398eb95?w=800&auto=format&fit=crop&q=80", // Prenzlauer Berg Street
            impressionImages = listOf(
                "https://images.unsplash.com/photo-1572953109213-3be62398eb95?w=600&auto=format&fit=crop&q=80",
                "https://images.unsplash.com/photo-1501339847302-ac426a4a7cbb?w=600&auto=format&fit=crop&q=80"
            ),
            rentIndex = "18,90 €/m²",
            vibe = "Entspannt & Ästhetisch"
        ),
        Kiez(
            id = "charlottenburg",
            name = "Charlottenburg",
            subtitle = "Elegant, Grün, Shopping",
            postalCodes = "10585, 10587, 10589, 10623, 10625",
            population = "ca. 130.000",
            specialty = "Kurfürstendamm, Schloss Charlottenburg, Theater",
            description = "Klassische Berliner Eleganz trifft Moderne: Der berühmte Ku'damm lädt zum Shoppen ein, während das barocke Schloss Charlottenburg und der Savignyplatz mit ihren Literatencafés pure Kultur verströmen.",
            tags = listOf("Ku'damm", "Savignyplatz", "Schloss", "Klassisch"),
            lat = 52.5167,
            lng = 13.3039,
            highlights = listOf("Schloss Charlottenburg", "Kurfürstendamm", "Savignyplatz", "Deutsche Oper"),
            imageUrl = "https://images.unsplash.com/photo-1599946347371-68eb71b16afc?w=800&auto=format&fit=crop&q=80", // Schloss Charlottenburg
            impressionImages = listOf(
                "https://images.unsplash.com/photo-1599946347371-68eb71b16afc?w=600&auto=format&fit=crop&q=80"
            ),
            rentIndex = "19,20 €/m²",
            vibe = "Vornehm & Kulturreich"
        ),
        Kiez(
            id = "schoeneberg",
            name = "Schöneberg",
            subtitle = "Tolerant, Vielfältig, Gemütlich",
            postalCodes = "10777, 10779, 10781, 10783",
            population = "ca. 122.000",
            specialty = "Winterfeldtplatz, Akazienkiez, Regenbogenkiez",
            description = "Ein traditionsreicher Bezirk mit Herz: Am Winterfeldtplatz lockt der bekannteste Wochenmarkt der Stadt, der Akazienkiez bezaubert mit Feinkostläden und der Nollendorfplatz steht weltweit für gelebte Freiheit.",
            tags = listOf("Winterfeldtplatz", "Akazienstraße", "Wochenmarkt", "Kiez-Flair"),
            lat = 52.4820,
            lng = 13.3550,
            highlights = listOf("Winterfeldtmarkt", "Gasometer Schöneberg", "Akazienkiez", "Viktoria-Luise-Platz"),
            imageUrl = "https://images.unsplash.com/photo-1555396273-367ea4eb4db5?w=800&auto=format&fit=crop&q=80", // Schöneberg market
            rentIndex = "16,40 €/m²",
            vibe = "Aufgeschlossen & Charismatisch"
        ),
        Kiez(
            id = "wedding",
            name = "Wedding",
            subtitle = "Authentisch, Rau, Im Kommen",
            postalCodes = "13347, 13351, 13353, 13355",
            population = "ca. 87.000",
            specialty = "Sprengelkiez, Humboldthain, Leopoldplatz",
            description = "'Der Wedding kommt!' – dieser Berliner Spruch gilt mehr denn je. Zwischen historischen Industriehöfen, dem Volkspark Humboldthain mit Bunker-Aussicht und dem charmanten Sprengelkiez am Kanal entfaltet sich pure Berliner Ehrlichkeit.",
            tags = listOf("Sprengelkiez", "Humboldthain", "Uferhallen", "Ehrlich"),
            lat = 52.5510,
            lng = 13.3650,
            highlights = listOf("Flakturm Humboldthain", "Sprengelkiez Ufer", "Uferhallen Kultur", "Plötzensee"),
            imageUrl = "https://images.unsplash.com/photo-1513635269975-59663e0ac1ad?w=800&auto=format&fit=crop&q=80", // Wedding street
            rentIndex = "12,90 €/m²",
            vibe = "Echt & Unverstellt"
        ),
        Kiez(
            id = "moabit",
            name = "Moabit",
            subtitle = "Kanal-Insel, Industrie-Erbe, Kiez-Gefühl",
            postalCodes = "10551, 10553, 10555, 10557, 10559",
            population = "ca. 81.000",
            specialty = "Arminiusmarkthalle, Spreebogen, Huttenkiez",
            description = "Von Wasserstraßen komplett umgeben ist Moabit eine echte Insel mitten im Zentrum. Die historische Arminius-Markthalle, verträumte Uferwege und alte Gründerzeitkuben machen den Charme dieses aufstrebenden Kiezes aus.",
            tags = listOf("Markthalle", "Spreeufer", "Kanal", "Huttenkiez"),
            lat = 52.5280,
            lng = 13.3420,
            highlights = listOf("Arminiusmarkthalle", "Kulturfabrik Moabit", "Spreebogenpark", "Carl-von-Ossietzky-Park"),
            imageUrl = "https://images.unsplash.com/photo-1546726747-421c6d69c929?w=800&auto=format&fit=crop&q=80", // Moabit canal
            rentIndex = "14,80 €/m²",
            vibe = "Gemütlich & Inselartig"
        ),
        Kiez(
            id = "tiergarten",
            name = "Tiergarten",
            subtitle = "Grüne Lunge, Botschaften, Kulturforum",
            postalCodes = "10557, 10785",
            population = "ca. 49.000",
            specialty = "Großer Tiergarten, Siegessäule, Philharmonie",
            description = "Die gigantische Parkanlage Großer Tiergarten bildet die grüne Lunge Berlins. Hier thronen die Goldelse (Siegessäule), das Kulturforum mit der Neuen Nationalgalerie und das Regierungsviertel direkt an der Spree.",
            tags = listOf("Siegessäule", "Grünanlage", "Kulturforum", "Regierungsviertel"),
            lat = 52.5140,
            lng = 13.3500,
            highlights = listOf("Siegessäule", "Neue Nationalgalerie", "Café am Neuen See", "Haus der Kulturen der Welt"),
            imageUrl = "https://images.unsplash.com/photo-1509198397868-475647b2a1e5?w=800&auto=format&fit=crop&q=80", // Tiergarten Park & Siegessäule
            rentIndex = "19,50 €/m²",
            vibe = "Monumental & Naturgrün"
        ),
        Kiez(
            id = "wilmersdorf",
            name = "Wilmersdorf",
            subtitle = "Gediegen, Thailändisch, Weinbrunnen",
            postalCodes = "10707, 10709, 10711, 10713, 10715",
            population = "ca. 101.000",
            specialty = "Preußenpark (Thai Park), Rüdesheimer Platz",
            description = "Bürgerliche Gemütlichkeit trifft multikulturelle Köstlichkeiten: Berühmt ist der 'Thai Park' im Preußenpark sowie der idyllische Weinbrunnen am Rüdesheimer Platz unter alten Linden.",
            tags = listOf("Thai Park", "Rüdesheimer Platz", "Bürgerlich", "Weinbrunnen"),
            lat = 52.4870,
            lng = 13.3150,
            highlights = listOf("Thai-Streetfood Preußenpark", "Rüdesheimer Platz", "Fasanenplatz", "Volkspark Wilmersdorf"),
            imageUrl = "https://images.unsplash.com/photo-1541872703-74c5e44368f9?w=800&auto=format&fit=crop&q=80", // Wilmersdorf street
            rentIndex = "17,60 €/m²",
            vibe = "Ruhig & Lebensfroh"
        ),
        Kiez(
            id = "tempelhof",
            name = "Tempelhof",
            subtitle = "Flugfeld-Weite, Freiheit, Familien",
            postalCodes = "12099, 12101, 12103, 12105",
            population = "ca. 65.000",
            specialty = "Tempelhofer Feld, Fliegerviertel, Hafen",
            description = "Das Tempelhofer Feld ist das Symbol für Berlins Freiraum: Ein stillgelegter historischer Flughafen, der den Menschen heute als gigantischer Sport- und Entspannungspark gehört. Kiten, Skaten und Urban Gardening pur.",
            tags = listOf("Flughafenfeld", "Skaten", "Fliegerviertel", "Freiheit"),
            lat = 52.4730,
            lng = 13.3850,
            highlights = listOf("Tempelhofer Feld", "Flughafengebäude Tempelhof", "Tempelhofer Hafen", "Bärensiedlung"),
            imageUrl = "https://images.unsplash.com/photo-1508873696983-2df5293cb32b?w=800&auto=format&fit=crop&q=80", // Tempelhofer Feld
            rentIndex = "13,80 €/m²",
            vibe = "Grenzenlos & Entspannt"
        )
    )

    // Gastronomie with authentic photos matching Screen 6
    val foodSpots = listOf(
        FoodSpot(
            id = "mustafas",
            name = "Mustafa's Gemüse Kebab",
            cuisine = "Imbiss · Türkisch · Streetfood",
            rating = 4.7,
            reviewsCount = 3812,
            kiezName = "Kreuzberg",
            distance = "450 m",
            address = "Mehringdamm 32, 10961 Berlin",
            priceRange = "€",
            imageUrl = "https://images.unsplash.com/photo-1561758033-d89a9ad46330?w=600&auto=format&fit=crop&q=80", // Döner Kebab
            isSponsored = false,
            phone = "+49 30 69532940",
            website = "https://mustafas.de",
            openingHours = "Täglich: 10:00 - 02:00 Uhr",
            popularDishes = listOf("Gemüse Kebab mit Feta & Zitrone", "Dürüm Kebab Spezial", "Falafel im Brot")
        ),
        FoodSpot(
            id = "anna_blume",
            name = "Café Anna Blume",
            cuisine = "Café · Frühstück · Kuchen",
            rating = 4.5,
            reviewsCount = 1204,
            kiezName = "Prenzlauer Berg",
            distance = "1,2 km",
            address = "Kollwitzstraße 83, 10435 Berlin",
            priceRange = "€€",
            imageUrl = "https://images.unsplash.com/photo-1533089860892-a7c6f0a88666?w=600&auto=format&fit=crop&q=80", // Breakfast Etagere
            isSponsored = true,
            phone = "+49 30 44048641",
            website = "https://cafe-anna-blume.de",
            openingHours = "Mo-So: 08:30 - 20:00 Uhr",
            popularDishes = listOf("Große Frühstücksetagere für Zwei", "Schoko-Himbeer-Torte", "Rührei mit Kräutern")
        ),
        FoodSpot(
            id = "markthalle_neun",
            name = "Markthalle Neun",
            cuisine = "Streetfood · International",
            rating = 4.6,
            reviewsCount = 2331,
            kiezName = "Kreuzberg",
            distance = "850 m",
            address = "Eisenbahnstraße 42/43, 10997 Berlin",
            priceRange = "€€",
            imageUrl = "https://images.unsplash.com/photo-1555396273-367ea4eb4db5?w=600&auto=format&fit=crop&q=80", // Streetfood Hall
            isSponsored = false,
            phone = "+49 30 61074714",
            website = "https://markthalleneun.de",
            openingHours = "Di-Sa: 12:00 - 22:00 Uhr",
            popularDishes = listOf("Street Food Thursday Specials", "Artisan Pasta", "Craft Beer Tasting")
        ),
        FoodSpot(
            id = "burgermeister",
            name = "Burgermeister",
            cuisine = "Burger · International · Streetfood",
            rating = 4.4,
            reviewsCount = 2105,
            kiezName = "Friedrichshain",
            distance = "1,5 km",
            address = "Oberbaumstraße 8, 10997 Berlin",
            priceRange = "€",
            imageUrl = "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=600&auto=format&fit=crop&q=80", // Burger
            isSponsored = false,
            phone = "+49 30 23883840",
            website = "https://burger-meister.de",
            openingHours = "Täglich: 11:00 - 03:00 Uhr",
            popularDishes = listOf("Meisterburger mit Bacon & BBQ", "Hausgemachte Pommes", "Chili Cheese Fries")
        ),
        FoodSpot(
            id = "five_elephant",
            name = "Five Elephant",
            cuisine = "Café · Spezialitätenkaffee",
            rating = 4.6,
            reviewsCount = 1902,
            kiezName = "Mitte",
            distance = "2,3 km",
            address = "Alte Schönhauser Str. 14, 10119 Berlin",
            priceRange = "€€",
            imageUrl = "https://images.unsplash.com/photo-1501339847302-ac426a4a7cbb?w=600&auto=format&fit=crop&q=80", // Specialty Coffee & Cheesecake
            isSponsored = false,
            phone = "+49 30 96081527",
            website = "https://fiveelephant.com",
            openingHours = "Mo-Fr: 08:00 - 18:00, Sa-So: 09:00 - 18:00",
            popularDishes = listOf("New York Cheesecake", "Flat White Single Origin", "Handfilter Ethiopia")
        ),
        FoodSpot(
            id = "curry36",
            name = "Curry 36",
            cuisine = "Imbiss · Deutsch · Currywurst",
            rating = 4.3,
            reviewsCount = 3450,
            kiezName = "Kreuzberg",
            distance = "500 m",
            address = "Mehringdamm 36, 10961 Berlin",
            priceRange = "€",
            imageUrl = "https://images.unsplash.com/photo-1627042633145-b780d842ba45?w=600&auto=format&fit=crop&q=80", // Currywurst & Fries
            isSponsored = false,
            phone = "+49 30 2517368",
            website = "https://curry36.de",
            openingHours = "Täglich: 09:00 - 03:00 Uhr",
            popularDishes = listOf("Currywurst mit Darm", "Currywurst ohne Darm", "Pommes Schranke")
        )
    )

    // Events with real photos matching Screen 7
    val events = listOf(
        KiezEvent(
            id = "lange_nacht",
            title = "Lange Nacht der Museen",
            dayLabel = "FR",
            dateNum = "12",
            monthLabel = "SEP",
            time = "18:00 - 02:00 Uhr",
            kiezName = "Mitte",
            price = "ab 15 €",
            isFree = false,
            category = "Kultur",
            description = "Über 75 Berliner Museen öffnen ihre Tore bis tief in die Nacht mit Sonderführungen, Musik und Performances.",
            imageUrl = "https://images.unsplash.com/photo-1518998053901-5348d3961a04?w=600&auto=format&fit=crop&q=80",
            locationAddress = "Museumsinsel Berlin, 10178 Berlin",
            organizer = "Kulturprojekte Berlin GmbH"
        ),
        KiezEvent(
            id = "street_food_fest",
            title = "Street Food Festival",
            dayLabel = "SA",
            dateNum = "13",
            monthLabel = "SEP",
            time = "12:00 - 22:00 Uhr",
            kiezName = "Kreuzberg",
            price = "Kostenlos",
            isFree = true,
            category = "Kulinarik",
            description = "Kulinarische Highlights aus aller Welt: Food-Trucks, Live-DJs und Craft Beer am Landwehrkanal.",
            imageUrl = "https://images.unsplash.com/photo-1555396273-367ea4eb4db5?w=600&auto=format&fit=crop&q=80",
            locationAddress = "Paul-Lincke-Ufer, 10999 Berlin",
            organizer = "Kiez Foodies e.V."
        ),
        KiezEvent(
            id = "flohmarkt_mauerpark",
            title = "Flohmarkt am Mauerpark",
            dayLabel = "SO",
            dateNum = "14",
            monthLabel = "SEP",
            time = "10:00 - 18:00 Uhr",
            kiezName = "Prenzlauer Berg",
            price = "Kostenlos",
            isFree = true,
            category = "Markt & Musik",
            description = "Der legendäre Berliner Sonntagsflohmarkt mit Vintage-Schätzen, Kiez-Gastro und Bearpit-Karaoke.",
            imageUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=600&auto=format&fit=crop&q=80",
            locationAddress = "Bernauer Str. 63-64, 13355 Berlin",
            organizer = "Mauerpark Flohmarkt GbR"
        ),
        KiezEvent(
            id = "open_air_kino",
            title = "Open Air Kino",
            dayLabel = "FR",
            dateNum = "19",
            monthLabel = "SEP",
            time = "19:30 - 23:00 Uhr",
            kiezName = "Friedrichshain",
            price = "ab 8 €",
            isFree = false,
            category = "Kino & Film",
            description = "Großes Kino unter freiem Sternenhimmel im Innenhof des Bethanien mit Originalfassungen und Untertiteln.",
            imageUrl = "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?w=600&auto=format&fit=crop&q=80",
            locationAddress = "Mariannenplatz 2, 10997 Berlin",
            organizer = "Piffl Medien"
        )
    )

    // Local Businesses with real photos matching Screen 8
    val businesses = listOf(
        LocalBusiness(
            id = "kiez_friseur",
            name = "Kiez Friseur",
            category = "Dienstleistungen · Friseur",
            rating = 5.0,
            reviewsCount = 124,
            kiezName = "Kreuzberg",
            distance = "300 m",
            address = "Oranienstraße 185, 10999 Berlin",
            imageUrl = "https://images.unsplash.com/photo-1503951914875-452162b0f3f1?w=600&auto=format&fit=crop&q=80", // Barber salon
            isSponsored = true,
            phone = "+49 30 61284455",
            website = "https://kiezfriseur-berlin.de",
            openingHours = "Mo-Sa: 10:00 - 20:00 Uhr",
            galleryImages = listOf(
                "https://images.unsplash.com/photo-1503951914875-452162b0f3f1?w=600&auto=format&fit=crop&q=80",
                "https://images.unsplash.com/photo-1585747860715-2ba37e788b70?w=600&auto=format&fit=crop&q=80",
                "https://images.unsplash.com/photo-1622286342621-4bd786c2447c?w=600&auto=format&fit=crop&q=80"
            ),
            description = "Traditionelles Handwerk trifft moderne Styles. Spezialisiert auf Herren- und Damenschnitte, Bartpflege und Naturfarben in entspannter Kiez-Atmosphäre."
        ),
        LocalBusiness(
            id = "brotzeit",
            name = "Bäckerei & Café Brotzeit",
            category = "Shopping · Bäckerei",
            rating = 4.8,
            reviewsCount = 231,
            kiezName = "Neukölln",
            distance = "400 m",
            address = "Sonnenallee 54, 12045 Berlin",
            imageUrl = "https://images.unsplash.com/photo-1509440159596-0249088772ff?w=600&auto=format&fit=crop&q=80", // Bakery
            isSponsored = false,
            phone = "+49 30 55432100",
            website = "https://brotzeit-neukoelln.de",
            openingHours = "Di-So: 07:00 - 18:00 Uhr",
            galleryImages = listOf(
                "https://images.unsplash.com/photo-1509440159596-0249088772ff?w=600&auto=format&fit=crop&q=80",
                "https://images.unsplash.com/photo-1555507036-ab1f4038808a?w=600&auto=format&fit=crop&q=80"
            ),
            description = "Täglich frisch gebackenes Sauerteigbrot aus regionalem Bio-Getreide, traditionelle Berliner Schrippen, Zimtschnecken und Specialty Coffee."
        ),
        LocalBusiness(
            id = "kiez_fitness",
            name = "Kiez Fitness",
            category = "Fitness · Fitnessstudio",
            rating = 4.6,
            reviewsCount = 98,
            kiezName = "Kreuzberg",
            distance = "550 m",
            address = "Wrangelstraße 72, 10997 Berlin",
            imageUrl = "https://images.unsplash.com/photo-1534438327276-14e5300c3a48?w=600&auto=format&fit=crop&q=80", // Gym
            isSponsored = false,
            phone = "+49 30 69812300",
            website = "https://kiezfitness-kreuzberg.de",
            openingHours = "Mo-Fr: 06:30 - 23:00, Sa-So: 08:00 - 21:00",
            galleryImages = listOf(
                "https://images.unsplash.com/photo-1534438327276-14e5300c3a48?w=600&auto=format&fit=crop&q=80"
            ),
            description = "Dein familiäres Gym im Kiez. State-of-the-Art Freihanteln, Functional-Area, Sauna und über 30 wöchentliche Kurse von Calisthenics bis Yoga."
        ),
        LocalBusiness(
            id = "kiez_apotheke",
            name = "Kiez Apotheke",
            category = "Gesundheit · Apotheke",
            rating = 4.9,
            reviewsCount = 176,
            kiezName = "Mitte",
            distance = "700 m",
            address = "Torstraße 112, 10119 Berlin",
            imageUrl = "https://images.unsplash.com/photo-1586015555751-63bb77f4322a?w=600&auto=format&fit=crop&q=80", // Pharmacy
            isSponsored = false,
            phone = "+49 30 28091100",
            website = "https://apotheke-torstrasse.de",
            openingHours = "Mo-Fr: 08:30 - 19:30, Sa: 09:00 - 16:00",
            galleryImages = listOf(
                "https://images.unsplash.com/photo-1586015555751-63bb77f4322a?w=600&auto=format&fit=crop&q=80"
            ),
            description = "Kompetente pharmazeutische Beratung, Botendienst direkt zu dir nach Hause im Kiez und ein breites Sortiment an Naturkosmetik."
        ),
        LocalBusiness(
            id = "rechtbox",
            name = "Rechtbox Steuerberatung",
            category = "Dienstleistungen · Steuerberatung",
            rating = 5.0,
            reviewsCount = 52,
            kiezName = "Pankow",
            distance = "1,1 km",
            address = "Prenzlauer Allee 88, 10405 Berlin",
            imageUrl = "https://images.unsplash.com/photo-1497366216548-37526070297c?w=600&auto=format&fit=crop&q=80", // Modern Office
            isSponsored = false,
            phone = "+49 30 44332211",
            website = "https://rechtbox-beratung.de",
            openingHours = "Mo-Fr: 09:00 - 18:00 Uhr",
            galleryImages = listOf(
                "https://images.unsplash.com/photo-1497366216548-37526070297c?w=600&auto=format&fit=crop&q=80"
            ),
            description = "Digitale Steuerberatung und Buchhaltung für Gastronomen, Freiberufler, Kiez-Geschäfte und Startups. Papierlos, schnell und transparent."
        ),
        LocalBusiness(
            id = "wedding_doener",
            name = "Wedding Döner & Grill",
            category = "Imbiss & Döner",
            rating = 4.9,
            reviewsCount = 310,
            kiezName = "Wedding",
            distance = "500 m",
            address = "Müllerstraße 142, 13353 Berlin",
            imageUrl = "https://images.unsplash.com/photo-1561651823-34feb02250e4?w=600&auto=format&fit=crop&q=80",
            isSponsored = true,
            phone = "+49 30 45021234",
            whatsapp = "+4915126509586",
            website = "https://wedding-grill.de",
            openingHours = "Mo-So: 10:00 - 02:00 Uhr",
            description = "Echter Berliner Kiez-Döner mit hausgemachtem Brot, frischen Kräutern und saftigem Kalb- & Hähnchenfleisch."
        ),
        LocalBusiness(
            id = "cafe_pfoertner",
            name = "Café Pförtner",
            category = "Gastronomie · Café & Frühstück",
            rating = 4.8,
            reviewsCount = 185,
            kiezName = "Wedding",
            distance = "750 m",
            address = "Uferstraße 8, 13357 Berlin",
            imageUrl = "https://images.unsplash.com/photo-1554118811-1e0d58224f24?w=600&auto=format&fit=crop&q=80",
            phone = "+49 30 45678912",
            whatsapp = "+4915126509586",
            website = "https://cafe-pfoertner.de",
            openingHours = "Mo-Sa: 09:00 - 18:00 Uhr",
            description = "Kult-Café in den Uferhallen mit italienischer Pasta, Spezialitätenkaffee und hausgemachten Kuchen in historischem Bus-Ambiente."
        ),
        LocalBusiness(
            id = "silberfisch_fshain",
            name = "Silberfisch Kiez-Shop",
            category = "Shopping · Geschenke & Mode",
            rating = 4.7,
            reviewsCount = 92,
            kiezName = "Friedrichshain",
            distance = "350 m",
            address = "Grünberger Str. 34, 10245 Berlin",
            imageUrl = "https://images.unsplash.com/photo-1472851294608-062f824d29cc?w=600&auto=format&fit=crop&q=80",
            phone = "+49 30 29364400",
            whatsapp = "+4915126509586",
            website = "https://silberfisch-berlin.de",
            openingHours = "Mo-Sa: 11:00 - 19:30 Uhr",
            description = "Liebevoll kuratierte Kiez-Boutique mit Berliner Design, Schmuck, Wohnaccessoires und Postkarten."
        ),
        LocalBusiness(
            id = "simondach_barber",
            name = "Simon-Dach Barber & Friseur",
            category = "Handwerk · Friseur & Barber",
            rating = 4.9,
            reviewsCount = 143,
            kiezName = "Friedrichshain",
            distance = "450 m",
            address = "Simon-Dach-Straße 22, 10245 Berlin",
            imageUrl = "https://images.unsplash.com/photo-1585747860715-2ba37e788b70?w=600&auto=format&fit=crop&q=80",
            phone = "+49 30 29778899",
            whatsapp = "+4915126509586",
            website = "https://simondach-barber.de",
            openingHours = "Mo-Sa: 10:00 - 20:00 Uhr",
            description = "Klassische Bartpflege, Nassrasur mit heißem Tuch und moderne Haarschnitte direkt am Boxhagener Kiez."
        ),
        LocalBusiness(
            id = "kant_konditorei",
            name = "Kant Konditorei & Bäckerei",
            category = "Shopping · Bäckerei & Konditorei",
            rating = 4.9,
            reviewsCount = 210,
            kiezName = "Charlottenburg",
            distance = "600 m",
            address = "Kantstraße 64, 10627 Berlin",
            imageUrl = "https://images.unsplash.com/photo-1555507036-ab1f4038808a?w=600&auto=format&fit=crop&q=80",
            phone = "+49 30 3241100",
            whatsapp = "+4915126509586",
            website = "https://kant-konditorei.de",
            openingHours = "Di-So: 07:30 - 18:30 Uhr",
            description = "Traditionsreiche Berliner Konditorei mit feinsten Torten, Petit Fours, Croissants und Kaffee-Spezialitäten."
        ),
        LocalBusiness(
            id = "schwarzes_cafe",
            name = "Schwarzes Café",
            category = "Gastronomie · Café & Restaurant",
            rating = 4.6,
            reviewsCount = 420,
            kiezName = "Charlottenburg",
            distance = "850 m",
            address = "Kantstraße 148, 10623 Berlin",
            imageUrl = "https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?w=600&auto=format&fit=crop&q=80",
            phone = "+49 30 3138038",
            whatsapp = "+4915126509586",
            website = "https://schwarzescafe-berlin.de",
            openingHours = "Rund um die Uhr (24/7)",
            description = "Legendäre Berliner Institution seit den 1970ern. Frühstück zu jeder Tages- und Nachtzeit und internationale Küche."
        ),
        LocalBusiness(
            id = "winterfeldt_markt",
            name = "Winterfeldt Bäckerei & Feinkost",
            category = "Shopping · Bäckerei & Markt",
            rating = 4.8,
            reviewsCount = 160,
            kiezName = "Schöneberg",
            distance = "400 m",
            address = "Winterfeldtstraße 45, 10781 Berlin",
            imageUrl = "https://images.unsplash.com/photo-1509440159596-0249088772ff?w=600&auto=format&fit=crop&q=80",
            phone = "+49 30 2168900",
            whatsapp = "+4915126509586",
            website = "https://winterfeldt-kiez.de",
            openingHours = "Mo-Sa: 08:00 - 19:00 Uhr",
            description = "Frisches Bio-Brot, Kiez-Käse, Oliven und Spezialitäten direkt am beliebten Winterfeldtplatz."
        ),
        LocalBusiness(
            id = "arminius_markthalle",
            name = "Arminius Markthalle Kiez-Restaurant",
            category = "Gastronomie · Restaurant & Markt",
            rating = 4.8,
            reviewsCount = 380,
            kiezName = "Moabit",
            distance = "500 m",
            address = "Arminiusstraße 2, 10551 Berlin",
            imageUrl = "https://images.unsplash.com/photo-1555396273-367ea4eb4db5?w=600&auto=format&fit=crop&q=80",
            phone = "+49 30 39876543",
            whatsapp = "+4915126509586",
            website = "https://arminiusmarkthalle.com",
            openingHours = "Mo-Sa: 10:00 - 22:00 Uhr",
            description = "Historische Markthalle Moabit mit vielfältigen Essensständen, Craft Beer und geselligem Kiez-Flair."
        ),
        LocalBusiness(
            id = "anna_blume_kiez",
            name = "Café Anna Blume",
            category = "Gastronomie · Café & Frühstück",
            rating = 4.9,
            reviewsCount = 520,
            kiezName = "Prenzlauer Berg",
            distance = "300 m",
            address = "Kollwitzstraße 83, 10435 Berlin",
            imageUrl = "https://images.unsplash.com/photo-1525610553991-2bede1a236e2?w=600&auto=format&fit=crop&q=80",
            isSponsored = true,
            hasCoupon = true,
            couponText = "%20 İndirim (Frühstückset)",
            phone = "+49 30 44048641",
            whatsapp = "+4915126509586",
            website = "https://cafe-annablume.de",
            openingHours = "Mo-So: 08:30 - 20:00 Uhr",
            description = "Berühmtes Café und Blumengeschäft mit dreistöckigen Frühstücks-Etagèren, Bio-Rührei und eigener Tortenmanufaktur."
        ),
        LocalBusiness(
            id = "mustafas_kiez",
            name = "Mustafa's Gemüse Kebap",
            category = "Imbiss & Döner",
            rating = 4.8,
            reviewsCount = 890,
            kiezName = "Kreuzberg",
            distance = "250 m",
            address = "Mehringdamm 32, 10961 Berlin",
            imageUrl = "https://images.unsplash.com/photo-1561651823-34feb02250e4?w=600&auto=format&fit=crop&q=80",
            phone = "+49 30 69532100",
            whatsapp = "+4915126509586",
            website = "https://mustafas.de",
            openingHours = "Mo-So: 10:00 - 02:00 Uhr",
            description = "Der weltbekannte Berliner Gemüse-Döner mit gebratenem Gemüse, frischem Schafskäse und Zitronenspritzer."
        ),
        LocalBusiness(
            id = "azzam_kiez",
            name = "Azzam Hummus & Falafel",
            category = "Gastronomie · Restaurant",
            rating = 4.8,
            reviewsCount = 450,
            kiezName = "Neukölln",
            distance = "350 m",
            address = "Sonnenallee 54, 12045 Berlin",
            imageUrl = "https://images.unsplash.com/photo-1540914124281-342587941389?w=600&auto=format&fit=crop&q=80",
            phone = "+49 30 62725678",
            whatsapp = "+4915126509586",
            website = "https://azzam-berlin.de",
            openingHours = "Mo-So: 09:00 - 23:00 Uhr",
            description = "Legendäre orientalische Küche auf der Sonnenallee: Hausgemachter cremiger Hummus, warme Fatteh und knusprige Falafel."
        ),
        LocalBusiness(
            id = "tempelhof_sport",
            name = "Tempelhofer Rad & Sport",
            category = "Fitness · Sport & Verleih",
            rating = 4.7,
            reviewsCount = 88,
            kiezName = "Tempelhof",
            distance = "600 m",
            address = "Tempelhofer Damm 90, 12101 Berlin",
            imageUrl = "https://images.unsplash.com/photo-1534438327276-14e5300c3a48?w=600&auto=format&fit=crop&q=80",
            phone = "+49 30 78912300",
            whatsapp = "+4915126509586",
            website = "https://tempelhof-sport.de",
            openingHours = "Mo-Sa: 10:00 - 19:00 Uhr",
            description = "Fahrradreparatur, Verleih für das Tempelhofer Feld, Inline-Skates und Outdoor-Equipment."
        ),
        LocalBusiness(
            id = "schloss_apotheke",
            name = "Schloss Apotheke Steglitz",
            category = "Gesundheit · Apotheke",
            rating = 4.8,
            reviewsCount = 130,
            kiezName = "Steglitz",
            distance = "700 m",
            address = "Schloßstraße 42, 12165 Berlin",
            imageUrl = "https://images.unsplash.com/photo-1586015555751-63bb77f4322a?w=600&auto=format&fit=crop&q=80",
            phone = "+49 30 7914422",
            whatsapp = "+4915126509586",
            website = "https://schloss-apotheke-steglitz.de",
            openingHours = "Mo-Fr: 08:30 - 19:30, Sa: 09:00 - 16:00",
            description = "Persönliche Beratung, Kiez-Botendienst und große Auswahl an Naturheilmitteln im Herzen von Steglitz."
        )
    )

    // Deals with real photos matching Screen 9
    val deals = listOf(
        Deal(
            id = "deal_anna_blume",
            businessName = "Café Anna Blume",
            discountBadge = "20 %",
            title = "20 % auf alle Frühstückssets",
            validUntil = "Gültig bis 30.09.2026",
            category = "Essen & Trinken",
            code = "KIEZ20BLUME",
            imageUrl = "https://images.unsplash.com/photo-1533089860892-a7c6f0a88666?w=600&auto=format&fit=crop&q=80"
        ),
        Deal(
            id = "deal_la_piazza",
            businessName = "La Piazza",
            discountBadge = "2 für 1",
            title = "2 für 1 Steinofenpizza nach Wahl",
            validUntil = "Gültig bis 15.10.2026",
            category = "Essen & Trinken",
            code = "PIAZZA2FOR1",
            imageUrl = "https://images.unsplash.com/photo-1513104890138-7c749659a591?w=600&auto=format&fit=crop&q=80"
        ),
        Deal(
            id = "deal_kiez_friseur",
            businessName = "Kiez Friseur",
            discountBadge = "15 %",
            title = "15 % auf alle Haarschnitte",
            validUntil = "Gültig bis 31.10.2026",
            category = "Dienstleistungen",
            code = "KIEZSTYLE15",
            imageUrl = "https://images.unsplash.com/photo-1503951914875-452162b0f3f1?w=600&auto=format&fit=crop&q=80"
        ),
        Deal(
            id = "deal_kiez_fitness",
            businessName = "Kiez Fitness",
            discountBadge = "10 %",
            title = "10 % auf Monatsmitgliedschaft",
            validUntil = "Gültig bis 31.12.2026",
            category = "Freizeit & Fitness",
            code = "FITKIEZ10",
            imageUrl = "https://images.unsplash.com/photo-1534438327276-14e5300c3a48?w=600&auto=format&fit=crop&q=80"
        )
    )

    // Quiz Questions with real photos matching Screen 5
    val quizQuestions = listOf(
        QuizQuestion(
            id = 1,
            question = "In welchem Bezirk steht das Brandenburger Tor?",
            imageUrl = "https://images.unsplash.com/photo-1560969184-10fe8719e047?w=800&auto=format&fit=crop&q=80",
            options = listOf("Mitte", "Friedrichshain", "Charlottenburg", "Neukölln"),
            correctIndex = 0,
            explanation = "Richtig! 🎉 Das Brandenburger Tor steht im Bezirk Mitte und ist eines der bekanntesten Wahrzeichen Berlins."
        ),
        QuizQuestion(
            id = 2,
            question = "Welches Tier ist das traditionelle Wappentier von Berlin?",
            imageUrl = "https://images.unsplash.com/photo-1589656966895-2f33e7653819?w=800&auto=format&fit=crop&q=80",
            options = listOf("Der Berliner Adler", "Der Berliner Bär", "Der Berliner Löwe", "Der Berliner Fuchs"),
            correctIndex = 1,
            explanation = "Richtig! 🐻 Der Berliner Bär schmückt seit dem 13. Jahrhundert das Stadtwappen und begegnet einem überall in Berlin."
        ),
        QuizQuestion(
            id = 3,
            question = "Wie hoch ist der Berliner Fernsehturm am Alexanderplatz?",
            imageUrl = "https://images.unsplash.com/photo-1546726747-421c6d69c929?w=800&auto=format&fit=crop&q=80",
            options = listOf("288 Meter", "325 Meter", "368 Meter", "412 Meter"),
            correctIndex = 2,
            explanation = "Richtig! 🗼 Mit genau 368 Metern ist der Fernsehturm das höchste freistehende Bauwerk Deutschlands."
        ),
        QuizQuestion(
            id = 4,
            question = "Was bedeutet das typische Berliner Wort 'Schrippe'?",
            imageUrl = "https://images.unsplash.com/photo-1509440159596-0249088772ff?w=800&auto=format&fit=crop&q=80",
            options = listOf("Ein Brötchen", "Eine U-Bahn-Fahrkarte", "Ein alter Mantel", "Ein Regenschirm"),
            correctIndex = 0,
            explanation = "Genau! 🥖 In Berlin bestellt man beim Bäcker niemals Brötchen oder Semmeln, sondern immer echte 'Schrippen'."
        ),
        QuizQuestion(
            id = 5,
            question = "In welchem Jahr fiel die Berliner Mauer?",
            imageUrl = "https://images.unsplash.com/photo-1569336415962-a4bd9f69cd83?w=800&auto=format&fit=crop&q=80",
            options = listOf("1987", "1989", "1991", "1993"),
            correctIndex = 1,
            explanation = "Richtig! 🧱 Am 9. November 1989 öffnete sich die Berliner Mauer und markierte das Ende der Teilung Europas."
        )
    )

    // Kiez Wissen Articles with real photos matching Screen 10
    val articles = listOf(
        KiezArticle(
            id = "geschichte_berlins",
            title = "Die Geschichte Berlins",
            subtitle = "Von der Doppelstadt zur Hauptstadt",
            category = "Geschichte",
            readTimeMinutes = 5,
            imageUrl = "https://images.unsplash.com/photo-1560969184-10fe8719e047?w=600&auto=format&fit=crop&q=80",
            fullContent = "Berlin entstand im 13. Jahrhundert als Doppelstadt Berlin und Cölln an den Ufern der Spree. Über die Jahrhunderte entwickelte sich die Stadt von einer kurfürstlichen und preußischen Residenz zur pulsierenden Kulturmetropole der Goldenen Zwanziger Jahre. Nach den Zerstörungen des Zweiten Weltkriegs und der 28 Jahre währenden Teilung im Kalten Krieg feierte Berlin 1989 die friedliche Wiedervereinigung. Heute ist die deutsche Hauptstadt ein globales Zentrum für Kreativität, Hochtechnologie, Wissenschaft und gelebte Toleranz."
        ),
        KiezArticle(
            id = "berliner_mauer",
            title = "Berliner Mauer",
            subtitle = "Entstehung, Verlauf und Heute",
            category = "Geschichte",
            readTimeMinutes = 6,
            imageUrl = "https://images.unsplash.com/photo-1569336415962-a4bd9f69cd83?w=600&auto=format&fit=crop&q=80",
            fullContent = "Vom 13. August 1961 bis zum 9. November 1989 trennte die Berliner Mauer West-Berlin von Ost-Berlin und der DDR auf einer Länge von über 155 Kilometern. Die Grenzanlagen mit Todesstreifen, Wachtürmen und Selbstschussanlagen kosteten mindestens 140 Menschen das Leben. Der historische Mauerfall im November 1989 leitete das Ende des Kalten Krieges ein. Heute mahnen die Gedenkstätte Bernauer Straße und die 1,3 Kilometer lange East Side Gallery als weltweit längste Open-Air-Galerie an diese Epoche."
        ),
        KiezArticle(
            id = "kiez_kultur",
            title = "Die Berliner Kieze",
            subtitle = "Vielfalt und Besonderheiten",
            category = "Kultur",
            readTimeMinutes = 4,
            imageUrl = "https://images.unsplash.com/photo-1572953109213-3be62398eb95?w=600&auto=format&fit=crop&q=80",
            fullContent = "Ein 'Kiez' ist in Berlin weit mehr als ein einfacher Stadtteil. Es ist das Mikrokosmos-Gefühl der eigenen Nachbarschaft: der Bäcker an der Ecke, der Späti um Mitternacht, der Wochenmarkt und die Nachbarn, die sich seit Jahrzehnten grüßen. Jeder Kiez – ob Bergmannkiez, Kollwitzplatz oder Reuterkiez – besitzt seine ganz eigene Identität."
        ),
        KiezArticle(
            id = "sehenswuerdigkeiten",
            title = "Sehenswürdigkeiten",
            subtitle = "Das darf man nicht verpassen",
            category = "Sehenswürdigkeiten",
            readTimeMinutes = 5,
            imageUrl = "https://images.unsplash.com/photo-1599946347371-68eb71b16afc?w=600&auto=format&fit=crop&q=80",
            fullContent = "Vom Brandenburger Tor über die Museumsinsel bis zum Schloss Charlottenburg und der Siegessäule im Tiergarten: Berlin bietet unzählige weltberühmte Monumente und versteckte architektonische Juwele, die man unbedingt gesehen haben muss."
        ),
        KiezArticle(
            id = "berliner_dialekt",
            title = "Berliner Dialekt",
            subtitle = "Typische Wörter und Ausdrücke",
            category = "Sprache",
            readTimeMinutes = 3,
            imageUrl = "https://images.unsplash.com/photo-1516321318423-f06f85e504b3?w=600&auto=format&fit=crop&q=80",
            fullContent = "Der Berliner Dialekt ('Berliner Schnauze') ist direkt, herzlich und schlagfertig. 'Icke' heißt Ich, 'Knorke' bedeutet großartig, eine 'Stulle' ist ein belegtes Butterbrot und 'Jwd' steht für 'janz weit draußen'."
        )
    )

    // B2B Pricing Packages matching the user's explicit decision:
    // Basic (19,90 €), Plus (49,90 €), Premium (99,90 €)
    val pricingPackages = listOf(
        PricingPackage(
            id = "basic",
            name = "Kiez Basic",
            priceMonthly = "19,90 €",
            priceYearlyMonthly = "16,50 €",
            isPopular = false,
            isMaxVisibility = false,
            features = listOf(
                "Unternehmensprofil mit allen wichtigen Infos",
                "Adresse, Telefon, Website & Öffnungszeiten",
                "Bilder (bis zu 5)",
                "Eintrag in der Kiez-Suche",
                "Ihr Standort auf der Kiez-Karte",
                "Kundenbewertungen",
                "Social-Media-Verlinkung"
            )
        ),
        PricingPackage(
            id = "plus",
            name = "Kiez Plus",
            priceMonthly = "49,90 €",
            priceYearlyMonthly = "41,50 €",
            isPopular = true,
            isMaxVisibility = false,
            features = listOf(
                "Alle Leistungen aus Kiez Basic",
                "Hervorgehobener Eintrag in Listen",
                "Platzierung als Anzeige in passenden Kategorien",
                "Angebote & Deals einstellen (bis zu 4 pro Monat)",
                "Event-Veranstaltungen eintragen",
                "Mehr Bilder & Galerie",
                "Erweiterte Unternehmensbeschreibung",
                "Statistiken zu Aufrufen und Klicks"
            )
        ),
        PricingPackage(
            id = "premium",
            name = "Kiez Premium",
            priceMonthly = "99,90 €",
            priceYearlyMonthly = "82,50 €",
            isPopular = false,
            isMaxVisibility = true,
            features = listOf(
                "Alle Leistungen aus Kiez Plus",
                "Top-Platzierung: 'Top Unternehmen im Kiez'",
                "Startseiten-Präsenz (wechselnd)",
                "Gesponserter Pin auf der Kiez-Karte (auffällig)",
                "Mehr Angebote & Deals (bis zu 8 pro Monat)",
                "Event-Highlights",
                "Bevorzugte Platzierung in Suchergebnissen",
                "Detaillierte Statistiken & monatlicher Bericht",
                "Persönlicher Support"
            )
        )
    )

    val addOns = listOf(
        AddOnOption(
            id = "deal_addon",
            title = "Kiez Deal",
            pricePerWeek = "14,90 € / Woche",
            priceNum = 14.90,
            description = "Ihr Angebot prominent in der Deals-Übersicht"
        ),
        AddOnOption(
            id = "event_addon",
            title = "Event hervorheben",
            pricePerWeek = "19,90 € / Woche",
            priceNum = 19.90,
            description = "Ihre Veranstaltung ganz oben in den Events"
        ),
        AddOnOption(
            id = "top_addon",
            title = "Top-Platzierung im Kiez",
            pricePerWeek = "29,90 € / Woche",
            priceNum = 29.90,
            description = "Ihr Unternehmen an erster Stelle in der Kategorie"
        ),
        AddOnOption(
            id = "banner_addon",
            title = "Startseiten-Banner",
            pricePerWeek = "49,90 € / Woche",
            priceNum = 49.90,
            description = "Exklusive Präsentation auf der Startseite"
        )
    )

    // Complete 22 Berlin Districts from worker.js
    val allKieze = listOf(
        KiezDistrict("neukoelln", "Neukölln", 52.4811, 13.4350),
        KiezDistrict("kreuzberg", "Kreuzberg", 52.4986, 13.4030),
        KiezDistrict("wedding", "Wedding", 52.5496, 13.3526),
        KiezDistrict("gesundbrunnen", "Gesundbrunnen", 52.5507, 13.3905),
        KiezDistrict("moabit", "Moabit", 52.5302, 13.3420),
        KiezDistrict("tiergarten", "Tiergarten", 52.5145, 13.3501),
        KiezDistrict("mitte", "Mitte", 52.5200, 13.4050),
        KiezDistrict("friedrichshain", "Friedrichshain", 52.5156, 13.4541),
        KiezDistrict("prenzlauer-berg", "Prenzlauer Berg", 52.5388, 13.4244),
        KiezDistrict("schoeneberg", "Schöneberg", 52.4830, 13.3530),
        KiezDistrict("tempelhof", "Tempelhof", 52.4664, 13.3850),
        KiezDistrict("charlottenburg", "Charlottenburg", 52.5163, 13.3040),
        KiezDistrict("spandau", "Spandau", 52.5354, 13.1990),
        KiezDistrict("reinickendorf", "Reinickendorf", 52.5870, 13.3290),
        KiezDistrict("steglitz", "Steglitz", 52.4560, 13.3220),
        KiezDistrict("lichtenberg", "Lichtenberg", 52.5130, 13.4990),
        KiezDistrict("pankow", "Pankow", 52.5690, 13.4020),
        KiezDistrict("treptow", "Treptow", 52.4900, 13.4700),
        KiezDistrict("britz", "Britz", 52.4430, 13.4330),
        KiezDistrict("koepenick", "Köpenick", 52.4450, 13.5760),
        KiezDistrict("marzahn", "Marzahn", 52.5450, 13.5640),
        KiezDistrict("umgebung", "Berlin & Umgebung", 52.5200, 13.4050)
    )

    // 12 Categories from worker.js with German and Turkish labels
    val categoriesMap = mapOf(
        "fruehstueck" to Pair("Frühstück", "Kahvaltı"),
        "cafe" to Pair("Café", "Kafe"),
        "restaurant" to Pair("Restaurant", "Restoran"),
        "imbiss" to Pair("Imbiss & Döner", "Büfe & Döner"),
        "baeckerei" to Pair("Bäckerei", "Fırın & Pastane"),
        "markt" to Pair("Supermarkt & Markt", "Market"),
        "friseur" to Pair("Friseur & Barber", "Kuaför & Berber"),
        "beauty" to Pair("Kosmetik & Nails", "Güzellik"),
        "service" to Pair("Dienstleistung", "Hizmet"),
        "shop" to Pair("Einkaufen", "Alışveriş"),
        "handel" to Pair("Einzel- & Großhandel", "Perakende & Toptan"),
        "sonstiges" to Pair("Sonstiges", "Diğer")
    )

    // The 7 Real Sponsors from seed-sponsoren.sql
    val sponsors = listOf(
        SponsorBanner(
            id = "deindigitalerhelfer",
            name = "Dein Digitaler Helfer",
            drawableRes = com.example.R.drawable.sponsor_deindigitalerhelfer,
            headlineDe = "Behördenbriefe verstehen, Anträge vorbereiten",
            headlineTr = "Resmi yazıları anla, başvuruları hazırla",
            headlineEn = "Understand official letters, prepare applications",
            ctaDe = "Kostenlos starten",
            ctaTr = "Ücretsiz başla",
            ctaEn = "Start for free",
            linkWeb = "https://deindigitalerhelfer.com",
            linkWa = "",
            linkTel = "",
            category = "service",
            priority = 5
        ),
        SponsorBanner(
            id = "ersenbox",
            name = "ErsenBox",
            drawableRes = com.example.R.drawable.sponsor_ersenbox,
            headlineDe = "Dokumente verstehen, Risiken erkennen",
            headlineTr = "Belgeleri anla, riskleri gör",
            headlineEn = "Understand documents, spot risks",
            ctaDe = "Mehr erfahren",
            ctaTr = "Keşfet",
            ctaEn = "Learn more",
            linkWeb = "https://ersenbox.com",
            linkWa = "",
            linkTel = "",
            category = "service",
            priority = 4
        ),
        SponsorBanner(
            id = "deinfinanzenhelfer",
            name = "Dein Finanzen Helfer",
            drawableRes = com.example.R.drawable.sponsor_deinfinanzenhelfer,
            headlineDe = "Finanzamt-Brief verstehen, Steuererklärung online",
            headlineTr = "Finanzamt mektubunu anla, vergi beyannamesi online",
            headlineEn = "Understand tax office letters, file online",
            ctaDe = "Anfrage starten",
            ctaTr = "Başvur",
            ctaEn = "Get started",
            linkWeb = "https://deinfinanzenhelfer.com",
            linkWa = "",
            linkTel = "",
            category = "service",
            priority = 3
        ),
        SponsorBanner(
            id = "it-rechtbox",
            name = "IT-Rechtbox",
            drawableRes = com.example.R.drawable.sponsor_it_rechtbox,
            headlineDe = "Website prüfen: Impressum, Datenschutz, Cookies",
            headlineTr = "Web siteni kontrol et: Impressum, Datenschutz, çerezler",
            headlineEn = "Check your website: imprint, privacy, cookies",
            ctaDe = "Website prüfen",
            ctaTr = "Siteyi kontrol et",
            ctaEn = "Check website",
            linkWeb = "https://it-rechtbox.pages.dev",
            linkWa = "",
            linkTel = "",
            category = "service",
            priority = 4
        ),
        SponsorBanner(
            id = "tek-getraenke",
            name = "Terra Verde TEK Getränke",
            drawableRes = com.example.R.drawable.sponsor_tek_getraenke,
            headlineDe = "Getränke, Lebensmittel & Verpackung im Großhandel",
            headlineTr = "İçecek, gıda ve ambalajda toptan satış",
            headlineEn = "Wholesale drinks, food & packaging in Berlin",
            ctaDe = "Sortiment ansehen",
            ctaTr = "Ürünleri gör",
            ctaEn = "See products",
            linkWeb = "https://www.tekgetraenke.de",
            linkWa = "",
            linkTel = "",
            category = "handel",
            priority = 6
        ),
        SponsorBanner(
            id = "the-visit",
            name = "The Visit Coffee Roastery",
            drawableRes = com.example.R.drawable.sponsor_the_visit,
            headlineDe = "Kaffeerösterei & Specialty Coffee in Kreuzberg",
            headlineTr = "Kreuzberg'de taze kahve kavurma evi & kafe",
            headlineEn = "Specialty coffee roastery in Kreuzberg",
            ctaDe = "Website ansehen",
            ctaTr = "Siteye git",
            ctaEn = "Visit website",
            linkWeb = "https://thevisitcoffeeroastery.pages.dev/",
            linkWa = "",
            linkTel = "",
            category = "cafe",
            priority = 6
        ),
        SponsorBanner(
            id = "berlin-handel",
            name = "Berlin Handel",
            drawableRes = com.example.R.drawable.sponsor_berlin_handel,
            headlineDe = "Getränke-Lieferservice für Berlin & Umgebung per WhatsApp",
            headlineTr = "Berlin ve çevresine içecek teslimatı – WhatsApp ile sipariş",
            headlineEn = "Beverage delivery service in Berlin via WhatsApp",
            ctaDe = "WhatsApp bestellen",
            ctaTr = "WhatsApp ile sipariş",
            ctaEn = "Order on WhatsApp",
            linkWeb = "",
            linkWa = "https://wa.me/4915126509586",
            linkTel = "+4915126509586",
            category = "handel",
            priority = 6
        )
    )

    // Kiez-Stories (Instagram/WhatsApp style 24h stories)
    val stories = listOf(
        KiezStory(
            id = "story_1",
            headline = "Straßensperrung am Kottbusser Damm wegen Bauarbeiten",
            subtext = "U8 Schienenersatzverkehr zwischen Hermannplatz und Moritzplatz am Wochenende.",
            kind = "traffic",
            headlineColor = "gelb",
            kiez = "Kreuzberg",
            author = "BVG & Kiez-Team",
            timeAgo = "vor 25 Min.",
            source = "BVG Berlin",
            link = "https://www.bvg.de",
            whatsapp = "+4915126509586"
        ),
        KiezStory(
            id = "story_2",
            headline = "Neuer Bio-Markt eröffnet in der Weserstraße!",
            subtext = "Regionale Produkte, frisches Obst & Gemüse und unverpackte Lebensmittel.",
            kind = "news",
            headlineColor = "blau",
            kiez = "Neukölln",
            author = "Ali (Kiez-Reporter)",
            timeAgo = "vor 1 Std.",
            source = "Weserstraße Kiez",
            link = "https://www.berlin.de",
            whatsapp = "+4915126509586"
        ),
        KiezStory(
            id = "story_3",
            headline = "Mauerpark Flohmarkt: Großes Open-Air Karaoke ab 15 Uhr",
            subtext = "Tolles Wetter erwartet! Kommt vorbei und bringt Freunde mit.",
            kind = "event",
            headlineColor = "rot",
            kiez = "Prenzlauer Berg",
            author = "Mauerpark Freunde",
            timeAgo = "vor 3 Std.",
            source = "Mauerpark Berlin",
            link = "https://www.mauerpark.info",
            whatsapp = "+4915126509586"
        ),
        KiezStory(
            id = "story_4",
            headline = "Warnung: Taschendiebe rund um Alexanderplatz U-Bahn",
            subtext = "Polizei bittet um Wachsamkeit bei dichtem Gedränge an den Rolltreppen.",
            kind = "warn",
            headlineColor = "rot",
            kiez = "Mitte",
            author = "Polizei Berlin / Kiez",
            timeAgo = "vor 4 Std.",
            source = "Polizei Berlin",
            link = "https://www.berlin.de/polizei/"
        ),
        KiezStory(
            id = "story_5",
            headline = "Kiezfest Bergmannstraße: Anmeldung für Stände offen",
            subtext = "Anwohner können sich bis Ende des Monats für kostenlose Nachbarschaftsstände bewerben. Ein buntes Straßenfest für die ganze Familie mit Musik, Essen und Flohmarktständen.",
            kind = "info",
            headlineColor = "weiss",
            kiez = "Kreuzberg",
            author = "Kiez-Initiative",
            timeAgo = "vor 6 Std.",
            source = "Kiez-Initiative Bergmannstraße",
            link = "",
            whatsapp = "+4915126509586"
        )
    )

    // Real Kiez Jobs
    val jobs = listOf(
        KiezJob(
            id = "job_1",
            title = "Barista & Servicekraft (m/w/d)",
            company = "The Visit Coffee Roastery",
            kiez = "Kreuzberg",
            jobType = "Teilzeit / Vollzeit",
            salary = "14,50 € - 17,00 € / Std.",
            description = "Wir suchen begeisterte Baristi für unser Team in Kreuzberg. Leidenschaft für Specialty Coffee und kundenorientiertes Arbeiten erwünscht.",
            contactWa = "+4915126509586",
            contactPhone = "+49 30 61284455",
            contactEmail = "jobs@thevisitcoffee.de",
            isUrgent = true
        ),
        KiezJob(
            id = "job_2",
            title = "Verkäufer / Kasiyer (m/w/d)",
            company = "Bolu Supermarkt",
            kiez = "Neukölln",
            jobType = "Vollzeit",
            salary = "2.300 € - 2.600 € / Monat",
            description = "Für unseren Standort am Hermannplatz suchen wir engagierte Verkäufer für die Obst- und Gemüseabteilung und die Kasse.",
            contactWa = "+4915126509586",
            contactPhone = "+49 30 6214589",
            contactEmail = "kontakt@bolu.berlin",
            isUrgent = false
        ),
        KiezJob(
            id = "job_3",
            title = "Lieferfahrer / Kurye (Klasse B)",
            company = "Berlin Handel Getränkeservice",
            kiez = "Mitte",
            jobType = "Vollzeit / Minijob",
            salary = "15,00 € / Std. + Trinkgeld",
            description = "Getränke-Auslieferung im Stadtgebiet Berlin mit Firmenfahrzeug. Zuverlässigkeit und Ortskenntnisse von Vorteil.",
            contactWa = "+4915126509586",
            contactPhone = "+49 151 26509586",
            contactEmail = "service@berlin-handel.de",
            isUrgent = true
        ),
        KiezJob(
            id = "job_4",
            title = "Friseur / Barber (m/w/d)",
            company = "Kiez Barber Club",
            kiez = "Wedding",
            jobType = "Vollzeit",
            salary = "2.400 € / Monat + Provision",
            description = "Moderner Barbershop sucht talentierte Friseure für Bartpflege, Fade-Cuts und klassische Haarschnitte.",
            contactWa = "+4915126509586",
            contactPhone = "+49 30 4678123",
            contactEmail = "info@kiezbarber.de",
            isUrgent = false
        ),
        KiezJob(
            id = "job_5",
            title = "Koch / Küchenhilfe (m/w/d)",
            company = "Hasir Restaurant",
            kiez = "Kreuzberg",
            jobType = "Vollzeit",
            salary = "2.500 € - 2.800 € / Monat",
            description = "Erfahrener Grillmeister oder Koch für traditionelle anatolische Küche und Holzkohlegrill gesucht.",
            contactWa = "+4915126509586",
            contactPhone = "+49 30 6142373",
            contactEmail = "karriere@hasir.de",
            isUrgent = false
        )
    )

    // Berlin Emergency Numbers (Notruf & Wichtige Nummern)
    val emergencyContacts = listOf(
        EmergencyContact(
            id = "polizei",
            nameDe = "Polizei Berlin",
            nameTr = "Berlin Polisi (Acil)",
            nameEn = "Berlin Police (Emergency)",
            descDe = "Bei akuter Gefahr, Straftaten und Unfällen",
            descTr = "Acil tehlike, suç ve kazalarda arayın",
            descEn = "Immediate danger, crime and accidents",
            number = "110",
            category = "notruf",
            isFree = true
        ),
        EmergencyContact(
            id = "feuerwehr",
            nameDe = "Feuerwehr & Rettungsdienst",
            nameTr = "İtfaiye & Acil Ambulans",
            nameEn = "Fire & Ambulance",
            descDe = "Brände, schwere Verletzungen, lebensbedrohliche Notfälle",
            descTr = "Yangın, ağır yaralanma ve hayati tehlikeler",
            descEn = "Fires, severe injuries and medical emergencies",
            number = "112",
            category = "notruf",
            isFree = true
        ),
        EmergencyContact(
            id = "aerztlicher_dienst",
            nameDe = "Ärztlicher Bereitschaftsdienst",
            nameTr = "Nöbetçi Doktor Hattı",
            nameEn = "Medical On-Call Service",
            descDe = "Bei Erkrankungen außerhalb der Praxisöffnungszeiten",
            descTr = "Muayenehane kapalıyken nöbetçi hekim",
            descEn = "Non-emergency medical care outside practice hours",
            number = "116117",
            category = "gesundheit",
            isFree = true
        ),
        EmergencyContact(
            id = "krisendienst",
            nameDe = "Berliner Krisendienst",
            nameTr = "Berlin Kriz Servisi (24 Saat)",
            nameEn = "Berlin Crisis Service (24h)",
            descDe = "Kostenlose Hilfe bei seelischen Krisen & Notfällen rund um die Uhr",
            descTr = "Psikolojik kriz ve çaresizlik anlarında 24 saat ücretsiz destek",
            descEn = "Psychological support in crises around the clock",
            number = "0303906310",
            category = "krise",
            isFree = true
        ),
        EmergencyContact(
            id = "giftnotruf",
            nameDe = "Giftnotruf Berlin",
            nameTr = "Zehirlenme Acil Danışma",
            nameEn = "Poison Control Center",
            descDe = "Vergiftungsnotfälle bei Kindern und Erwachsenen (24h)",
            descTr = "Çocuk ve yetişkinlerde zehirlenme durumunda 24 saat danışma",
            descEn = "24-hour guidance for poisoning emergencies",
            number = "03019240",
            category = "gesundheit",
            isFree = false
        ),
        EmergencyContact(
            id = "drogennotdienst",
            nameDe = "Drogennotdienst Berlin",
            nameTr = "Uyuşturucu & Bağımlılık Acil",
            nameEn = "Drug Emergency Hotline",
            descDe = "24 Stunden Beratung & Hilfe bei Suchtnotfällen",
            descTr = "Madde ve alkol bağımlılığı acil destek hattı",
            descEn = "24-hour help and counseling for addictions",
            number = "03019237",
            category = "krise",
            isFree = false
        ),
        EmergencyContact(
            id = "frauennotruf",
            nameDe = "Frauennotruf Berlin",
            nameTr = "Kadın Acil Destek Hattı",
            nameEn = "Women's Crisis Helpline",
            descDe = "Beratung bei Gewalt gegen Frauen und sexuellen Übergriffen",
            descTr = "Kadına yönelik şiddet ve taciz durumunda destek",
            descEn = "Crisis counseling for women facing violence",
            number = "0306154243",
            category = "soziales",
            isFree = false
        ),
        EmergencyContact(
            id = "jugendnotdienst",
            nameDe = "Kinder- und Jugendnotdienst",
            nameTr = "Çocuk & Genç Acil Servis",
            nameEn = "Child & Youth Emergency Hotline",
            descDe = "Zuflucht und Beratung für Kinder, Jugendliche und Eltern",
            descTr = "Çocuklar, gençler ve aileler için acil koruma ve danışma",
            descEn = "Immediate help and shelter for youth in distress",
            number = "030610061",
            category = "soziales",
            isFree = false
        ),
        EmergencyContact(
            id = "tiernotdienst",
            nameDe = "Tierärztlicher Notdienst Berlin",
            nameTr = "Nöbetçi Veteriner Hattı",
            nameEn = "Veterinary Emergency",
            descDe = "Zentrale Vermittlung des tierärztlichen Notdienstes",
            descTr = "Evcil hayvanlar için nöbetçi veteriner yönlendirmesi",
            descEn = "Emergency medical assistance for pets",
            number = "03083862356",
            category = "tiere",
            isFree = false
        ),
        EmergencyContact(
            id = "buergertelefon",
            nameDe = "Bürgertelefon Berlin",
            nameTr = "Berlin Vatandaş Danışma Hattı",
            nameEn = "Berlin Citizen Helpline",
            descDe = "Behördenauskunft zu Terminen, Pässen und Ummeldung",
            descTr = "Resmi daire randevuları, ikametgah ve kimlik işlemleri",
            descEn = "Official information on Berlin municipal services",
            number = "115",
            category = "soziales",
            isFree = true
        )
    )

    // Local Kiez Coupons
    val coupons = listOf(
        KiezCoupon(
            id = "coup_1",
            title = "10 % Rabatt auf Specialty Coffee Bohnen",
            businessName = "The Visit Coffee Roastery",
            kiez = "Kreuzberg",
            discount = "10 % RABATT",
            code = "KIEZCHECK10",
            description = "Gilt vor Ort beim Kauf von mindestens einer Packung Kaffeebohnen nach Wahl.",
            validUntil = "31.12.2026",
            whatsapp = "+4915126509586",
            address = "Adalbertstraße 9, 10999 Berlin"
        ),
        KiezCoupon(
            id = "coup_2",
            title = "Kostenloses Heißgetränk zum Frühstück",
            businessName = "Café Anna Blume",
            kiez = "Prenzlauer Berg",
            discount = "1x GRATIS KAFFEE",
            code = "ANNABLUME26",
            description = "Bestelle eine Frühstücksetagere und erhalte einen Cappuccino oder Filterkaffee gratis dazu.",
            validUntil = "30.11.2026",
            whatsapp = "+4915126509586",
            address = "Kollwitzstraße 83, 10435 Berlin"
        ),
        KiezCoupon(
            id = "coup_3",
            title = "5 € Rabatt ab 30 € Einkaufswert",
            businessName = "Bolu Supermarkt",
            kiez = "Neukölln",
            discount = "5 € SPAREN",
            code = "BOLU5SPAR",
            description = "Gültig für den gesamten Frische-Einkauf in der Neuköllner Filiale.",
            validUntil = "15.12.2026",
            whatsapp = "+4915126509586",
            address = "Karl-Marx-Straße 102, 12043 Berlin"
        ),
        KiezCoupon(
            id = "coup_4",
            title = "Gratis Pommes zu jedem Doppel-Burger",
            businessName = "Burgermeister",
            kiez = "Kreuzberg",
            discount = "GRATIS FRIES",
            code = "BURGERFREE",
            description = "Zeige diesen Gutschein an der Kasse am Schlesischen Tor.",
            validUntil = "31.12.2026",
            whatsapp = "+4915126509586",
            address = "Oberbaumstraße 8, 10997 Berlin"
        )
    )

    // Kiez-Reporter Community Posts
    val reporterPosts = listOf(
        KiezReporterPost(
            id = "rep_1",
            title = "Neuer Pop-Up Flohmarkt am Sonntag am Boxhagener Platz",
            description = "Lokale Nachbarn organisieren einen kleinen Second-Hand-Stand mit Büchern und Schallplatten.",
            kiez = "Friedrichshain",
            category = "Kultur",
            author = "Sarah",
            timeAgo = "vor 2 Std.",
            upvotes = 14
        ),
        KiezReporterPost(
            id = "rep_2",
            title = "Baustelle und Einbahnstraße in der Skalitzer Straße",
            description = "Fahrbahnverengung bis Freitag wegen Rohrarbeiten. Radfahrer bitte über Görlitzer Ufer ausweichen.",
            kiez = "Kreuzberg",
            category = "Baustelle",
            author = "Murat",
            timeAgo = "vor 4 Std.",
            upvotes = 9
        ),
        KiezReporterPost(
            id = "rep_3",
            title = "Schlüsselbund mit grünem Band im Volkspark Friedrichshain gefunden",
            description = "Gefunden nahe dem Märchenbrunnen und beim Parkwächter abgegeben.",
            kiez = "Friedrichshain",
            category = "Fundbüro",
            author = "Tobias",
            timeAgo = "vor 5 Std.",
            upvotes = 6
        )
    )
}
