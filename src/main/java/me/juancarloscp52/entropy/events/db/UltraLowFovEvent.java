package me.juancarloscp52.entropy.events.db;

import me.juancarloscp52.entropy.Entropy;
import me.juancarloscp52.entropy.Variables;
import me.juancarloscp52.entropy.events.AbstractTimedEvent;
import net.minecraft.client.util.math.MatrixStack;

public class UltraLowFovEvent extends AbstractTimedEvent {

    public UltraLowFovEvent() {
        this.translationKey="entropy.events.ultraLowFov";
    }

    @Override
    public void initClient() {
        Variables.forcedFov=true;
        Variables.ignoreVariableFov =true;
        Variables.fov=10;
    }

    @Override
    public void endClient() {
        Variables.forcedFov=false;
        Variables.ignoreVariableFov =false;
        Variables.fov=0;
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

    @Override
    public String type() {
        return "fov";
    }
}
