package me.juancarloscp52.entropy.events.db;

import me.juancarloscp52.entropy.Entropy;
import me.juancarloscp52.entropy.Variables;
import me.juancarloscp52.entropy.events.AbstractTimedEvent;
import net.minecraft.client.util.math.MatrixStack;

public class ReducedReachEvent extends AbstractTimedEvent {

    public ReducedReachEvent() {
        this.translationKey="entropy.events.reducedReach";
    }

    @Override
    public void initClient() {
        Variables.reducedReachDistance=true;
    }

    @Override
    public void endClient() {
        Variables.reducedReachDistance=false;
        this.hasEnded=true;

    }

    @Override
    public void render(MatrixStack matrixStack, float tickdelta) {}

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public short getDuration() {
        return Entropy.getInstance().settings.baseEventDuration;
    }
}
