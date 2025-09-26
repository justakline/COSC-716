public class Main {
    public static void main(String[] args) {
        // Create a room
        Room inn = new Room("A cozy in with a warm fire.");

        // Create a player
        Player player = new Player("Player One", 100);

        // Create an NPC
        NPC innKeeper = new NPC("InnKeeper", 50);

        // Add them to the room
        inn.addCharacter(player);
        inn.addCharacter(innKeeper);

        // Create an item and add it to the room
        Item mug = new Item("Mug of <favorite beverage here>");
        inn.addItem(mug);

        // Example sequence of actions
        // NPC takes a turn
        innKeeper.takeTurn();

        // Player picks up the mug (simulate interact)
        mug.interact();
        player.getInventory().addItem(mug);

        // Player takes a turn
        player.takeTurn();
    }
}