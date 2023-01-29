package info.inpureprojects.core.Utils.Events;

import java.util.List;

import cpw.mods.fml.common.eventhandler.Event;

public class EventNEIReady extends Event {

    private List<String> list;

    public List<String> getList() {
        return this.list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
