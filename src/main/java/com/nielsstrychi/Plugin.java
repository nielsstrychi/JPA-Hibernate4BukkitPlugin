package com.nielsstrychi;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

    /**
     * Example how to setup JPA + Hibernate for Minecraft plugin
     *
     * @author Niels Strychi
     */

    private final static Database database = new Database();

    @Override
    public void onEnable() {
        super.onEnable();
        PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
    }

    @Override
    public void onLoad() {
        super.onLoad();

        /* Important class loading */
        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());

        /* Testing write and read. */
        database.write();
        database.read();

    }
}
