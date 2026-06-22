package com.example.ecommerceapplication.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.ecommerceapplication.entity.Category;
import com.example.ecommerceapplication.entity.Product;
import com.example.ecommerceapplication.entity.User;
import com.example.ecommerceapplication.enums.Role;
import com.example.ecommerceapplication.repository.CategoryRepository;
import com.example.ecommerceapplication.repository.ProductRepository;
import com.example.ecommerceapplication.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public DataLoader(
            CategoryRepository categoryRepository,
            ProductRepository productRepository,
            UserRepository userRepository)  {

        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;       
    }

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {

        User admin = new User();

        admin.setName("Admin");

        admin.setEmail("admin@gmail.com");

        admin.setPassword("admin123");

        admin.setRole(Role.ADMIN);

        userRepository.save(admin);

        System.out.println("Admin created successfully");
    }

        if (categoryRepository.count() > 0
                || productRepository.count() > 0) {
            return;
        }

        

    

        String[] categories = {
            "Electronics",         // 0
            "Fashion",             // 1
            "Books",               // 2
            "Home Appliances",     // 3
            "Furniture",           // 4
            "Sports",              // 5
            "Beauty",              // 6
            "Toys",                // 7
            "Groceries",           // 8
            "Automotive",          // 9
            "Jewellery",           // 10
            "Footwear",            // 11
            "Gaming",              // 12
            "Health",              // 13
            "Office Supplies",     // 14
            "Pet Supplies",        // 15
            "Kitchen",             // 16
            "Mobile Accessories",  // 17
            "Travel",              // 18
            "Baby Products",       // 19
            "Music",               // 20
            "Cameras",             // 21
            "Watches",             // 22
            "Bags & Luggage",      // 23
            "Garden & Outdoors",   // 24
            "Stationery",          // 25
            "Art & Craft",         // 26
            "Fitness Equipment",   // 27
            "Nutrition",           // 28
            "Clothing Accessories",// 29
            "Smart Home",          // 30
            "Computer Peripherals",// 31
            "Networking",          // 32
            "Wearables",           // 33
            "Audio & Video",       // 34
            "Personal Care",       // 35
            "Bedding",             // 36
            "Cleaning Supplies",   // 37
            "Lighting",            // 38
            "Tools & Hardware",    // 39
            "Educational",         // 40
            "Cycling",             // 41
            "Swimming",            // 42
            "Camping",             // 43
            "Winter Wear",         // 44
            "Ethnic Wear",         // 45
            "Sunglasses",          // 46
            "Perfumes",            // 47
            "Snacks",              // 48
            "Beverages"            // 49
        };

        List<Category> categoryList = new ArrayList<>();
        for (String categoryName : categories) {
            Category category = new Category();
            category.setName(categoryName);
            categoryList.add(category);
        }
        categoryRepository.saveAll(categoryList);

        // 50 categories × 20 products each = 1000 products
        String[][] productData = {

            // 0: Electronics (20)
            {"iPhone 16", "Samsung Galaxy S25", "OnePlus 14", "Google Pixel 10",
             "MacBook Air M4", "Dell Inspiron 15", "HP Pavilion", "Sony Headphones",
             "Apple Watch", "iPad Air", "Lenovo ThinkPad", "Asus ROG Laptop",
             "Sony Bravia TV", "LG OLED TV", "Bose Speaker", "JBL Bluetooth Speaker",
             "Amazon Echo", "Google Nest Hub", "Kindle Paperwhite", "Nvidia RTX 4080"},

            // 1: Fashion (20)
            {"Puma Hoodie", "Levis Jeans", "Allen Solly Shirt", "Nike Shorts",
             "H&M Jacket", "Zara Sweatshirt", "Roadster Jacket", "Adidas Tracksuit",
             "Polo Tshirt", "Casual Tshirt", "Denim Jacket", "Cotton Kurti",
             "Formal Blazer", "Chinos", "Linen Shirt", "Graphic Tee",
             "Woollen Sweater", "Bomber Jacket", "Cargo Pants", "Maxi Dress"},

            // 2: Books (20)
            {"Head First Java", "Java Complete Reference", "Clean Code", "Atomic Habits",
             "Ikigai", "Rich Dad Poor Dad", "Deep Work", "Spring Boot In Action",
             "Effective Java", "Think And Grow Rich", "The Alchemist", "Sapiens",
             "Zero To One", "The Psychology of Money", "Start With Why", "Mindset",
             "The Lean Startup", "Design Patterns", "Data Structures Algorithms", "Power Of Now"},

            // 3: Home Appliances (20)
            {"LG Refrigerator", "Samsung Washing Machine", "Philips Iron", "Panasonic Microwave",
             "Kent RO", "Havells Fan", "Bajaj Mixer", "Voltas AC",
             "Usha Heater", "Air Cooler", "Dyson Vacuum", "Bosch Dishwasher",
             "Whirlpool Dryer", "IFB Front Load", "Crompton Geyser", "Orient Ceiling Fan",
             "Blue Star Air Purifier", "Morphy Richards OTG", "Pigeon Induction Cooker", "Eureka Forbes Vacuum"},

            // 4: Furniture (20)
            {"Office Chair", "Study Table", "Sofa Set", "TV Unit",
             "Bookshelf", "Computer Desk", "Wardrobe", "Dining Table",
             "Shoe Rack", "Wooden Bed", "Recliner Chair", "Bean Bag",
             "Coffee Table", "Side Table", "Dressing Table", "Kids Bunk Bed",
             "Wall Shelves", "Folding Chair", "Bar Stool", "L-Shape Sofa"},

            // 5: Sports (20)
            {"Cricket Bat", "Football", "Basketball", "Volleyball",
             "Badminton Racket", "Tennis Ball", "Yoga Mat", "Gym Gloves",
             "Skipping Rope", "Helmet", "Swimming Goggles", "Table Tennis Bat",
             "Golf Club", "Hockey Stick", "Rugby Ball", "Archery Set",
             "Dumbbell Set", "Pull-Up Bar", "Boxing Gloves", "Treadmill"},

            // 6: Beauty (20)
            {"Face Wash", "Face Serum", "Lip Balm", "Hair Dryer",
             "Trimmer", "Shampoo", "Conditioner", "Beard Oil",
             "Sunscreen", "Body Lotion", "Foundation", "Mascara",
             "Kajal", "BB Cream", "Under Eye Cream", "Micellar Water",
             "Toner", "Face Mask", "Nail Polish", "Setting Spray"},

            // 7: Toys (20)
            {"Teddy Bear", "Lego Set", "Remote Car", "Puzzle Board",
             "Toy Train", "Barbie Doll", "Rubik Cube", "Drone Toy",
             "Building Blocks", "Toy Kitchen Set", "Action Figure", "Board Game",
             "Play-Doh Set", "Water Gun", "Frisbee", "Yo-Yo",
             "Magnetic Drawing Board", "Robot Toy", "Finger Puppets", "Kite"},

            // 8: Groceries (20)
            {"Rice", "Sugar", "Salt", "Tea Powder",
             "Coffee Powder", "Sunflower Oil", "Basmati Rice", "Turmeric Powder",
             "Chilli Powder", "Wheat Flour", "Mustard Oil", "Coconut Oil",
             "Dal", "Chickpeas", "Oats", "Honey",
             "Jam", "Butter", "Ghee", "Paneer"},

            // 9: Automotive (20)
            {"Helmet", "Car Perfume", "Seat Cover", "Engine Oil",
             "Tyre Inflator", "Mobile Holder", "Bike Mirror", "Car Vacuum",
             "Bike Gloves", "Car Cover", "Jump Starter", "Dash Cam",
             "Car Wash Kit", "Steering Cover", "Parking Sensor", "Car Jack",
             "LED Car Lights", "Wiper Blades", "Car Organizer", "Anti-Rust Spray"},

            // 10: Jewellery (20)
            {"Gold Chain", "Silver Ring", "Diamond Ring", "Gold Earrings",
             "Bracelet", "Pendant", "Necklace", "Anklet",
             "Bangles", "Nose Ring", "Pearl Earrings", "Kundan Necklace",
             "Oxidised Ring", "Rose Gold Bracelet", "Platinum Ring", "Charm Bracelet",
             "Temple Jewellery Set", "Mangalsutra", "Cuff Earrings", "Hair Pin Set"},

            // 11: Footwear (20)
            {"Nike Air Max", "Adidas Ultraboost", "Puma Shoes", "Campus Shoes",
             "Woodland Boots", "Crocs", "Bata Formal Shoes", "Sandals",
             "Slippers", "Sneakers", "Heels", "Kolhapuri Chappal",
             "Sports Shoes", "Running Shoes", "Loafers", "Flip Flops",
             "Rain Boots", "Chelsea Boots", "Ankle Boots", "Moccasins"},

            // 12: Gaming (20)
            {"PS5", "Xbox Series X", "Gaming Mouse", "Gaming Keyboard",
             "Gaming Chair", "Gaming Headset", "Gaming Monitor", "PS5 Controller",
             "Mouse Pad", "Steering Wheel", "Nintendo Switch", "Gaming Router",
             "VR Headset", "Gaming Webcam", "Capture Card", "Gaming Desk",
             "RGB LED Strip", "Gaming Microphone", "Elite Controller", "Gaming Backpack"},

            // 13: Health (20)
            {"BP Monitor", "Oximeter", "Thermometer", "Protein Powder",
             "Massager Gun", "First Aid Kit", "Weighing Machine", "Resistance Bands",
             "Foam Roller", "Multivitamin", "Glucometer", "Nebulizer",
             "Heating Pad", "Cold Gel Pack", "Back Brace", "Knee Support",
             "Eye Drops", "Neck Massager", "Air Purifier", "UV Sterilizer"},

            // 14: Office Supplies (20)
            {"Notebook", "Pen Set", "Stapler", "Calculator",
             "Marker Set", "Desk Organizer", "Sticky Notes", "Whiteboard",
             "File Folder", "Printer Paper", "Scissors", "Tape Dispenser",
             "Letter Tray", "Envelopes", "Correction Fluid", "Binder Clips",
             "Rubber Bands", "Highlighters", "Stamp Pad", "Lamination Pouch"},

            // 15: Pet Supplies (20)
            {"Dog Food", "Cat Food", "Pet Bed", "Pet Shampoo",
             "Dog Leash", "Pet Brush", "Pet Carrier", "Pet Collar",
             "Pet Bowl", "Pet Toy Ball", "Cat Litter", "Bird Cage",
             "Fish Tank", "Aquarium Filter", "Pet Vitamins", "Dog Treat",
             "Cat Scratcher", "Pet Nail Clipper", "Dog Raincoat", "Pet Water Fountain"},

            // 16: Kitchen (20)
            {"Pressure Cooker", "Knife Set", "Electric Kettle", "Lunch Box",
             "Storage Container", "Water Bottle", "Dinner Set", "Blender",
             "Chopping Board", "Fry Pan", "Roti Maker", "Sandwich Maker",
             "Coffee Maker", "Juicer", "Mixer Grinder", "Air Fryer",
             "Rice Cooker", "Non-Stick Tawa", "Steel Kadai", "Serving Spoon Set"},

            // 17: Mobile Accessories (20)
            {"Phone Case", "Tempered Glass", "Power Bank", "Charger",
             "Type C Cable", "Wireless Charger", "Selfie Stick", "Earbuds",
             "Car Charger", "Mobile Stand", "Pop Socket", "Phone Ring Holder",
             "Lens Kit", "Bluetooth Speaker", "OTG Adapter", "Micro SD Card",
             "Lightning Cable", "Phone Wallet", "Portable Fan", "UV Sanitizer"},

            // 18: Travel (20)
            {"Backpack", "Trolley Bag", "Passport Cover", "Travel Adapter",
             "Travel Pillow", "Travel Bottle", "Sleeping Bag", "Raincoat",
             "Travel Organizer", "Trekking Shoes", "Packing Cubes", "Luggage Tag",
             "Travel Wallet", "Neck Pouch", "Compression Bags", "RFID Blocker",
             "Portable Clothesline", "Travel Towel", "Sunglasses Case", "Fanny Pack"},

            // 19: Baby Products (20)
            {"Baby Diapers", "Baby Wipes", "Baby Lotion", "Baby Shampoo",
             "Baby Powder", "Feeding Bottle", "Baby Blanket", "Baby Walker",
             "Baby Toy Set", "Baby Bath Tub", "Stroller", "Baby Monitor",
             "Cradle", "Baby Carrier", "Nursing Pillow", "Teether",
             "Baby High Chair", "Diaper Bag", "Baby Food Maker", "Baby Nail Clipper"},

            // 20: Music (20)
            {"Acoustic Guitar", "Electric Guitar", "Violin", "Keyboard Piano",
             "Tabla", "Harmonium", "Ukulele", "Bass Guitar",
             "Cajon", "Flute", "Drum Kit", "Saxophone",
             "Microphone", "Music Stand", "Guitar Strap", "Tuner Clip",
             "Metronome", "Guitar Picks", "Violin Bow", "Piano Bench"},

            // 21: Cameras (20)
            {"Canon DSLR", "Nikon D3500", "Sony Alpha", "Fujifilm X-T5",
             "GoPro Hero", "DJI Osmo", "Polaroid Camera", "Canon PowerShot",
             "Nikon Coolpix", "Drone Camera", "Camera Tripod", "Camera Bag",
             "UV Filter", "Camera Lens 50mm", "Wide Angle Lens", "Camera Flash",
             "Memory Card 128GB", "SD Card Reader", "Camera Cleaning Kit", "Gimbal Stabilizer"},

            // 22: Watches (20)
            {"Titan Analog", "Fastrack Digital", "Casio G-Shock", "Apple Watch SE",
             "Samsung Galaxy Watch", "Fossil Smartwatch", "Noise ColorFit", "Boat Storm",
             "Timex Classic", "HMT Janata", "Rolex Submariner", "Omega Seamaster",
             "Garmin Vivoactive", "Fitbit Charge", "Amazfit Bip", "Seiko Presage",
             "Tag Heuer Carrera", "Citizen Eco Drive", "Movado Bold", "Orient Bambino"},

            // 23: Bags & Luggage (20)
            {"Wildcraft Backpack", "American Tourister", "VIP Trolley", "Lavie Handbag",
             "Baggit Sling Bag", "Fossil Wallet", "Leather Laptop Bag", "Tote Bag",
             "Duffel Bag", "Clutch Purse", "Messenger Bag", "School Bag",
             "Gym Bag", "Waist Pouch", "Camera Bag", "Diaper Backpack",
             "Drawstring Bag", "Laptop Sleeve", "Portfolio Case", "Garment Bag"},

            // 24: Garden & Outdoors (20)
            {"Garden Hose", "Plant Pots", "Soil Mix", "Garden Gloves",
             "Watering Can", "Garden Fork", "Pruning Shears", "Lawn Mower",
             "Rake", "Shovel", "Seeds Kit", "Fertilizer",
             "Insecticide Spray", "Drip Irrigation Kit", "Garden Kneeler", "Plant Support Stakes",
             "Hanging Planter", "Garden Light", "Compost Bin", "Outdoor Umbrella"},

            // 25: Stationery (20)
            {"Fountain Pen", "Gel Pens", "Ballpoint Pens", "Sketch Pens",
             "Notebook Set", "Diary", "Planner", "Index Cards",
             "Post-It Notes", "Washi Tape", "Stapler Set", "Hole Puncher",
             "Paper Clips", "Binder", "Spiral Notebook", "Pencil Box",
             "Eraser Set", "Sharpener", "Ruler Set", "Compass Set"},

            // 26: Art & Craft (20)
            {"Acrylic Paint Set", "Watercolor Kit", "Oil Pastel", "Sketching Pencils",
             "Canvas Board", "Paint Brushes", "Clay Kit", "Origami Paper",
             "Craft Scissors", "Glue Gun", "Rhinestone Kit", "Knitting Needles",
             "Crochet Kit", "Resin Kit", "Macrame Rope", "Fabric Paint",
             "Stamp Set", "Foam Sheets", "Craft Wire", "Beads Set"},

            // 27: Fitness Equipment (20)
            {"Barbell Set", "Kettlebell", "Pull-Up Bar", "Resistance Bands",
             "Dumbbell Pair", "Ab Roller", "Push-Up Bars", "Battle Rope",
             "Plyo Box", "Medicine Ball", "Weight Bench", "TRX Straps",
             "Ankle Weights", "Jump Rope", "Punching Bag", "Yoga Block",
             "Balance Board", "Suspension Trainer", "Stepper Machine", "Rowing Machine"},

            // 28: Nutrition (20)
            {"Whey Protein", "Casein Protein", "Mass Gainer", "BCAA Powder",
             "Creatine Monohydrate", "Pre-Workout", "Fish Oil Capsules", "Vitamin D3",
             "Biotin Tablets", "Omega 3", "Zinc Tablets", "Iron Supplement",
             "Protein Bar", "Energy Gel", "Electrolyte Powder", "Collagen Powder",
             "Ashwagandha", "Turmeric Capsules", "Green Tea Extract", "Meal Replacement"},

            // 29: Clothing Accessories (20)
            {"Leather Belt", "Canvas Belt", "Necktie", "Pocket Square",
             "Bow Tie", "Suspenders", "Cufflinks", "Scarf",
             "Woollen Muffler", "Cap", "Hat", "Beanie",
             "Gloves", "Socks Pack", "Handkerchief Set", "Sunglasses",
             "Brooch", "Hair Band", "Headband", "Eyeglass Chain"},

            // 30: Smart Home (20)
            {"Smart Bulb", "Smart Plug", "Smart Lock", "Video Doorbell",
             "Smart Thermostat", "Wi-Fi Camera", "Smart Speaker", "Smart TV Remote",
             "Smart Switch", "Motion Sensor", "Smoke Detector", "Smart Fan",
             "Smart AC Remote", "Smart Curtain Motor", "Smart Irrigation", "Smart Pet Feeder",
             "Smart Baby Monitor", "Smart Scale", "Home Hub", "Smart Garage Opener"},

            // 31: Computer Peripherals (20)
            {"Mechanical Keyboard", "Wireless Mouse", "USB Hub", "External HDD",
             "SSD 1TB", "Webcam 4K", "Monitor 27 inch", "Graphics Tablet",
             "Printer", "Scanner", "Docking Station", "Trackpad",
             "Numeric Keypad", "Mouse Bungee", "USB-C Adapter", "Cable Management Kit",
             "Monitor Light Bar", "Keyboard Wrist Rest", "CPU Stand", "Cable Sleeve"},

            // 32: Networking (20)
            {"Wi-Fi Router", "Mesh Wi-Fi System", "Wi-Fi Extender", "Ethernet Switch",
             "Network Cable", "Powerline Adapter", "NAS Drive", "Cable Modem",
             "Firewall Device", "VPN Router", "Access Point", "PoE Switch",
             "Fiber Media Converter", "SFP Module", "Rack Mount", "Patch Panel",
             "Network Tester", "Crimping Tool", "Wi-Fi Analyzer", "Ethernet Adapter"},

            // 33: Wearables (20)
            {"Fitness Band", "Smart Ring", "GPS Tracker", "Smart Glasses",
             "ECG Watch", "Running Watch", "Kids Smartwatch", "Safety Band",
             "Sleep Tracker", "Posture Corrector", "Smart Clip", "Cycling Computer",
             "Underwater Watch", "Adventure GPS", "Stress Monitor", "Smart Patch",
             "Hearing Aid", "Body Camera", "Smart Insole", "Temperature Monitor"},

            // 34: Audio & Video (20)
            {"Soundbar", "Home Theatre", "Projector", "Blu-ray Player",
             "Streaming Device", "Smart TV Box", "HDMI Cable", "AV Receiver",
             "Bluetooth Transmitter", "Record Player", "Karaoke Machine", "Portable Projector",
             "Webcam", "Video Conference Kit", "Laser Projector", "OLED TV 65 inch",
             "Speaker Stand", "Remote Control", "TV Wall Mount", "Cable Organizer"},

            // 35: Personal Care (20)
            {"Electric Toothbrush", "Water Flosser", "Tongue Cleaner", "Nose Trimmer",
             "Ear Trimmer", "Shaving Kit", "Razor", "Aftershave Lotion",
             "Deodorant", "Perfume", "Foot Scrubber", "Pumice Stone",
             "Nail Cutter Set", "Eyebrow Trimmer", "Face Roller", "Gua Sha Stone",
             "Eyelash Curler", "Tweezers", "Pedicure Kit", "Bath Loofah"},

            // 36: Bedding (20)
            {"Bedsheet Set", "Pillow Cover", "Comforter", "Duvet",
             "Mattress Protector", "Pillow Set", "Bed Runner", "Quilt",
             "Fitted Sheet", "Woollen Blanket", "Electric Blanket", "Memory Foam Pillow",
             "Orthopedic Mattress", "Mattress Topper", "Bolster Pillow", "Travel Pillow",
             "Kids Printed Sheet", "Bamboo Pillow", "Cooling Gel Pillow", "Waterproof Sheet"},

            // 37: Cleaning Supplies (20)
            {"Mop Set", "Broom", "Dustpan", "Scrubber Sponge",
             "Floor Cleaner", "Toilet Cleaner", "Glass Cleaner", "Dish Soap",
             "Laundry Detergent", "Fabric Softener", "Disinfectant Spray", "Trash Bags",
             "Rubber Gloves", "Microfiber Cloth", "Steam Cleaner", "Spin Mop",
             "Air Freshener", "Drain Cleaner", "Kitchen Degreaser", "Toilet Brush"},

            // 38: Lighting (20)
            {"LED Bulb", "Panel Light", "Tube Light", "Spotlight",
             "Night Lamp", "Floor Lamp", "Table Lamp", "Pendant Light",
             "Wall Sconce", "Outdoor Garden Light", "Solar Light", "LED Strip",
             "Emergency Light", "Torch", "Lantern", "Dimmer Switch",
             "Smart Ceiling Light", "Study Lamp", "Bedside Lamp", "Chandelier"},

            // 39: Tools & Hardware (20)
            {"Drill Machine", "Screwdriver Set", "Hammer", "Wrench Set",
             "Measuring Tape", "Level Tool", "Pliers Set", "Wire Cutter",
             "Hacksaw", "Sandpaper", "Spray Paint", "Tool Box",
             "Allen Key Set", "Riveter", "Soldering Iron", "Heat Gun",
             "Cordless Drill", "Circular Saw", "Jigsaw", "Power Sander"},

            // 40: Educational (20)
            {"Globe", "Periodic Table Chart", "Solar System Model", "Telescope",
             "Microscope", "Science Kit", "Math Kit", "Robot Building Kit",
             "Coding Kit", "Chess Set", "Scrabble", "Flash Cards",
             "Map Puzzle", "Abacus", "Multiplication Table", "Human Body Model",
             "Chemistry Set", "Electronics Kit", "Anatomy Chart", "Magnifying Glass"},

            // 41: Cycling (20)
            {"Mountain Bike", "Road Bike", "Hybrid Bike", "Kids Cycle",
             "Cycle Helmet", "Cycling Gloves", "Cycling Shorts", "Cycle Lock",
             "Cycle Pump", "Cycle Lights", "Cycle Bell", "Cycle Bag",
             "Cycle Computer", "Mudguard", "Cycle Stand", "Inner Tube",
             "Cycling Jersey", "Clipless Pedals", "Water Bottle Cage", "Chain Lubricant"},

            // 42: Swimming (20)
            {"Swimming Goggles", "Swim Cap", "Swimsuit", "Swim Fins",
             "Kickboard", "Pull Buoy", "Swim Snorkel", "Hand Paddles",
             "Waterproof Bag", "Swim Earplugs", "Nose Clip", "Swim Towel",
             "Swim Bag", "Float Belt", "Swim Resistance Band", "Waterproof Watch",
             "Swim Training Fins", "Pool Shoes", "Underwater MP3", "Swim Tracker"},

            // 43: Camping (20)
            {"Camping Tent", "Sleeping Bag", "Camping Stove", "Trekking Pole",
             "Headlamp", "Camp Lantern", "Camping Cookware", "Waterproof Jacket",
             "Hiking Boots", "Camping Hammock", "Fire Starter", "Compass",
             "First Aid Kit", "Insect Repellent", "Solar Charger", "Multi-Tool",
             "Water Purifier", "Dry Bag", "Camping Chair", "Bear Spray"},

            // 44: Winter Wear (20)
            {"Puffer Jacket", "Woollen Coat", "Thermal Innerwear", "Fleece Jacket",
             "Woollen Cap", "Muffler", "Hand Gloves", "Woollen Socks",
             "Snow Boots", "Ski Jacket", "Down Jacket", "Hoodie",
             "Turtle Neck Sweater", "Cardigan", "Woollen Leggings", "Ear Muffs",
             "Sherpa Blanket", "Hand Warmers", "Balaclava", "Shearling Jacket"},

            // 45: Ethnic Wear (20)
            {"Saree", "Salwar Kameez", "Lehenga Choli", "Kurta Pyjama",
             "Sherwani", "Dhoti Kurta", "Anarkali Suit", "Palazzo Set",
             "Sharara Set", "Dupatta", "Nehru Jacket", "Pathani Suit",
             "Bandhgala", "Ethnic Skirt", "Mirror Work Top", "Printed Kaftan",
             "Indo-Western Set", "Raw Silk Kurta", "Chanderi Saree", "Banarasi Dupatta"},

            // 46: Sunglasses (20)
            {"Ray-Ban Aviator", "Ray-Ban Wayfarer", "Oakley Holbrook", "Polaroid Sunglasses",
             "Fastrack Sunglasses", "Titan Sunglasses", "Carrera Sunglasses", "Persol Sunglasses",
             "Maui Jim", "Prada Sunglasses", "Gucci Sunglasses", "Tom Ford",
             "Cat-Eye Sunglasses", "Round Retro Sunglasses", "Sports Goggles", "Kids Sunglasses",
             "Clip-On Sunglasses", "Blue-Light Glasses", "Night Vision Glasses", "Photochromic Glasses"},

            // 47: Perfumes (20)
            {"Davidoff Cool Water", "Dior Sauvage", "Chanel No 5", "Versace Eros",
             "Tom Ford Black Orchid", "Gucci Bloom", "Paco Rabanne 1 Million", "Hugo Boss",
             "Calvin Klein Eternity", "Armani Code", "Burberry London", "Lacoste L12.12",
             "Park Avenue", "Fogg Black", "Wild Stone", "Denver Hamilton",
             "Engage W1", "Titan Sketo", "Bella Vita Organic", "Set Wet"},

            // 48: Snacks (20)
            {"Lays Chips", "Kurkure", "Bingo Mad Angles", "Pringles",
             "Too Yumm", "Doritos", "Act II Popcorn", "Haldiram Namkeen",
             "Bikaji Bhujia", "Diet Bhel", "Almonds", "Cashews",
             "Dark Chocolate", "Protein Bar", "Granola Bar", "Rice Cakes",
             "Multigrain Crackers", "Roasted Makhana", "Trail Mix", "Dates Box"},

            // 49: Beverages (20)
            {"Coca Cola", "Pepsi", "Sprite", "Mountain Dew",
             "Red Bull", "Monster Energy", "Tropicana Juice", "Real Fruit Juice",
             "Paper Boat", "B Natural", "Nescafe Classic", "Bru Gold",
             "Tata Tea Premium", "Lipton Green Tea", "Chaayos Masala Chai", "Nestle Milo",
             "Horlicks", "Complan", "Boost", "Buttermilk Pack"}
        };

        List<Product> products = new ArrayList<>();

        for (int i = 0; i < categories.length; i++) {

            String category = categories[i];

            for (int j = 0; j < 20; j++) {

                Product product = new Product();

                product.setName(productData[i][j]);

                product.setDescription(
                        productData[i][j] + " for " + category
                );

                product.setPrice(
                        500.0 + (j * 500)
                );

                product.setStock(
                        String.valueOf(10 + j)
                );

                product.setBrand(
                        "Brand " + (j + 1)
                );

                product.setCategory(categoryList.get(i));

                product.setImageUrl(
                        "https://picsum.photos/seed/"
                                + productData[i][j].replace(" ", "").replace("&", "")
                                + "/500/500"
                );

                products.add(product);
            }
        }

        productRepository.saveAll(products);

        System.out.println(
                "50 Categories + 1000 Products inserted successfully"
        );
    }
}