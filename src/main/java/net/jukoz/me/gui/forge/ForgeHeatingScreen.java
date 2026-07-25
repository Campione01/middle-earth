package net.jukoz.me.gui.forge;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.special.forge.ForgeBlockEntity;
import net.jukoz.me.block.special.forge.MetalTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import java.util.List;

public class ForgeHeatingScreen extends AbstractContainerScreen<ForgeHeatingScreenHandler> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "textures/gui/forge_heating.png");

    private static final int PROGRESS_FIRE_SIZE = 21;
    private static final int COOKING_FIRE_SIZE = 14;
    private static final int LIQUID_HEIGHT = 60;
    private Boolean heatingMode = null;

    public ForgeHeatingScreen(ForgeHeatingScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleLabelX = (imageWidth - font.width(title)) / 2;
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        if(heatingMode == null)
            heatingMode = menu.heatingMode();
        else if(menu.heatingMode() != heatingMode) {
            heatingMode = menu.heatingMode();
            this.onClose();
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void renderBg(GuiGraphics context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        RenderSystem.setShaderTexture(0, TEXTURE);
        context.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        renderProgressArrow(context, x, y);
        renderLiquidStorage(context, x, y);
    }

    private void renderProgressArrow(GuiGraphics context, int x, int y) {
        if(menu.isCooking()) {
            int cookingTime = (int) (menu.getScaledCooking() * COOKING_FIRE_SIZE);
            context.blit(TEXTURE, x + 80, y + 50 - cookingTime, 176, COOKING_FIRE_SIZE - cookingTime, COOKING_FIRE_SIZE, cookingTime);
        }
        if(menu.isCrafting()) {
            int progress = (int) (menu.getScaledProgress() * PROGRESS_FIRE_SIZE);
            context.blit(TEXTURE, x + 13, y + 34 - progress, 177, 53 - progress, 13, progress);
        }
    }

    private void renderLiquidStorage(GuiGraphics context, int x, int y) {
        int storedLiquid = (int) (menu.getScaledStoredLiquid() * LIQUID_HEIGHT);
        context.blit(TEXTURE, x + 147, y + 71 - storedLiquid, 177, 114 - storedLiquid, 16, storedLiquid);
    }

    private void renderLiquidStorageTooltip(GuiGraphics context, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        if (mouseX >= x + 147 && mouseX <= x + 162 && mouseY >= y + 12 && mouseY <= y + 71){
            MetalTypes metal = MetalTypes.getValue(menu.getCurrentMetal());
            if(metal != MetalTypes.EMPTY){
                context.renderTooltip(this.minecraft.font, Lists.transform(
                        List.of(Component.translatable("tooltip." + MiddleEarth.MOD_ID +".liquid_" + metal.getSerializedName().toLowerCase()).withColor(metal.getColor()),
                                Component.literal(menu.getStoredLiquid() / 144  + " ").append(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".ingots_number")),
                                Component.literal(menu.getStoredLiquid() % 144 / 16  + " ").append(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".nuggets_number"))
                        ), Component::getVisualOrderText), mouseX, mouseY);
            }
        }
    }

    private void renderModeTooltip(GuiGraphics context, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        if (mouseX >= x + 13 && mouseX <= x + 27 && mouseY >= y + 57 && mouseY <= y + 71){
            context.renderTooltip(this.minecraft.font, Lists.transform(
                    List.of(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".forge_mode_heating"),
                    Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".forge_mode_heating_switch").withStyle(ChatFormatting.GOLD),
                            Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".forge_mode_heating_switch_2").withStyle(ChatFormatting.GOLD)
                    ), Component::getVisualOrderText), mouseX, mouseY);
        }
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX,mouseY,delta);
        super.render(context, mouseX, mouseY, delta);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        renderTooltip(context, mouseX, mouseY);
        renderLiquidStorageTooltip(context, mouseX, mouseY);
        renderModeTooltip(context, mouseX, mouseY);
    }
}
