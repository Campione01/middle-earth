package net.jukoz.me.client.screens;

import net.jukoz.me.compat.neoforge.dist.EnvType;
import net.jukoz.me.compat.neoforge.dist.Environment;
import net.jukoz.me.compat.neoforge.api.client.networking.v1.ClientPlayNetworking;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.client.screens.faction_selection.FactionSelectionScreen;
import net.jukoz.me.network.packets.C2S.PacketTeleportToCurrentSpawn;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import java.awt.event.KeyEvent;

@Environment(EnvType.CLIENT)
public class OnboardingSelectionScreen extends Screen {
    private static final Component ONBOARDING_SELECTION_TITLE = Component.translatable("ui.me.onboarding_selection.title");
    private static final ResourceLocation BUTTON_WIDGET = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID,"textures/gui/widget/button_widget.png");
    private boolean focusEnabled;
    public Button continueAsCharacterButton;
    public Button resetCharacterButton;

    private int mouseX;
    private int mouseY;
    private boolean canResetCharacter;
    private LocalPlayer player;
    float currentDelay;
    public OnboardingSelectionScreen(float delay, boolean canResetCharacter) {
        super(ONBOARDING_SELECTION_TITLE);
        this.canResetCharacter = canResetCharacter;
        focusEnabled = false;
        currentDelay = delay;
    }

    @Override
    protected void init() {
        Button.OnPress continueAsFaction = button -> {
            teleportPlayerToMiddleEarth();
        };
        continueAsCharacterButton = Button.builder(Component.nullToEmpty("continue_character"), continueAsFaction).build();
        addRenderableWidget(continueAsCharacterButton);
        if(currentDelay > 0)
            continueAsCharacterButton.active = false;

        if(canResetCharacter){
            Button.OnPress resetCharacterAction = button -> {
                Minecraft mc = Minecraft.getInstance();
                mc.setScreen(new FactionSelectionScreen(Math.max(0, currentDelay)));
            };
            resetCharacterButton = Button.builder(Component.nullToEmpty("reset_character"), resetCharacterAction).build();
            addRenderableWidget(resetCharacterButton);
        }
    }

    private void teleportPlayerToMiddleEarth() {
        ClientPlayNetworking.send(new PacketTeleportToCurrentSpawn(false));
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        Entity cameraEntity = this.minecraft.getCameraEntity();
        if (cameraEntity != null) {
            if (cameraEntity instanceof LocalPlayer clientPlayerEntity) {
                this.player = clientPlayerEntity;
                this.mouseX = mouseX;
                this.mouseY = mouseY;
                this.renderBackground(context, mouseX, mouseY, delta);
                this.drawContent(context);
            } else {
                this.player = null;
            }
        }
    }

    @Override
    public void tick() {
        if(currentDelay > 0){
            currentDelay = Math.max(0, currentDelay - (1f / 20));
            if(currentDelay == 0) {
                continueAsCharacterButton.active = true;
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
        if(continueAsCharacterButton.active){
            context.blit(BUTTON_WIDGET,
                    startX,
                    startY,
                    0, continueAsCharacterButton.isFocused() || isMouseOver(startX, panelSizeX, startY, panelSizeY) ? 19 : 0,
                    panelSizeX,
                    panelSizeY
            );
            Component continueText = Component.translatable("ui.me.continue_character");
            context.drawString(font, continueText,
                    startX + (int)((panelSizeX - font.width(continueText)) / 2f),
                    startY + (int) ((panelSizeY / 2f) - (font.lineHeight / 2f)) + 1,
                    0, false);

            continueAsCharacterButton.setRectangle(panelSizeX, panelSizeY, startX, startY);
            if(focusEnabled && continueAsCharacterButton.isFocused()){
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

        if(canResetCharacter) {

            startY += panelSizeY + margin;

            context.blit(BUTTON_WIDGET,
                    startX,
                    startY,
                    0, resetCharacterButton.isFocused() || isMouseOver(startX, panelSizeX, startY, panelSizeY) ? 19 : 0,
                    panelSizeX,
                    panelSizeY
            );
            Component resetText = Component.translatable("ui.me.reset_character");
            context.drawString(font, resetText,
                    startX + (int) ((panelSizeX - font.width(resetText)) / 2f),
                    startY + (int) ((panelSizeY / 2f) - (font.lineHeight / 2f)) + 1,
                    0, false);
            resetCharacterButton.setRectangle(panelSizeX, panelSizeY, startX, startY);
            if(focusEnabled && resetCharacterButton.isFocused()){
                context.blit(BUTTON_WIDGET,
                        startX,
                        startY,
                        103, 0,
                        panelSizeX,
                        panelSizeY
                );
            }
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        // Keybind : Tabulation
        if(keyCode == KeyEvent.VK_CODE_INPUT && !focusEnabled){
            focusEnabled = true;
            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private boolean isMouseOver(int startX, int sizeX, int startY, int sizeY) {
        return mouseX >= startX && mouseX <= startX + sizeX
                && mouseY >= startY && mouseY <= startY + sizeY;
    }
}
