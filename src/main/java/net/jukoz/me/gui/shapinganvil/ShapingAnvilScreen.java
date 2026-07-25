package net.jukoz.me.gui.shapinganvil;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import net.jukoz.me.compat.neoforge.api.client.networking.v1.ClientPlayNetworking;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.gui.artisantable.ArtisanTableScreenHandler;
import net.jukoz.me.item.ModToolItems;
import net.jukoz.me.network.packets.C2S.AnvilIndexPacket;
import net.jukoz.me.network.packets.C2S.ForgeOutputPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.StateSwitchingButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import java.util.List;

public class ShapingAnvilScreen extends AbstractContainerScreen<ShapingAnvilScreenHandler> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "textures/gui/shaping_anvil.png");
    private static final int PROGRESS_ARROW_SIZE = 24;

    private static final ResourceLocation LEFT_CYCLE_OUTPUT_BUTTON = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "left_cycle_arrow");
    private static final ResourceLocation LEFT_CYCLE_OUTPUT_BUTTON_FOCUSED = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "left_cycle_arrow_focused");
    private static final WidgetSprites LEFT_CYCLE_OUTPUT_BUTTON_TEXTURES = new WidgetSprites(LEFT_CYCLE_OUTPUT_BUTTON, LEFT_CYCLE_OUTPUT_BUTTON_FOCUSED);

    private static final ResourceLocation RIGHT_CYCLE_OUTPUT_BUTTON = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "right_cycle_arrow");
    private static final ResourceLocation RIGHT_CYCLE_OUTPUT_BUTTON_FOCUSED = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "right_cycle_arrow_focused");
    private static final WidgetSprites RIGHT_CYCLE_OUTPUT_BUTTON_TEXTURES = new WidgetSprites(RIGHT_CYCLE_OUTPUT_BUTTON, RIGHT_CYCLE_OUTPUT_BUTTON_FOCUSED);

    public StateSwitchingButton leftOutputCycleButton;
    public StateSwitchingButton rightOutputCycleButton;

    public ShapingAnvilScreen(ShapingAnvilScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleLabelX = (imageWidth - font.width(title)) / 2;

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.leftOutputCycleButton = new StateSwitchingButton(x + 69, y + 19, 7 ,11, true);
        this.leftOutputCycleButton.initTextureValues(LEFT_CYCLE_OUTPUT_BUTTON_TEXTURES);

        this.rightOutputCycleButton = new StateSwitchingButton(x + 100, y + 19, 7,11, true);
        this.rightOutputCycleButton.initTextureValues(RIGHT_CYCLE_OUTPUT_BUTTON_TEXTURES);

        addRenderableWidget(leftOutputCycleButton);
        addRenderableWidget(rightOutputCycleButton);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.leftOutputCycleButton.mouseClicked(mouseX, mouseY, button)) {
            ClientPlayNetworking.send(new AnvilIndexPacket(true, menu.getPos().getX(),menu.getPos().getY(),menu.getPos().getZ()));
        }

        if (this.rightOutputCycleButton.mouseClicked(mouseX, mouseY, button)) {
            ClientPlayNetworking.send(new AnvilIndexPacket(false, menu.getPos().getX(),menu.getPos().getY(),menu.getPos().getZ()));
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void renderBg(GuiGraphics context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        context.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX,mouseY,delta);
        super.render(context, mouseX, mouseY, delta);
        renderTooltip(context, mouseX, mouseY);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        context.renderItem(ModToolItems.SMITHING_HAMMER.getDefaultInstance(), x + 81, y + 34);

        renderHammerTooltip(context, mouseX, mouseY);

        if (this.menu.getOutput().isEmpty()){
            context.blit(TEXTURE, x + 79, y + 15, 177, 115,18, 18);
        } else {
            context.renderItem(this.menu.getOutput(), x + 80, y + 16);
            renderOutputTooltip(context, mouseX, mouseY);
        }
    }

    private void renderOutputTooltip(GuiGraphics context, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        if (mouseX >= x + 79 && mouseX <= x + 96 && mouseY >= y + 16 && mouseY <= y + 33){
            context.renderTooltip(this.minecraft.font, menu.getOutput().getItem().getDescription(), mouseX, mouseY);
        }
    }

    private void renderHammerTooltip(GuiGraphics context, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        if (mouseX >= x + 79 && mouseX <= x + 96 && mouseY >= y + 34 && mouseY <= y + 51){
            context.renderTooltip(this.minecraft.font,
                    Lists.transform(List.of(Component.translatable("tooltip." + MiddleEarth.MOD_ID +".anvil_hammer"),
                            Component.translatable("tooltip." + MiddleEarth.MOD_ID +".anvil_hammer_2")),
                            Component::getVisualOrderText), mouseX, mouseY);
        }
    }
}
