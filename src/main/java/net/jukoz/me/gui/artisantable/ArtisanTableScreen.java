package net.jukoz.me.gui.artisantable;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jukoz.me.compat.neoforge.dist.EnvType;
import net.jukoz.me.compat.neoforge.dist.Environment;
import net.jukoz.me.compat.neoforge.api.client.networking.v1.ClientPlayNetworking;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.item.ModEquipmentItems;
import net.jukoz.me.item.ModToolItems;
import net.jukoz.me.item.ModWeaponItems;
import net.jukoz.me.network.packets.C2S.ArtisanTableTabPacket;
import net.jukoz.me.recipe.ArtisanRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.*;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Environment(value= EnvType.CLIENT)
public class ArtisanTableScreen extends AbstractContainerScreen<ArtisanTableScreenHandler> implements ContainerListener {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "textures/gui/artisan_table.png");
    public static final int SLOT_SCALE = 18;
    public static final int SLOT_OFFSET_X = 12;
    public static final int SLOT_OFFSET_Y = 15;
    public static final int SPRITE_OFFSET_X = SLOT_OFFSET_X + 1;
    public static final int SPRITE_OFFSET_Y = SLOT_OFFSET_Y + 1;

    private float scrollAmount;
    private boolean mouseClicked;
    private int scrollOffset;
    private boolean canCraft;

    private static final Vector3f field_45497 = new Vector3f();
    private static final Quaternionf ARMOR_STAND_ROTATION = new Quaternionf().rotationXYZ(0.43633232f, 0.0f, (float)Math.PI);

    private ArmorStand armorStand;
    private final List<ArtisanTableTab> categories = new ArrayList<>();
    private final HashMap<Integer, List<ArtisanTableTab>> tabs = new HashMap<>();
    @Nullable
    private ArtisanTableTab selectedCategory;
    private ArtisanTableTab selectedTab;

    public ArtisanTableScreen(ArtisanTableScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
        this.imageWidth = 232;
        this.imageHeight = 166;
        handler.setContentsChangedListener(this::onInventoryChange);

        int index = 0;
        categories.add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.LEFT, index, getTabTranslation("weapons"), ModWeaponItems.GONDORIAN_NOBLE_LONGSWORD.getDefaultInstance()));
        tabs.put(index, new ArrayList<>());
        tabs.get(index).add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.ABOVE, 0, getTabTranslation("sword"), ModWeaponItems.STEEL_SWORD.getDefaultInstance(), ArtisanTableInputsShape.SWORD));
        tabs.get(index).add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.ABOVE, 1, getTabTranslation("axe"), ModToolItems.STEEL_AXE.getDefaultInstance(), ArtisanTableInputsShape.AXE));
        tabs.get(index).add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.ABOVE, 2, getTabTranslation("spear"), ModWeaponItems.STEEL_SPEAR.getDefaultInstance(), ArtisanTableInputsShape.SPEAR));
        tabs.get(index).add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.ABOVE, 3, getTabTranslation("bow"), ModWeaponItems.GONDORIAN_BOW.getDefaultInstance(), ArtisanTableInputsShape.BOW));
        tabs.get(index).add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.ABOVE, 4, getTabTranslation("crossbow"), Items.CROSSBOW.getDefaultInstance(), ArtisanTableInputsShape.CROSSBOW));
        index++;

        categories.add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.LEFT, index, getTabTranslation("tools"), ModToolItems.MITHRIL_PICKAXE.getDefaultInstance()));
        tabs.put(index, new ArrayList<>());
        tabs.get(index).add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.ABOVE, 0, getTabTranslation("pickaxe"), ModToolItems.STEEL_PICKAXE.getDefaultInstance(), ArtisanTableInputsShape.PICKAXE));
        tabs.get(index).add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.ABOVE, 1, getTabTranslation("shovel"), ModToolItems.STEEL_SHOVEL.getDefaultInstance(), ArtisanTableInputsShape.SHOVEL));
        tabs.get(index).add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.ABOVE, 2, getTabTranslation("hoe"), ModToolItems.STEEL_HOE.getDefaultInstance(), ArtisanTableInputsShape.HOE));
        index++;

        categories.add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.LEFT, index, getTabTranslation("armors"), ModEquipmentItems.GONDORIAN_FOUNTAIN_GUARD_CHESTPLATE.getDefaultInstance()));
        tabs.put(index, new ArrayList<>());
        tabs.get(index).add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.ABOVE, 0, getTabTranslation("helmet"), ModEquipmentItems.RAVENHILL_WATCHWARDEN_HELMET.getDefaultInstance(), ArtisanTableInputsShape.HELMET));
        tabs.get(index).add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.ABOVE, 1, getTabTranslation("chestplate"), ModEquipmentItems.RAVENHILL_WATCHWARDEN_CHESTPLATE.getDefaultInstance(), ArtisanTableInputsShape.CHESTPLATE));
        tabs.get(index).add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.ABOVE, 2, getTabTranslation("leggings"), ModEquipmentItems.RAVENHILL_WATCHWARDEN_LEGGINGS.getDefaultInstance(), ArtisanTableInputsShape.LEGGINGS));
        tabs.get(index).add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.ABOVE, 3, getTabTranslation("boots"), ModEquipmentItems.RAVENHILL_WATCHWARDEN_BOOTS.getDefaultInstance(), ArtisanTableInputsShape.BOOTS));
        tabs.get(index).add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.ABOVE, 4, getTabTranslation("mount_armor"), ModEquipmentItems.ROHIRRIC_HORSE_ARMOR.getDefaultInstance(), ArtisanTableInputsShape.MOUNT_ARMOR));
        index++;

        categories.add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.LEFT, index, getTabTranslation("shields"), Items.SHIELD.getDefaultInstance()));
        tabs.put(index, new ArrayList<>());
        tabs.get(index).add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.ABOVE, 0, getTabTranslation("light_shield"), ModWeaponItems.GUNDABAD_WOODEN_SHIELD.getDefaultInstance(), ArtisanTableInputsShape.LIGHT_SHIELD));
        tabs.get(index).add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.ABOVE, 1, getTabTranslation("medium_shield"), ModWeaponItems.ROUND_SHIELD.getDefaultInstance(), ArtisanTableInputsShape.MEDIUM_SHIELD));
        tabs.get(index).add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.ABOVE, 2, getTabTranslation("heavy_shield"), ModWeaponItems.URUK_HAI_WHITE_HAND_SHIELD.getDefaultInstance(), ArtisanTableInputsShape.HEAVY_SHIELD));
        index++;

        categories.add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.LEFT, index, getTabTranslation("misc"), ModEquipmentItems.STRAW_HAT.getDefaultInstance()));
        tabs.put(index, new ArrayList<>());
        tabs.get(index).add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.ABOVE, 0, getTabTranslation("hat"), ModEquipmentItems.STRAW_HAT.getDefaultInstance(), ArtisanTableInputsShape.HAT));
        tabs.get(index).add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.ABOVE, 1, getTabTranslation("hood"), ModEquipmentItems.HOOD.getDefaultInstance(), ArtisanTableInputsShape.HOOD));
        tabs.get(index).add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.ABOVE, 2, getTabTranslation("cape"), ModEquipmentItems.CAPE.getDefaultInstance(), ArtisanTableInputsShape.CAPE));
        tabs.get(index).add(new ArtisanTableTab(this.minecraft, this, ArtisanTableTabType.ABOVE, 3, getTabTranslation("pipe"), ModToolItems.CLAY_PIPE.getDefaultInstance(), ArtisanTableInputsShape.PIPE));
        index++;

        selectedCategory = categories.getFirst();
        selectedTab = tabs.get(selectedCategory.getIndex()).getFirst();
    }

    @Override
    protected void init() {
        super.init();
        titleLabelX = (imageWidth - font.width(title)) / 2;
        titleLabelY -= 1;

        (this.menu).addSlotListener(this);
        changeTab();

        this.armorStand = new ArmorStand(this.minecraft.level, 0.0, 0.0, 0.0);
        this.armorStand.setNoBasePlate(true);
        this.armorStand.setShowArms(true);
        this.armorStand.yBodyRot = 210.0f;
        this.armorStand.setXRot(25.0f);
        this.armorStand.yHeadRot = this.armorStand.getYRot();
        this.armorStand.yHeadRotO = this.armorStand.getYRot();
        this.equipArmorStand((this.menu).getSlot(9).getItem());
    }


    @Override
    public void slotChanged(AbstractContainerMenu handler, int slotId, ItemStack stack) {
        if (slotId == 9) {
            this.equipArmorStand(stack);
        }
    }

    @Override
    public void dataChanged(AbstractContainerMenu handler, int property, int value) {

    }

    private void equipArmorStand(ItemStack stack) {
        if (this.armorStand == null) {
            return;
        }
        for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
            this.armorStand.setItemSlot(equipmentSlot, ItemStack.EMPTY);
        }
        if (!stack.isEmpty()) {
            ItemStack itemStack = stack.copy();
            Item item = stack.getItem();
            if (item instanceof ArmorItem armorItem) {
                this.armorStand.setItemSlot(armorItem.getEquipmentSlot(), itemStack);
            } else {
                this.armorStand.setItemSlot(EquipmentSlot.OFFHAND, itemStack);
            }
        }
    }

    @Override
    protected void renderBg(GuiGraphics context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int i = this.leftPos;
        int j = this.topPos;
        context.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
        int k = (int)(41.0F * this.scrollAmount);
        context.blit(TEXTURE, i + 143, j + 15 + k, 232 + (this.shouldScroll() ? 0 : 12), 0, 12, 15);

        int l = this.leftPos + 76;
        int m = this.topPos + 14;
        int n = this.scrollOffset + 12;

        ArtisanTableInputsShape shape = selectedTab.getInputShape();
        for(int w = 0; w < 3; w++) {
            for(int z = 0; z < 3; z++) {
                if(menu.slots.get(w*3 + z).isActive()) {
                    context.blit(TEXTURE,leftPos + SLOT_OFFSET_X + SLOT_SCALE*z,topPos + SLOT_OFFSET_Y + SLOT_SCALE*w, 232, 15, SLOT_SCALE, SLOT_SCALE);
                    InputType inputType = shape.getInputType(z, w);
                    if(!menu.slots.get(w*3 + z).hasItem()) {
                        switch (inputType){
                            case HANDLE -> context.blit(TEXTURE,leftPos + SPRITE_OFFSET_X + SLOT_SCALE*z,topPos + SPRITE_OFFSET_Y + SLOT_SCALE*w, 232, 87, 16, 16);
                            case HILT -> context.blit(TEXTURE,leftPos + SPRITE_OFFSET_X + SLOT_SCALE*z,topPos + SPRITE_OFFSET_Y + SLOT_SCALE*w, 232, 103, 16, 16);
                            case BLADE -> context.blit(TEXTURE,leftPos + SPRITE_OFFSET_X + SLOT_SCALE*z,topPos + SPRITE_OFFSET_Y + SLOT_SCALE*w, 232, 119, 16, 16);
                            case AXE -> context.blit(TEXTURE,leftPos + SPRITE_OFFSET_X + SLOT_SCALE*z,topPos + SPRITE_OFFSET_Y + SLOT_SCALE*w, 232, 135, 16, 16);
                            case PICKAXE -> context.blit(TEXTURE,leftPos + SPRITE_OFFSET_X + SLOT_SCALE*z,topPos + SPRITE_OFFSET_Y + SLOT_SCALE*w, 232, 151, 16, 16);
                            case SHOVEL -> context.blit(TEXTURE,leftPos + SPRITE_OFFSET_X + SLOT_SCALE*z,topPos + SPRITE_OFFSET_Y + SLOT_SCALE*w, 232, 167, 16, 16);
                            case HOE -> context.blit(TEXTURE,leftPos + SPRITE_OFFSET_X + SLOT_SCALE*z,topPos + SPRITE_OFFSET_Y + SLOT_SCALE*w, 232, 183, 16, 16);
                        }
                    }
                }
            }
        }

        this.renderRecipeBackground(context, mouseX, mouseY, l, m, n);
        this.renderRecipeIcons(context, l, m, n);
        InventoryScreen.renderEntityInInventory(context, this.leftPos + 206, this.topPos + 75, 30.0f, field_45497, ARMOR_STAND_ROTATION, null, this.armorStand);
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);

        super.render(context, mouseX, mouseY, delta);

        for (ArtisanTableTab category : this.categories) {
            category.drawBackground(context, leftPos, topPos + 3, category == this.selectedCategory);
            category.drawIcon(context, leftPos, topPos + 3);
        }
        for (ArtisanTableTab tab : this.tabs.get(selectedCategory.getIndex())) {
            tab.drawBackground(context, leftPos + 3, topPos, tab == this.selectedTab);
            tab.drawIcon(context, leftPos + 3, topPos);
        }

        for (ArtisanTableTab category : this.categories) {
            if (category.isClickOnTab(leftPos, topPos + 3, mouseX, mouseY)) continue;
            context.renderTooltip(this.font, category.getTitle(), mouseX, mouseY);
        }
        for (ArtisanTableTab tab : this.tabs.get(selectedCategory.getIndex())) {
            if (tab.isClickOnTab(leftPos + 3, topPos, mouseX, mouseY)) continue;
            context.renderTooltip(this.font, tab.getTitle(), mouseX, mouseY);
        }
        renderTooltip(context, mouseX, mouseY);
    }

    protected void renderTooltip(GuiGraphics context, int x, int y) {
        super.renderTooltip(context, x, y);
        if (this.canCraft) {
            int i = this.leftPos + 76;
            int j = this.topPos + 14;
            int k = this.scrollOffset + 12;
            List<RecipeHolder<ArtisanRecipe>> list = (this.menu).getAvailableRecipes();

            for(int l = this.scrollOffset; l < k && l < (this.menu).getAvailableRecipeCount(); ++l) {
                int m = l - this.scrollOffset;
                int n = i + m % 4 * 16;
                int o = j + m / 4 * 18 + 2;
                if (x >= n && x < n + 16 && y >= o && y < o + 18) {
                    context.renderTooltip(this.font, list.get(l).value().getResultItem(this.minecraft.level.registryAccess()), x, y);
                }
            }
        }
    }

    private void renderRecipeBackground(GuiGraphics context, int mouseX, int mouseY, int x, int y, int scrollOffset) {
        for(int i = this.scrollOffset; i < scrollOffset && i < ((ArtisanTableScreenHandler)this.menu).getAvailableRecipeCount(); ++i) {
            int j = i - this.scrollOffset;
            int k = x + j % 4 * 16;
            int l = j / 4;
            int m = y + l * 18 + 2;
            int n = this.imageHeight;
            if (i == (this.menu).getSelectedRecipe()) {
                n += 18;
            } else if (mouseX >= k && mouseY >= m && mouseX < k + 16 && mouseY < m + 18) {
                n += 36;
            }

            context.blit(TEXTURE, k, m - 1, 0, n, 16, 18);
        }

    }

    private void renderRecipeIcons(GuiGraphics context, int x, int y, int scrollOffset) {
        List<RecipeHolder<ArtisanRecipe>> list = this.menu.getAvailableRecipes();
        for (int i = this.scrollOffset; i < scrollOffset && i < (this.menu).getAvailableRecipeCount(); ++i) {
            int j = i - this.scrollOffset;
            int k = x + j % 4 * 16;
            int l = j / 4;
            int m = y + l * 18 + 2;
            context.renderItem(list.get(i).value().getResultItem(this.minecraft.level.registryAccess()), k, m);
        }

    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.mouseClicked = false;
        if (button == 0) {
            for (ArtisanTableTab category : this.categories) {
                if (category.isClickOnTab(leftPos, topPos, mouseX, mouseY)) continue;
                this.selectedCategory = category;
                selectedTab = tabs.get(selectedCategory.getIndex()).getFirst();
                Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                changeTab();
                break;
            }
            for (ArtisanTableTab tab : tabs.get(selectedCategory.getIndex())) {
                if (tab.isClickOnTab(leftPos, topPos, mouseX, mouseY)) continue;
                this.selectedTab = tab;
                Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                changeTab();
                break;
            }
        }
        if (this.canCraft) {
            int i = this.leftPos + 76;
            int j = this.topPos + 14;
            int k = this.scrollOffset + 12;

            for(int l = this.scrollOffset; l < k; ++l) {
                int m = l - this.scrollOffset;
                double d = mouseX - (double)(i + m % 4 * 16);
                double e = mouseY - (double)(j + m / 4 * 18);
                if (d >= 0.0 && e >= 0.0 && d < 16.0 && e < 18.0 && (this.menu).clickMenuButton(this.minecraft.player, l)) {
                    Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                    this.minecraft.gameMode.handleInventoryButtonClick((this.menu).containerId, l);
                    return true;
                }
            }

            i = this.leftPos + 119;
            j = this.topPos + 9;
            if (mouseX >= (double)i && mouseX < (double)(i + 12) && mouseY >= (double)j && mouseY < (double)(j + 54)) {
                this.mouseClicked = true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.mouseClicked && this.shouldScroll()) {
            int i = this.topPos + 14;
            int j = i + 54;
            this.scrollAmount = ((float)mouseY - (float)i - 7.5F) / ((float)(j - i) - 15.0F);
            this.scrollAmount = Mth.clamp(this.scrollAmount, 0.0F, 1.0F);
            this.scrollOffset = (int)((double)(this.scrollAmount * (float)this.getMaxScroll()) + 0.5) * 4;
            return true;
        } else {
            return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        if (this.shouldScroll()) {
            int i = this.getMaxScroll();
            float f = (float)verticalAmount / (float)i;
            this.scrollAmount = Mth.clamp(this.scrollAmount - f, 0.0F, 1.0F);
            this.scrollOffset = (int)((double)(this.scrollAmount * (float)i) + 0.5) * 4;
        }

        return true;
    }

    private boolean shouldScroll() {
        return this.canCraft && this.menu.getAvailableRecipeCount() > 12;
    }

    protected int getMaxScroll() {
        return (this.menu.getAvailableRecipeCount() + 4 - 1) / 4 - 3;
    }

    private void onInventoryChange() {
        this.canCraft = this.menu.canCraft();
        if (!this.canCraft) {
            this.scrollAmount = 0.0F;
            this.scrollOffset = 0;
        }
    }
    
    private void changeTab() {
        menu.changeTab(selectedTab.getInputShape().getId());
        ClientPlayNetworking.send(new ArtisanTableTabPacket(selectedTab.getInputShape().getId(), menu.containerId));
    }

    private static Component getTabTranslation(String tab) {
        return Component.translatable("screen." + MiddleEarth.MOD_ID + ".artisan_table." + tab);
    }
}
