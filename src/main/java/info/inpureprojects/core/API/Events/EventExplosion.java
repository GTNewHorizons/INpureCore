package info.inpureprojects.core.API.Events;

import cpw.mods.fml.common.eventhandler.Event;


public class EventExplosion
    extends Event {
    private float chance;

    public EventExplosion(float chance) {
        this.chance = chance;
    }

    public float getChance() {
        return this.chance;
    }

    public void setChance(float chance) {
        this.chance = chance;
    }


    public boolean isCancelable() {
        return true;
    }
}
