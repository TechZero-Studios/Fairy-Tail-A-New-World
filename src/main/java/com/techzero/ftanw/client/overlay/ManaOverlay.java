package com.techzero.ftanw.client.overlay;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.techzero.ftanw.FairyTailMod;
import com.techzero.ftanw.capabilities.magic.MagicData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
//import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = FairyTailMod.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class ManaOverlay {
	
	private static final ResourceLocation MANA_BAR = new ResourceLocation(FairyTailMod.MOD_ID, "textures/gui/mana_bar");
    public static final Minecraft minecraft = Minecraft.getInstance();
    
    private static int currentMana = 0;
    private static int maxMana = 50;
    
    public static void updateManaBar(int newCurrentMana, int newMaxMana) {
        currentMana = newCurrentMana;
        maxMana = newMaxMana;
    }
    
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        mc.player.getCapability(MagicData.MAGIC).ifPresent(magic -> {
            int currentMana = magic.currentMana();
            int maxMana = magic.maxMana();

            drawManaBar(event.getMatrixStack(), currentMana, maxMana, event.getWindow().getGuiScaledWidth(), event.getWindow().getGuiScaledHeight());
        });
    }
    
    public static void drawManaBar(MatrixStack matrixStack, int currentMana, int maxMana, int screenWidth, int screenHeight) {
        Minecraft mc = Minecraft.getInstance();
        mc.getTextureManager().bind(MANA_BAR);
        
        int barWidth = 100;  // Width of the mana bar
        int barHeight = 10;  // Height of the mana bar
        
        // Position of the mana bar on the screen
        int x = 10;
        int y = screenHeight - 40;

        // Calculate how much of the mana bar should be filled
        int filledWidth = (int) ((currentMana / (float) maxMana) * barWidth);

        // Draw the empty mana bar
        AbstractGui.blit(matrixStack, x, y, 0, 0, barWidth, barHeight, 100, 20);
        
        // Draw the filled portion of the mana bar
        if (filledWidth > 0) {
            AbstractGui.blit(matrixStack, x, y, 0, 10, filledWidth, barHeight, 100, 20);  // Adjust these texture coordinates as necessary
        }

        // Optionally, show the current mana value above the bar
        String manaText = currentMana + " / " + maxMana;
        mc.font.draw(matrixStack, manaText, x + 2, y - 10, 0x00FFFF);
    }

}
