package dev.azarattum.spigot.shulkerrespawnplugin;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.StructureType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

/**
 * Interceptor for enderman spawns
 */
public class SpawnInterceptor implements Listener {
    /**
     * Shulker spawnable material list
     */
    static private Material[] SpawnableBlocks = new Material[] { Material.END_STONE_BRICKS, Material.PURPUR_BLOCK,
            Material.PURPUR_SLAB, Material.PURPUR_PILLAR, Material.PURPUR_STAIRS };

    private Random RNG = new Random();

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (event.getEntityType() != EntityType.ENDERMAN)
            return;
        if (event.getSpawnReason() != SpawnReason.NATURAL)
            return;

        LivingEntity enderman = event.getEntity();
        Location location = enderman.getLocation();
        Location structure = enderman.getWorld().locateNearestStructure(location, StructureType.END_CITY, 0, false);

        if (structure == null)
            return;

        if ((Math.abs(structure.getChunk().getX() - location.getChunk().getX()) > 1))
            return;

        if ((Math.abs(structure.getChunk().getZ() - location.getChunk().getZ()) > 1))
            return;

        Material block = location.add(0, -1, 0).getBlock().getType();

        if (!Arrays.asList(SpawnableBlocks).contains(block))
            return;

        if (RNG.nextInt(10) >= 7)
            return;

        Entity shulker = enderman.getWorld().spawnEntity(enderman.getLocation(), EntityType.SHULKER);
        if (shulker != null)
            event.setCancelled(true);
    }
}