package com.mrcrayfish.backpacked.client.renderer.backpack.value.source;

import com.mojang.serialization.MapCodec;
import com.mrcrayfish.backpacked.client.renderer.backpack.BackpackRenderContext;
import net.minecraft.resources.ResourceLocation;

/**
 * Author: MrCrayfish
 */
public class WalkSpeedSource implements BaseSource
{
    public static final Type TYPE = new Type(ResourceLocation.withDefaultNamespace("walk_speed"), MapCodec.unit(new WalkSpeedSource()));

    @Override
    public Type type()
    {
        return TYPE;
    }

    @Override
    public double apply(BackpackRenderContext context)
    {
        return context.entity() != null ? context.entity().walkAnimation.speed(context.partialTick()) : 0;
    }
}
