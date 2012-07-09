package net.citizensnpcs.trait;

import net.citizensnpcs.api.exception.NPCLoadException;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.api.util.DataKey;

import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

public class VillagerProfession extends Trait {
    private final NPC npc;
    private Profession profession = Profession.FARMER;

    public VillagerProfession(NPC npc) {
        this.npc = npc;
    }

    @Override
    public void load(DataKey key) throws NPCLoadException {
        try {
            profession = Profession.valueOf(key.getString(""));
        } catch (IllegalArgumentException ex) {
            throw new NPCLoadException("Invalid profession.");
        }
    }

    @Override
    public void onNPCSpawn() {
        if (npc.getBukkitEntity() instanceof Villager)
            ((Villager) npc.getBukkitEntity()).setProfession(profession);
    }

    @Override
    public void save(DataKey key) {
        key.setString("", profession.name());
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
        if (npc.getBukkitEntity() instanceof Villager)
            ((Villager) npc.getBukkitEntity()).setProfession(profession);
    }

    @Override
    public String toString() {
        return "Profession{" + profession + "}";
    }
}