package com.mrcrayfish.backpacked.client;

import com.mrcrayfish.backpacked.client.gui.screen.inventory.BackpackScreen;
import com.mrcrayfish.backpacked.client.renderer.entity.layers.BackpackLayer;
import com.mrcrayfish.backpacked.client.renderer.entity.layers.ShelfRenderer;
import com.mrcrayfish.backpacked.client.renderer.entity.layers.VillagerBackpackLayer;
import com.mrcrayfish.backpacked.common.backpack.loader.FabricModelMetaLoader;
import com.mrcrayfish.backpacked.core.ModBlockEntities;
import com.mrcrayfish.backpacked.core.ModContainers;
import com.mrcrayfish.backpacked.core.ModItems;
import io.wispforest.accessories.api.client.AccessoriesRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.WanderingTraderRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.server.packs.PackType;

/**
 * Author: MrCrayfish
 */
public class ClientHandler implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        ClientBootstrap.earlyInit();
        ClientBootstrap.init();
        ModelLoadingPlugin.register(new BackpackedModelLoadingPlugin());
        MenuScreens.register(ModContainers.BACKPACK.get(), BackpackScreen::new);
        BlockEntityRenderers.register(ModBlockEntities.SHELF.get(), ShelfRenderer::new);
        AccessoriesRendererRegistry.registerNoRenderer(ModItems.BACKPACK.get());

        // Add backpack layers for player and wandering trader
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if(entityRenderer instanceof WanderingTraderRenderer renderer) {
                registrationHelper.register(new VillagerBackpackLayer<>(renderer, context.getItemRenderer()));
            } else if(entityRenderer instanceof PlayerRenderer renderer) {
                registrationHelper.register(new BackpackLayer<>(renderer, context.getItemRenderer()));
            }
        });

        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(new FabricModelMetaLoader());
    }
}
