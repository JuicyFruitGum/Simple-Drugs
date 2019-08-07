package me.Coderforlife.Drugs;

import java.util.Arrays;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;



public class Main
  extends JavaPlugin
{
  Logger logger = Logger.getLogger("Minecraft");
 public final ItemStack stack = new ItemStack(Material.WHEAT, 1);
  
  public void onEnable()
  {

	  
	  
    getCommand("drugs").setExecutor(new KillerCommands(this));
    getConfig().options().header("Simple Drugs Config.");
    if(Bukkit.getVersion().contains("1.8.8")){
        getServer().getPluginManager().registerEvents(new OldEvents(this), this);
        System.out.println("I suggest updating to the lastest verison");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "version");

    }else{
        getServer().getPluginManager().registerEvents(new Events(this), this);
        

    }
    
    loadConfiguration();
    Coke();
  }
  
   public void loadConfiguration(){
	   String CokeName = "Drugs.Items.Coke.Name";
	   String WeedName = "Drugs.Items.Weed.Name";
	   String wheat = "Drugs.Toggle.wheat";
	   String suagr = "Drugs.Toggle.sugar";
	   String paper = "Drugs.Toggle.paper";
	   String gunp  = "Drugs.Toggle.gunpowder";
	   String bone  = "Drugs.Toggle.bone";
	   String beet = "Drugs.Toggle.beet";
	   String cactus = "Drugs.Toggle.cactus";
	//   String announce = "Drugs.Toggle.announce";
	   String wart = "Drugs.Toggle.wart";
	   //String nether = "Drugs.Toggle.nether";
	   String effect = "Drugs.Effect.length";
	   String console = "Drugs.Console.logs";
	   String shift = "Drugs.Toggle.shift";
	   /*String remove =  "Drugs.Effect.Clear";
	   String sound = "Drugs.Effect.Sound";*/
	   getConfig().addDefault(wheat, true);
	   getConfig().addDefault(suagr, true);
	   getConfig().addDefault(paper, true);
	   getConfig().addDefault(gunp, true);
	   getConfig().addDefault(bone, true);
	   getConfig().addDefault(beet, true);
	   getConfig().addDefault(cactus, true);
	   getConfig().addDefault(wart, true);
	   getConfig().addDefault(WeedName, "Weed");
	   getConfig().createSection("Drugs.Items.Weed.Effects");
	   getConfig().addDefault("Drugs.Items.Weed.Effects", "SLOW");
	   //getConfig().addDefault(mushrooms, true);
	   getConfig().addDefault(effect, 5220);
	   getConfig().addDefault(console, true);
	 //  getConfig().addDefault(announce, true);
	   getConfig().addDefault(shift, true);
	  /* getConfig().addDefault(remove, true);
	   * getConfig().addDefault(sound, true)*/
       getConfig().options().copyDefaults(true);
       getConfig().addDefault(CokeName, "&4Coke");
	   saveConfig();
   }
  public void onDisable()
  {
  }
  
  public void Coke(){
	  ItemStack coke = new ItemStack(Material.SUGAR, 1);
	  ItemMeta cokemeta = coke.getItemMeta();
	  cokemeta.setDisplayName(getConfig().getString("Drugs.Items.Coke.Name").replaceAll("&", "§"));
	  cokemeta.setLore(Arrays.asList("Luck" 
			                       , "Speed"));
	  coke.setItemMeta(cokemeta);

	  
	  ShapedRecipe CokeR = new ShapedRecipe(coke);
	   
	  CokeR.shape(" a "
			     ," a "
			     ," a ");
	   
	  CokeR.setIngredient('a', Material.SUGAR);
	  Bukkit.getServer().addRecipe(CokeR);
  }
  
  public boolean onCommand(CommandSender sender, Command command, String Commandlabel, String[] args){
	  if(command.getName().equalsIgnoreCase("dreload")){
		  Player p = (Player) sender;
		  if(p.hasPermission("drugs.reload")){
		  reloadConfig();
		  }
	  }
	return true;
	  
  }
}
