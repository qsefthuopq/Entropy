package me.juancarloscp52.entropy.events.db;

import me.juancarloscp52.entropy.Entropy;
import me.juancarloscp52.entropy.events.AbstractInstantEvent;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.block.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.RaycastContext;

public class Teleport0Event extends AbstractInstantEvent {

    MinecraftServer server;
    int count = 0;
    public Teleport0Event() {
        this.translationKey="entropy.events.teleport0";
    }

    @Override
    public void init() {
        server = Entropy.getInstance().eventHandler.server;
        PlayerLookup.all(server).forEach(serverPlayerEntity -> {
            server.getCommandManager().execute(server.getCommandSource(),"/spreadplayers 0 0 0 10 false "+serverPlayerEntity.getEntityName());
            serverPlayerEntity.getServerWorld().breakBlock(serverPlayerEntity.getBlockPos(),false);
            serverPlayerEntity.getServerWorld().breakBlock(serverPlayerEntity.getBlockPos().up(),false);
            BlockHitResult blockHitResult = serverPlayerEntity.world.raycast(new RaycastContext(serverPlayerEntity.getPos(), serverPlayerEntity.getPos().subtract(0,-6,0), RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.ANY, serverPlayerEntity));
            if(blockHitResult.getType() == HitResult.Type.MISS || serverPlayerEntity.getServerWorld().getBlockState(blockHitResult.getBlockPos()).getMaterial().isLiquid()){
                serverPlayerEntity.getServerWorld().setBlockState(serverPlayerEntity.getBlockPos().down(), Blocks.STONE.getDefaultState());
            }
        });

    }

    @Override
    public void tick() {
        if(count<=2){
            if(count==2){
                PlayerLookup.all(server).forEach(serverPlayerEntity -> {
                    serverPlayerEntity.getServerWorld().breakBlock(serverPlayerEntity.getBlockPos(),false);
                    serverPlayerEntity.getServerWorld().breakBlock(serverPlayerEntity.getBlockPos().up(),false);
                    BlockHitResult blockHitResult = serverPlayerEntity.world.raycast(new RaycastContext(serverPlayerEntity.getPos(), serverPlayerEntity.getPos().subtract(0,-6,0), RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.ANY, serverPlayerEntity));
                    if(blockHitResult.getType() == HitResult.Type.MISS || serverPlayerEntity.getServerWorld().getBlockState(blockHitResult.getBlockPos()).getMaterial().isLiquid()){
                        serverPlayerEntity.getServerWorld().setBlockState(serverPlayerEntity.getBlockPos().down(), Blocks.STONE.getDefaultState());
                    }
                });
            }
            count++;
        }
        super.tick();
    }

    @Override
    public boolean hasEnded() {
        return count>2;
    }

}
