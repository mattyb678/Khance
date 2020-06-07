package xyz.mattyb.khance

internal val originalTlds = listOf(
        "com", "org", "net", "int", "edu", "gov", "mil"
)

internal val countryTlds = listOf(
        "ac", "ad", "ae", "af", "ag", "ai", "al", "am", "ao", "aq", "ar", "as", "at", "au", "aw",
        "ax", "az", "ba", "bb", "bd", "be", "bf", "bg", "bh", "bi", "bj", "bm", "bn", "bo", "bq",
        "br", "bs", "bt", "bw", "by", "bz", "ca", "cc", "cd", "cf", "cg", "ch", "ci", "ck", "cl",
        "cm", "cn", "co", "cr", "cu", "cv", "cw", "cx", "cy", "cz", "de", "dj", "dk", "dm", "do",
        "dz", "ec", "ee", "eg", "eh", "er", "es", "et", "eu", "fi", "fj", "fk", "fm", "fo", "fr",
        "ga", "gd", "ge", "gf", "gg", "gh", "gi", "gl", "gm", "gn", "gp", "gq", "gr", "gs", "gt",
        "gu", "gw", "gy", "hk", "hm", "hn", "hr", "ht", "hu", "id", "ie", "il", "im", "in", "io",
        "iq", "ir", "is", "it", "je", "jm", "jo", "jp", "ke", "kg", "kh", "ki", "km", "kn", "kp",
        "kr", "kw", "ky", "kz", "la", "lb", "lc", "li", "lk", "lr", "ls", "lt", "lu", "lv", "ly",
        "ma", "mc", "md", "me", "mg", "mh", "mk", "ml", "mm", "mn", "mo", "mp", "mq", "mr", "ms",
        "mt", "mu", "mv", "mw", "mx", "my", "mz", "ma", "nc", "ne", "nf", "ng", "ni", "nl", "no",
        "np", "nr", "nu", "nz", "om", "pa", "pe", "pf", "pg", "ph", "pk", "pl", "pm", "pn", "pr",
        "ps", "pt", "pw", "py", "qa", "re", "ro", "rs", "ru", "rw", "sa", "sb", "sc", "sd", "se",
        "sg", "sh", "si", "sk", "sl", "sm", "sn", "so", "sr", "ss", "st", "su", "sv", "sx", "sy",
        "sz", "tc", "td", "tf", "tg", "th", "tj", "tk", "tl", "tm", "tn", "to", "tr", "tt", "tv",
        "tw", "tz", "ua", "ug", "uk", "us", "uy", "uz", "va", "vc", "ve", "vg", "vi", "vn", "vu",
        "wf", "ws", "ye", "yt", "za", "zm", "zw"
)

internal val genericTlds = listOf(
        "academy", "accountant", "accountants", "active", "actor", "ads", "adult", "aero",
        "africa", "agency", "airforce", "amazon", "analytics", "apartments", "app", "apple",
        "archi", "army", "art", "associates", "attorney", "auction", "audible", ".audio",
        "author", "auto", "autos", "aws", "baby", "band", "bank", "bar", "barefoot", "bargains",
        "baseball", "basketball", "beauty", "beer", "best", "bestbuy", "bet", "bible", "bid",
        "bike", "bingo", "bio", "biz", "black", "blackfriday", "blockbuster", "blog", "blue",
        "boo", "book", "boots", "bot", "boutique", "box", "broadway", "broker", "build",
        "builders", "business", "buy", "buzz", "cab", "cafe", "call", "cam", "camera", "camp",
        "cancerresearch", "capital", "car", "cards", "care", "career", "careers", "cars", "case",
        "cash", "casino", "catering", "catholic", "center", "cern", "ceo", "cfd", "channel",
        "chat", "cheap", "christmas", "church", "cipriani", "circle", "city", "claims", "cleaning",
        "click", "clinic", "clothing", "cloud", "club", "coach", "codes", "coffee", "college",
        "community", "company", "compare", "computer", "condos", "construction", "consulting",
        "contact", "contractors", "cooking", "cool", "coop", "country", "coupon", "coupons",
        "courses", "credit", "creditcard", "cruise", "cricket", "cruises", "data", "dance", "data",
        "date", "dating", "day", "deal", "deals", "degree", "delivery", "democrat", "dental",
        "dentist", "design", "dev", "diamonds", "diet", "digital", "direct", "directory",
        "discount", "diy", "docs", "doctor", "dog", "domains", "dot", "download", "drive", "duck",
        "earth", "eat", "eco", "education", "email", "energy", "engineer", "engineering", "edeka",
        "enterprises", "equipment", "esq", "estate", "events", "exchange", "expert", "exposed",
        "express", "fail", "faith", "family", "fan", "fans", "farm", "fashion", "fast", "feedback",
        "film", "final", "finance", "financial", "fire", "fish", "fishing", "fit", "fitness",
        "flights", "florist", "flowers", "fly", "foo", "food", "foodnetwork", "football",
        "forsale", "forum", "foundation", "free", "frontdoor", "fun", "fund", "furniture",
        "futbol", "fyi", "gallery", "game", "games", "garden", "gay", "gdn", "gift", "gifts",
        "gives", "glass", "global", "gold", "golf", "google", "gop", "graphics", "green", "gripe",
        "grocery", "group", "guide", "guitars", "guru", "hair", "hangout", "health", "healthcare",
        "help", "here", "hiphop", "hiv", "hockey", "holdings", "holiday", "homegoods", "homes",
        "homesense", "horse", "hospital", "host", "hosting", "hot", "hotels", "house", "how",
        "ice", "icu", "industries", "info", "ing", "ink", "institute", "insurance", "insure",
        "international", "investments", "jewelry", "jobs", "joy", "kim", "kitchen", "land", "lat",
        "law", "lawyer", "lease", "legal", "lgbt", "life", "lifeinsurance", "lighting", "like",
        "limited", "limo", "link", "live", "living", "loan", "loans", "locker", "lol", "lotto",
        "love", "ltd", "luxury", "makeup", "management", "map", "market", "marketing", "markets",
        "mba", "med", "media", "meet", "meme", "memorial", "men", "menu", "mint", "mobi", "mobile",
        "mobily", "moe", "mom", "money", "monster", "mortgage", "motorcycles", "mov", "movie",
        "museum", "music", "name", "navy", "network", "new", "news", "ngo", "ninja", "now", "ntt",
        "observer", "off", "one", "ong", "onl", "online", "ooo", "open", "organic", "origins",
        "page", "partners", "parts", "party", "pay", "pet", "pharmacy", "phone", "photo",
        "photography", "photos", "physio", "pics", "pictures", "pid", "pin", "pink", "pizza",
        "place", "plumbing", "plus", "poker", "porn", "post", "press", "prime", "pro",
        "productions", "prof", "promo", "properties", "property", "protection", "pub", "qpon",
        "racing", "radio", "read", "realestate", "realtor", "realty", "recipes", "red", "rehab",
        "reit", "rent", "rentals", "repair", "report", "republican", "rest", "restaurant",
        "review", "reviews", "rich", "rip", "rocks", "rodeo", "room", "rugby", "run", "safe",
        "sale", "save", "sbi", "scholarships", "school", "science", "search", "secure", "security",
        "select", "services", "sex", "sexy", "shoes", "shop", "shopping", "show", "showtime",
        "silk", "singles", "site", "ski", "skin", "sky", "sling", "smile", "soccer", "social",
        "software", "solar", "solutions", "song", "space", "spot", "spreadbetting", "storage",
        "store", "stream", "studio", "study", "style", "sucks", "supplies", "supply", "support",
        "surf", "surgery", "systems", "talk", "tattoo", "tax", "taxi", "team", "tech",
        "technology", "tel", "tennis", "theater", "theatre", "tickets", "tips", "tires", "today",
        "tools", "top", "tours", "town", "toys", "trade", "trading", "training", "travel",
        "travelersinsurance", "trust", "tube", "tunes", "uconnect", "university", "vacations",
        "ventures", "vet", "video", "villas", "vip", "vision", "vodka", "vote", "voting", "voyage",
        "wang", "watch", "watches", "weather", "webcam", "website", "wed", "wedding", "whoswho",
        "wiki", "win", "wine", "winners", "work", "works", "world", "wow", "wtf", "xxx", "xyz",
        "yachts", "yoga", "you", "zero", "zone"
)

internal val genericChineseTlds = listOf(
        "ren", "shouji", "tushu", "wanggou", "weibo", "xihuan", "xin"
)

internal val genericFrenchTlds = listOf(
        "arte", "clinique", "luxe", "maison", "moi", "rsvp", "sarl"
)

internal val genericGermanTlds = listOf(
        "berlin", "epost", "gmbh", "haus", "immobilien", "jetzt", "kaufen", "kinder", "reise",
        "reisen", "schule", "versicherung"
)

internal val genericHindiTlds = listOf(
        "desi", "shiksha"
)

internal val genericItalianTlds = listOf(
        "casa", "immo", "moda", "voto"
)

internal val genericPortugueseTlds = listOf(
        "bom", "passagens"
)

internal val genericSpanishTlds = listOf(
        "abogado", "futbol", "gratis", "hoteles", "juegos", "ltda", "soy", "tienda", "uno",
        "viajes", "vuelos"
)

internal val geoAfricaTlds = listOf(
        "africa", "capetown", "durban", "joburg"
)

internal val geoAsiaTlds = listOf(
        "abudhabi", "arab", "asia", "doha", "dubai", "krd", "kyoto", "nagoya", "okinawa",
        "osaka", "ryukyu", "taipei", "tatar", "tokyo", "yokohama"
)

internal val geoEuropeTlds = listOf(
        "alsace", "amsterdam", "bar", "bcn", "barcelona", "bayern", "berlin", "brussels",
        "budapest", "bzh", "cat", "cologne", "corsica", "cymru", "eus", "frl", "gal", "gent",
        "hamburg", "helsinki", "irish", "ist", "istanbul", "koeln", "london", "madrid", "moscow",
        "nrw", "paris", "ruhr", "saarland", "scot", "stockholm", "swiss", "tirol", "vlaanderen",
        "wales", "wien", "zuerich"
)

internal val geoNorthAmericaTlds = listOf(
        "boston", "miami", "nyc", "quebec", "vegas"
)

internal val geoOceaniaTlds = listOf(
        "kiwi", "melbourne", "sydney"
)

internal val geoSouthAmericaTlds = listOf(
        "lat", "rio"
)