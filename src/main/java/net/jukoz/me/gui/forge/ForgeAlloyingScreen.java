package net.jukoz.me.gui.forge;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import net.jukoz.me.compat.neoforge.api.client.networking.v1.ClientPlayNetworking;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.special.forge.MetalTypes;
import net.jukoz.me.item.ModResourceItems;
import net.jukoz.me.network.packets.C2S.ForgeOutputPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.StateSwitchingButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import java.util.List;

public class ForgeAlloyingScreen extends AbstractContainerScreen<ForgeAlloyingScreenHandler> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "textures/gui/forge.png");
    private static final ResourceLocation EXTRACT_BUTTON = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "extract");
    private static final ResourceLocation EXTRACT_BUTTON_FOCUSED = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "extract_focused");
    private static final WidgetSprites EXTRACT_BUTTON_TEXTURES = new WidgetSprites(EXTRACT_BUTTON, EXTRACT_BUTTON_FOCUSED);

    private static final ResourceLocation LEFT_CYCLE_EXTRACT_BUTTON = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "left_cycle_arrow");
    private static final ResourceLocation LEFT_CYCLE_EXTRACT_BUTTON_FOCUSED = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "left_cycle_arrow_focused");
    private static final WidgetSprites LEFT_CYCLE_EXTRACT_BUTTON_TEXTURES = new WidgetSprites(LEFT_CYCLE_EXTRACT_BUTTON, LEFT_CYCLE_EXTRACT_BUTTON_FOCUSED);

    private static final ResourceLocation RIGHT_CYCLE_EXTRACT_BUTTON = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "right_cycle_arrow");
    private static final ResourceLocation RIGHT_CYCLE_EXTRACT_BUTTON_FOCUSED = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "right_cycle_arrow_focused");
    private static final WidgetSprites RIGHT_CYCLE_EXTRACT_BUTTON_TEXTURES = new WidgetSprites(RIGHT_CYCLE_EXTRACT_BUTTON, RIGHT_CYCLE_EXTRACT_BUTTON_FOCUSED);

    private static final int PROGRESS_ARROW_SIZE = 24;
    private static final int COOKING_FIRE_SIZE = 14;
    private static final int LIQUID_HEIGHT = 60;

    public ImageButton extractButton;
    public StateSwitchingButton leftExtractCycleButton;
    public StateSwitchingButton rightExtractCycleButton;

    private int outputMode = 0;
    private Boolean heatingMode = null;

    public ForgeAlloyingScreen(ForgeAlloyingScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleLabelX = (imageWidth - font.width(title)) / 2;
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.leftExtractCycleButton = new StateSwitchingButton(x + 132, y + 56, 7 ,11, true);
        this.leftExtractCycleButton.initTextureValues(LEFT_CYCLE_EXTRACT_BUTTON_TEXTURES);
        this.leftExtractCycleButton.visible = false;

        this.extractButton = new ImageButton(x + 141, y + 52, 20 ,20, EXTRACT_BUTTON_TEXTURES, (button)-> {
            int amount = 0;
            switch (outputMode){
                case 1 -> amount = 16;
                case 2 -> amount = 144;
                case 3 -> amount = 288;
                case 4 -> amount = 432;
            }

            ClientPlayNetworking.send(new ForgeOutputPacket(amount, menu.getPos().getX(),menu.getPos().getY(),menu.getPos().getZ()));
            }, Component.translatable("button." + MiddleEarth.MOD_ID + ".extract_metal"));

        this.extractButton.setTooltip(Tooltip.create(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".forge_output_mode" + this.outputMode)));

        this.rightExtractCycleButton = new StateSwitchingButton(x + 163, y + 56, 7,11, true);
        this.rightExtractCycleButton.initTextureValues(RIGHT_CYCLE_EXTRACT_BUTTON_TEXTURES);
        this.rightExtractCycleButton.visible = false;

        addRenderableWidget(leftExtractCycleButton);
        addRenderableWidget(extractButton);
        addRenderableWidget(rightExtractCycleButton);
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

        this.leftExtractCycleButton.visible = true;
        this.rightExtractCycleButton.visible = true;
        this.extractButton.visible = true;

        if(menu.checkMaxOutput() == 4 && outputMode >= 4){
            outputMode = 4;
        }
        if(menu.checkMaxOutput() == 3 && outputMode >= 3){
            outputMode = 3;
        }
        if(menu.checkMaxOutput() == 2 && outputMode >= 2){
            outputMode = 2;
        }
        if(menu.checkMaxOutput() == 1 && outputMode >= 1){
            outputMode = 1;
        }
        if(menu.checkMaxOutput() == 0 && outputMode >= 1){
            outputMode = 0;
        }
        if(menu.checkMaxOutput() >= 1 && outputMode ==0){
            outputMode = 1;
        }
        if(menu.checkMaxOutput() <= 1){
            this.leftExtractCycleButton.visible = false;
            this.rightExtractCycleButton.visible = false;
        } else {
            this.leftExtractCycleButton.visible = true;
            this.rightExtractCycleButton.visible = true;
        }
        this.extractButton.setTooltip(Tooltip.create(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".forge_output_mode" + this.outputMode)));

    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.leftExtractCycleButton.mouseClicked(mouseX, mouseY, button)) {
            if(outputMode == 1){
                outputMode = menu.checkMaxOutput();
            } else if(outputMode > 1){
                outputMode--;
            }

            this.extractButton.setTooltip(Tooltip.create(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".forge_output_mode" + this.outputMode)));
            return true;
        }

        if (this.rightExtractCycleButton.mouseClicked(mouseX, mouseY, button)) {
            if(outputMode == menu.checkMaxOutput()){
                outputMode = 1;
            } else if(outputMode < 4){
                outputMode++;
            }

            this.extractButton.setTooltip(Tooltip.create(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".forge_output_mode" + this.outputMode)));
            return true;
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

        RenderSystem.setShaderTexture(0, TEXTURE);
        context.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        renderProgressArrow(context, x, y);
        renderLiquidStorage(context, x, y);
    }

    private void renderProgressArrow(GuiGraphics context, int x, int y) {
        if(menu.isCooking()) {
            int cookingTime = (int) (menu.getScaledCooking() * COOKING_FIRE_SIZE);
            context.blit(TEXTURE, x + 42, y + 50 - cookingTime, 176, COOKING_FIRE_SIZE - cookingTime, COOKING_FIRE_SIZE, cookingTime);
        }
        if(menu.isCrafting()) {
            context.blit(TEXTURE, x + 87, y + 15, 176, 14, (int) (menu.getScaledProgress() * PROGRESS_ARROW_SIZE), 17);
        }
    }

    private void renderLiquidStorage(GuiGraphics context, int x, int y) {
        int storedLiquid = (int) (menu.getScaledStoredLiquid() * LIQUID_HEIGHT);
        context.blit(TEXTURE, x + 113, y + 71 - storedLiquid, 177, 114 - storedLiquid, 16, storedLiquid);
    }

    private void renderLiquidStorageTooltip(GuiGraphics context, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        if (mouseX >= x + 113 && mouseX <= x + 128 && mouseY >= y + 12 && mouseY <= y + 71){
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
            context.renderTooltip(this.minecraft.font, Component.translatable("tooltip." + MiddleEarth.MOD_ID +".forge_mode_alloying"), mouseX, mouseY);
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

        ItemStack itemstack;
        switch (outputMode){
            case 0:
                context.blit(TEXTURE, x + 140, y + 51, 177, 115,22, 22);
                break;
            case 1:
                itemstack = new ItemStack(Items.IRON_NUGGET);
                context.renderItem(itemstack, x + 143, y + 54);
                break;
            case 2:
                itemstack = new ItemStack(Items.IRON_INGOT);

                context.renderItem(itemstack, x + 143, y + 54);
                break;
            case 3:
                itemstack = new ItemStack(ModResourceItems.ROD);
                context.renderItem(itemstack, x + 143, y + 54);
                break;
            case 4:
                itemstack = new ItemStack(ModResourceItems.LARGE_ROD);
                context.renderItem(itemstack, x + 143, y + 54);
                break;
        }
    }
}
