package services;

import classes.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class Services {
    private static Services servicesInstance = null;
    private List<User> users = new ArrayList<>();
    private List<Auction> auctions = new ArrayList<>();
    private List<Product> products = new ArrayList<>();

    private User user;
    private Database database;

    // Singleton -> private constructor
    private Services() {
        // Get database
        this.user = null;
        getDataBase();
    }

    // Get Instance function for singleton
    public static Services getServicesInstance() {
        if (servicesInstance == null)
            servicesInstance = new Services();
        return servicesInstance;
    }

    private void getDataBase() {
        database = Database.getDatabaseInstance();


        // Getting bidder users from database
        List<String []> info = database.readDataFromCsv("bidders.csv");
        info.stream()
                .map(str -> new Bidder(Integer.parseInt(str[0]), str[1], str[2], str[3], str[4],str[5], str[6]))
                .forEach(bid -> users.add(bid));


        // Getting seller users from database
        info = database.readDataFromCsv("sellers.csv");
        info.stream()
                .map(str -> new Seller(Integer.parseInt(str[0]), str[1], str[2], str[3], str[4],str[5], str[6]))
                .forEach(bid -> users.add(bid));


        // Getting collection products from database
        info = database.readDataFromCsv("collection-products.csv");
        info.stream()
                .forEach(str ->
                {
                    CollectionProduct p = new CollectionProduct(Integer.parseInt(str[0]),str[1], str[2], Float.parseFloat(str[3]),
                            Boolean.parseBoolean(str[4]), Integer.parseInt(str[5]), str[6],
                            new Date(Integer.parseInt(str[7]), Integer.parseInt(str[8]), Integer.parseInt(str[9])), str[10]);
                    for(User u : users)
                        if(u.getUserID() == Integer.parseInt(str[5])){
                            ((Seller)u).addProduct(p); // add product to owner(seller)
                            break;
                        }
                    products.add(p);
                });


        // Getting auctions from database
        info = database.readDataFromCsv("auctions.csv");
        info.stream()
                .forEach(str ->
                {
                    Auction a = new Auction(Integer.parseInt(str[0]), str[1],
                        new Date(Integer.parseInt(str[2]), Integer.parseInt(str[3]), Integer.parseInt(str[4])));

                    int i = 6;
                    for(i = 6; i < 6 + Integer.parseInt(str[5]); i++){ // add users to auction
                        for(User u : users)
                            if(u.getUserID() == Integer.parseInt(str[i])){
                                a.addUser(u);
                                ((Bidder)u).addAuction(a);
                                break;
                            }
                    }

                    i ++;
                    for(; i < str.length; i++) // add products to auction
                        for(Product p : products)
                            if(p.getProductID() == Integer.parseInt(str[i])){
                                a.addProduct(p);
                                break;
                            }
                    auctions.add(a);
                });

//        System.out.println("Database:");
//        System.out.println("Users:");
//        users.forEach(x -> System.out.println("    (" +  x.getUserID() + ") " + x.getNickName()));
//        System.out.println("Auctions:");
//        displayAuctions();


//        // Users
//        User bidder1 = new Bidder(-1,"Vlascenco", "Daniel", "vlascenco.daniel@yahoo.com", "0770xxxxxx", "VLCDaniel", "123");
//        User bidder2 = new Bidder(-1,"Bidder2", "Cosmin", "bidder2.cosmin@gmail.com", "0771xxxxxx", "Bidder2Cosmin", "123");
//        User agent1 = new BidderAgent(-1,"Agent", "007", "agent.007@yahoo.com", "0772xxxxxx", "Agent007", "123");
//        User seller1 = new Seller(-1,"Seller1", "Marius", "marius.business@yahoo.com", "0773xxxxxx", "BussMarius", "123");
//        users.add(bidder1); users.add(bidder2); users.add(agent1); users.add(seller1);
//
//        // Auctions
//        Auction auction1 = new Auction(-1,"closed", new Date(120, 0, 29));
//        Auction auction2 = new CharityAuction(-1,"closed", new Date(120, 0, 28), "All money will be raised for planting trees");
//        auction1.addUser(bidder1); auction1.addUser(agent1);
//        auction1.addProduct(new CollectionProduct(-1,"Postage", "Large collection of postages", 400f, false,
//                seller1.getUserID(), "untouched", new Date(87, 4, 31), "my father"));
//        auction2.addUser(bidder1); auction2.addUser(bidder2);
//        auction2.addProduct(new AncientProduct(-1,"Armor", "Very old antique armor", 150000f, true,
//                seller1.getUserID(), "well kept", new Date(12, 2, 8), "Gladiator Spartacus", "Leonardo Dicaprio",
//                "Blacksmith John Fritz", false, "museum"));
//        auctions.add(auction1); auctions.add(auction2);
//        ((Bidder)bidder1).addAuction(auction1); ((Bidder)bidder1).addAuction(auction2);
//        ((Bidder)bidder2).addAuction(auction2);
    }

    // Display the message that the user receives the first time he enters the application
    public void Welcome(){
        System.out.println("############################################################");
        System.out.println("Welcome!");
        System.out.println("In order to sell or start bidding, you have to log in first!");
        System.out.println("Press enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // User Login/Register
        userLogin();
    }

    // User Login
    private void userLogin() {
        Scanner input = new Scanner(System.in);

        String year, month, day;
        Integer Year, Month, Day;
        System.out.println("For testing purposes, you can modify the date to update auctions status");
        while(true){
            System.out.println("Enter year (0 = 1900, 100 = 2000, 121 = 2021, etc):");
            year = input.nextLine();
            try {
                Year = Integer.parseInt(year);
                break;
            }
            catch (Exception e){
                System.out.println("Enter a valid number");
            }
        }

        while(true){
            System.out.println("Enter month of apparition (0 = January, 1 = February, etc):");
            month = input.nextLine();
            try {
                Month = Integer.parseInt(month);
                if(Month <= 11)
                    break;
            }
            catch (Exception e){
                System.out.println("Enter a valid number");
            }
        }

        while(true){
            System.out.println("Enter day of apparition(1-31):");
            day = input.nextLine();
            try {
                Day = Integer.parseInt(day);
                if(Month <= 31)
                    break;
            }
            catch (Exception e){
                System.out.println("Enter a valid number");
            }
        }

        Date d = new Date(Year, Month, Day);
        System.out.println("\nDate you entered:\n   " + d);
        updateAuctions(d);

        System.out.println("\nLogin/Register");
        String nickName;
        boolean isUser, validPassword;

        int i;
        for (i = 5; i > 0; i--) { // 5 attempts to login
            String login;
            while(true){
                System.out.println("Login? (yes/no)");
                login = input.nextLine();
                if("yes".equals(login) || "no".equals(login))
                    break;
            }

            // Login
            if ("yes".equals(login)) {
                // Get nickname
                while(true){
                    System.out.println("Nickname: ");
                    nickName = input.nextLine();

                    if(isUser(nickName))
                        break;

                    System.out.println("Nickname: " + nickName + " unfortunately doesn't exist.");
                    System.out.println("Perhaps you want to create a new account? (yes/no)");
                    String newAcc = input.nextLine();
                    if("yes".equals(newAcc)){
                        userRegistration();
                        break;
                    }
                }

                // Get password
                System.out.println("Password: ");
                String password = input.nextLine();

                // Check password
                validPassword = checkPassword(nickName, password);

                // Exists nickname and valid password => logged in
                if (validPassword) {
                    user = getUser(nickName);
                    System.out.println("############################################################");
                    System.out.println("Welcome back, " + user.getLastName() + ' ' + user.getFirstName() + '!');
                    if(user instanceof Bidder)
                        System.out.println("You are logged in as a Bidder");
                    if(user instanceof BidderAgent)
                        System.out.println("You are logged in as a Bidder Agent");
                    if(user instanceof Seller)
                        System.out.println("You are logged in as a Seller");

                    displayOptions();
                    return;
                }
                else // invalid password
                    System.out.println("Password missmatch! You have " + (i - 1) + " tries left.");
            }
            else{
                String register;
                while(true){
                    System.out.println("Register with a new account? (yes/no)");
                    register = input.nextLine();
                    if("yes".equals(register) || "no".equals(register))
                        break;
                }
                if("yes".equals(register)){
                    userRegistration();
                    break;
                }

                System.out.println("You have " + (i - 1) + " tries left.");
            }
        }

        if(i == 0)
            System.out.println("Your 5 attempts expired :(. Try again later (Restart app)!");
    }

    // Helper functions
    private void updateAuctions(Date d){
        for (Auction a : auctions){
            if(d.compareTo(a.getDate()) >= 0)
                a.setStatus("closed");
            else
                a.setStatus("available");
        }
        System.out.println("Auctions updated!");
    }

    private boolean isUser(String nickName) {
        for(int i = 0; i < users.size(); i++)
            if(users.get(i).getNickName().equals(nickName))
                return true;
        return false;
    }

    private boolean checkPassword(String nickName, String password) {
        for(int i = 0; i < users.size(); i++)
            if(users.get(i).getNickName().equals(nickName) && users.get(i).getPassword().equals(password))
                return true;
        return false;
    }

    private User getUser(String nickName){
        for(int i = 0; i < users.size(); i++)
            if(users.get(i).getNickName().equals(nickName))
                return users.get(i);

        // We already know the user exists, so this return is never executer
        return users.get(0);
    }

    // User Registration
    private void userRegistration(){
        Scanner input = new Scanner(System.in);
        String type, lastName, firstName, email, phoneNumber, nickName, password;

        while(true){
            System.out.println("How would you like to use your account:");
            System.out.println("(0) Exit Application");
            System.out.println("(1) Bidding");
            System.out.println("(2) Bidding Agent");
            System.out.println("(3) Selling");
            System.out.println("Type: 0 / 1 / 2 / 3");
            type = input.nextLine();
            if(type.equals("0") || type.equals("1") || type.equals("2") || type.equals("3"))
                break;
        }

        if(type.equals("0"))
            return;

        System.out.println("First Name: ");
        firstName = input.nextLine();
        System.out.println("Last Name: ");
        lastName = input.nextLine();
        System.out.println("Email: ");
        email = input.nextLine();
        System.out.println("Phone Number");
        phoneNumber = input.nextLine();

        while(true){
            System.out.println("Nickname: ");
            nickName = input.nextLine();
            if(!isUser(nickName))
                break;
            System.out.println("Nickname already exists. Please try another one.");
        }

        System.out.println("Password: ");
        password = input.nextLine();

        if(type.equals("1")) {
            System.out.println("You have successfully registered as a Bidder!");
            user = new Bidder(-1, lastName, firstName, email, phoneNumber, nickName, password);
        }
        else if(type.equals("2")){
            System.out.println("You have successfully registered as a BidderAgent!");
            user = new BidderAgent(-1, lastName, firstName, email, phoneNumber, nickName, password);
        }
        else{
            System.out.println("You have successfully registered as a Seller!");
            user = new Seller(-1, lastName, firstName, email, phoneNumber, nickName, password);
        }

        users.add(user);
        System.out.println("Press enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        displayOptions();
    }

    // User services
    private void displayOptions(){
        Scanner input = new Scanner(System.in);
        String option;
        boolean exit = false;
        while(true && !exit){
            if(user instanceof Bidder){ // Options for Bidder
                System.out.println("(0) Exit application");
                System.out.println("(1) Logout");
                System.out.println("(2) List of auctions ordered by date");
                System.out.println("(3) Register to a new auction");
                System.out.println("(4) Place bet");
                System.out.println("(8) Delete Account");
                option = input.nextLine();
                switch(option){
                    case "0": {
                        exit = true;
                        break;
                    }
                    case "1": {
                        logout();
                        break;
                    }
                    case "2": {
                        displayAuctions();
                        break;
                    }
                    case "3":{
                        auctionEntry();
                        break;
                    }
                    case "4":{
                        placeBet();
                        break;
                    }
                    case "8":{
                        deleteAccount();
                        break;
                    }
                    default: {
                        System.out.println("Please enter a number from the above list");
                        break;
                    }
                }
            }

            if(user instanceof Seller){ // Options for seller
                System.out.println("(0) Exit application");
                System.out.println("(1) Logout");
                System.out.println("(2) List of auctions ordered by date");
                System.out.println("(3) Add a new product to your account");
                System.out.println("(4) Display your products");
                System.out.println("(5) Add a new auction");
                System.out.println("(6) Add product to auction");
                System.out.println("(8) Delete Account");
                option = input.nextLine();
                switch(option){
                    case "0": {
                        exit = true;
                        break;
                    }
                    case "1": {
                        logout();
                        break;
                    }
                    case "2": {
                        displayAuctions();
                        break;
                    }
                    case "3":{
                        addProduct();
                        break;
                    }
                    case "4":{
                        displayProducts();
                        break;
                    }
                    case "5":{
                        addAuction();
                        break;
                    }
                    case "6":{
                        addProductToAuction();
                        break;
                    }
                    case "8":{
                        deleteAccount();
                        break;
                    }
                    default: {
                        System.out.println("Please enter a number from the above list");
                        break;
                    }
                }
            }

            if(user instanceof BidderAgent){ // Options for seller
                System.out.println("(0) Exit application");
                System.out.println("(1) Logout");
                System.out.println("(2) List of auctions ordered by date");
                System.out.println("(8) Delete Account");
                option = input.nextLine();
                switch(option){
                    case "0": {
                        exit = true;
                        break;
                    }
                    case "1": {
                        logout();
                        break;
                    }
                    case "2": {
                        displayAuctions();
                        break;
                    }
                    case "8":{
                        deleteAccount();
                        break;
                    }
                    default: {
                        System.out.println("Please enter a number from the above list");
                        break;
                    }
                }
            }
        }
    }

    // Logout
    private void logout(){
        user = null;
        System.out.println("You have successfully logged out. Thank you for using our app!");
        System.out.println("Press enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        userLogin();
    }

    // Helper functions
    private void displayAuctions(){
        for(Auction a : auctions)
            System.out.println(a);

        System.out.println("Press enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void auctionEntry(){
        Scanner input = new Scanner(System.in);
        Auction auction = null;
        boolean validNumber;
        String id;
        int ID;
        System.out.println("############################################################");
        displayAuctions();
        while(true){
            System.out.println("Enter auction id, -1 to exit:");
            id = input.nextLine();
            if(id.equals("-1"))
                return;
            try{
                validNumber = true;
                ID = Integer.parseInt(id);
                auction = getAuctionByID(ID);
            }
            catch (Exception e){
                System.out.println("Enter valid number");
                validNumber = false;
            }
            if(auction != null && auction.getStatus() != "closed")
                if(auction.searchUser(user) == true)
                    System.out.println("You are already registered in this auction!");
                else
                    break;
            else if(validNumber == true && auction != null)
                System.out.println("Auction status is closed and you can't entry!");
            else
                System.out.println("Auction id doesn't exist!");
        }

        auction.addUser(user);
        ((Bidder) user).addAuction(auction);
        System.out.println("Successfully registered to auction!\nPress enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Auction getAuctionByID(int id){
        for (Auction a : auctions)
            if(a.getAuctionID() == id)
                return a;
        return null;
    }

    private void deleteAccount(){
        System.out.println("Your account has been deleted!");
        userLogin();
    }

    private void addProduct(){
        Scanner input = new Scanner(System.in);
        String productName, description, startprice, Insurance, firstOwner, state, year, month, day;
        float startPrice;
        boolean insurance;
        int Year, Month, Day;

        System.out.println("Enter product name:");
        productName = input.nextLine();
        System.out.println("Enter product description:");
        description = input.nextLine();

        while(true){
            System.out.println("Enter product starting price(float):");
            startprice = input.nextLine();
            try {
                startPrice = Float.parseFloat(startprice);
                break;
            }
            catch (Exception e) {
                System.out.println("Enter valid float number");
            }
        }

        while(true){
            System.out.println("Enter insurance: (yes/no)");
            Insurance = input.nextLine();
            if("yes".equals(Insurance)){
                insurance = true;
                break;
            }
            if("no".equals(Insurance)) {
                insurance = false;
                break;
            }
            System.out.println("Please enter yes/no");
        }

        System.out.println("Enter first owner of the product:");
        firstOwner = input.nextLine();
        System.out.println("Enter state of the product:");
        state = input.nextLine();

        while(true){
            System.out.println("Enter year of apparition (0 = 1900, 100 = 2000, 121 = 2021, etc):");
            year = input.nextLine();
            try {
                Year = Integer.parseInt(year);
                break;
            }
            catch (Exception e){
                System.out.println("Enter a valid number");
            }
        }

        while(true){
            System.out.println("Enter month of apparition (0 = January, 1 = February, etc):");
            month = input.nextLine();
            try {
                Month = Integer.parseInt(month);
                if(Month <= 11)
                    break;
            }
            catch (Exception e){
                System.out.println("Enter a valid number");
            }
        }

        while(true){
            System.out.println("Enter day of apparition(1-31):");
            day = input.nextLine();
            try {
                Day = Integer.parseInt(day);
                if(Month <= 31)
                    break;
            }
            catch (Exception e){
                System.out.println("Enter a valid number");
            }
        }

        String type;
        while(true){
            System.out.println("Enter product type: 0 - Collection, 1 - Ancient");
            type = input.nextLine();
            if(type.equals("1"))
                break;
            if(type.equals("0"))
                break;
        }

        if(type.equals("0")){
            CollectionProduct p = new CollectionProduct(-1, productName, description, startPrice, insurance, user.getUserID(), state,
                    new Date(Year, Month, Day), firstOwner);
            products.add(p);
            ((Seller)user).addProduct(p);

            System.out.println("Product added to your products collection.\nPress enter to continue...");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(type.equals("1")){
            String previousOwner, creator, reasembled, storagePlace;
            boolean Reasembled;

            System.out.println("Previous Owner:");
            previousOwner = input.nextLine();
            System.out.println("Creator:");
            creator = input.nextLine();

            while(true){
                System.out.println("Enter if the product was reasembled (yes/no)");
                reasembled = input.nextLine();
                if("yes".equals(reasembled)){
                    Reasembled = true;
                    break;
                }
                if("no".equals(reasembled)){
                    Reasembled = false;
                    break;
                }
                System.out.println("Please enter yes/no");
            }

            System.out.println("Enter the storage place of the product:");
            storagePlace = input.nextLine();

            AncientProduct p = new AncientProduct(-1, productName, description, startPrice, insurance, user.getUserID(), state,
                    new Date(Year, Month, Day), firstOwner, previousOwner, creator, Reasembled, storagePlace);
            products.add(p);
            ((Seller)user).addProduct(p);

            System.out.println("Product added to your products collection.\nPress enter to continue...");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void displayProducts(){
        System.out.println("############################################################");
        ((Seller) user).displayProducts();
        System.out.println("Press enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addAuction(){
        Scanner input = new Scanner(System.in);
        System.out.println("############################################################");
        String day, month, year;
        int Day, Month, Year;

        System.out.println("Enter auction ending date:");

        while(true){
            System.out.println("Enter year of apparition (0 = 1900, 100 = 2000, 121 = 2021, etc):");
            year = input.nextLine();
            try {
                Year = Integer.parseInt(year);
                break;
            }
            catch (Exception e){
                System.out.println("Enter a valid number");
            }
        }

        while(true){
            System.out.println("Enter month of apparition (0 = January, 1 = February, etc):");
            month = input.nextLine();
            try {
                Month = Integer.parseInt(month);
                if(Month <= 11)
                    break;
            }
            catch (Exception e){
                System.out.println("Enter a valid number");
            }
        }

        while(true){
            System.out.println("Enter day of apparition(1-31):");
            day = input.nextLine();
            try {
                Day = Integer.parseInt(day);
                if(Month <= 31)
                    break;
            }
            catch (Exception e){
                System.out.println("Enter a valid number");
            }
        }

        String type;
        while(true){
            System.out.println("Enter auction type: 0 - Normal, 1 - Charity");
            type = input.nextLine();
            if(type.equals("1"))
                break;
            if(type.equals("0"))
                break;
        }

        if(type.equals("0"))
            auctions.add(new Auction(-1,"available", new Date(Year, Month, Day)));
        else{
            String description;
            System.out.println("Enter charity description");
            description = input.nextLine();
            auctions.add(new CharityAuction(-1,"available", new Date(Year, Month, Day), description));
        }

        System.out.println("Auction added!\nPress enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addProductToAuction(){
        Scanner input = new Scanner(System.in);
        Auction auction = null;
        boolean validNumber;
        String id;
        int ID;
        System.out.println("############################################################");
        displayAuctions();
        while(true){
            System.out.println("Enter auction id, -1 to exit:");
            id = input.nextLine();
            if(id.equals("-1"))
                return;
            try{
                validNumber = true;
                ID = Integer.parseInt(id);
                auction = getAuctionByID(ID);
            }
            catch (Exception e){
                System.out.println("Enter valid number");
                validNumber = false;
            }
            if(auction != null && auction.getStatus() != "closed")
                break;
            if(validNumber == true)
                if(auction != null)
                    System.out.println("Auction status is closed and you can't entry!");
                else
                    System.out.println("Auction id does not exist!");
        }

        System.out.println("You have selected the following auction:");
        System.out.println(auction.toString());
        System.out.println("We'll display your products now.\nPress enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String prodID;
        int productID = 0;
        Product product = null;
        displayProducts();
        while(true){
            while(true){
                System.out.println("Select a valid product id from your products or enter -1 to exit:");
                prodID = input.nextLine();
                if("-1".equals(prodID))
                    return;
                try{
                    productID = Integer.parseInt(prodID);
                    validNumber = true;
                }
                catch (Exception e){
                    System.out.println("Enter valid number");
                    validNumber = false;
                }
                if(validNumber == true){
                    product = ((Seller)user).getProductByID(productID);
                    if(product != null)
                        break;
                    System.out.println("Product id does not exist!");
                }
            }

            if(product.getBuyerID() == -1)
                break;
            System.out.println("This product has been sold, try another one, or enter -1 to exit");
            prodID = input.nextLine();
            if("-1".equals(prodID))
                return;
        }

        auction.addProduct(product);
        System.out.println("Product added to your list!\nPress enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void placeBet() {
        Scanner input = new Scanner(System.in);
        Auction auction = null;
        boolean validNumber;
        String id;
        int ID;

        System.out.println("Displaying auctions you have registered in:");
        ((Bidder) user).displayAuctions();
        System.out.println("Press enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            System.out.println("Enter auction id, -1 to exit:");
            id = input.nextLine();
            if (id.equals("-1"))
                return;
            try {
                validNumber = true;
                ID = Integer.parseInt(id);
                auction = getAuctionByID(ID);
            } catch (Exception e) {
                System.out.println("Enter valid number");
                validNumber = false;
            }
            if (auction != null && auction.getStatus() != "closed" && auction.searchUser(user) == true)
                break;
            if (validNumber == true)
                if (auction == null)
                    System.out.println("Auction id does not exist!");
                else if (auction.getStatus() == "closed")
                    System.out.println("Auction status is closed and you can't entry!");
                else
                    System.out.println("You are not registered to this auction!");
        }

        System.out.println("\n Products from selected auction:");
        auction.displayProducts();
        System.out.println("Press enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String prodID;
        int productID = 0;
        Product product = null;
        while (true) {
            System.out.println("Select product you want to bet on, or -1 to exit:");
            prodID = input.nextLine();
            if ("-1".equals(prodID))
                return;
            try {
                productID = Integer.parseInt(prodID);
                validNumber = true;
            } catch (Exception e) {
                System.out.println("Enter valid number");
                validNumber = false;
            }
            if (validNumber == true) {
                product = auction.getProduct(productID);
                if (product != null)
                    break;
                System.out.println("Product id does not exist!");
            }
        }

        float minBet = Math.max(product.getStartPrice(), product.getSoldPrice());
        System.out.println("Min bet for this product is: " + minBet);
        String bet;
        float Bet = 0;
        while(true){
            System.out.println("Enter betting amount(float):");
            bet = input.nextLine();
            try {
                Bet = Float.parseFloat(bet);
                validNumber = true;
            }
            catch (Exception e) {
                System.out.println("Enter valid float number");
                validNumber = false;
            }

            if(validNumber == true)
                if(Bet > minBet)
                    break;
                else
                    System.out.println("Minimum bet is: " + minBet);
        }

        product.setSoldPrice(Bet);
        product.setBuyerID(user.getUserID());
        System.out.println("Congrats! You are the new price leader on this product!\nPress enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

