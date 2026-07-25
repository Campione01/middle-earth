package net.jukoz.me.client.screens.utils.widgets;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.entity.ModEntities;
import net.jukoz.me.entity.humans.bandit.BanditHumanEntity;
import net.jukoz.me.item.items.weapons.ReachWeaponItem;
import net.jukoz.me.resources.datas.npcs.NpcUtil;
import net.jukoz.me.resources.datas.races.Race;
import net.jukoz.me.resources.datas.npcs.data.NpcGearData;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import com.mojang.blaze3d.platform.Lighting;
import java.util.ArrayList;
import java.util.List;

public class PlayableNpcPreviewWidget extends ModWidget{
    private static final ResourceLocation NPC_PREVIEW = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID,"textures/gui/widget/npc_preview_widget.png");

    private static final int MINIMAL_MARGIN = 4;
    private static final float DEFAULT_ANGLE = 145f; // 210f;
    private static final float SMOOTH_THRESHOLD = 15000;
    private static final float SMOOTH_SPEED_MODIFIER = 2.5f ;
    private static final float STEP_SPEED = 45;

    private LivingEntity entity;
    private static final Quaternionf ENTITY_ROTATION;
    private static final Vector3f VECTOR;
    private float currentAngle = DEFAULT_ANGLE ; // 210f;
    private final Button leftButton;
    private final Button rightButton;
    private final Button resetButton;

    private Button currentButtonClicked;
    private boolean isLeftButton = false;
    private float tickHoldingStart = 0;
    private boolean isEnterKeyPressed = false;

    public boolean haveBeenInitialized;

    public PlayableNpcPreviewWidget(){
        Button.OnPress leftButtonAction = button -> {
            addAngle();
            setCurrentButton(true);
        };
        haveBeenInitialized = false;

        Button.OnPress resetButtonAction = button -> {
            currentAngle = DEFAULT_ANGLE;
        };

        Button.OnPress rightButtonAction = button -> {
            reduceAngle();
            setCurrentButton(false);
        };

        leftButton = Button.builder(Component.nullToEmpty("left_button"), leftButtonAction).build();
        leftButton.setSize(14, 9);

        resetButton = Button.builder(Component.nullToEmpty("reset_button"), resetButtonAction).build();
        resetButton.setSize(6,6);

        rightButton = Button.builder(Component.nullToEmpty("right_button"), rightButtonAction).build();
        rightButton.setSize(14, 9);
    }

    private void addAngle(){
        if(canRotateSmoothly()){
            currentAngle += 1 * SMOOTH_SPEED_MODIFIER;
        } else {
            currentAngle += STEP_SPEED;
        }
    }

    private void reduceAngle(){
        if(canRotateSmoothly()){
            currentAngle -= 1 * SMOOTH_SPEED_MODIFIER;
        } else {
            currentAngle -= STEP_SPEED;
        }
    }

    public List<Button> getButtons(){
        ArrayList<Button> listOfButtons = new ArrayList<>();
        listOfButtons.add(leftButton);
        listOfButtons.add(resetButton);
        listOfButtons.add(rightButton);

        return listOfButtons;
    }

    private void setCurrentButton(boolean isLeft){
        if(currentButtonClicked != null) return;
        isLeftButton = isLeft;
        if(isLeft){
            this.currentButtonClicked = leftButton;
        } else {
            this.currentButtonClicked = rightButton;
        }
        this.tickHoldingStart = 0;
    }

    public void updateEntity(NpcGearData data, Race race, Level world) {
        if(world != null)
            haveBeenInitialized = true;

        updateEntityRace(race, world);
        updateEquipment(data);
    }

    public void updateToDefaultEntity(Level world) {
        BanditHumanEntity entity = new BanditHumanEntity(ModEntities.BANDIT_MILITIA, world);
        entity.setNoAi(true);

        this.entity = entity;
    }

    private void updateEquipment(NpcGearData data){
        if(data == null) {
            this.entity = null;
            return;
        }
        if(this.entity == null) return;

        this.entity.yBodyRot = currentAngle;
        this.entity.setXRot(0f);
        this.entity.yHeadRot = this.entity.getVisualRotationYInDegrees();
        this.entity.yHeadRotO = this.entity.getVisualRotationYInDegrees();

        NpcUtil.equipAll(entity, data);
    }

    private void updateEntityRace(Race race, Level world) {
        this.entity = race.getModel(world);
    }

    public void drawCenteredAnchoredBottom(GuiGraphics context, int centerX, int endY) {
        float size = 35f;
        int x = centerX;
        int y = endY;

        Lighting.setupForFlatItems();
        Lighting.setupLevel();
        if(this.entity == null) return;

        if(currentButtonClicked != null){
            if(isEnterKeyPressed || isMouseOver(currentButtonClicked.getWidth(), currentButtonClicked.getHeight(), currentButtonClicked.getX(), currentButtonClicked.getY())) {
                tickHoldingStart += Minecraft.getInstance().gui.getGuiTicks();
                if(canRotateSmoothly())
                    currentButtonClicked.onPress();
            }
            else{
                resetCurrentButton();
                LoggerUtil.logDebugMsg("Was out of button reach");
            }
        }

        // TODO : Find a way to display the entity behind the buttons.
        InventoryScreen.renderEntityInInventory(context, x, y - 9, size, VECTOR, ENTITY_ROTATION, (Quaternionf)null, this.entity);
        int horizontalMargin = MINIMAL_MARGIN + 1;

        if(leftButton.active){
            int width = leftButton.getWidth();
            int height = leftButton.getHeight();
            boolean isMouseOver = isMouseOver(width, height, x - width - horizontalMargin, y - MINIMAL_MARGIN);
            context.blit(NPC_PREVIEW,
                    x - width - horizontalMargin, y - MINIMAL_MARGIN,
                    0, (currentButtonClicked != null && isLeftButton) ? 18
                            : (leftButton.isFocused() || isMouseOver) ? 9 : 0,
                    width, height
            );
            if(leftButton.isFocused() && getFocusEnabled()){
                context.blit(NPC_PREVIEW,
                        x - width - horizontalMargin, y - MINIMAL_MARGIN,
                        0, 27,
                        width, height
                );
            }

            leftButton.setPosition(x - width - horizontalMargin, y - MINIMAL_MARGIN);
        }

        if(resetButton.active){
            int width = resetButton.getWidth();
            int height = resetButton.getHeight();
            boolean isMouseOver = isMouseOver(width, height, x - (width / 2), y - MINIMAL_MARGIN + 2);

            context.blit(NPC_PREVIEW,
                    x - 3, y - MINIMAL_MARGIN + 2,
                    28, (resetButton.isFocused() || isMouseOver) ? 6 : 0,
                    width, height
            );
            if(resetButton.isFocused() && getFocusEnabled()){
                context.blit(NPC_PREVIEW,
                        x - 3, y - MINIMAL_MARGIN + 2,
                        28, 12,
                        width, height
                );
            }
            resetButton.setPosition(x - (width / 2), y - 2);
        }

        if(rightButton.active){
            int width = rightButton.getWidth();
            int height = rightButton.getHeight();
            boolean isMouseOver = isMouseOver(width, height, x + horizontalMargin, y - MINIMAL_MARGIN);

            context.blit(NPC_PREVIEW,
                    x + horizontalMargin, y - MINIMAL_MARGIN,
                    14, (currentButtonClicked != null && !isLeftButton) ? 18
                        : (rightButton.isFocused() || isMouseOver) ? 9 : 0,
                    width, height
            );
            if(rightButton.isFocused() && getFocusEnabled()){
                context.blit(NPC_PREVIEW,
                        x + horizontalMargin, y - MINIMAL_MARGIN,
                        14, 27,
                        width, height
                );
            }
            rightButton.setPosition(x + horizontalMargin, y - MINIMAL_MARGIN);
        }

        this.entity.yBodyRot = currentAngle;
        this.entity.setXRot(0f);
        this.entity.yHeadRot = this.entity.getVisualRotationYInDegrees();
        this.entity.yHeadRotO = this.entity.getVisualRotationYInDegrees();
    }


    private boolean canRotateSmoothly(){
        return tickHoldingStart >= SMOOTH_THRESHOLD;
    }

    private void resetCurrentButton(){
        currentButtonClicked = null;
        tickHoldingStart = 0;
        isEnterKeyPressed = false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        resetCurrentButton();
        return true;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if(keyCode == 257){
            if((leftButton.isFocused() || rightButton.isFocused())){
                if(!isEnterKeyPressed){
                    isEnterKeyPressed = true;
                } else{
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        resetCurrentButton();
        return true;
    }

    static {
        VECTOR = new Vector3f(0, 0, 0);
        // Vanilla values from SmithingScreen
        ENTITY_ROTATION = (new Quaternionf()).rotationXYZ(0.43633232F, 0.0F, 3.1415927F);
    }
}
