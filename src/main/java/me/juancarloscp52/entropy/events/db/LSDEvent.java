package me.juancarloscp52.entropy.events.db;

import me.juancarloscp52.entropy.Entropy;
import me.juancarloscp52.entropy.Variables;
import me.juancarloscp52.entropy.events.AbstractTimedEvent;
import net.minecraft.client.util.math.MatrixStack;

public class LSDEvent extends AbstractTimedEvent {


    public LSDEvent() {
        this.translationKey="entropy.events.LSD";
    }

    @Override
    public void initClient() {
        Variables.wobble=true;
    }

    @Override
    public void endClient() {
        Variables.wobble=false;
        this.hasEnded=true;
    }

    @Override
    public void render(MatrixStack matrixStack, float tickdelta) {}

    @Override
    public String type() {
        return "shader";
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public short getDuration() {
        return Entropy.getInstance().settings.baseEventDuration;
    }
}
