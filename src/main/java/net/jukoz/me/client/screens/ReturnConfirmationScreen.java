package net.jukoz.me.client.screens;

import net.jukoz.me.compat.neoforge.api.client.networking.v1.ClientPlayNetworking;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.client.screens.faction_selection.FactionSelectionScreen;
import net.jukoz.me.client.screens.utils.widgets.ModWidget;
import net.jukoz.me.network.packets.C2S.PacketTeleportToCurrentOverworldSpawn;
import net.jukoz.me.network.packets.C2S.PacketTeleportToCurrentSpawn;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import java.awt.event.KeyEvent;

public class ReturnConfirmationScreen extends Screen {
    private static final Component RETURN_CONFIRMATION_TITLE = Component.translatable("ui.me.return_confirmation.title");
    private static final ResourceLocation BUTTON_WIDGET = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID,"textures/gui/widget/button_widget.png");
    public Button returnToOverworldButton;
    public Button closeButton;
    float currentDelay;
    public ReturnConfirmationScreen(float delay) {
        super(RETURN_CONFIRMATION_TITLE);
        currentDelay = delay;
    }

    @Override
    protected void init() {
        Button.OnPress returnToOverworldAction = button -> {
            returnToOverworld();
        };
        returnToOverworldButton = Button.builder(Component.translatable("ui.me.return_confirmation.continue_character.title"), returnToOverworldAction).build();
        addRenderableWidget(returnToOverworldButton);
        if(currentDelay > 0)
            returnToOverworldButton.active = false;
    }

    private void returnToOverworld() {
        ClientPlayNetworking.send(new PacketTeleportToCurrentOverworldSpawn());
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        ModWidget.updateMouse(mouseX, mouseY);
        this.renderBackground(context, mouseX, mouseY, delta);
        this.drawContent(context);
    }

    @Override
    public void tick() {
        if(currentDelay > 0){
            currentDelay = Math.max(0, currentDelay - (1f / 20));
            if(currentDelay == 0) {
                returnToOverworldButton.active = true;
            }
        }
        super.tick();
    }

    private void drawContent(GuiGraphics context) {
        int panelSizeX = 102;
        int panelSizeY = 18;
        int margin = 5;

        // Draw buttons
        int startX = (width / 2) - (panelSizeX / 2);
        int startY = (height / 2) - (panelSizeY / 2);
        if(returnToOverworldButton.active){
            context.blit(BUTTON_WIDGET,
                    startX,
                    startY,
                    0, returnToOverworldButton.isFocused() || isMouseOver(startX, panelSizeX, startY, panelSizeY) ? 19 : 0,
                    panelSizeX,
                    panelSizeY
            );
            Component continueText = Component.translatable("ui.me.return_confirmation.continue_character.content");
            context.drawString(font, continueText,
                    startX + (int)((panelSizeX - font.width(continueText)) / 2f),
                    startY + (int) ((panelSizeY / 2f) - (font.lineHeight / 2f)) + 1,
                    0, false);

            returnToOverworldButton.setRectangle(panelSizeX, panelSizeY, startX, startY);
            if(ModWidget.getFocusEnabled() && returnToOverworldButton.isFocused()){
                context.blit(BUTTON_WIDGET,
                        startX,
                        startY,
                        103, 0,
                        panelSizeX,
                        panelSizeY
                );
            }
        } else {
            context.blit(BUTTON_WIDGET,
                    startX,
                    startY,
                    0, 38,
                    panelSizeX,
                    panelSizeY
            );
            Component delayText = Component.literal(String.valueOf((Math.round(this.currentDelay * 10f) /10f)));
            context.drawString(font, delayText,
                    startX + (panelSizeX / 2) - (font.width(delayText) / 2),
                    startY + 5, 0xc4343e, true);
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        // Keybind : Tabulation
        if(keyCode == KeyEvent.VK_CODE_INPUT && !ModWidget.getFocusEnabled()){
            ModWidget.enableFocus(true);
            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private boolean isMouseOver(int startX, int sizeX, int startY, int sizeY) {
        return ModWidget.isMouseOver(sizeX, sizeY, startX, startY);
    }
}
